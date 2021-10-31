package ir.type;

import java.util.ArrayList;

public class FunctionType extends Type {
    private Type retTy;//函数的返回类型，只有 void 或者 int
    private ArrayList<Type> paramsTypes;//形参类型的列表

    public FunctionType(Type retTy, ArrayList<Type> paramsTypes) {
        this.retTy = retTy;
        this.paramsTypes = paramsTypes;
    }

    public Type getRetTType() {return retTy;}

    public ArrayList<Type> getParamsTypes() {return paramsTypes;}


}

