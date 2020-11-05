package com.thoughtworks.capacity.gtb.mvc;

import com.thoughtworks.capacity.gtb.mvc.Exception.UsernameExistException;
import com.thoughtworks.capacity.gtb.mvc.Exception.UsernameNotExistException;
import com.thoughtworks.capacity.gtb.mvc.Exception.WrongUsernameOrPasswordException;
import com.thoughtworks.capacity.gtb.mvc.domain.user;
import com.thoughtworks.capacity.gtb.mvc.domain.userResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private Map<Integer, user> userMap = new HashMap<>();

    private UserService() {
        userMap.put(1, new user("daiyi", "abcde", "1@th.com"));
    }

    public void registerUser(user user) throws UsernameExistException {
        for(Map.Entry<Integer, user> entry : userMap.entrySet()) {
            if (user.getUsername().equals(entry.getValue().getUsername())) {
                throw new UsernameExistException("Username already exist, please change it.");
            }
        }
        userMap.put(userMap.size() + 1, user);
    }

    public userResponse logUser(String username, String password) throws WrongUsernameOrPasswordException, UsernameNotExistException {
        for(Map.Entry<Integer, user> entry : userMap.entrySet()) {
            if(username.equals(entry.getValue().getUsername())) {
                if(password.equals(entry.getValue().getPassword())) {
                    return userResponse.builder()
                            .id(entry.getKey())
                            .username(username)
                            .password(password)
                            .email(entry.getValue().getEmail())
                            .build();
                } else throw new WrongUsernameOrPasswordException("Wrong username or password");
            }
        } throw new UsernameNotExistException("Username not exist");
    }
}
