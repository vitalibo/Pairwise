package ua.edu.lp.asu.pairwise;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Parameter<T> extends ArrayList<T> {

    @Getter
    @Setter
    private String name;

    public Parameter() {
        super();
    }

    public Parameter(String name) {
        this.name = name;
    }

    public Parameter(String name, Collection<T> c) {
        this(name);
        this.addAll(c);
    }

    public Parameter(String name, T... values) {
        this(name, Arrays.asList(values));
    }

    @Override
    public String toString() {
        return name + ": " + super.toString();
    }

}
