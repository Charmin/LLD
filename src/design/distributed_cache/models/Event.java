package design.distributed_cache.models;

public abstract class Event<K, V> {
    String id;
    long timestamp;
    Record<K, V> record;

    public Event(Record<K, V> record, long timestamp) {
        this.timestamp = timestamp;
        this.record = record;
    }
}
