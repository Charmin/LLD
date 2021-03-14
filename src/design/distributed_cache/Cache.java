package design.distributed_cache;

import design.distributed_cache.models.AccessDetails;
import design.distributed_cache.models.Event;
import design.distributed_cache.models.Record;
import design.distributed_cache.models.enums.CacheStrategy;
import design.distributed_cache.models.enums.EvictionPolicy;

import java.util.*;
import java.util.concurrent.*;

/*
* cache store, dll(cache priority), event queue(change publish), expiry queue
  data store (persistance)
  timer(for expiry)
  cache strategy, evictionPolicy
  size
  All the queues are ConcurrantSkipListMaps,
        because they are sorted concurrant variants of the
*
*
*/
public class Cache<K, V> {
    private long size;
    private Map<K,V> cacheStore;
    private ConcurrentSkipListMap<AccessDetails, List<K>> priorityQ;
    private ConcurrentSkipListMap<Long, List<K>> expiryQ;
    private List<Event<K, V>> eventQ;
    private Datasource<K, V> datasource;
    private EvictionPolicy evictionPolicy;
    private CacheStrategy cacheStrategy;
    private ExecutorService[] executorPool;
    private int poolSize;
    private Timer timer;

    public Cache(final long size,
                 final int poolSize,
                 final ConcurrentSkipListMap<AccessDetails, List<K>> priorityQ,
                 final ConcurrentSkipListMap<Long, List<K>> expiryQ,
                 final Datasource<K, V> datasource,
                 final EvictionPolicy evictionPolicy,
                 final CacheStrategy cacheStrategy) {
        this.size = size;
        this.cacheStore = new ConcurrentHashMap<>();
        this.priorityQ = priorityQ;
        this.expiryQ = expiryQ;
        this.eventQ = new CopyOnWriteArrayList<>();
        this.datasource = datasource;
        this.evictionPolicy = evictionPolicy;
        this.cacheStrategy = cacheStrategy;
        this.poolSize = poolSize;
        //muliple executor services instead of a single executor with a large pool size?
        for (int i = 0; i < poolSize; i++) {
            executorPool[i] = Executors.newSingleThreadExecutor();
        }
    }


    public CompletionStage<Void> put(Record<K,V> record) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        //halt for later
        return null;
    }

    public CompletionStage<Void> get(Record<K,V> record) {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        //halt for later
        return null;
    }

    private CompletionStage<Record<K,V>> addToCache(final K key, final CompletionStage<V> value) {
        value.thenApply(v -> {
            final Record<K, V> record = new Record<>(key, value, new AccessDetails(1, System.currentTimeMillis()));
//            expiryQ.putIfAbsent(record.getInsertionTime(), new CopyOnWriteArrayList<>());
//            expiryQ.get(record.getInsertionTime()).add(key);
//            priorityQ.putIfAbsent(record.getAccessDetails(), new CopyOnWriteArrayList<>());
//            priorityQ.get(record.getAccessDetails()).add(key);
            return record;
        });

        if (cacheStore.containsKey(key)) {
            priorityQ.remove(key);
            expiryQ.remove(key);
        }
        //halt for later
        return null;
    }

}
