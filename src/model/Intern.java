package model;

import java.time.LocalDate;

public class Intern extends Staff {
    private String title;
    private String university;
    private String mentorId;

    public Intern(String i, String f, String m, String l, LocalDate d, double s, String u, String mi) {
        super(i, f, m, l, d, s);
        this.title = "Intern";
        this.university = u;
        this.mentorId = mi;
    }

    public String getUniversity() {
        return this.university;
    }

    public void setUniversity(String newUniversity) {
        this.university = newUniversity;
    }

    public String getMentor() {
        return this.mentorId;
    }

    public void setMentor(String newMentor) {
        this.mentorId = newMentor;
    }

    public String getTitle() {
        return this.title;
    }

    public void giveRaise() {
        this.setSalary(Math.round((this.getSalary() * 1.02) * 100) / 100.0);
    }

    // public static void addIntern(String text, String text2, String text3, LocalDate value, double parseDouble,
    //         String text4, String id) {
    // }
}
