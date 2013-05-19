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


import com.google.common.primitives.Ints;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: Jul 11, 2010
 * Time: 9:09:59 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class IntsTest {
    @Test()
    public void testMethods() {
        List<Integer> ints = Ints.asList(1, 2, 3, 4, 5, 6, 7); //returns a list of Integers
        int value = Ints.checkedCast(1233045L); //casts the the value or throws an
        // IllegalArugmentException if it's too big or small
        int value2 = Ints.saturatedCast(1233045L); //no IllegalArgumentException thrown, will return MAX_VALUE
        //  is exceeded, MIN_VALUE otherwise
        assertEquals(Ints.compare(23, -23), 1); //provides Comparator results for primitives
        assertTrue(Ints.contains(new int[]{1, 2, 3, 4, 5, 6}, 3));
        assertEquals(Ints.indexOf(new int[]{1, 2, 3, 4, 5, 6}, 3), 2);
        assertEquals(Ints.lastIndexOf(new int[]{1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1}, 5), 6);
        assertEquals(Ints.min(1, 2, 3, 4, 5, 6), 1); //No need to explicitly set an array since it is a varargs parameter.
        assertEquals(Ints.max(1, 2, 3, 4, 5, 6), 6);
        assertTrue(
                Arrays.equals(   //java.util.Arrays
                        Ints.concat(new int[]{1, 2, 3, 4, 5, 6},
                                new int[]{7, 8, 9, 10},
                                new int[]{11, 12, 13, 14, 15}),
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}));
    }
}
