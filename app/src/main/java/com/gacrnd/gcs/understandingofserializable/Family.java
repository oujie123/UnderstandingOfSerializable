package com.gacrnd.gcs.understandingofserializable;

/**
 * @author Jack_Ou  created on 2020/7/17.
 */
public class Family extends School{

    private String addr;

    public Family(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Family{" +
                "addr='" + addr + '\'' +
                '}';
    }
}
