package ir.type;

public class ArrayType extends Type {
    private int intContains; //有几个int型的值（因为sysy的base type只有int）
    private Type contained; //ArrayType contain的是什么type
    private int num_elements;

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
        return "[" + num_elements + " x " + contained.toString() + "}";
    }
}