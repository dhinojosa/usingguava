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

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: Jul 14, 2010
 * Time: 10:33:30 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class ImmutableMapTest {
    @Test(groups = "unit")
    public void immutableMapTest() {
        Map<String, String> capitals = new ImmutableMap.Builder<String, String>()
                .put("Brazil", "Brasilia")
                .put("United States", "Washington, DC")
                .put("Portugal", "Lisbon")
                .build();
        System.out.println(capitals);
        
    }
}
