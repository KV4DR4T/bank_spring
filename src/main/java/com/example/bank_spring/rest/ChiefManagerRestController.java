package com.example.bank_spring.rest;

import com.example.bank_spring.dto.UserService;
import com.example.bank_spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chief-manager")
public class ChiefManagerRestController {
    private final UserService userService;

    @Autowired
    public ChiefManagerRestController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/showAllUsers")
    public ResponseEntity<?> showAllUsers(@RequestHeader("Authorization") String token) throws Exception {
        return userService.showAllUsers(token);
    }
    @GetMapping(value ="/show/{userId}")
    public User showById(@RequestHeader("Authorization") String token,
                         @PathVariable Long userId) throws Exception {
        return userService.showUserById(userId,token);
    }
    @DeleteMapping(value ="/delete/{userId}")
    public void deleteById(@RequestHeader("Authorization") String token,
                           @PathVariable Long userId) throws Exception {
        userService.deleteById(userId,token);
    }
    @PostMapping(value ="/update/{userId}")
    public User updateById(@RequestHeader("Authorization") String token,
                           @PathVariable Long userId,
                           @RequestBody RegisterRequestDto requestDto) throws Exception {
        return userService.updateById(userId,token,requestDto);
    }

    @PostMapping(value = "/createManager")
    public void createManager(@RequestHeader("Authorization") String token,
                              @RequestBody RegisterRequestDto requestDto) throws Exception {
        userService.createManager(token,requestDto);
    }
}
