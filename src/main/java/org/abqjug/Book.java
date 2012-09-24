package org.abqjug;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: Jul 11, 2010
 * Time: 2:46:57 AM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class Book {
    private List<Integer> grades;

    public Book() {
        this.grades = new ArrayList<Integer>();
    }

    public void addGrade(Integer grade) {
        checkNotNull(grade);
        checkArgument(grade >= 0 && grade < 101, "Grade must be between 0 and 101");
        this.grades.add(grade);
    }

    public void addAnotherWayGrade(Integer grade) {
        this.grades.add(checkNotNull(grade));
    }

    public void addGradeOldJavaWay(Integer grade) {
        if (grade == null) throw new NullPointerException();
        if (grade >= 0 && grade < 101) throw new IllegalArgumentException("Grade must be between 0 and 101");
        this.grades.add(grade);
    }

    public List<Integer> getGrades() {
        return ImmutableList.copyOf(grades).asList();
    }

    public Integer getHighestGrade() {
        checkState(grades != null, "Grades are not set");
        checkState(grades.size() > 0, "No grades are entered");
        return Collections.max(this.grades);
    }
}
