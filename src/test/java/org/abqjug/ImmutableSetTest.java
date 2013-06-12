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

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 03 Mar 14, 2010, 2010
 * Time: 2:30:21 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class ImmutableSetTest {

    @Test(groups = "immutables")
    public void testWhatIsWrongWithUnmodifiable() {
        Set<Integer> intSet = new HashSet<Integer>();
        intSet.add(4);
        intSet.add(5);
        intSet.add(6);
        intSet.add(7);
        Set<Integer> unmodifiableSet = Collections.unmodifiableSet(intSet);
        try {
            unmodifiableSet.add(10);
            fail("Unsupported Operation Exception found");
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }

        assertEquals(unmodifiableSet.size(), 4);
        intSet.add(10); //This will be allowed.
//        assertEquals(unmodifiableSet.size(), 4);    //fail
        System.out.println(unmodifiableSet);
    }

    @Test(groups = "immutables")
    public void testImmutableSetOf() {
        // it is strongly recommended to place only immutable objects into this collection.
        // This class has been observed to perform significantly better than HashSet for objects
        // with very fast Object.hashCode() implementations (as a well-behaved immutable object should).
        // While this class's factory methods create hash-based instances, the ImmutableSortedSet subclass
        // performs binary searches instead.

        Set<Integer> intSet = ImmutableSet.of(4, 5, 6, 7);

        try {
            intSet.add(10);
            fail("Unsupported Operation Exception found");
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }

        assertEquals(intSet.size(), 4);
    }

    @Test(groups = "immutables")
    public void testImmutableSetBuilder() {

        Set<Integer> intSet =
                ImmutableSet.<Integer>builder()
                        .add(1, 2, 3, 4, 5)
                        .addAll(Arrays.asList(6, 7, 8, 9, 10))
                        .build();

        try {
            intSet.add(12);
            fail("Unsupported Operation Exception expected");
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }

        assertEquals(intSet.size(), 10);
    }

    @Test(groups = "immutables")
    public void testImmutableSetBuilderWithUnmodifiableIterator() {
        // Showing the iterator.  Adding duplicate 7s.
        Set<Integer> intSet =
                ImmutableSet.<Integer>builder().add(1, 2, 3, 4, 5).addAll(Arrays.asList(6, 7, 7, 8, 9, 10)).build();

        Iterator<Integer> itInt = intSet.iterator();
        while (itInt.hasNext()) {
            System.out.println(itInt.next());
            try {
                itInt.remove();
                fail("Unsupported Operation Exception expected");
            } catch (UnsupportedOperationException e) {
                assertTrue(true);
            }
        }
        assertEquals(intSet.size(), 10);
    }

    @Test(groups = "immutables")
    public void testImmutableCopyOf() {
        // Showing the iterator.  Adding duplicate 7s.
        List<Integer> integerList = Lists.newArrayList(6, 7, 7, 8, 9, 10);
        //....
        Set<Integer> intSet =
                ImmutableSet.<Integer>builder().addAll(integerList).build();

        assertEquals(intSet.toString(), "[6, 7, 8, 9, 10]");
        integerList.add(12);
        assertEquals(intSet.toString(), "[6, 7, 8, 9, 10]");

        Set<Integer> anotherSet = new HashSet<Integer>(intSet);
        anotherSet.add(100);

        assertEquals(intSet.toString(), "[6, 7, 8, 9, 10]");
    }


    @Test(groups = "immutables")
    public void testImmutableSetOfWithDuplicates() {
        // Showing the iterator.  Adding duplicate 7s.
        Set<Integer> intSet = ImmutableSet.of(6, 7, 7, 8, 9, 10);
        assertEquals(intSet.toString(), "[6, 7, 8, 9, 10]");
    }


}
