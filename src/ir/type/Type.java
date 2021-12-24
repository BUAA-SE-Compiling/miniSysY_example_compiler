package ir.type;

import java.util.ArrayList;

/*
 * Type 是所有类型的基类
 * LLVM IR 是强类型的语言，不允许隐式转换
 */


public class Type {

    //给没有Type的Value
    public static class NonType extends Type {
        private static NonType type = new NonType();

        public static NonType getType() {return type;}

        private NonType() {}
    }

    //Void类型并没有其他额外的信息，所以单例就行
    public static class VoidType extends Type {
        private static VoidType type = new VoidType();

        public static VoidType getType() {return type;}

        @Override
        public String toString() {
            return "void";
        }

        private VoidType() {}
    }

    //同上，Label也没有其他额外信息，LabelTyoe是BasicBlock的类型
    public static class LabelType extends Type {
        public static LabelType getType() {
            return type;
        }

        private static LabelType type = new LabelType();

        @Override
        public String toString() {
            return "label ";
        }

        private LabelType() {}
    }

    public boolean isLabelTy() {return this instanceof LabelType;}

    public boolean isNonTy() {return this instanceof NonType;}

    public boolean isVoidTy() {return this instanceof VoidType;}

    public boolean isFunctionTy() {return this instanceof FunctionType;}

    public boolean isArrayTy() {return this instanceof ArrayType;}

    public boolean isPointerTy() {return this instanceof PointerType;}

    public boolean isIntegerType() {return this instanceof IntegerType;}

    public boolean isI1() {
        if (this instanceof IntegerType o) return o.getNumBits() == 1;
        return false;
    }
}
