package ir.values.instructions;

import ir.Value;
import ir.type.PointerType;
import ir.type.Type;

public class MemInst {
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

    public static class Store extends Inst {
        public Store(Value val, Value pointer) {
            super(Type.VoidType.getType(), TAG.Store, 2);
            setOperand(val, 0);
            setOperand(pointer, 1);
            this.needName = false;
        }

        public Value getVal() {return this.getOP(0);}

        public Value getPointer() {return this.getOP(1);}
    }

    public static class Zext extends Inst {
        private Type destTy;//will only be i32 in miniSysY

        public Zext(Value val, Type destTy) {
            super(destTy, TAG.Zext, 1);
            this.destTy = destTy;
            this.setOperand(val, 0);
        }

        @Override
        public String toString() {
            return this.name
                    + " = "
                    + "zext i1 "
                    + this.getOP(0).name
                    + " to i32";
        }
    }

    public static class GetElementPtr extends Inst {
        public GetElementPtr(Type type, TAG tag, int numOP) {
            super(type, tag, numOP);
            //todo
        }
    }

    public static class Phi extends Inst {
        public Phi(Type type, TAG tag, int numOP) {
            super(type, tag, numOP);
        }
        //todo
    }


}
