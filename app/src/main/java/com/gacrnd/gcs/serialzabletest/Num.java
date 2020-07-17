package com.gacrnd.gcs.serialzabletest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *  枚举序列化
 */
public enum Num {
    ONE,TWO,THREE;

    private static final long serializableVersionUID = 1111111L;

    public void printValues() {
        System.out.println(ONE + " ONE.ordinal " + ONE.ordinal());
        System.out.println(TWO + " TWO.ordinal " + TWO.ordinal());
        System.out.println(THREE + " THREE.ordinal " + THREE.ordinal());
    }

    public static void testSerializable() throws Exception {
        File file = new File("p.dat");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(Num.ONE);
        oos.close();
        Num.ONE.printValues();

        System.out.println("=========反序列化后=======");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Num s1 = (Num) ois.readObject();
        s1.printValues();
        ois.close();
    }
    public static void main(String... args) throws Exception {
        testSerializable();
    }

}
