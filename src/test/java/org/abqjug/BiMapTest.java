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

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 03 Mar 21, 2010, 2010
 * Time: 4:38:38 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class BiMapTest {

    @Test
    public void testHashBiMap() {
        HashBiMap<String, String> englishSpanishMap =
            HashBiMap.create();
        englishSpanishMap.put("book", "libro");
        englishSpanishMap.put("cloud", "nubio");
        englishSpanishMap.put("school", "escuela");
        englishSpanishMap.put("computer", "ordenador");
        assertEquals(englishSpanishMap.get("computer"), "ordenador");
        englishSpanishMap.put("fill", "llenar");
//        englishSpanishMap.put("feed", "llenar"); //illegal argument exception by default with same value
        englishSpanishMap.forcePut("feed", "llenar");


//        assertNull(englishSpanishMap.get("fill"));
//        assertEquals(englishSpanishMap.get("feed"), "llenar");
//        assertTrue(englishSpanishMap.containsValue("nubio"));
        BiMap<String, String> spanishEnglishMap = englishSpanishMap.inverse();
//        System.out.println(spanishEnglishMap);
        spanishEnglishMap.put("futbol", "soccer");
        System.out.println(englishSpanishMap);
        System.out.println(spanishEnglishMap);
    }

    @Test
    public void testImmutableBiMap() {
        ImmutableBiMap<String, String> englishSpanishMap = ImmutableBiMap
                .<String, String>builder()
                .put("book", "libro")
                .put("cloud", "nubio")
                .put("school", "escuela")
                .put("computer", "ordenador").build();
        assertEquals(englishSpanishMap.get("computer"), "ordenador");
        try {
            englishSpanishMap.put("fill", "llenar");  //Should not happen
            fail("expected java.lang.UnsupportedOperationException");
        } catch (java.lang.UnsupportedOperationException e) {
            assertTrue(true);
        }
    }

    //First Lets see how Java handles EnumMap<K extends Enum<K>,V>

    enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    @Test
    public void testEnumMap() {
        EnumMap<Day, List<String>> map = new EnumMap<Day, List<String>>(Day.class);
        map.put(Day.SUNDAY, Arrays.<String>asList("Cereal", "Turkey Sandwich", "Ribs", "Cole Slaw"));
        map.put(Day.MONDAY, Arrays.<String>asList("Cereal", "Cornbeef Sandwich", "Salmon", "Salad"));
        map.put(Day.TUESDAY, Arrays.<String>asList("Cereal", "Veggie Sandwich", "Tacos", "Beans"));
        map.put(Day.WEDNESDAY, Arrays.<String>asList("Eggs", "Toast", "Chicken Soup", "Steak", "Potato"));
        assertEquals(map.get(Day.WEDNESDAY).toString(), "[Eggs, Toast, Chicken Soup, Steak, Potato]");
    }


    enum SpanishDay {
        DOMINGO, LUNES, MARTES, MIERCOLOES, JUEVES, VIERNES, SABADO
    }

    @Test
    public void testEnumBiMap() {
        //This is different because since it is a bi map, both K, V need to be enums and unique.
        EnumBiMap<Day, SpanishDay> englishSpanishDayMap = EnumBiMap.create(Day.class, SpanishDay.class);
        englishSpanishDayMap.put(Day.MONDAY, SpanishDay.LUNES);
        try {
            englishSpanishDayMap.put(Day.TUESDAY, SpanishDay.LUNES);
            fail("expected java.lang.IllegalArgumentException");
        } catch (java.lang.IllegalArgumentException e) {
            assertTrue(true);
        }

        assertTrue(englishSpanishDayMap.containsKey(Day.MONDAY));
        englishSpanishDayMap.forcePut(Day.SATURDAY, SpanishDay.LUNES);
        assertEquals(englishSpanishDayMap.get(Day.SATURDAY), SpanishDay.LUNES);
    }

}
