package com.nerdyGeek.smat.entities;

import com.nerdyGeek.smat.dto.OrganizationDTO;
import com.nerdyGeek.smat.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "organizations")
@Entity
public class OrganizationsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String email;
    public String phoneNumber;
    @Enumerated(EnumType.ORDINAL)
    public SubscriptionStatus subscriptionStatus;

    public OrganizationsEntity(OrganizationDTO organizationDTO) {
        this.id = organizationDTO.getId();
        this.name = organizationDTO.getName();
        this.email = organizationDTO.getEmail();
        this.phoneNumber = organizationDTO.getPhoneNumber();
        this.subscriptionStatus = organizationDTO.getSubscriptionStatus();
    }
}
