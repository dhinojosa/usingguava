package org.abqjug;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.cache.*;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;

public class LoadCacheTest {

    public class Key {
        private int value;

        public Key(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return Objects.
                    toStringHelper(this)
                    .add("value", value).toString();
        }

        @Override
        public boolean equals(Object object) {
            return object instanceof Key &&
                    Objects.equal(value, ((Key) object)
                            .getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value, 13);
        }
    }

    public class Result {
        private int value;

        public Result(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("value", value).toString();
        }

        @Override
        public boolean equals(Object object) {
            return object instanceof Key && Objects.equal(value, ((Key) object).getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value, 23);
        }
    }

    public class MyRemovalListener implements RemovalListener<Key, Result> {

        public void onRemoval(RemovalNotification<Key, Result> keyGraphRemovalNotification) {
            System.out.println(keyGraphRemovalNotification.getCause());
            System.out.println(keyGraphRemovalNotification.getKey());
        }
    }

    public Result createResult(Key key) {
        return new Result(key.getValue() * 2);
    }

    @Test
    public void testLoadingCache() throws ExecutionException {
        LoadingCache<Key, Result> graphs = CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .initialCapacity(50)
                .weakKeys()
                .weakValues()
//                .softValues()
                .removalListener(new MyRemovalListener())
                .build(
                        new CacheLoader<Key, Result>() {
                            public Result load(Key key) {
                                return createResult(key);
                            }
                        });


        assertThat(graphs.get(new Key(13)).getValue()).isEqualTo(26);
        assertThat(graphs.get(new Key(15)).getValue()).isEqualTo(30);
        System.out.println(graphs.getAll
                (Lists.transform(Lists.newArrayList(1, 3, 4, 19, 20, 25)
                        , new IntegerToKeyFunction())));

    }


    class IntegerToKeyFunction implements Function<Integer, Key> {
        public Key apply(Integer value) {
            return new Key(value);
        }
    }
}
