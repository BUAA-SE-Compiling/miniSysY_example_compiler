package ir.values;

import ir.Module;
import ir.User;
import ir.type.PointerType;
import ir.type.Type;

//全局变量，所有全局变量都是 PointerType的
public class GlobalVariable extends User {
    public boolean isConst = false;
    public Constant init;//初始化时候的值是需要存储的，但之后进行值的变动就不需要存储了。是体现在指令中的

    public void setConst() {this.isConst = true;}

    public GlobalVariable(String name, Type type) {
        super(new PointerType(type));//是地址
        this.name = name;
        Module.module.globalVariables.add(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(" = dso_local ");//gv 肯定有一个名字
        if (isConst) sb.append("constant ");//如果是 constant
        else sb.append("global ");
        var tmp = ((PointerType) this.type);
        if (tmp.getContained().isIntegerType()) {// 这个gv指向的是一个 i32
            sb.append(tmp.getContained().toString());
            sb.append(" ");
            //没有初始化的值默认其为0
            sb.append(this.init == null ? "0 " : ((Constant.ConstantInt) this.init).getVal());
        } else if (tmp.getContained().isArrayTy()) {
            if (this.init == null) {
                //如果是空的初始化，那么会由 zeroinitializer 代替初始化值
                sb.append(tmp.getContained().toString());
                sb.append(" ");
                sb.append("zeroinitializer ");
            } else sb.append(init);
        }
        return sb.toString();
    }
}
