package flipkart.memory_manager.entities;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    String name;
    List<int[]> ranges;
    Long accessedOn;


    public Variable(String n) {
        this.name = n;
        ranges = new ArrayList<>();
    }

    public void assignAddress(int start, int end) {
        int[] range = {start, end};
        ranges.add(range);
        accessedOn = System.currentTimeMillis();
    }

    public int getSize() {
        int size = 0;
        for (int[] r : ranges) {
            size += (r[1] - r[0] + 1);
        }

        return size;
    }

    public List<int[]> getRanges() {
        return ranges;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(name);
        for (int[] range : ranges) {
            res.append(" [").append(range[0]).append(",").append(range[1]).append("]");
        }
        return res.toString();
    }
}
