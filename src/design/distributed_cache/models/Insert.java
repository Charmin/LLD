package design.distributed_cache.models;

public class Insert extends Event {
    public Insert(Record record, long timestamp) {
        super(record, timestamp);
    }
}
