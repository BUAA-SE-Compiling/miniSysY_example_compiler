package frontend;

import ir.Value;

import java.util.ArrayList;
import java.util.HashMap;

public class Scope {
    private final ArrayList<HashMap<String, Value>> tables;
    public boolean preEnter = false;

    Scope() {
        tables = new ArrayList<>();
        tables.add(new HashMap<>());
    }
}
