package com.nerdyGeek.smat.sql.repositories;

import com.nerdyGeek.smat.entities.OrganizationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationsRepository extends JpaRepository<OrganizationsEntity, Long> {

    Optional<OrganizationsEntity> findByEmailAndPhoneNumber(String email, String phoneNumber);

}
