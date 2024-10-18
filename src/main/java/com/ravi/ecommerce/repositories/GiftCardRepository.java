package com.ravi.ecommerce.repositories;

import com.ravi.ecommerce.models.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCardRepository extends JpaRepository<GiftCard,Integer> {
}
