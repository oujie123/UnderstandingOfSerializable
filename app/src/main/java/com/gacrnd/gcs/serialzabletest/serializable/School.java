package com.gacrnd.gcs.serialzabletest.serializable;

import java.io.Serializable;

public class School implements Serializable {

    private String name;
    private String addr;

    public School(){}

    public School(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
