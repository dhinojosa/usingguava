package org.abqjug;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

public class SplitterTest {

    @Test
    public void simpleSplit() {
        Iterable<String> items = Splitter.on(",").trimResults().split("Manny, Moe, Java");
        List<String> itemList = Lists.newArrayList(items);
        assertThat(itemList.get(0)).isEqualTo("Manny");
        assertThat(itemList.get(1)).isEqualTo("Moe");
        assertThat(itemList.get(2)).isEqualTo("Java");
    }

    @Test
    public void splitTests() {
        Iterator it = Iterables.transform(Splitter.on(',').split("foo, bar"),
          new Function<String, String>() {
            @Override
            public String apply(String input) {
                return input.trim();
            }
        }).iterator();

        assertThat(it.next()).isEqualTo("foo");
        assertThat(it.next()).isEqualTo("bar");
    }

    @Test
    public void splitMap() {
        String value = "New Mexico -> Santa Fe, Texas -> Austin, Arizona -> Phoenix";
        Map<String, String> splitKeyValues = Splitter.on(",")
                .omitEmptyStrings()
                .trimResults()
                .withKeyValueSeparator("->")
                .split(value);
         assertThat(splitKeyValues).hasSize(3);
    }
}
