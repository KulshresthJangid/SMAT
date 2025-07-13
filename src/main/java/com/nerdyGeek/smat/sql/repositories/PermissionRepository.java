package com.nerdyGeek.smat.sql.repositories;

import com.nerdyGeek.smat.entities.PermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionsEntity, Long> {
}
