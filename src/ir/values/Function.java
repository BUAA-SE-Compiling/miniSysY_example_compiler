package ir.values;

import ir.Value;
import ir.type.FunctionType;
import ir.type.Type;
import util.IList;
import util.IList.INode;

import java.util.ArrayList;
import java.util.List;

// 函数类
public class Function extends Value {
    private boolean isBuiltin = false;
    public IList<BasicBlock, Function> list;
    public INode<Function, Module> node;
    private ArrayList<Function> caller;
    private ArrayList<Function> callee;
    private ArrayList<Param> paramList;

    public Function(Type type, boolean isBuiltin) {
        super(type);
        list = new IList<>(this);
        node = new INode<>(this);
        paramList = new ArrayList<>();
        caller = new ArrayList<>();
        callee = new ArrayList<>();
        buildParams();
    }

    private void buildParams() {
        var funcTy = this.type;
        var arr = ((FunctionType) funcTy).getParamsTypes();
        for (int i = 0; i < arr.size(); i++) {
            paramList.add(new Param(arr.get(i), i));
        }
    }

    //形参，记录各位置类型，不含值
    public class Param extends Value {
        private List<Value> bounds;
        private int rank;

        public Param(Type type, int rank) {
            super(type);
            this.rank = rank;
        }

        public void setBounds(List<Value> bounds) {this.bounds = bounds;}

        @Override
        public String toString() {
            return this.type + " " + this.name;
        }
    }

    @Override
    public String toString() {
        //todo
        return "";
    }
}
