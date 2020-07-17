package com.gacrnd.gcs.understandingofserializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @author Jack_Ou  created on 2020/7/16.
 */
public class Student implements Externalizable {

    private static final long serialVersionUID = -1560733723605286440L;
    private String name;
    private int score;
    private School school;
    private Family family;

    public Student(){}

    public Student(String name, int score, School school,Family family) {
        this.name = name;
        this.score = score;
        this.school = school;
        this.family = family;
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        System.out.println("writeExternal");
        objectOutput.writeObject(name);
        objectOutput.writeInt(score);
        objectOutput.writeObject(school);
        objectOutput.writeObject(family);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws ClassNotFoundException, IOException {
        System.out.println("readExternal");
        name = (String) objectInput.readObject();
        score = objectInput.readInt();
        school = (School) objectInput.readObject();
        family = (Family) objectInput.readObject();
    }

    public static void main(String[] args) throws Exception {
        Student student = new Student("Jack_Ou", 95,new School(222),new Family("chongqing"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(student);
        student.setScore(100);
        byte[] bs = byteArrayOutputStream.toByteArray();
        for (int i = 0; i < bs.length; i++) {
            System.out.print(bs[i]);
        }
        System.out.println();
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bs));
        Student student1 = (Student) objectInputStream.readObject();
        System.out.println(student1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", school=" + school.toString() +
                ", family=" + family.toString() +
                '}';
    }
}
