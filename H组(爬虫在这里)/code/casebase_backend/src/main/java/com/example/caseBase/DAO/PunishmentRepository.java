package com.example.caseBase.DAO;

import com.example.caseBase.PO.punishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PunishmentRepository extends JpaRepository<punishment,Long> , JpaSpecificationExecutor<punishment> {
    punishment save(punishment punish);
    void getPunishmentById(Long id);

}
