package com.authentication.controller;

import com.authentication.entity.UserEntity;
import com.authentication.service.QrService;
import com.authentication.service.TotpService;
import com.authentication.service.UserService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final TotpService totpService;
    private final QrService qrService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password) throws IOException, WriterException {
        UserEntity userEntity = userService.register(username, password);

        String qr = qrService.generateQrCode(username, userEntity.getSecret());

        return ResponseEntity.ok(Map.of(
                "qrCode", qr,
                "secret", userEntity.getSecret()
        ));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String username,
                                    @RequestParam String code) {

        UserEntity user = userService.findByUsername(username).orElseThrow();

        System.out.println("Secret: " + user.getSecret());
        System.out.println("Code from user: " + code);

        boolean isValid = totpService.verifyCode(user.getSecret(), code);

        System.out.println("Is valid: " + isValid);

        return ResponseEntity.ok(Map.of("valid", isValid));
    }

}
