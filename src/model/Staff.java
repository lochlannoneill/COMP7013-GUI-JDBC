package model;

import java.io.Serializable;
// import java.util.Random;
import java.time.LocalDate;

public class Staff implements Serializable {
    private String id;
    private Name name;
    private LocalDate startDate;
    private double salary;
    // private Random rand = new Random();

    public Staff(String i, String f, String m, String l, LocalDate d, double s) {
        // this.id = String.valueOf(f.charAt(0)).toLowerCase() + String.valueOf(m.charAt(0)).toLowerCase() + l.toLowerCase() + String.valueOf(this.rand.nextInt(99));
        this.id = i;
        this.name = new Name(f, m, l);
        this.startDate = d;
        this.salary = s;
    }

    public String getId() {
        return this.id;
    }

    public Name getName() {
        return this.name;
    }

    public void setName(String newFirst, String newMiddle, String newLast) {
        this.name = new Name(newFirst, newMiddle, newLast);
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate newStartDate) {
        this.startDate = newStartDate;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double newSalary) {
        this.salary = newSalary;
    }

    public String toString() {
        return this.getId();
    }

}