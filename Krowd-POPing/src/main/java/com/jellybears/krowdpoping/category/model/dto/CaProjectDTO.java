package com.jellybears.krowdpoping.category.model.dto;

import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaProjectDTO {

    private int projectCode; //프로젝트 코드
    private CaUserDTO creatorName; //창작자명
    private String projectTitle; //프로젝트 제목
    private String projectDetail; //프로젝트 소개
    private List<CaProjectImageDTO> image;


}
