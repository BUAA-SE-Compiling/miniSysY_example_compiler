package ir;

import ir.type.Type;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

public class User extends Value {
    private LinkedList<Use> operands;
    private int numOP;

    public Value getOP(int i) {//这是个 O(n) 的操作，所幸 n 不会太大，而且operands是基本有序的
        for (Use use : operands) {
            if (use.Rank() == i) {
                return use.V();
            }
        }
        return null;
    }

    public void addOperand(Value v) {
        operands.add(new Use(v, this, numOP++));
    }

    public void setOperand(Value v, int i) {
        assert i < numOP && i >= 0;
        for (Use use : operands) {
            if (use.Rank() == i) {
                use.V().uses.removeIf(h -> h.equals(use));
                use.setV(v);
                return;
            }
        }
        Use newUse = new Use(v, this, i);

    }

    public int getNumOP() {return numOP;}

    public User(Type type) {
        super(type);
    }

    public User(Type type, int numOp) {
        super(type);
        this.numOP = numOp;
    }

    //因为JAVA没有友元，所以我把Use作为User的内部类来绕过一些限制
    public static class Use {
        //Value在User中是第几个操作数，
        // 比如 add i32 %a,%b 中 %a 是第0个，记录这个关系的Use的operandRank就是0，%b是第1个
        private int operandRank;
        private User u;
        private Value v;

        public Use(Value v, User u, int operandRank) {
            this.v = v;
            this.u = u;
            this.operandRank = operandRank;
            v.uses.add(this);
            u.operands.add(this);
        }

        public void setV(Value v) {this.v = v;}

        public int Rank() {return operandRank;}

        public Value V() {return v;}
    }
}
