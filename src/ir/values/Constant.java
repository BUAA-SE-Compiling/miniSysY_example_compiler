package ir.values;

import ir.User;
import ir.type.IntegerType;
import ir.type.Type;

import java.util.ArrayList;

public class Constant extends User {
    public Constant(Type type) {
        super(type);
    }

    public Constant(Type type, int numOp) {
        super(type, numOp);
    }

    public static class ConstantInt extends Constant {
        private int val;
        private static final ConstantInt zero = new ConstantInt(IntegerType.getI32(), 0);

        public ConstantInt zero() {
            return zero;
        }

        @Override
        public String toString() {return "i32 " + this.val;}

        public void setVal(int val) {this.val = val;}

        public int getVal() {return val;}

        public ConstantInt(Type type, int val) {
            super(type);
            this.val = val;
        }

        public static ConstantInt get(int val) {
            return new ConstantInt(IntegerType.getI32(), val);
        }
    }

    public static class ConstantArray extends Constant {
        private ArrayList<Constant> constants;

        public ConstantArray(Type type, ArrayList<Constant> arr) {
            super(type, arr.size());
            for (int i = 0; i < arr.size(); i++) {
                setOperand(arr.get(i), i);
            }
        }
    }

}
