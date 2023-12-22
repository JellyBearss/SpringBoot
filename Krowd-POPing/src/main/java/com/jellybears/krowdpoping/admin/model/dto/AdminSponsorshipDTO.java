package com.jellybears.krowdpoping.admin.model.dto;

import com.jellybears.krowdpoping.projectRegister.section01.model.dto.ProjectDTO;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminSponsorshipDTO {  /* support_info */
    private int supportCode;
    private String status;
    private Date supportDate;
    private int goodsCode;
    private int projectCode;
    private int user_code;
    private AdminGoodsDTO adminGoodsDTO;
    private AdminFundingDTO adminFundingDTO;
    private UserDTO userDTO;
}
