package com.example.caseBase.DAO;

import com.example.caseBase.PO.litigant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LitigantRepository extends JpaRepository<litigant, Long> {
    litigant save(litigant li);

    Optional<litigant> getLitigantById(Long id);

    litigant getLitigantByName(String name);
    
}
