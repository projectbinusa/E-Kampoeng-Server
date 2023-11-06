//package com.data.penduduk.controler;
//
//import com.data.penduduk.model.User;
//import com.data.penduduk.response.CustomResponse;
//import com.data.penduduk.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class UserControler {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/Rw/register")
//    public ResponseEntity<String> register(@RequestBody User user) {
//        if (userService.register(user)) {
//            return ResponseEntity.ok("Pendaftaran berhasil.");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Gagal mendaftar.");
//        }
//    }
//
//    @PostMapping("/admin/login")
//    public ResponseEntity<CustomResponse> loginAdmin(@RequestBody User loginRequest) {
//        CustomResponse response = userService.loginAdmin(loginRequest);
//        return ResponseEntity.ok(response);
//    }
//}
