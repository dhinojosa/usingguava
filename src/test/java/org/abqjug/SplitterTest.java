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

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public void splitStuff() {
        System.out.println(Splitter.onPattern(",").split("and,aid,alm,awk,boy"));

    }

    private List<String> regexToList(String words, String regex) {
        List wordList = new ArrayList<>();
        Matcher m = Pattern.compile(regex).matcher(words);
        while (m.find())
            wordList.add(m.group());
        return wordList;
    }

    @Test
    public void splitStuff2() {
        Pattern.compile("b.*\\W").splitAsStream("a bhutanese bonnet brings bowling to boston and buffalo").forEach(System.out::println);
        System.out.println("Cool");
    }
}
