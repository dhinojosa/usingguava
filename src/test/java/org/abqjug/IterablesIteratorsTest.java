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

import java.util.Arrays;
import java.util.List;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 03 Mar 22, 2010, 2010
 * Time: 12:15:31 AM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */

//An iterable is anything that implements BeanContext, BeanContextServices, BlockingDeque<E>,
//BlockingQueue<E>, Collection<E>, Deque<E>, List<E>, NavigableSet<E>, Queue<E>, Set<E>, SortedSet<E>
public class IterablesIteratorsTest {

    @Test
    public void testIterableAddAll() {
        List<String> northAmericaCountries = Lists.newArrayList("Canada", "Mexico", "United States");
        List<String> centralAmericaCountries =
                Lists.newArrayList("Guatamala", "Belize", "El Salvador", "Honduras", "Costa Rica", "Nicaragua");
        Iterables.addAll(northAmericaCountries, centralAmericaCountries);
        System.out.println(northAmericaCountries);
    }
}
