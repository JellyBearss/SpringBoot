package com.jellybears.krowdpoping.project.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailCategoryDTO {

    private int categoryCode;
    private String categoryTitle;
    private int upperCategoryCode;
}
