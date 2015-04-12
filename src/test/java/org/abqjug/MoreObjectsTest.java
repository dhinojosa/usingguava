package org.abqjug;

import com.google.common.base.MoreObjects;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class MoreObjectsTest {

   /** MoreObjects is to be used in conjunction with java.util.Objects,
    *  this exclusively to be used for Java 8
    **/
   @Test
   public void testToStringHelper() {
      Employee employee = new Employee("Bob", "Barker");
      String result = MoreObjects.toStringHelper(Employee.class)
              .add("firstName", employee.getFirstName())
              .add("lastName",employee.getLastName())
              .toString();
      assertThat(result).isEqualTo("Employee{firstName=Bob, lastName=Barker}");
   }
}
