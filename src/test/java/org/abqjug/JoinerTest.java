package org.abqjug;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 03 Mar 13, 2010, 2010
 * Time: 8:30:25 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class JoinerTest {


    @Test(groups = "joiner")
    public void joinTestBasic() {
        assertEquals(Joiner.on(',').join("Manny", "Moe", "Java"), "Manny,Moe,Java");
    }


    /**
     * Appendable is any of the following but not limited to:
     * BufferedWriter, CharArrayWriter, CharBuffer, FileWriter, FilterWriter, LogStream, OutputStreamWriter,
     * PipedWriter, PrintStream, PrintWriter, StringBuffer, StringBuilder, StringWriter, Writer
     *
     * @throws java.io.IOException if the something happened to the write.
     */
    @Test(groups = "joiner")
    public void testUsingAppendable() throws IOException {
        StringWriter w = new StringWriter();
        assertEquals(Joiner.on(": ").appendTo(w, "Alpha", "Beta", "Gamma", "Delta").toString(), "Alpha: Beta: Gamma: Delta");
    }


    @Test(groups = "joiner")
    public void testSkipNulls() {
        assertEquals(Joiner.on(" ~ ").skipNulls().join(
                Lists.newArrayList("Elvis", "Jim Morrison", "Roy Orbison", null, "Buddy Holly")),
                "Elvis ~ Jim Morrison ~ Roy Orbison ~ Buddy Holly");
    }

    @Test(groups = "joiner")
    public void testUseForNull() {
        int c = 0;
        assertEquals(Joiner.on(" ! ").useForNull("something" + (++c)).join(
                Lists.newArrayList("Elvis", "Jim Morrison", null, "Roy Orbison", null, "Buddy Holly")),
                "Elvis ! Jim Morrison ! Roy Orbison ! <NOTHING> ! Buddy Holly");
    }

    @Test(groups = "joiner")
    public void testUseWithMap() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("New Mexico", "Santa Fe");
        map.put("Texas", "Austin");
        map.put("Arizona", "Phoenix");
        assertEquals(Joiner.on(", ").withKeyValueSeparator(" -> ").join(map),
                "New Mexico -> Santa Fe, Texas -> Austin, Arizona -> Phoenix");
    }

    @Test(groups = "joiner")
    public void testUseWithMapJavaStyle() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("New Mexico", "Santa Fe");
        map.put("Texas", "Austin");
        map.put("Arizona", "Phoenix");

        StringBuffer stringBuffer = new StringBuffer();
        boolean started = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (started) stringBuffer.append(", ");
            stringBuffer.append(entry.getKey());
            stringBuffer.append(" -> ");
            stringBuffer.append(entry.getValue());
            started = true;
        }

        assertEquals(stringBuffer.toString(),
                "New Mexico -> Santa Fe, Texas -> Austin, Arizona -> Phoenix");
    }
}
