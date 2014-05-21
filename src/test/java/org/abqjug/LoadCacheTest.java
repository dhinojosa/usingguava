/**
 * Copyright 2010-2013 Daniel Hinojosa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.abqjug;

import com.google.common.cache.*;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;

public class LoadCacheTest {

    public class MyRemovalListener implements RemovalListener<Integer, BigInteger> {
        private List<Integer> keysRemovedRecently;

        public MyRemovalListener() {
              this.keysRemovedRecently = new ArrayList<>();
        }

        @SuppressWarnings("NullableProblems")
        public void onRemoval(RemovalNotification<Integer, BigInteger>
                                      keyGraphRemovalNotification) {
            keysRemovedRecently.add(keyGraphRemovalNotification.getKey());
            System.out.println(keyGraphRemovalNotification.getCause());
            System.out.println(keyGraphRemovalNotification.getKey());
        }

        public List<Integer> getKeysRemovedRecently() {
            return keysRemovedRecently;
        }
    }


    @Test
    public void testLoadingCache() throws ExecutionException, InterruptedException {
        MyRemovalListener removalListener = new MyRemovalListener();
        LoadingCache<Integer, BigInteger> map = CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .initialCapacity(50)
                .weakKeys()
                .softValues()
                .removalListener(removalListener)
                .recordStats()
                .build(
                        new CacheLoader<Integer, BigInteger>() {
                            @SuppressWarnings("NullableProblems")
                            public BigInteger load(Integer source) throws InterruptedException {
                                Thread.sleep(5000);
                                return BigInteger.valueOf(source).multiply(new BigInteger("500"));
                            }
                        }
                );

        {
            Instant before = Instant.now();
            BigInteger result = map.get(13);
            Instant after = Instant.now();

            assertThat(after.getEpochSecond() - before.getEpochSecond()).isGreaterThanOrEqualTo(5);
            assertThat(result).isEqualTo(new BigInteger("6500"));
        }


        {
            Instant before = Instant.now();
            BigInteger result = map.get(13);
            Instant after = Instant.now();

            assertThat(after.getEpochSecond() - before.getEpochSecond()).isLessThan(1);
            assertThat(result).isEqualTo(new BigInteger("6500"));
        }

        {
            map.invalidate(13);
            assertThat(removalListener.getKeysRemovedRecently().get(0)).isEqualTo(13);
        }

        {
            map.stats().averageLoadPenalty();
            map.stats().evictionCount();
            map.stats().hitCount();
            map.stats().hitRate();
            map.stats().missCount();
        }
    }
}
