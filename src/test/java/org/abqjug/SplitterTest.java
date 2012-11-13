package org.abqjug;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.fest.assertions.Assertions.assertThat;

public class SplitterTest {

    @Test
    public void splitTests() {
        Iterator it = Iterables.transform(Splitter.on(',').split("foo, bar"), new Function<String, String>() {
            @Override
            public String apply(String input) {
                return input.trim();
            }
        }).iterator();

        assertThat(it.next()).isEqualTo("foo");
        assertThat(it.next()).isEqualTo("bar");

        //If I did Scala: "foo, bar".split(',').map(_.trim)
    }
}
