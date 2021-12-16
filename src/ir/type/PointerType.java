package ir.type;

public class PointerType extends Type {
    private Type contained;//指针类型，gv,alloca,getElementPtr 指令都是指针类型的

    public static PointerType getPointTy(Type contained){return new  PointerType(contained);}
    public PointerType(Type contained) {this.contained = contained;}

    public Type getContained() {return contained;}

    @Override
    public String toString() {
        return contained.toString() + "* ";
    }
}
