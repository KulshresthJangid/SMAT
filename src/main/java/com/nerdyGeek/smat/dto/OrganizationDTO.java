package com.nerdyGeek.smat.dto;

import com.nerdyGeek.smat.entities.OrganizationsEntity;
import com.nerdyGeek.smat.enums.SubscriptionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationDTO {
    public Long id = null;
    public String name;
    public String email;
    public String phoneNumber;
    public SubscriptionStatus subscriptionStatus = SubscriptionStatus.TRIAL;

    public OrganizationDTO(OrganizationsEntity organizationsEntity) {
        this.id = organizationsEntity.getId();
        this.name = organizationsEntity.getName();
        this.email = organizationsEntity.getEmail();
        this.phoneNumber = organizationsEntity.getPhoneNumber();
        this.subscriptionStatus = organizationsEntity.getSubscriptionStatus();
    }
}
