package com.e_kampoeng.controller;

import com.e_kampoeng.dto.UserDTO;
import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.model.UserModel;
import com.e_kampoeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/e-kampoeng/api")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register-admin", method = RequestMethod.POST)
    public CommonResponse<UserModel> registerAdmin(@RequestBody UserDTO user) throws Exception {
        return ResponseHelper.ok(userService.saveWargaRoleAdmin(user));
    }
}
