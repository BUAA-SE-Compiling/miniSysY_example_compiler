package ir.values.instructions;

import ir.Value;
import ir.type.ArrayType;
import ir.type.PointerType;
import ir.type.Type;

import java.util.ArrayList;

public class MemInst {
    // alloca 指令是 pointer 类型的，用来向内存中申请一块空间
    public static class Alloca extends Inst {
        private boolean isInit = false;
        public final Type allocated;

        public Alloca(Type allocated) {
            super(new PointerType(allocated), TAG.Alloca, 0);
            this.allocated = allocated;
        }

        public void setInit() {isInit = true;}

        public boolean isInit() {return isInit;}

        @Override
        public String toString() {
            return this.name + " = " + "alloca " + allocated;
        }
    }

    // load指令用于从内存的某个位置中读出值，它的类型是不确定的（当然在本实验中是确定的，只有i32）
    public static class Load extends Inst {

        public Load(Type type, Value v) {
            super(type, TAG.Load, 1);
            this.setOperand(v, 0);
        }

        public Value getPointer() {return this.getOP(0);}

        @Override
        public String toString() {
            return this.name
                    + " = load "
                    + this.type
                    + ","
                    + this.getOP(0).type
                    + " "
                    + this.getOP(0).name;
        }
    }

    //store 指令用于将某个值存入某个内存位置，store指令是不需要名字的
    public static class Store extends Inst {
        public Store(Value val, Value pointer) {
            super(Type.VoidType.getType(), TAG.Store, 2);
            setOperand(val, 0);
            setOperand(pointer, 1);
            this.needName = false;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            var lhs = getOP(0);
            var rhs = getOP(1);
            sb.append("store ").
                    append(lhs.type.toString()).
                    append(" ").
                    append(lhs.name).
                    append(", ").
                    append(rhs.type.toString()).
                    append(" ").
                    append(rhs.name);
            return sb.toString();
        }

        public Value getVal() {return this.getOP(0);}

        public Value getPointer() {return this.getOP(1);}
    }
//zext 是用于进行类型转换的指令，用于进行 0 拓展，LLVM IR 中不允许隐式类型转换的存在，从 i1（通常出现在比较结果中转换到 i32 需要用到 zext

    public static class Zext extends Inst {
        private Type destTy;//will only be i32 in miniSysY

        public Zext(Value val, Type destTy) {
            super(destTy, TAG.Zext, 1);
            this.destTy = destTy;
            this.setOperand(val, 0);
        }

        @Override
        public String toString() {
            //只有从 i1 zext 到 i32 的情况
            return this.name
                    + " = "
                    + "zext i1 "
                    + this.getOP(0).name
                    + " to i32";
        }
    }

    //**计算**这个指针指向的元素的**类型**
    private static Type getElementType(Value ptr, ArrayList<Value> indices) {
        assert ptr.type.isPointerTy();
        Type type = ((PointerType) ptr.type).getContained();
        if (type.isIntegerType()) return type;
        else if (type.isArrayTy()) {
            for (int i = 1; i < indices.size(); i++) {
                type = ((ArrayType) type).getEleType();
            }
            return type;
        }
        return null;
    }


    // 第一个 operand 是 pointer ，之后的 operand 是 indice
    public static class GetElementPtr extends Inst {
        private final Type elementType_;

        public GetElementPtr(Value pointer, ArrayList<Value> idx) {
            super(new PointerType(getElementType(pointer, idx)), TAG.GEP, idx.size() + 1);
            setOperand(pointer, 0);
            for (int i = 0; i < idx.size(); i++) {
                setOperand(idx.get(i), i + 1);
            }
            elementType_ = getElementType(pointer, idx);
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            b
                    .append(this.name)
                    .append("= getelementptr ")
                    .append(((PointerType) getOP(0).type).getContained())
                    .append(",")
                    .append(getOP(0).type)
                    .append(" ")
                    .append(getOP(0).name)
                    .append(" ");
            for (int i = 1; i < getNumOP(); i++) {
                b
                        .append(", ")
                        .append(getOP(i).type)
                        .append(" ")
                        .append(getOP(i).name);
            }
            return b.toString();
        }
    }

    public static class Phi extends Inst {
        public Phi(Type type, TAG tag, int numOP) {
            super(type, tag, numOP);
        }
        //todo
    }


}
