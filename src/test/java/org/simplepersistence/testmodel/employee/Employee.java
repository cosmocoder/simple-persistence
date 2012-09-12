package org.simplepersistence.testmodel.employee;

import java.util.Date;

public class Employee {
    private final User user;
    private Date hireDate;

    public Employee(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

}
