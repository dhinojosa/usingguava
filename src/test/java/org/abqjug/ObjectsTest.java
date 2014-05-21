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

import com.google.common.base.Objects;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: Jul 11, 2010
 * Time: 8:03:12 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class ObjectsTest {
    public boolean testObjects(String s1, String s2) {
        if (s1 != null) return s1.equals(s2);
        return s2 != null && s2.equals(s1);
    }

    public boolean testObjects2(String s1, String s2) {
        return Objects.equal(s1, s2);
    }

    @Test
    public void testObjectsUtil() {
        assertThat(java.util.Objects.equals("Goat", "Goat")).isEqualTo(true);
        assertThat(Objects.equal("Goat", "Goat")).isEqualTo(true);
    }
    @Test(groups = "unit")
    public void testObjectsRegularWay() {
        String s1 = "Cool";
        String s2 = null;
        assertThat(testObjects(s1, s2)).isFalse();
    }

    @Test(groups = "unit")
    public void testObjectsRegularWay2() {
        String s1 = null;
        String s2 = "Cool";
        assertThat(testObjects(s1, s2)).isFalse();
    }

    @Test(groups = "unit")
    public void testObjectsRegularWay3() {
        String s1 = "Cool";
        String s2 = "Cool";
        assertThat(testObjects(s1, s2)).isTrue();
    }

    @Test(groups = "unit")
    public void testObjectsWithGuava() {
        String s1 = "Cool";
        String s2 = null;
        assertThat(Objects.equal(s1, s2)).isFalse();
    }

    @Test(groups = "unit")
    public void testObjectsWithGuava2() {
        String s1 = null;
        String s2 = "Cool";
        assertThat(Objects.equal(s1, s2)).isFalse();
    }

    @Test(groups = "unit")
    public void testObjectsWithGuava3() {
        String s1 = "Cool";
        String s2 = "Cool";
        assertThat(Objects.equal(s1, s2)).isTrue();
    }

    @Test(groups = "unit")
    public void testObjectFirstNonNull() {
        String s1 = null;
        String s2 = "Cool";
        assertThat(Objects.firstNonNull(s1, s2)).isEqualTo(s2);
    }

    @Test(groups = "unit")
    public void testObjectFirstNonNull2() {
        String s1 = "Cool";
        String s2 = null;
        assertThat(Objects.firstNonNull(s1, s2)).isEqualTo(s1);
    }

    @Test(groups = "unit")
    public void testObjectFirstNonNull3() {
        String s1 = "Cool";
        String s2 = "Cool";
        assertThat(Objects.firstNonNull(s1, s2)).isEqualTo(s1);
    }


}
