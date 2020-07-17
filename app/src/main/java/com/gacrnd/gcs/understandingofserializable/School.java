package com.gacrnd.gcs.understandingofserializable;

import java.io.Serializable;

/**
 * @author Jack_Ou  created on 2020/7/16.
 */
public class School implements Serializable{

    private static final long serialVersionUID = 2043803466266290060L;
    private int i = 1;
    private transient Building building;
    public static int j = 11;

    //私有构造方法
//    private School(){
//
//    }

    /**
     * 没有实现序列号，看反序列化之后是什么东西,是空
     *
     * 如果实现了序列化，反序列化之后就有值了
     */

    public School(int i, Building building,int ji){
        this.i = i;
        this.building = building;
        j = ji;
    }

    public School(int i, Building building){
        this.i = i;
        this.building = building;
    }

    public School(int i){
        this.i = i;
    }

    public School(){}

    public static void main(String[] args) throws Exception {
        School school = new School(2222,new Building("56456"),123);
        byte[] bs = SerializeableUtils.serialize(school);

        school = SerializeableUtils.deserialize(bs);
        System.out.println(school.toString());
    }

    @Override
    public String toString() {
        return "School{" +
                "i=" + i +
                ", building=" + building +
                "j = " + j +
                '}';
    }

    static class Building implements Serializable{
        private String buildingNum;

        public Building(String buildingNum){
            this.buildingNum = buildingNum;
        }

        @Override
        public String toString() {
            return "Building{" +
                    "buildingNum='" + buildingNum + '\'' +
                    '}';
        }
    }
}
