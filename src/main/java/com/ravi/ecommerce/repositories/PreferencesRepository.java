package com.ravi.ecommerce.repositories;


import com.ravi.ecommerce.models.Preference;
import com.ravi.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PreferencesRepository extends JpaRepository<Preference, Integer> {
}