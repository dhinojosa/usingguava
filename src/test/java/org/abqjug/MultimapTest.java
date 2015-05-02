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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 03 Mar 14, 2010, 2010
 * Time: 3:20:24 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class MultimapTest {
    @Test(groups = "multimap")
    public void testMultiMap() {
        Multimap<String, Integer> superBowlMap =
                ArrayListMultimap.create();
        superBowlMap.put("Dallas Cowboys", 1971);
        superBowlMap.put("Dallas Cowboys", 1992);
        superBowlMap.put("Dallas Cowboys", 1993);
        superBowlMap.put("Dallas Cowboys", 1995);
        superBowlMap.put("Dallas Cowboys", 1977);
        superBowlMap.put("Pittsburgh Steelers", 1975);
        superBowlMap.put("Pittsburgh Steelers", 1976);
        superBowlMap.put("Pittsburgh Steelers", 1979);
        superBowlMap.put("Pittsburgh Steelers", 1980);
        superBowlMap.put("Pittsburgh Steelers", 2006);
        superBowlMap.put("Pittsburgh Steelers", 2009);

        assertThat(superBowlMap.get("Dallas Cowboys").size()).isEqualTo(5);
        assertThat(superBowlMap.get("Buffalo Bills").size()).isEqualTo(0);
        assertThat(superBowlMap.containsKey("Seattle Seahawks")).isFalse();

        assertThat(superBowlMap.keySet()).hasSize(2);
        superBowlMap.get("Denver Broncos").add(1999);
        assertThat(superBowlMap.keySet()).hasSize(3);

    }
}
