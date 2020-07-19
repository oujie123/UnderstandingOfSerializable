package com.gacrnd.gcs.serialzabletest.json;

import com.google.gson.Gson;

/**
 * 简单使用
 */
public class JsonTest {
    static class GsonBean {
        private String name;
        private int score;

        @Override
        public String toString() {
            return "GsonBean{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }

    public static void main(String[] args) {
        Gson gson = new Gson();

        //没有健的值
        System.out.println(gson.toJson("Jack Ou"));
        System.out.println(gson.toJson(123));

        //数组
        int[] values = {1, 2, 1, 2, 3};
        int[] integerValues = new int[]{111, 222, 333};
        System.out.println(gson.toJson(values));
        System.out.println(gson.toJson(integerValues));

        //反序列化
        int i = gson.fromJson("1", Integer.class);
        System.out.println("i : " + i);

        GsonBean gsonBean = new GsonBean();
        gsonBean.name = "Jack Ou";
        gsonBean.score = 99;

        //序列化
        String json = gson.toJson(gsonBean);
        System.out.println("json:"+json);

        //反序列化
        GsonBean gsonBean1 = gson.fromJson(json,GsonBean.class);
        System.out.println(gsonBean1);
    }
}
