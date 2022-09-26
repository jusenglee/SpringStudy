package fighting.spirit.springstudy.service;


import fighting.spirit.springstudy.DataNotFoundException;
import fighting.spirit.springstudy.entity.SiteUserEntity;
import fighting.spirit.springstudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUserEntity create(String username, String email, String password){
        SiteUserEntity userEntity = new SiteUserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(userEntity);
        return userEntity;
    }
    public SiteUserEntity getUser(String username){
        Optional<SiteUserEntity> siteUserEntity = this.userRepository.findByusername(username);
        if (siteUserEntity.isPresent()){
            return siteUserEntity.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
}
