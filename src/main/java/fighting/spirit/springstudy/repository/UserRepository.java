package fighting.spirit.springstudy.repository;

import fighting.spirit.springstudy.entity.SiteUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<SiteUserEntity, Long> {
    Optional<SiteUserEntity> findByusername(String username);
}
