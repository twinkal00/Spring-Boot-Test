/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.service;

import com.example.test.model.User;
import java.util.List;

/**
 *
 * @author twinkal
 */
public interface UserFetchService {

    public void storeUserData();
    public User getById(Long id);
    public List<User> getByUserId(Integer userId);
    public List<User> getByTitle(String title);
}
