package com.nerdyGeek.smat.entities;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BaseEntity {
    @Column(name = "is_enabled")
    public boolean isEnabled;

    @Column(name = "created_at")
    public Date createdAt;

    @Column(name = "updated_at")
    public Date updatedAt;
}
