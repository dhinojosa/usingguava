package org.abqjug;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimaps;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 03 Mar 14, 2010, 2010
 * Time: 3:20:24 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class MultimapTest {
    @Test(groups = "multimap")
    public void testMultiMap() {
        ArrayListMultimap<String, Integer> superBowlMap =
                ArrayListMultimap.create();
        superBowlMap.put("Dallas Cowboys", 1971);
        superBowlMap.put("Dallas Cowboys", 1992);
        superBowlMap.put("Dallas Cowboys", 1993);
        superBowlMap.put("Dallas Cowboys", 1993);
        superBowlMap.put("Dallas Cowboys", 1995);
        superBowlMap.put("Dallas Cowboys", 1977);
        superBowlMap.put("Pittsburgh Steelers", 1975);
        superBowlMap.put("Pittsburgh Steelers", 197);
        superBowlMap.put("Pittsburgh Steelers", 1979);
        superBowlMap.put("Pittsburgh Steelers", 1980);
        superBowlMap.put("Pittsburgh Steelers", 2006);
        superBowlMap.put("Pittsburgh Steelers", 2009);

        assertEquals(superBowlMap.get("Dallas Cowboys").size(), 6);
//        assertEquals(superBowlMap.get("Buffalo Bills").toString(), "[]");
        assertThat(superBowlMap.get("Dallas Cowboys")).contains(1971, 1992, 1993, 1993, 1995, 1977);

        assertThat(superBowlMap.containsKey("Seattle Seahawks")).isFalse();
//        ArrayListMultimap<Integer, String> newMap =
// ArrayListMultimap.create();
//        superBowlMap.put("Dallas Cowboys", 1975);
//        newMap.put(1984, "Oakland Raiders");
//        Multimaps.invertFrom(superBowlMap, newMap);
//        assertTrue(newMap.keys().size() == 12);
        superBowlMap.get("Seattle Seahawks");
        assertThat(superBowlMap.containsKey("Seattle Seahawks")).isFalse();
        assertThat(superBowlMap.get("Chicago Bears").size()).isEqualTo(0);
        assertThat(superBowlMap.containsKey("Chicago Bears")).isFalse();



//        assertThat(superBowlMap.get("Seattle Seahawks")).hasSize(1);
//        assertThat(superBowlMap.containsKey("Seattle Seahawks")).isTrue();
        System.out.println(superBowlMap.keySet());
    }
}
