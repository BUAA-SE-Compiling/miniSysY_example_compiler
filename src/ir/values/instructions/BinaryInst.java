package ir.values.instructions;

import ir.Value;
import ir.type.IntegerType;
import ir.type.Type;
// 二元操作指令，加减乘除比较都能够通过这个类来表达，lhs是左手元素，rhs是右手元素
public class BinaryInst extends Inst {

    public BinaryInst(Type type, TAG tag, Value lhs, Value rhs) {
        super(type, tag, 2);
        if (this.isLogicalBinary()) this.type = IntegerType.getI1();
        if (this.isArithmeticBinary()) this.type = IntegerType.getI32();
        this.setOperand(lhs, 0);
        this.setOperand(rhs, 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(" = ");
        sb.append(switch (this.tag) {
            case Add -> "add  i32 ";
            case Sub -> "sub  i32 ";
            case Mul -> "mul  i32 ";
            case Div -> "sdiv  i32 ";
            case Lt -> "icmp slt " + this.getOP(0).type + " ";
            case Le -> "icmp sle " + this.getOP(0).type + " ";
            case Ge -> "icmp sge " + this.getOP(0).type + " ";
            case Gt -> "icmp sgt " + this.getOP(0).type + " ";
            case Eq -> "icmp eq " + this.getOP(0).type + " ";
            case Ne -> "icmp ne  " + this.getOP(0).type + " ";
            default -> "";
        });
        sb.append(this.getOP(0).name).append(",").append(getOP(1).name);
        return sb.toString();
    }

}
