package org.abqjug;

import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.sun.xml.internal.fastinfoset.util.DuplicateAttributeVerifier;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;

public class FluentIterableTest {

    private final String capitals = "Montgomery\tAlabama\n" +
            "Juneau\tAlaska\n" +
            "Phoenix\tArizona\n" +
            "Little Rock\tArkansas\n" +
            "Sacramento\tCalifornia\n" +
            "Denver\tColorado\n" +
            "Hartford\tConnecticut\n" +
            "Dover\tDelaware\n" +
            "Tallahassee\tFlorida\n" +
            "Atlanta\tGeorgia\n" +
            "Honolulu\tHawaii\n" +
            "Boise\tIdaho\n" +
            "Springfield\tIllinois\n" +
            "Indianapolis\tIndiana\n" +
            "Des Moines\tIowa\n" +
            "Topeka\tKansas\n" +
            "Frankfort\tKentucky\n" +
            "Baton Rouge\tLouisiana\n" +
            "Augusta\tMaine\n" +
            "Annapolis\tMaryland\n" +
            "Boston\tMassachusetts\n" +
            "Lansing\tMichigan\n" +
            "Saint Paul\tMinnesota\n" +
            "Jackson\tMississippi\n" +
            "Jefferson City\tMissouri\n" +
            "Helena\tMontana\n" +
            "Lincoln\tNebraska\n" +
            "Carson City\tNevada\n" +
            "Concord\tNew Hampshire\n" +
            "Trenton\tNew Jersey\n" +
            "Santa Fe\tNew Mexico\n" +
            "Albany\tNew York\n" +
            "Raleigh\tNorth Carolina\n" +
            "Bismarck\tNorth Dakota\n" +
            "Columbus\tOhio\n" +
            "Oklahoma City\tOklahoma\n" +
            "Salem\tOregon\n" +
            "Harrisburg\tPennsylvania\n" +
            "Providence\tRhode Island\n" +
            "Columbia\tSouth Carolina\n" +
            "Pierre\tSouth Dakota\n" +
            "Nashville\tTennessee\n" +
            "Austin\tTexas\n" +
            "Salt Lake City\tUtah\n" +
            "Montpelier\tVermont\n" +
            "Richmond\tVirginia\n" +
            "Olympia\tWashington\n" +
            "Charleston\tWest Virginia\n" +
            "Madison\tWisconsin\n" +
            "Cheyenne\tWyoming";


    @Test
    public void testBasicFluentIterable() {
        List<String> result = FluentIterable
                .from(Arrays.asList(capitals.split("\n")))
                .transform(s -> s.split("\t")[0].toLowerCase().trim())
                .filter(Predicates.containsPattern("[a-z&&[^aeiou]]{4}"))
                .toList();

        assertThat(result).containsExactly("olympia");
    }

    @Test
    public void testBasicFunctionalProgrammingUsingJava8() {
        Pattern p = Pattern.compile("[a-z&&[^aeiou]]{4}");
        //Can't use matches, since matches uses entire string
        List<String> result = Arrays.asList(capitals.split("\n"))
                .stream()
                .map(s -> s.split("\t")[0].toLowerCase().trim())
                .filter(s -> p.matcher(s).find())
                .collect(Collectors.toList());
        assertThat(result).containsExactly("olympia");
    }
}








