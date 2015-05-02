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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class IterablesTest {


    @Test
    public void testCycle() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        Iterable iterable = Iterables.cycle(list);
        Iterator it = iterable.iterator();
        for (int i = 0; i < 1000; i++) {
            it.next();
        }
        assertEquals(it.next(), 1);
    }

    @Test
    public void testPartition() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        Iterable iterable = Iterables.partition(list, 2);
        Iterator it = iterable.iterator();
        assertEquals(it.next(), Lists.newArrayList(1, 2));
        assertEquals(it.next(), Lists.newArrayList(3, 4));
        assertEquals(it.next(), Lists.newArrayList(5));
    }

    @Test
    public void testPaddedPartition() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        Iterable iterable = Iterables.paddedPartition(list, 2);
        Iterator it = iterable.iterator();
        assertEquals(it.next(), Lists.newArrayList(1, 2));
        assertEquals(it.next(), Lists.newArrayList(3, 4));
        assertEquals(it.next(), Lists.newArrayList(5, null));
    }
}
