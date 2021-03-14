package flipkart.memory_manager.entities;

import java.util.HashMap;
import java.util.Map;

public class Process {
    Map<String, Variable> variableMap;

    public Process() {
        variableMap = new HashMap<>();
    }

    public void assignVar(Variable var) {
        variableMap.put(var.name, var);
    }

    public void removeVar(String var) {
        variableMap.remove(var);
    }

    public Map<String, Variable> getVariableMap() {
        return variableMap;
    }
}
