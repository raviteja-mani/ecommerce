package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.UserNotFoundException;
import com.ravi.ecommerce.models.Advertisement;
import com.ravi.ecommerce.models.Preference;
import com.ravi.ecommerce.models.User;
import com.ravi.ecommerce.repositories.AdvertisementRepository;
import com.ravi.ecommerce.repositories.PreferencesRepository;
import com.ravi.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {
    private UserRepository userRepository;
    private AdvertisementRepository advertisementRepository;
    private PreferencesRepository preferencesRepository;
   @Autowired
    public AdsServiceImpl(UserRepository userRepository, AdvertisementRepository advertisementRepository) {
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }
    @Override
    public Advertisement getAdvertisementForUser(int userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
//        userRepository.findAllByPreferencesIdIn(user.getPreferences().stream().map(Preference::getId).toList());
        List<Preference> preferences = user.getPreferences();
        List<Advertisement> advertisements = advertisementRepository.findAllByPreferenceIdIn(preferences.stream().map(Preference::getId).toList());
        if(!advertisements.isEmpty()){
            return advertisements.getFirst();
        }
        else return advertisementRepository.findAll().getFirst();
    }
}
