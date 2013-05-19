/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2010-2013 Daniel Hinojosa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
