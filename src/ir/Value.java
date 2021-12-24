package ir;

import ir.type.Type;
import ir.User.Use;

import java.util.LinkedList;

/*
 * LLVM 中 Value Use User 的关系介绍
 * https://buaa-se-compiling.github.io/miniSysY-tutorial/pre/design_hints.html
 */

/*
 * Value 类，几乎所有实体都是Value的子类
 *
 */
public class Value {
    public Value(Type type) {
        this.type = type;
        uses = new LinkedList<>();
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
