package com.gacrnd.gcs.serialzabletest.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 重新的方法被调用顺序
 * writeReplace
 * writeObject
 * readObject
 * readResolve
 */
public class Course1 implements Serializable {
    private static final long serialVersionUID = 667279791530738499L;
    private String name;
    private float score;

    public Course1(String name, float score) {
        this.name = name;
        this.score = score;
    }

    private void readObject(ObjectInputStream inputStream) throws Exception {
        System.out.println("readObject");
        inputStream.defaultReadObject();
        name = (String) inputStream.readObject();
        score = inputStream.readFloat();
    }

    private void writeObject(ObjectOutputStream outputStream) throws Exception {
        System.out.println("writeObject");
        outputStream.defaultWriteObject();
        outputStream.writeObject(name);
        outputStream.writeFloat(score);
    }

    private Object readResolve() {
        System.out.println("readResolve");
        return new Course1("Jack Ou", 50);
    }

    private Object writeReplace() {
        System.out.println("writeReplace");
        return new Course1("Jack Ou" + "Handsome",80);
    }

    public static void main(String... args) throws Exception { //TODO:
        Course1 course = new Course1("英语", 12f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(course);
        byte[] bs = out.toByteArray();
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bs));
        Course1 course1 = (Course1) ois.readObject();
        System.out.println("course1: " + course1);
    }

    @Override
    public String toString() {
        return "Course1{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
