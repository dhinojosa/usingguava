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

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class MapsTest {

    @Test
    public void differenceTest() {
        Map<String, String> stateCaps = ImmutableMap.<String, String>builder()
                .put("Tallahassee", "Florida")
                .put("Santa Fe", "New Mexico")
                .put("Trenton", "New Jersey")
                .put("Olympia", "Washington")
                .put("Albany", "New York").build();
        Map<String, String> stateCaps2 = ImmutableMap.<String, String>builder()
                .put("Tallahassee", "Florida")
                .put("Raleigh", "North Carolina")
                .put("Salem", "Oregon")
                .put("Santa Fe", "New Mexico")
                .put("Denver", "Colorado")
                .put("Bismarck", "North Dakota").build();
        MapDifference<String, String> diff = Maps.difference(stateCaps, stateCaps2);
        assertEquals(diff.entriesInCommon().size(), 2);
        assertEquals(diff.entriesOnlyOnRight().size(), 4);
    }

    @Test
    public void differenceTestEntriesInCommon() {
        Map<String, String> stateCaps = ImmutableMap.<String, String>builder()
                .put("Tallahassee", "Florida")
                .put("Santa Fe", "New Mexico")
                .put("Trenton", "New Jersey")
                .put("Olympia", "Washington")
                .put("Albany", "New York").build();
        Map<String, String> stateCaps2 = ImmutableMap.<String, String>builder()
                .put("Tallahassee", "Florida")
                .put("Raleigh", "North Carolina")
                .put("Bismarck", "North Dakota").build();
        Map<String, String> common = Maps.difference(stateCaps, stateCaps2).entriesInCommon();
        assertEquals(common.size(), 1);
        assertEquals(common.get("Tallahassee"), "Florida");
    }

    @Test
    public void filterValuesTest() {
        Map<String, String> stateCaps = ImmutableMap.<String, String>builder()
                .put("Tallahassee", "Florida")
                .put("Olympia", "Washington")
                .put("Albany", "New York").build();
        Predicate<CharSequence> startsWithNew = Predicates.containsPattern("New.*");
        Map<String, String> filtered = Maps.filterValues(stateCaps, startsWithNew);
        assertEquals(filtered.size(), 1);
    }
}
