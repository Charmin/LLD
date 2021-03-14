package design.distributed_cache.models;

public class AccessDetails {
    long accessCount;
    long lastAccessTime;

    public AccessDetails(long accessCount, long lastAccessTime) {
        this.accessCount = accessCount;
        this.lastAccessTime = lastAccessTime;
    }
}
