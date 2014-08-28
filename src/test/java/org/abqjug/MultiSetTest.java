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

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.testng.Assert.*;

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
        assertFalse(worldCupChampionships.contains("United States"));
        worldCupChampionships.add("United States", 0);
        assertEquals(worldCupChampionships.count("United States"), 0);
        assertFalse(worldCupChampionships.contains("United States"));
//
//        Multiset<String> updatedWorldCupChampionships = worldCupChampionships.stream().map((s) ->
//                String.format("Team %s", s)).collect(Collectors.toCollection(HashMultiset::create));
//        System.out.println(updatedWorldCupChampionships);


        Collection<String> g = Collections2.transform(worldCupChampionships, new Function<String, String>() {
            public String apply(String input) {
                return "Team " + input;
            }
        });

        assertTrue(g.contains("Team Brazil"));

//
//        worldCupChampionships.remove("Brazil");
//        assertEquals(worldCupChampionships.count("Brazil"), 4);
////        worldCupChampionships.remove("Brazil",
////                worldCupChampionships.count("Brazil"));
//        worldCupChampionships.setCount("Brazil", 0);
//        assertEquals(worldCupChampionships.count("Brazil"), 0);
//        assertEquals(worldCupChampionships.count("Uruguay"), 0);
    }
}
