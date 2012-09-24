package org.abqjug;

import com.google.common.collect.Iterables;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 03 Mar 22, 2010, 2010
 * Time: 12:15:31 AM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */

//An iterable is anything that implements BeanContext, BeanContextServices, BlockingDeque<E>,
//BlockingQueue<E>, Collection<E>, Deque<E>, List<E>, NavigableSet<E>, Queue<E>, Set<E>, SortedSet<E>
public class IterablesIteratorsTest {

    @Test
    public void testIterableAddAll() {
        List<String> northAmericaCountries = Arrays.<String>asList("Canada", "Mexico", "United States");
        List<String> centralAmericaCountries =
                Arrays.<String>asList("Guatamala", "Belize", "El Salvador", "Honduras", "Costa Rica", "Nicaragua");
        Iterables.addAll(northAmericaCountries, centralAmericaCountries);
        System.out.println(northAmericaCountries);
    }
}
