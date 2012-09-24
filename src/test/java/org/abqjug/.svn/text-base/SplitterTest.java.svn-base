package org.abqjug;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: Jul 15, 2010
 * Time: 4:23:20 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class SplitterTest {

    @Test
    public void splitTests() {
        assertThat(Iterables.toString(Splitter.on(',').split("Gryfondor, Slytherin"))).isEqualTo("[foo, bar]");
    }
}
