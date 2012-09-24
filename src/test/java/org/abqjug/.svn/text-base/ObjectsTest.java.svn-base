package org.abqjug;

import com.google.common.base.Objects;
import org.testng.annotations.Test;

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
    @Test(groups="unit")
    public boolean testObjects(String s1, String s2) {
        if (s1 != null) return s1.equals(s2);
        return s2 != null && s2.equals(s1);
    }

    @Test(groups="unit")
    public boolean testObjects2(String s1, String s2) {
        return Objects.equal(s1, s2);
    }
}
