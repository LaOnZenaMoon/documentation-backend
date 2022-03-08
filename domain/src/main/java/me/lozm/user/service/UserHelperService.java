package me.lozm.user.service;

import lombok.RequiredArgsConstructor;
import me.lozm.user.entity.User;
import me.lozm.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserHelperService {

    private final UserRepository userRepository;


    public Optional<User> findUser(Long userId) {
        return userRepository.findById(userId);
    }

    public User getUser(Long userId) {
        return findUser(userId).orElseThrow(() -> new IllegalArgumentException(format("사용자 정보가 존재하지 않습니다. 사용자 ID: %s", userId)));
    }

    public Optional<User> findUser(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUser(String email) {
        return findUser(email).orElseThrow(() -> new IllegalArgumentException(format("사용자 정보가 존재하지 않습니다. 사용자 이메일: %s", email)));
    }

}
