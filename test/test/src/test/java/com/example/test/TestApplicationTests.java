package com.example.test;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import com.example.test.service.UserFetchService;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TestApplicationTests {

	@Autowired
        private UserFetchService userFetchService;
        
        @MockBean
        private UserRepository userRepository;
        
        @Test
        public void fetchUserByIdTest(){
            Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new User(1L,1,"title test","body test")));
            Assertions.assertEquals(1, userFetchService.getById(Mockito.anyLong()).getId());
        }

        @Test
        public void fetchUserByUserIdTest(){
            Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(Stream.of(new User(1L,1,"title test","body test")).collect(Collectors.toList()));   
            Assertions.assertEquals(1, userFetchService.getByUserId(Mockito.anyInt()).get(0).getUserId());
        }

        @Test
        public void fetchUserByTitleTest(){
            Mockito.when(userRepository.findByTitle(Mockito.anyString())).thenReturn(Stream.of(new User(1L,1,"title test","body test")).collect(Collectors.toList()));
            Assertions.assertEquals(1, userFetchService.getByTitle(Mockito.anyString()).get(0).getId());
        }        
}
