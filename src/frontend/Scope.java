package frontend;

import ir.Value;

import java.util.ArrayList;
import java.util.HashMap;

public class Scope {
    private final ArrayList<HashMap<String, Value>> tables;
    public boolean preEnter = false;
    public HashMap<String,Value>top(){return tables.get(tables.size()-1);}
    public void addLayer() {
        if (preEnter) {
            preEnter = false;
            return;
        }
        tables.add(new HashMap<>());
    }
    //弹出一层Scope,这通常意味着完成了一片作用域的visit
    public void popLayer() {tables.remove(tables.size() - 1);}
    //最外层的Scope是Global的
    public boolean isGlobal() {return this.tables.size() == 1;}
    public Value find(String name) {//从里到外搜索变量名，找到就返回，找不到就返回 null
        for (int i = tables.size() - 1; i >= 0; i--) {
            Value t = tables.get(i).get(name);
            if (t != null)return t;
        }
        return null;
    }
    public void put(String name, Value v) {
        if (top().get(name) != null) {
            throw new RuntimeException("name already exists");
        } else {
            top().put(name, v);
        }
    }
    Scope() {
        tables = new ArrayList<>();
        tables.add(new HashMap<>());
    }

}
