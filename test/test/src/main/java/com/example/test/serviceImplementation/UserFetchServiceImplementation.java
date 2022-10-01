/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.serviceImplementation;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import com.example.test.service.UserFetchService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author twinkal
 */
@Service
@Transactional
public class UserFetchServiceImplementation implements UserFetchService{
    
    private static final Logger logger = LoggerFactory.getLogger(UserFetchServiceImplementation.class);
    

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void storeUserData() {
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(BASE_URL, User[].class);
        User[] userArray = responseEntity.getBody();
        List<User> userData = new ArrayList<>();
        userData.addAll(Arrays.asList(userArray));
        
        userRepository.saveAll(userData);
    }

    @Override
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id); //Optional Class Java 8 feature
        if(user.isEmpty()){
            logger.error("User not found");
        }
        return user.get();
    }

    @Override
    public List<User> getByUserId(Integer userId) {
        List<User> userList = userRepository.findByUserId(userId);
        return userList;
    }

    @Override
    public List<User> getByTitle(String title) {
        List<User> user = userRepository.findByTitle(title);
        return user;
    }
}
