package com.jellybears.krowdpoping.user.model.dto;




import lombok.*;


import java.sql.Date;




@AllArgsConstructor
@NoArgsConstructor

public class UserDTO  {
    private int user_code;
    private String userId;
    private String password;
    private String name;
    private String nickname;
    private Date birthday;
    private String email;
    private String phone_number;
    private String user_status;
    private int email_code;
    private Object addressDTO;

    public int getUser_code() {
        return user_code;
    }

    public void setUser_code(int user_code) {
        this.user_code = user_code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public int getEmail_code() {
        return email_code;
    }

    public void setEmail_code(int email_code) {
        this.email_code = email_code;
    }

    public Object getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(Object addressDTO) {
        this.addressDTO = addressDTO;
    }
}
