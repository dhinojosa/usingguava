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
