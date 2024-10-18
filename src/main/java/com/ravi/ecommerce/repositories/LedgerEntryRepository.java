package com.ravi.ecommerce.repositories;

import com.ravi.ecommerce.models.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerEntryRepository extends JpaRepository<LedgerEntry,Integer> {
}
