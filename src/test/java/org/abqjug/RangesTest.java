package org.abqjug;

import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Ranges;
import org.testng.annotations.Test;

import static com.google.common.collect.Ranges.closed;
import static org.fest.assertions.Assertions.assertThat;

public class RangesTest {
    @Test
    public void testRanges() {
        assertThat(closed(3, 5).isConnected(Ranges.open(5, 10))).isEqualTo(true); // returns true
        assertThat(closed(0, 9).isConnected(closed(3, 4))).isEqualTo(true); // returns true
        assertThat(closed(0, 5).isConnected(closed(3, 9))).isEqualTo(true); // returns true
        assertThat(Ranges.open(3, 5).isConnected(Ranges.open(5, 10))).isEqualTo(false); // returns false
        assertThat(closed(1, 5).isConnected(closed(6, 10))).isEqualTo(false); // returns false
    }

    @Test
    public void testWithIterations() {
        for (Integer g : closed(3, 10).asSet(DiscreteDomains.integers())) {
            System.out.println(g);
        }
    }


}
