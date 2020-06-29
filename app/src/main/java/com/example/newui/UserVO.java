package com.example.newui;



public class UserVO {
    private String id;
    private String password;
    private String name;
    private String phone;
    private int birth;
    private Boolean result;


    public UserVO() {

    }
    public UserVO(String id, String password) {
        this.id = id;
        this.password = password;
    }
    public UserVO(String id, String password, String name, String phone, int birth) {
        this.id = id;
        this.password = password;
        this.birth = birth;
        this.name = name;
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getBirth() {
        return birth;
    }
    public void setBirth(int birth) {
        this.birth = birth;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getResult() {
        return result;
    }
    public void setResult(Boolean phone) {
        this.result = result;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", phone=" + phone + ", birth="
                + birth + "]";
    }
}