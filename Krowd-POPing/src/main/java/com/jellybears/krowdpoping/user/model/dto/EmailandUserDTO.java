package com.jellybears.krowdpoping.user.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmailandUserDTO {
    private UserDTO userDTO;
    private EmailDTO emailDTO;
}
