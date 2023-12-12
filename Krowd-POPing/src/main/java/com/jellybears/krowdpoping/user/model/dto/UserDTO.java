package com.jellybears.krowdpoping.user.model.dto;




import lombok.*;


import java.sql.Date;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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



}
