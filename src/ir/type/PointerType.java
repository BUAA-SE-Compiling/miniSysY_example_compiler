package ir.type;

public class PointerType extends Type {
    private Type contained;

    public PointerType(Type contained) {this.contained = contained;}

    public Type getContained() {return contained;}

    @Override
    public String toString() {
        return contained.toString() + "* ";
    }
}
