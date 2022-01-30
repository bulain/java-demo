package com.bulain.netty.push.pojo;

import java.io.Serializable;

public class Printer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private boolean active = true;

    public static Printer createPrinter(String name) {
        Printer printer = new Printer();
        printer.setName(name);
        return printer;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Printer) {
            return name.equals(((Printer) obj).getName());
        }
        return super.equals(obj);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

}
