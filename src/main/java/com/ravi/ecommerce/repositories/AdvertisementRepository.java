package com.ravi.ecommerce.repositories;

import com.ravi.ecommerce.models.Advertisement;
import com.ravi.ecommerce.models.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
    List<Advertisement> findAllByPreferenceIdIn(List<Integer> preference);
}
