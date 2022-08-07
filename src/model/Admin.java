package model;

import java.time.LocalDate;

public class Admin extends Staff {
    private String title;
    private int phone;

    public Admin(String i, String f, String m, String l, LocalDate d, double s, int p) {
        super(i, f, m, l, d, s);
        this.title = "Admin";
        this.phone = p;
    }
    
    public int getPhone() {
        return this.phone;
    }
    
    public void setPhone(int newPhone) {
        this.phone = newPhone;
    }

    public String getTitle() {
        return this.title;
    }

    public void giveRaise() {
        this.setSalary(Math.round((this.getSalary() * 1.05) * 100) / 100.0);
    }

}
