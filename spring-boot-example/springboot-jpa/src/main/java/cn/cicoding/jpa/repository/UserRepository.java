package cn.cicoding.jpa.repository;

import cn.cicoding.jpa.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRespository
 *
 * @author cn.cicoding
 * @version v1.0
 * @since 2020/2/27 23:47
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
