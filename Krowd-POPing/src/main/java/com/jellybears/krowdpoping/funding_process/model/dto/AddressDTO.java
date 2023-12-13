package com.jellybears.krowdpoping.funding_process.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddressDTO {
    private String addressTitle;
    private String recipientName;
    private String mergedAddress;
    private String recipientPhoneNumber;
    private Boolean whetherDefaultAddress;
}