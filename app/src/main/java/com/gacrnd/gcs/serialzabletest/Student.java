package com.gacrnd.gcs.serialzabletest;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author Jack_Ou  created on 2020/7/17.
 */
public class Student implements Externalizable {
    

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput objectInput) throws ClassNotFoundException, IOException {

    }
}
