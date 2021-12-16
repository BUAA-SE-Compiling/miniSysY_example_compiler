package ir;

import ir.values.Function;
import ir.values.GlobalVariable;
import util.IList;

import java.util.ArrayList;

public class Module {
    public ArrayList<GlobalVariable> globalVariables;
    public IList<Function, Module> functions;
    public static final Module module = new Module();//SysY是单文件的，所以module直接单例了

    private Module() {
        functions = new IList<>(this);
        globalVariables = new ArrayList<>();
    }
}
