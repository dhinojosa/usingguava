package org.abqjug;


import org.testng.annotations.Test;

public class PreconditionsTest {

    @Test
    public void testPreconditions() {
        Book book = new Book();
        book.addGrade(90);
    }
}
