package cn.cicoding.repository;

import cn.cicoding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhaokj
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
