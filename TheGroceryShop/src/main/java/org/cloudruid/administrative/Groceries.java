package org.cloudruid.administrative;

import java.util.Objects;

public class Groceries {


    private String name;

    public Groceries(String name) {

        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroceriesDouble{" +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groceries groceries = (Groceries) o;
        return Objects.equals(name, groceries.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}