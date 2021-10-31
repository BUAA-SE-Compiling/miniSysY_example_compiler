package ir.values;

import ir.Value;
import ir.type.Type;
import ir.values.instructions.Inst;
import util.IList;

import java.util.ArrayList;

//基本块类
public class BasicBlock extends Value {
    private ArrayList<BasicBlock> predecessors;//这个基本块的前驱结点（能够跳转到这个基本块的基本块）
    private ArrayList<BasicBlock> successors;//这个基本块的后继结点（这个基本块能够跳转到的基本块）
    public IList<Inst, BasicBlock> list;// src/util/IList
    public IList.INode<BasicBlock, Function> node;// src/util/IList
    private boolean dirty;//标记是否处理过，后面会用到，现在摸了

    public BasicBlock(String name) {
        super(Type.LabelType.getType());
        this.predecessors = new ArrayList<>();
        this.successors = new ArrayList<>();
        list = new IList<>(this);
        node = new IList.INode<>(this);
    }

}
