package ir.type;

public class ArrayType extends Type {
    private int intContains; //有几个int型的值（因为sysy的base type只有int）
    private Type contained; //ArrayType contain的是什么type
    private int num_elements;//有几个元素，值得一提的是，LLVM IR 是通过多层嵌套的关系来表示多维数组的，比如一个三位数组可能是[2 x[2 x [2 x i32]]]

    public Type getEleType() {return contained;}

    public int getNumEle() {return num_elements;}

    public int getIntContains() {return intContains;}

    public ArrayType(Type contained, int num_elements) {
        assert num_elements > 0;
        this.contained = contained;
        this.num_elements = num_elements;
        if (contained.isIntegerType()) {
            intContains = num_elements;
        } else {
            intContains = ((ArrayType) contained).intContains * num_elements;
        }
    }

    @Override
    public String toString() {
        return "[" + num_elements + " x " + contained.toString() + "]";
    }
}