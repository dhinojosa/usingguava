package org.abqjug;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.testng.annotations.Test;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
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
                .put("Bismarck", "North Dakota").build();
        MapDifference<String, String> diff = Maps.difference(stateCaps, stateCaps2);
        assertEquals(diff.entriesOnlyOnLeft().size(), 4);
        assertEquals(diff.entriesOnlyOnRight().size(), 2);
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
//        assertThat(stateCaps).
    }

    @Test
    public void filterValuesTest() {
        Map<String, String> stateCaps = ImmutableMap.<String, String>builder()
                .put("Tallahassee", "Florida")
                .put("Santa Fe", "New Mexico")
                .put("Trenton", "New Jersey")
                .put("Olympia", "Washington")
                .put("Albany", "New York").build();
        Predicate<CharSequence> startsWithNew = Predicates.containsPattern("New.*");
        Map<String, String> filtered = Maps.filterValues(stateCaps,startsWithNew);
        assertEquals(filtered.size(), 3);
    }
}
