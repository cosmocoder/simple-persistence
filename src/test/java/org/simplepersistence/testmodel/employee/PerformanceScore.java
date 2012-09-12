package org.simplepersistence.testmodel.employee;

public class PerformanceScore {
    private int year;
    private Employee employee;
    private int score;

    public PerformanceScore(int year, Employee employee) {
        this.year = year;
        this.employee = employee;
    }

    public int getYear() {
        return year;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
