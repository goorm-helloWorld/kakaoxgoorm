package com.example.mvcproject.domain;

public class User {
    private int id;
    private String name = "관리자";
    private String password;
    private String email;
    private Integer age;

    private static final String PRE_EMIAL = "test@gmail.com";
    private static final String PRE_PW = "qwer1234";

    public User() {
    }

    public static boolean auth(String email, String password) {
        return PRE_EMIAL.equals(email) && PRE_PW.equals(password);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "id : " + id + ", name : " + name + ", password : " + password + ", email : " + email + ", age : " + age;
    }
}
