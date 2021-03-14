package flipkart.memory_manager;

import flipkart.memory_manager.entities.Block;
import flipkart.memory_manager.entities.Process;
import flipkart.memory_manager.entities.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllocatorServiceImpl implements AllocationService {
    static int capacity;
    static int occupied;
    Block[] blocks;
    static int holePointer;

    Map<String, Process> processMap;
    private static AllocatorServiceImpl allocatorService;

    public AllocatorServiceImpl(int size) {
        capacity = size;
        blocks = new Block[size];
        for (int i = 0; i < size; i++) {
            Block block = new Block(1);
            blocks[i] = block;
        }
        processMap = new HashMap<>();
    }

    public static AllocatorServiceImpl getInstance(int size) {
        if (allocatorService == null) {
            allocatorService = new AllocatorServiceImpl(size);
        }
        return allocatorService;
    }

    @Override
    public boolean allocate(String process, String var, int size, boolean isContiguous) {
        if (size > (capacity * 0.25)) {
            return false;
        }

        Variable variable = new Variable(var);
        boolean res = isContiguous
                 ? allocateFirstFitContiguous(size, variable)
                 : allocateNonContiguous(size, variable);

        if (res) {
            processMap.putIfAbsent(process, new Process());
            processMap.get(process).assignVar(variable);
            holePointer += size;
            occupied += size;
        }

        return res;
    }

    private boolean allocateFirstFitContiguous(int size, Variable variable) {
        int start = -1;
        boolean found = false;
        int originalSize = size;
        for (int i = 0; i < capacity; i++) {
            if (!blocks[i].isOccupied()) {
                start = i;
                while (i < capacity && !blocks[i].isOccupied()) {
                    size--;
                    if (size == 0) {
                        found = true;
                        break;
                    }
                    i++;
                }

                if (size > 0) {
                    size = originalSize;
                }
            }

            if (found) {
                List<int[]> occupiedMemory = new ArrayList<>();
                occupiedMemory.add(new int[] {start, i+1});
                occupyBlocks(occupiedMemory, variable);
                break;
            }
        }

        return size == 0;
    }

    private boolean allocateNonContiguous(int size, Variable variable) {
        if (size == 0) {
            return true;
        }

        int start = holePointer;
        List<int[]> occupiedMemory = new ArrayList<>();
        for (int i = start; i < capacity && size > 0; i++) {
            int s;
            int e;
            if (!blocks[i].isOccupied()) {
                s = i;
                while (i < capacity && !blocks[i].isOccupied() && size > 0) {
                    size -= 1;
                    i++;
                }
                if (size >= 0) {
                    e = i;
                    occupiedMemory.add(new int[] {s, e});
                }
            }
        }
        boolean isAllocated = (size == 0);
        if (isAllocated) {
            occupyBlocks(occupiedMemory, variable);
        }

        return isAllocated;
    }

    private void occupyBlocks(List<int[]> blockList, Variable variable) {
        for (int[] oc : blockList) {
            variable.assignAddress(oc[0], oc[1]-1);
            for (int i = oc[0]; i < oc[1]; i++) {
                blocks[i].occupy();
            }
        }
    }

    @Override
    public boolean free(String process, String var) {
        Process cur = processMap.get(process);
        if (cur != null && !cur.getVariableMap().isEmpty()) {
            Variable variable = cur.getVariableMap().get(var);
            if (variable == null) {
                throw new IllegalStateException("Variable not found");
            }
            int size = variable.getSize();
            //least start pos
            holePointer = Math.min(holePointer, variable.getRanges().get(0)[0]);
            for (int [] r : variable.getRanges()) {
                for (int i = r[0]; i <= r[1]; i++) {
                    blocks[i].freeSpace();
                }
            }
            cur.removeVar(var);
            occupied -= size;
        }
        return true;
    }

    @Override
    public boolean kill(String processName) {
        Process process = processMap.get(processName);
        if (process == null) {
            throw new IllegalArgumentException("Process does not exist");
        }
        for (Variable v : process.getVariableMap().values()) {
            free(processName, v.getName());
        }
        processMap.remove(processName);
        return true;
    }

    @Override
    public List<String> inspect(String processName) {
        Process process = processMap.get(processName);
        if (process == null) {
            throw new IllegalArgumentException("Process does not exist");
        }
        List<String> res = new ArrayList<>();
        for (Variable v : process.getVariableMap().values()) {
            res.add(v.toString());
        }
        return res;
    }

    @Override
    public int getUsedMemory() {
        return occupied;
    }
}
