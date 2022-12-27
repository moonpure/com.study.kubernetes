package com.study.kubernete.user.copy;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class CacheServiceImpl<K, V> {
    private Cache<K, V> beanCache;
    private long expireAfterAccess=1l;
    private long expireAfterWrite=1l;
    private long refreshAfterWrite=10l;
    private TimeUnit unit;

    private CacheServiceImpl(Builder builder) {
        this.expireAfterAccess = builder.expireAfterAccess;
        this.expireAfterWrite = builder.expireAfterWrite;
        this.refreshAfterWrite= builder.refreshAfterWrite;
        this.unit = builder.unit;
        beanCache = Caffeine.newBuilder().expireAfterWrite(expireAfterWrite, unit).expireAfterAccess(expireAfterAccess, unit).build();
    }

    public void put(K key, V value) {
        beanCache.put(key, value);
    }

    public V get(K key) {
        return beanCache.getIfPresent(key);
    }
    public V get(@NonNull K key, @NonNull Function<? super K, ? extends V> function)
    {
        return beanCache.get(key,function);
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder<K, V> {
        private long expireAfterAccess = 1;
        private long expireAfterWrite = 1;
        private long refreshAfterWrite=10l;
        private TimeUnit unit = TimeUnit.HOURS;

        public Builder expireAfterAccess(long expireAfterAccess) {
            this.expireAfterAccess = expireAfterAccess;
            return this;
        }

        public Builder expireAfterWrite(long expireAfterWrite) {
            this.expireAfterWrite = expireAfterWrite;
            return this;
        }
        public Builder refreshAfterWrite(long refreshAfterWrite) {
            this.refreshAfterWrite = refreshAfterWrite;
            return this;
        }

        public Builder unit(TimeUnit unit) {
            this.unit = unit;
            return this;
        }

        public <K, V> CacheServiceImpl<K, V> build() {
            return new CacheServiceImpl(this);
        }

    }
}
