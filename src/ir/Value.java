package ir;

import ir.type.Type;
import ir.User.Use;

import java.util.LinkedList;

//Value 类
public class Value {
    public Value(Type type) {
        this.type = type;
    }

    public Type type;
    public String name = "";
    protected LinkedList<Use> uses;
    public boolean needName = true;

    //将所有对 this 的使用换为对v的使用
    public void replaceAllUseWith(Value v) {
        for (Use use : uses) {
            use.setV(v);
            v.uses.add(use);
        }
    }

}
