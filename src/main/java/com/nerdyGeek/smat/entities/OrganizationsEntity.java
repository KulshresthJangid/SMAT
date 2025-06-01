package com.nerdyGeek.smat.entities;

import com.nerdyGeek.smat.enums.SubscriptionStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "organizations")
@Entity
public class OrganizationsEntity extends BaseEntity {
    public long id;
    public String name;
    public String email;
    public SubscriptionStatus status;

    @OneToMany(mappedBy = "organizations", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<UserEntity> users;
}
