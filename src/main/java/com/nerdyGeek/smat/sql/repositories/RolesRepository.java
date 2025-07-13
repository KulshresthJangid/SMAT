package com.nerdyGeek.smat.sql.repositories;

import com.nerdyGeek.smat.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
}
