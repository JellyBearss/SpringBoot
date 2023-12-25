package com.jellybears.krowdpoping.category.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDTO {

    private int categoryCode;
    private String categoryTitle;
    private int upperCategoryCode;
    private int subCategoryCode; // 하위 카테고리

}
