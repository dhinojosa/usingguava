package org.abqjug;

import com.google.common.base.Objects;

/**
 * @author Daniel Hinojosa
 * @since 6/12/13 1:14 AM
 *        url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 *        email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 *        tel: 505.363.5832
 */
public class Employee {
    private String firstName;
    private String lastName;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equal(this.firstName, employee.firstName) &&
                Objects.equal(this.lastName, employee.lastName);

//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Employee employee = (Employee) o;
//
//        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
//        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
//
//        return true;
    }

    @Override
    public int hashCode() {
        java.util.Objects.hash(firstName, lastName);
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
