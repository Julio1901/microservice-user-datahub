package com.julio.user.services;

import com.julio.user.models.UserModel;
import com.julio.user.producers.UserProducer;
import com.julio.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel){
      userModel = userRepository.save(userModel);
      userProducer.publishMessageEmail(userModel);
      return userModel;
    }



}
