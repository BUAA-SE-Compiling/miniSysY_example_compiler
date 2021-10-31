package ir.type;


/**
 * Integer 类型
 * numBits是包含的位数
 * **/
public class IntegerType extends Type {
    public static final IntegerType i32 = new IntegerType(32);//i32 type,仅携带类型信息，因此用单例
    public static final IntegerType i1 = new IntegerType(1);//同上

    public static IntegerType getI32() {return i32;}

    public static IntegerType getI1() {return i1;}

    private IntegerType(int numBits) {
        assert numBits > 0;
        this.numBits = numBits;
    }

    public int getNumBits() {
        return numBits;
    }

    private int numBits;

    @Override
    public String toString() {
        if (this.numBits == 32) return "i32";
        if (this.numBits == 1) return "i1";
        return "impossible";
    }
}
