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

import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
public class ImmutableListTest {


    @Test(groups = "immutables")
    public void testImmutableListOf() {

        List<Integer> integerList = ImmutableList.of(4, 4, 5, 6, 7);
        try {
            integerList.add(10);
            fail("Unsupported Operation Exception found");
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }

        assertEquals(integerList.size(), 5);
    }

    @Test(groups = "immutables")
    public void testImmutableListBuilder() {

        List<Integer> intList =
                ImmutableList.<Integer>builder().add(1, 2, 3, 4, 5).addAll(Arrays.asList(6, 7, 8, 9, 10)).build();

        try {
            intList.add(12);
            fail("Unsupported Operation Exception expected");
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }

        assertEquals(intList.size(), 10);
    }

    @Test(groups = "immutables")
    public void testImmutableListBuilderWithUnmodifiableIterator() {
        // Showing the iterator.  Adding duplicate 7s.
        List<Integer> intList =
                ImmutableList.<Integer>builder().add(1, 2, 3, 4, 5).addAll(Arrays.asList(6, 7, 7, 8, 9, 10)).build();

        Iterator<Integer> itInt = intList.iterator();
        while (itInt.hasNext()) {
            System.out.println(itInt.next());
            try {
                itInt.remove();
                fail("Unsupported Operation Exception expected");
            } catch (UnsupportedOperationException e) {
                assertTrue(true);
            }
        }
        assertEquals(intList.size(), 11);
    }

    @Test(groups = "immutables")
    public void testImmutableCopyOf() {
        // Showing the iterator.  Adding duplicate 7s.
        List<Integer> intList =
                ImmutableList.copyOf(Arrays.asList(6, 7, 7, 8, 9, 10));


        assertEquals(intList.toString(), "[6, 7, 7, 8, 9, 10]");
    }


}