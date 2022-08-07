package model;

import java.io.Serializable;

public class Name implements Serializable {
    private String first;
    private String middle;
    private String last;

    public Name(String f, String m, String l) {
        this.first = f;
        this.middle = m;
        this.last = l;
    }

    public String getFirst() {
        return this.first;
    }

    public void setFirst(String newFirst) {
        this.first = newFirst;
    }

    public String getMiddle() {
        return this.middle;
    }

    public void setMiddle(String newMiddle) {
        this.middle = newMiddle;
    }

    public String getLast() {
        return this.last;
    }

    public void setLast(String newLast) {
        this.last = newLast;
    }

    public String toString() {
        return this.first + " " + this.middle + " " + this.last;
    }
}
