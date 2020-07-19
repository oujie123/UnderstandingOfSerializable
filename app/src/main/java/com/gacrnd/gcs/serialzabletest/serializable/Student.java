package com.gacrnd.gcs.serialzabletest.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @author Jack_Ou  created on 2020/7/17.
 */
public class Student implements Externalizable {

    private static final long serializableVersionUID = 1L;
    private String name;
    private  int age;
    //不序列号关键字transient，去实现了writeExternal和readExternal的不起作用
     private transient School school;

    public Student() {
    }

    public Student(String name, int age, School school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        System.out.println("writeExternal");
        objectOutput.writeObject(name);
        objectOutput.writeInt(age);
        objectOutput.writeObject(school);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws ClassNotFoundException, IOException {
        System.out.println("writeExternal");
        name = (String) objectInput.readObject();
        age = objectInput.readInt();
        school = (School) objectInput.readObject();
    }

    public static void main(String[] args) throws Exception{
        Student student = new Student("Jack Ou", 18,new School("peihua","chengdu"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(student);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Student student1 = (Student) objectInputStream.readObject();
        System.out.println(student1);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school.toString() +
                '}';
    }
}
