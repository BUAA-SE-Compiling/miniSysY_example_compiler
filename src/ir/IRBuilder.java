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

import java.util.ArrayList;

public class IRBuilder {

    private static final IRBuilder b = new IRBuilder();

    public static IRBuilder getInstance() {return b;}

    private BasicBlock curBB;
    private Function curFunc;
    private Module m;//其实是不会变的

    public void setModule(Module m) {this.m = m;}

    public void setFunc(Function f) {curFunc = f;}

    public Function curFunc() {return curFunc;}

    public void setInsertPoint(BasicBlock bb) {
        curBB = bb;
    }

    public BasicBlock curBB() {return curBB;}

    private IRBuilder() {
    }

    public ConstantInt getConstantInt(int val) {
        return ConstantInt.get(val);
    }

    public ConstantArray getConstantArray(Type ty, ArrayList<Constant> constants) {return new ConstantArray(ty, constants);}

    public GlobalVariable getGlobalVariable(String name, Type type, Constant initValue) {
        return new GlobalVariable(name, type) {{
            init = initValue;
        }};
    }

    public Function buildFunction(String Fname, FunctionType type, boolean isBuiltin) {
        return new Function(type, isBuiltin) {{
            this.name = Fname;
            node.setParent(m.functions);
            node.insertAtEnd(m.functions);
        }};
    }

    public BasicBlock getBB(String name) {return new BasicBlock(name);}

    public BasicBlock buildBB(String name) {
        return new BasicBlock(name) {{
            node.insertAtEnd(curFunc.list);
        }};
    }

    public BinaryInst getBinary(Inst.TAG tag, Value lhs, Value rhs) {
        return new BinaryInst(lhs.type, tag, lhs, rhs);
    }

    public BinaryInst buildBinary(Inst.TAG tag, Value lhs, Value rhs) {
        return new BinaryInst(lhs.type, tag, lhs, rhs) {{
            this.node.insertAtEnd(curBB.list);
        }};
    }

    public TerminatorInst.Br getBr(BasicBlock trueblock) {
        return new TerminatorInst.Br(trueblock);
    }

    public TerminatorInst.Br getBr(Value cond, BasicBlock trueblock, BasicBlock falseblock) {
        return new TerminatorInst.Br(cond, trueblock, falseblock);
    }


    public TerminatorInst.Br buildBr(BasicBlock tureblock) {
        if ( curBB.list.getLast().getVal() != null && (curBB.list.getLast().getVal().tag == Inst.TAG.Ret
                || curBB.list.getLast().getVal().tag == Inst.TAG.Br)) {
            return null;
            //防止下面这种情况出现
            //ret i32 1
            //br label  %4
        }
        return new TerminatorInst.Br(tureblock) {{
            this.node.insertAtEnd(curBB.list);
        }};
    }

    public TerminatorInst.Br buildBr(Value cond, BasicBlock trueblock, BasicBlock falseblock) {

        if (curBB.list.getLast().getVal() != null && (curBB.list.getLast().getVal().tag == Inst.TAG.Ret
                || curBB.list.getLast().getVal().tag == Inst.TAG.Br)) {
            return null;
            //防止下面这种情况出现
            //ret i32 1
            //br label  %4
        }
        return new TerminatorInst.Br(cond, trueblock, falseblock) {{
            this.node.insertAtEnd(curBB.list);
        }};
    }

    public TerminatorInst.Ret getRet() {return new TerminatorInst.Ret();}

    public TerminatorInst.Ret getRet(Value val) {return new TerminatorInst.Ret(val);}

    public TerminatorInst.Ret buildRet() {
        return new TerminatorInst.Ret() {{
            node.insertAtEnd(curBB.list);
        }};
    }

    public TerminatorInst.Ret buildRet(Value val) {
        return new TerminatorInst.Ret(val) {{
            node.insertAtEnd(curBB.list);
        }};
    }

    public MemInst.Alloca getAlloca(Type type) {return new MemInst.Alloca(type);}

    public MemInst.Alloca buildAlloca(Type type) {
        return new MemInst.Alloca(type) {{
            node.insertAtEntry(curBB.node.getParent().getVal().list.getEntry().getVal().list);
        }};
    }


    public MemInst.Load getLoad(Type type, Value value) {return new MemInst.Load(type, value);}

    public MemInst.Load buildLoad(Type type, Value value) {
        return new MemInst.Load(type, value) {{
            node.insertAtEnd(curBB.list);
        }};
    }

    public MemInst.Store getStore(Value value, Value pointer) {return new MemInst.Store(value, pointer);}

    public MemInst.Store buildStore(Value value, Value pointer) {
        return new MemInst.Store(value, pointer) {{
            node.insertAtEnd(curBB.list);
        }};
    }

    public MemInst.Zext getZext(Value val) {return new MemInst.Zext(val, IntegerType.i32);}

    public MemInst.Zext buildZext(Value value) {
        return
                new MemInst.Zext(value, IntegerType.i32) {{
                    node.insertAtEnd(curBB.list);
                }};
    }

    public MemInst.GetElementPtr getGEP(Value ptr, ArrayList<Value> idxs) {
        return new MemInst.GetElementPtr(ptr, idxs);
    }

    public MemInst.GetElementPtr buildGEP(Value ptr, ArrayList<Value> idxs) {
        return new MemInst.GetElementPtr(ptr, idxs) {{
            node.insertAtEnd(curBB.list);
        }};
    }

    public TerminatorInst.Call getCall(Function func, ArrayList<Value> args) {

        return new TerminatorInst.Call(func, args);
    }

    public TerminatorInst.Call buildCall(Function func, ArrayList<Value> args) {
        return new TerminatorInst.Call(func, args) {{
            node.insertAtEnd(curBB.list);
        }};
    }
}

