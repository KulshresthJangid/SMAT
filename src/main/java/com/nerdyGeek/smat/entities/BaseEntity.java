package com.nerdyGeek.smat.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BaseEntity {
    @Column(name = "is_enabled")
    public boolean isEnabled = true;

    @Column(name = "created_at")
    @CreationTimestamp
    public Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    public Date updatedAt;
}
