package com.gacrnd.gcs.serialzabletest.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Course implements Serializable {

    private static final long serialVersionUID = 667279791530738499L;
    private String name;
    private float score;

    public Course(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public static void main(String... args) throws Exception {

        Course course = new Course("英语", 12f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(course);
        //如果使用unshared方法两个序列化的对象就不一样了,重新写入一个内容的时候，需要用unshared否者是一个对像，会写入失败。
        course.setScore(78f);
        //1. 结果12 78
        //oos.writeUnshared(course);
        //2.结果12 78
        //oos.reset();
        //oos.writeObject(course);
        // 结果12 12
        oos.writeObject(course);
        byte[] bs = out.toByteArray();
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bs));
        Course course1 = (Course) ois.readObject();
         Course course2 = (Course) ois.readObject();
        System.out.println("course1: " + course1.toString());
        System.out.println("course2: " + course2.toString());
        System.out.println("course1 == course2 ?" + course2.equals(course1));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
