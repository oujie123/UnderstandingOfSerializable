package com.gacrnd.gcs.serialzabletest.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * 演示注解
 */
public class JsonInjectTest {

    /**
     * 注解让变量在序列化的时候变成有意义的key
     */
    static class JsonBean {
        @SerializedName("name")
        private String a;

        @SerializedName(value = "School", alternate = {"park", "seaside"})
        private String b;

        private String c;

        public JsonBean(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    /**
     * Expose 代表被注解的字段是否参与序列化/反序列化，默认都为true
     * new Gson是不起作用的，需要new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
     */
    static class User1 {
        @Expose
        private String firstName;

        @Expose(serialize = true, deserialize = false)
        private String lastName;

        @Expose(serialize = false, deserialize = true)
        private String eMail;

        @Expose(serialize = false, deserialize = false)
        private String password;

        public User1(String firstName, String lastName, String eMail, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.eMail = eMail;
            this.password = password;
        }

        @Override
        public String toString() {
            return "User1{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", eMail='" + eMail + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    /**
     * Since版本控制，至少那个版本参加序列化
     */
    static class User2{
        private String firstName;
        private String lastName;

        @Since(1.1)
        private String eMail;

        @Since(1.2)
        private String password;

        public User2(String firstName, String lastName, String eMail, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.eMail = eMail;
            this.password = password;
        }

        @Override
        public String toString() {
            return "User2{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", eMail='" + eMail + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    /**
     * Until版本控制，那个版本以下的参加序列化
     */
    static class User3{
        private String firstName;
        private String lastName;

        @Until(2.0)
        private String eMail;

        @Until(2.1)
        private String password;
    }

    @JsonAdapter(UserJsonAdapter.class)
    static class User4{
        private String name;
        private String school;

        public User4(String name, String school) {
            this.name = name;
            this.school = school;
        }

        @Override
        public String toString() {
            return "User4{" +
                    "name='" + name + '\'' +
                    ", school='" + school + '\'' +
                    '}';
        }
    }

    static class UserJsonAdapter extends TypeAdapter<User4> {

        @Override
        public void write(JsonWriter out, User4 value) throws IOException {
            out.beginObject();
            out.name("name");
            out.value(value.name);
            out.name("school");
            out.value(value.school);
            out.endObject();
        }

        @Override
        public User4 read(JsonReader in) throws IOException {
            in.beginObject();
            in.nextName();
            String name = in.nextString();
            in.nextName();
            String school = in.nextString();
            in.endObject();
            return new User4(name,school);
        }
    }

    public static void main(String[] args) {
        JsonBean jsonBean = new JsonBean("Jack Ou", "chongqing university", "do homework");
        //Gson gson = new Gson();
        //建立一个带版本的Gson解析器
        Gson gson = new GsonBuilder()
                .setVersion(1.1)
                //设置格式
                .setPrettyPrinting()
                .create();
        System.out.println(gson.toJson(jsonBean));

        //测试Expose
        User1 user1 = new User1("Jack","Ou","oujie_forwork@foxmail.com","123");
        //new Gson是不起作用的
        Gson gson1 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String s1 = gson1.toJson(user1);
        System.out.println(s1);
        User1 user11 = gson1.fromJson(s1,User1.class);
        System.out.println(user11);

        //测试since
        User2 user2 = new User2("Jack","Ou","oujie_forwork@foxmail.com","123");
        String s2 = gson.toJson(user2);
        System.out.println(s2);
        User2 user21 = gson.fromJson(s1,User2.class);
        System.out.println(user21);

        //Json解析适配器
        User4 user4 = new User4("Jack Ou","chongqing university");
        String string = gson.toJson(user4);
        System.out.println(string);
        User4 user41 = gson.fromJson(string,User4.class);
        System.out.println(user41);
    }
}
