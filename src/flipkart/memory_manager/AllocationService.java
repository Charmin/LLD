package flipkart.memory_manager;

import java.util.List;

public interface AllocationService {
    boolean allocate(String process, String var, int size, boolean isContiguous);
    boolean free(String process, String var);
    boolean kill(String processName);
    List<String> inspect(String processName);
    int getUsedMemory();
}
