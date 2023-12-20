package com.jellybears.krowdpoping.projectRegister.section01.model.dto;

import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
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

    private CategoryDTO categoryDTO;

    private String content;

//    private List<ThumbnailDTO> thumbNails;

//    private List<TagDTO> tag;




}
