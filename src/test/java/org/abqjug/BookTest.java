package org.abqjug;

import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: Jul 11, 2010
 * Time: 7:11:20 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class BookTest {
    @Test()
    public void testGetGrades() {
        Book book = new Book();
        book.addGrade(100);
        book.addGrade(99);
        book.addGrade(87);
        book.addGrade(55);
        assertEquals(book.getHighestGrade(), new Integer(100));
    }

    @Test()
    public void testGetGradesWithNoGrades() {
        Book book = new Book();
        try {
            book.getHighestGrade();
        } catch (IllegalStateException ise) {
            assertThat(ise).hasMessage("No grades are entered");
        }
    }

    @Test()
    public void testAddAnotherWayGrade() {
        Book book = new Book();
        book.addAnotherWayGrade(100);
    }
}
