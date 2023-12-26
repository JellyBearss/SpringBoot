package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PolicyFileDTO {
    private int fileCode;
    private int fileSeq;
    private String fileName;
    private String saveName;
    private String filePath;
}
