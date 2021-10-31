package ir.values.instructions;

import ir.Value;
import ir.type.FunctionType;
import ir.type.Type;
import ir.values.BasicBlock;
import ir.values.Function;

import java.util.ArrayList;

public class TerminatorInst {
    public static class Call extends Inst {
        public Call(Function function, ArrayList<Value> args) {
            super(((FunctionType) function.type).getRetTType(), TAG.Call, args.size() + 1);
            if (this.type.isVoidTy()) {
                needName = false;
                retVoid = true;
            } else {
                retVoid = false;
            }
            setOperand(function, 0);
            for (int i = 0; i < args.size(); i++) {
                setOperand(args.get(i), i + 1);
            }
        }

        public Function getFunc() {return (Function) this.getOP(0);}

        public final boolean retVoid;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (retVoid) {
                sb.append("call ").append(this.type).append(" @").append(this.getOP(0).name);
            } else {
                sb.append(this.name).append(" = call ").append(this.type).append(" @")
                        .append(this.getOP(0).name);
            }
            sb.append("(");
            boolean a = false;
            for (int i = 1; i < this.getNumOP(); i++) {
                a = true;
                sb.append(this.getOP(i).type)
                        .append(" ").
                        append(this.getOP(i).name)
                        .append(",");
            }
            if (a) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
            return sb.toString();
        }
    }

    public static class Br extends Inst {
        public Br(Value cond, BasicBlock trueBlock, BasicBlock falseBlock) {//有条件跳转
            super(Type.LabelType.getType(), TAG.Br, 3);
            setOperand(cond, 0);
            setOperand(trueBlock, 1);
            setOperand(falseBlock, 2);
            needName = false;
        }

        public Br(BasicBlock trueBlock) {//无条件跳转
            super(Type.LabelType.getType(), TAG.Br, 1);
            setOperand(trueBlock, 0);
            needName = false;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("br ");
            if (this.getNumOP() == 1) {
                sb.append(getOP(0).type).append(" ").append("%" + getOP(0).name);
            }
            if (this.getNumOP() == 3) {
                sb.append(getOP(0).type).append(" ").append(getOP(0).name)
                        .append(",");
                sb.append(getOP(1).type).append(" ").append("%").append(getOP(1).name)
                        .append(",");
                sb.append(getOP(2).type).append(" ").append("%").append(getOP(2).name)
                        .append(" ");
            }
            sb.append("\n");
            return sb.toString();
        }
    }

    public static class Ret extends Inst {
        public Ret() { //ret void
            super(Type.VoidType.getType(), TAG.Ret, 0);
            needName = false;
        }

        public Ret(Value val) {//ret non-void
            super(Type.VoidType.getType(), TAG.Ret, 1);
            this.setOperand(val, 0);
            needName = false;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ret ");
            if (this.getNumOP() == 1) {
                sb.append(getOP(0).type + " " + getOP(0).name);
            } else {
                sb.append("void ");
            }
            return sb.toString();
        }
    }
}