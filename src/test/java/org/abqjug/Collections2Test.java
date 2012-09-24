package org.abqjug;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.testng.Assert.assertEquals;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 03 Mar 13, 2010, 2010
 * Time: 9:44:29 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class Collections2Test {

    @Test(groups = "unit")
    public void testFilter() {
        Collection<Integer> unfiltered = Lists.<Integer>newArrayList(1, 5, 6, 8, 9, 10, 44, 55, 19);
        assertEquals(Collections2.filter(unfiltered, new Predicate<Integer>() {
                    public boolean apply(Integer input) {
                        return input % 2 != 0;
                    }
                }).
                toString(), "[1, 5, 9, 55, 19]");
    }

    @Test(groups = "unit")
    public void testTransform() {
        Function<Integer, Integer> doubleIt = new Function<Integer, Integer>() {
            public Integer apply(Integer from) {
                return from * 2;
            }
        };

        Collection<Integer> untransformed = Lists
                .newArrayList(1, 5, 6, 8, 9, 10, 44, 55, 19);
        assertEquals(Collections2.transform(untransformed, doubleIt)
                .toString(),
                "[2, 10, 12, 16, 18, 20, 88, 110, 38]");

        assertEquals(untransformed.toString(), "[1, 5, 6, 8, 9, 10, 44, 55, 19]");
    }

    @Test(groups = "unit")
    public void testTransformOdd() {
        Function<Integer, Boolean> oddIt = new Function<Integer, Boolean>() {
            public Boolean apply(Integer from) {
                return from % 2 == 0;
            }
        };

        Collection<Integer> untransformed = Lists.newArrayList(1, 5, 6, 8, 9, 10, 44, 55, 19);
        assertEquals(Collections2.transform(untransformed, oddIt).toString(),
                "[false, false, true, true, false, true, true, false, false]");
        assertEquals(untransformed.toString(), "[1, 5, 6, 8, 9, 10, 44, 55, 19]");
    }
}
