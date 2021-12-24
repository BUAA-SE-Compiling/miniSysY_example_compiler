package ir.values.instructions;

import ir.User;
import ir.Value;
import ir.type.Type;
import ir.values.BasicBlock;
import util.IList;
import util.IList.INode;
/*
* Instruction 类，是所有 Inst 的父类
* 它本身是 User 的子类，可见所有的 Inst 都是 User
* 我们通过指令的 TAG 来判断指令的种类
* Inode 的介绍请看 src/util/IList，这是一个侵入式链表的设计，用来挂载到其所在的BB上
* */
public class Inst extends User {
    public TAG tag;
    public INode<Inst, BasicBlock> node;
    public boolean needname = true;
    public BasicBlock getBB() {return this.node.getParent().getVal();}

    public Inst(Type type, TAG tag, int numOP) {
        super(type, numOP);
        this.tag = tag;
        this.node = new INode<>(this);
    }

    public enum TAG {
        Add,// +
        Sub,// -
        Mul,// *
        Div,// /
        Lt, // <
        Le, // <=
        Ge, // >=
        Gt, // >
        Eq, // ==
        Ne, // !=
        And,// &
        Or, // |
        //terminator
        Br,
        Call,
        Ret,
        //mem op
        Alloca,
        Load,
        Store,
        GEP,
        Zext,
        Phi,//用于 mem2reg
    }

    public boolean isBinary() {
        return this.tag.ordinal() <= TAG.Or.ordinal()
                && this.tag.ordinal() <= TAG.Ge.ordinal();
    }

    public boolean isRelBinary() {
        return this.tag.ordinal() >= TAG.Lt.ordinal() &&
                this.tag.ordinal() <= TAG.Ne.ordinal();
    }

    public boolean isArithmeticBinary() {
        return this.tag.ordinal() >= TAG.Add.ordinal()
                && this.tag.ordinal() <= TAG.Div.ordinal();
    }

    public boolean isLogicalBinary() {
        return this.tag.ordinal() >= TAG.Lt.ordinal()
                && this.tag.ordinal() <= TAG.Or.ordinal();
    }

    public boolean isTerminator() {
        return this.tag.ordinal() >= TAG.Br.ordinal()
                && this.tag.ordinal() <= TAG.Ret.ordinal();
    }

    public boolean isMemOP() {
        return this.tag.ordinal() >= TAG.Alloca.ordinal()
                && this.tag.ordinal() <= TAG.Phi.ordinal();
    }

    //为了方便
    public boolean isAdd() {return this.tag == TAG.Add;}

    public boolean isSub() {return this.tag == TAG.Sub;}

    public boolean isMul() {return this.tag == TAG.Mul;}

    public boolean isDiv() {return this.tag == TAG.Div;}

    public boolean isLt() {return this.tag == TAG.Lt;}

    public boolean isLe() {return this.tag == TAG.Le;}

    public boolean isGe() {return this.tag == TAG.Ge;}

    public boolean isGt() {return this.tag == TAG.Gt;}

    public boolean isEq() {return this.tag == TAG.Eq;}

    public boolean isNe() {return this.tag == TAG.Ne;}

    public boolean isAnd() {return this.tag == TAG.And;}

    public boolean isOr() {return this.tag == TAG.Or;}

    public boolean isGEP() {return this.tag == TAG.GEP;}

    public boolean isLoad() {return this.tag == TAG.Load;}

    public boolean isStore() {return this.tag == TAG.Store;}

    public boolean isPhi() {return this.tag == TAG.Phi;}

    public boolean isRet() {return this.tag == TAG.Ret;}

    public boolean isBr() {return this.tag == TAG.Br;}

}
