package com.app.services;


import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.dto.ReqResp;
import com.app.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao usersdao;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ReqResp register(ReqResp registrationRequest){
        ReqResp resp = new ReqResp();

        try {
            User ourUser = new User();
            ourUser.setEmail(registrationRequest.getEmail());
            ourUser.setCity(registrationRequest.getCity());
            ourUser.setRole(registrationRequest.getRole());
            ourUser.setFirstName(registrationRequest.getFirstName());
            ourUser.setLastName(registrationRequest.getLastName());
            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            User ourUsersResult = usersdao.save(ourUser);
            if (ourUsersResult.getId()>0) {
                resp.setUser((ourUsersResult));
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }


    public ReqResp login(ReqResp loginRequest){
        ReqResp response = new ReqResp();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
            var user = usersdao.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generatetoken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshTime(refreshToken);
            response.setExpirtaionTime("24Hrs");
            response.setMessage("Successfully Logged In");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }





    public ReqResp refreshToken(ReqResp refreshTokenReqiest){
        ReqResp response = new ReqResp();
        try{
            String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
            User users = usersdao.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
                var jwt = jwtUtils.generatetoken(users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshTime(refreshTokenReqiest.getToken());
                response.setExpirtaionTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }


    public ReqResp getAllUsers() {
        ReqResp reqRes = new ReqResp();

        try {
            List<User> result = usersdao.findAll();
            if (!result.isEmpty()) {
                reqRes.setUserList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }


    public ReqResp getUsersById(Integer id) {
        ReqResp reqRes = new ReqResp();
        try {
            User usersById = usersdao.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setUser(usersById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqResp deleteUser(Integer userId) {
        ReqResp reqRes = new ReqResp();
        try {
            Optional<User> userOptional = usersdao.findById(userId);
            if (userOptional.isPresent()) {
                usersdao.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqResp updateUser(Integer userId, User updatedUser) {
        ReqResp reqRes = new ReqResp();
        try {
            Optional<User> userOptional = usersdao.findById(userId);
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setFirstName(updatedUser.getFirstName());
                existingUser.setLastName(updatedUser.getLastName());
                existingUser.setCity(updatedUser.getCity());
                existingUser.setRole(updatedUser.getRole());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                User savedUser = usersdao.save(existingUser);
                reqRes.setUser(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqResp getMyInfo(String email){
        ReqResp reqRes = new ReqResp();
        try {
            Optional<User> userOptional = usersdao.findByEmail(email);
            if (userOptional.isPresent()) {
                reqRes.setUser(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;

    }
}
