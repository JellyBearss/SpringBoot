package com.jellybears.krowdpoping.projectRegister.section01.model.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProjectDTO {

    private int projectCode;

    private String title;

    private int categoryCode;

    private String content;

    private String thumbNail;

//    private List<TagDTO> tag;








}
