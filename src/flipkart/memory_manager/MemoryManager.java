package flipkart.memory_manager;

import java.util.List;

public class MemoryManager {

    static AllocationService allocationService;
    static int capacity;

    public static void main(String[] args) {
        capacity = 100;
        allocationService = AllocatorServiceImpl.getInstance(capacity);

        System.out.println("allocate P1 var_x 1000 false");
        printStatus(allocationService.allocate("p1", "var_x", 1000, false));

        System.out.println("allocate P1 var_x 10 false");
        printStatus(allocationService.allocate("p1", "var_x", 10, false));
        printMatrix(allocationService.inspect("p1"));

        System.out.println("allocate P2 var_y 25 true");
        printStatus(allocationService.allocate("p2", "var_y", 25, true));
        printMatrix(allocationService.inspect("p2"));

        System.out.println("free P1 var_x");
        printStatus(allocationService.free("p1", "var_x"));

        System.out.println("kill P2");
        printStatus(allocationService.kill("p2"));

        System.out.println("allocate P1 var_z 10 true");
        printStatus(allocationService.allocate("p1", "var_z", 10, true));

        System.out.println("allocate P4 var_x 5 true");
        printStatus(allocationService.allocate("p4", "var_x", 5, true));

        System.out.println("allocate P1 var_w 5 true");
        printStatus(allocationService.allocate("p1", "var_w", 5, true));

        System.out.println("free P4 var_x");
        printStatus(allocationService.free("p4", "var_x"));

        System.out.println("allocate P1 var_y 6 false");
        printStatus(allocationService.allocate("p1", "var_y", 6, false));

        System.out.println("inspect P1");
        printMatrix(allocationService.inspect("p1"));
        printMatrix(allocationService.inspect("p4"));
    }

    private static void printStatus(boolean isSuccess) {
        String res = String.format(">> %s %s / %s", (isSuccess ? "success" : "error"), allocationService.getUsedMemory(),
                capacity - allocationService.getUsedMemory());
        System.out.println(res);
    }

    private static void printMatrix(List<String> res) {
        System.out.print(">> ");
        for (String s: res) {
            System.out.println(s);
        }
    }
}
