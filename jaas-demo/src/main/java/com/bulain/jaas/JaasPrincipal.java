package com.bulain.jaas;

import java.io.Serializable;
import java.security.Principal;

public class JaasPrincipal implements Principal, Serializable {
    private static final long serialVersionUID = -3839199868998121940L;

    private String name;

    public JaasPrincipal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object o) {
        if (o instanceof JaasPrincipal) {
            JaasPrincipal that = (JaasPrincipal) o;
            return (this.getName().equals(that.getName()));
        }

        return false;
    }

    public int hashCode() {
        return name.hashCode();
    }

}
