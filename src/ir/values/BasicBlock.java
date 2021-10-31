package ir.values;

import ir.Value;
import ir.type.Type;
import ir.values.instructions.Inst;
import util.IList;

import java.util.ArrayList;

//基本块类
public class BasicBlock extends Value {
    private ArrayList<BasicBlock> predecessors;
    private ArrayList<BasicBlock> successors;
    public IList<Inst, BasicBlock> list;
    public IList.INode<BasicBlock, Function> node;
    private boolean dirty;

    public BasicBlock(String name) {
        super(Type.LabelType.getType());
        this.predecessors = new ArrayList<>();
        this.successors = new ArrayList<>();
        list = new IList<>(this);
        node = new IList.INode<>(this);
    }

}
