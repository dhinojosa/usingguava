package org.abqjug;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class ListsTest {
    @Test
    public void testLists() {
        assertThat(Strings.repeat("rah ", 3)).isEqualTo("rah rah rah ");
        assertThat(Lists.reverse(Lists.newArrayList(1, 2, 3, 4, 5, 6))).isEqualTo(
                Lists.newArrayList(6, 5, 4, 3, 2, 1));
        assertThat(Lists.partition(Lists.newArrayList(1,2,3,4,5,6), 2)).isEqualTo(
                Lists.<List<Integer>>newArrayList(
                Lists.<Integer>newArrayList(1,2), Lists.<Integer>newArrayList(3,4), Lists.<Integer>newArrayList(5, 6)));
    }
}
