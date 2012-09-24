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
