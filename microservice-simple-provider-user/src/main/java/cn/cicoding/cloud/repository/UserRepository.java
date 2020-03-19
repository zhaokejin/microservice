package cn.cicoding.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.cicoding.cloud.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
