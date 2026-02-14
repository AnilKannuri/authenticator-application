package com.authentication.serviceImpl;

import com.authentication.entity.UserEntity;
import com.authentication.repository.UserRepository;
import com.authentication.service.TotpService;
import com.authentication.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final TotpService totpService;

    @Override
    public UserEntity register(String username, String password) {

        String secret = totpService.generateSecret();

        UserEntity userEntity = UserEntity.builder()
                .userName(username)
                .password(password)
                .secret(secret)
                .twoFactorEnabled(true)
                .build();
        log.info("records are saved");
        return repository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return repository.findByUserName(username);
    }
}
