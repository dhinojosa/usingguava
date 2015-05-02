package org.abqjug;


import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class RangeMapTest {


    @Test
    public void testRangeMap() {
        RangeMap<Integer, String> gradeScale = TreeRangeMap.create();
        gradeScale.put(Range.closed(0, 60), "F");
        gradeScale.put(Range.closed(61, 70), "D");
        gradeScale.put(Range.closed(71, 80), "C");
        gradeScale.put(Range.closed(81, 90), "B");
        gradeScale.put(Range.closed(91, 100), "A");

        assertThat(gradeScale.get(95)).isEqualTo("A");
        assertThat(gradeScale.get(60)).isEqualTo("F");
        //Credit: http://www.leveluplunch.com/java/examples/guava-rangemap-example/
    }
}
