package design.distributed_cache.models;

import java.util.concurrent.CompletionStage;

public class Record<K,V> {
    K key;
    V value;
    AccessDetails accessDetails;

    public Record(K key, CompletionStage<V> value, AccessDetails accessDetails) {
    }
}
