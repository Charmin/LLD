package design.distributed_cache;

import java.util.concurrent.CompletionStage;

public interface Datasource<K,V> {
    CompletionStage<V> load(K key);
    CompletionStage<V> persist(K key, V value, long timestamp);
}
