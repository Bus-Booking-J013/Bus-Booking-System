package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.app.dto.ReqResp;
import com.app.entity.User;
import com.app.services.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService usersService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqResp> regeister(@RequestBody ReqResp reg){
        return ResponseEntity.ok(usersService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqResp> login(@RequestBody ReqResp req){
        return ResponseEntity.ok(usersService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqResp> refreshToken(@RequestBody ReqResp req){
        return ResponseEntity.ok(usersService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqResp> getAllUsers(){
        return ResponseEntity.ok(usersService.getAllUsers());

    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqResp> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(usersService.getUsersById(userId));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqResp> updateUser(@PathVariable Integer userId, @RequestBody User reqres){
        return ResponseEntity.ok(usersService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqResp> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqResp response = usersService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqResp> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(usersService.deleteUser(userId));
    }


}

