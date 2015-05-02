package org.abqjug;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private String alias;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String alias) {
        this.alias = alias;
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

    public String getAlias() {
        return alias;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof Employee)) return false;
//        Employee employee = (Employee) o;
//        return Objects.equal(this.firstName, employee.firstName) &&
//                Objects.equal(this.lastName, employee.lastName) &&
//                Objects.equal(this.alias, employee.alias);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        return !(lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null)
                && !(alias != null ? !alias.equals(employee.alias) : employee.alias != null);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(firstName, lastName, alias);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("alias", alias)
                .toString();
    }
}
