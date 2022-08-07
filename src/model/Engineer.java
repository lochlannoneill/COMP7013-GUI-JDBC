package model;

import java.time.LocalDate;

public class Engineer extends Staff {
    private String title;
    private String email;

    public Engineer(String i, String f, String m, String l, LocalDate d, double s, String e) {
        super(i, f, m, l, d, s);
        this.title = "Engineer";
        this.email = e;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void giveRaise() {
        this.setSalary(Math.round((this.getSalary() * 1.075) * 100) / 100.0);
    }
}
