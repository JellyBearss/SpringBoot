package com.jellybears.krowdpoping.user.model.dto;




import lombok.*;


import java.sql.Date;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO  {
    private Long user_code;
    private String id;
    private String password;
    private String name;
    private String nickname;
    private Date birthday;
    private String email;
    private String phone_number;
    private String user_status;
 //이거 아님


}
