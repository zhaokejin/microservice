package cn.cicoding.mybatisplus.mapper;

import cn.cicoding.mybatisplus.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper
 *
 * @author cicoding
 * @version v1.0
 * @since 2020/3/10 1:30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<User> selectPageCustom(IPage<User> page, String userName);

}
