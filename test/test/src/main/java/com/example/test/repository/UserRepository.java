/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.repository;

import com.example.test.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author twinkal
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByUserId(Integer userId);

    public List<User> findByTitle(String title);


}
