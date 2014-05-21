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

import com.google.common.collect.Range;
import org.testng.annotations.Test;

import static com.google.common.collect.Range.closed;
import static org.fest.assertions.Assertions.assertThat;

public class RangesTest {
    @Test
    public void testRanges() {
        assertThat(closed(3, 5).isConnected(Range.open(5, 10))).isEqualTo(true); // returns true
        assertThat(closed(0, 9).isConnected(closed(3, 4))).isEqualTo(true); // returns true
        assertThat(closed(0, 5).isConnected(closed(3, 9))).isEqualTo(true); // returns true
        assertThat(Range.open(3, 5).isConnected(Range.open(5, 10))).isEqualTo(false); // returns false
        assertThat(closed(1, 5).isConnected(closed(6, 10))).isEqualTo(false); // returns false
    }

}
