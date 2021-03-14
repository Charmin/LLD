package flipkart.memory_manager.entities;

public class Block {
    int size;
    boolean isOccupied;
    String currentProcess;

    public Block(int size) {
        this.size = size;
    }

    public void setProcess(String process) {
        this.currentProcess = process;
    }

    public void occupy() {
        isOccupied = true;
    }

    public void freeSpace() {
        isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
