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

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: Jul 13, 2010
 * Time: 3:55:19 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class MultiSetTest {

    @Test(groups = "unit")
    public void testMultiset() {
        Multiset<String> worldCupChampionships = HashMultiset.<String>create();
        worldCupChampionships.add("Brazil");
        worldCupChampionships.add("Brazil");
        worldCupChampionships.add("Brazil");
        worldCupChampionships.add("Brazil");
        worldCupChampionships.add("Brazil");
        worldCupChampionships.add("Italy");
        worldCupChampionships.add("Italy");
        worldCupChampionships.add("Italy");
        worldCupChampionships.add("Italy");
        worldCupChampionships.add("Germany", 3);
        assertEquals(worldCupChampionships.count("Brazil"), 5);
        assertEquals(worldCupChampionships.count("Italy"), 4);
        assertEquals(worldCupChampionships.count("Germany"), 3);
        assertEquals(worldCupChampionships.count("United States"), 0);
        worldCupChampionships.remove("Brazil");
        assertEquals(worldCupChampionships.count("Brazil"), 4);
//        worldCupChampionships.remove("Brazil",
//                worldCupChampionships.count("Brazil"));
        worldCupChampionships.setCount("Brazil", 0);
        assertEquals(worldCupChampionships.count("Brazil"), 0);
        assertEquals(worldCupChampionships.count("Uruguay"), 0);

    }
}
