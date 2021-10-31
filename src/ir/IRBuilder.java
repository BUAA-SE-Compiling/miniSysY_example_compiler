package ir;

import ir.type.FunctionType;
import ir.type.IntegerType;
import ir.type.Type;
import ir.values.BasicBlock;
import ir.values.Constant;
import ir.values.Constant.ConstantInt;
import ir.values.Constant.ConstantArray;
import ir.values.Function;
import ir.values.GlobalVariable;
import ir.values.instructions.BinaryInst;
import ir.values.instructions.Inst;
import ir.values.instructions.MemInst;
import ir.values.instructions.TerminatorInst;

public class IRBuilder {

    private static final IRBuilder b = new IRBuilder();

    public static IRBuilder getInstance() {
        return b;
    }

    private BasicBlock curBB;

    public void setInsertPoint(BasicBlock bb) {
        curBB = bb;
    }

    private IRBuilder() {
    }

    public ConstantInt getConstantInt(int val) {
        return ConstantInt.get(val);
    }

    public ConstantArray getConstantArray() {return null;}// TODO: 2021/10/25

    public GlobalVariable getGlobalVariable(String name, Type type, Constant init) {
        var tmp = new GlobalVariable(name, type);
        tmp.init = init;
        return tmp;
    }

    public Function getFunction(FunctionType type, boolean isBuiltin) {
        return new Function(type, isBuiltin);
    }

    public BasicBlock getBB(String name) {return new BasicBlock(name);}

    public BinaryInst getBinary(Inst.TAG tag, Value lhs, Value rhs) {
        return new BinaryInst(lhs.type, tag, lhs, rhs);
    }

    public void buildBinary(Inst.TAG tag, Value lhs, Value rhs) {
        var tmp = new BinaryInst(lhs.type, tag, lhs, rhs);
        tmp.node.insertAtEnd(curBB.list);
    }

    public TerminatorInst.Br getBr(BasicBlock trueblock) {
        return new TerminatorInst.Br(trueblock);
    }

    public TerminatorInst.Br getBr(Value cond, BasicBlock trueblock, BasicBlock falseblock) {
        return new TerminatorInst.Br(cond, trueblock, falseblock);
    }


    public void buildBr(BasicBlock tureblock) {
        var tmp = new TerminatorInst.Br(tureblock);
        tmp.node.insertAtEnd(curBB.list);
    }

    public void buildBr(Value cond, BasicBlock trueblock, BasicBlock falseblock) {
        var tmp = new TerminatorInst.Br(cond, trueblock, falseblock);
        tmp.node.insertAtEnd(curBB.list);
    }

    public TerminatorInst.Ret getRet() {return new TerminatorInst.Ret();}

    public TerminatorInst.Ret getRet(Value val) {return new TerminatorInst.Ret(val);}

    public void buildRet() {
        var tmp = new TerminatorInst.Ret();
        tmp.node.insertAtEnd(curBB.list);
    }

    public void buildRet(Value val) {
        var tmp = new TerminatorInst.Ret(val);
        tmp.node.insertAtEnd(curBB.list);
    }

    public MemInst.Alloca getAlloca(Type type) {return new MemInst.Alloca(type);}

    public void buildAlloca(Type type) {
        var tmp = getAlloca(type);
        //魔怔代码，Alloca必须插入 当前基本块 所在函数 的 入口基本块 的 开头
        tmp.node.insertAtEntry(curBB.node.getParent().getVal().list.getEntry().getVal().list);
    }

    public MemInst.Load getLoad(Type type, Value value) {return new MemInst.Load(type, value);}

    public void buildLoad(Type type, Value value) {
        var tmp = getLoad(type, value);
        tmp.node.insertAtEnd(curBB.list);
    }

    public MemInst.Store getStore(Value value, Value pointer) {return new MemInst.Store(value, pointer);}

    public void buildStore(Value value, Value pointer) {
        var tmp = getStore(value, pointer);
        tmp.node.insertAtEnd(curBB.list);
    }

    public MemInst.Zext getZext(Value val) {return new MemInst.Zext(val, IntegerType.i32);}

    public void buildZext(Value value) {
        var tmp = getZext(value);
        tmp.node.insertAtEnd(curBB.list);
    }

}

