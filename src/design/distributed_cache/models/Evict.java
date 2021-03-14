package design.distributed_cache.models;

public class Evict<K, V> extends Event<K, V> {
    public Evict(Record<K, V> record, long timestamp) {
        super(record, timestamp);
    }
}
