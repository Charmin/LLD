package design.distributed_cache.models;

public class Update<K, V> extends Event<K, V> {
    public Update(Record<K, V> record, long timestamp) {
        super(record, timestamp);
    }
}
