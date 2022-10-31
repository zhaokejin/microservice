package com.cicoding.search.dao;

import com.cicoding.search.model.NbaPlayer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author www.cicoding.cn
 */
@Mapper
public interface NbaPlayerDao {
    /**
     * 查询所有篮球
     * @return
     */
    @Select("select * from nba_player")
    List<NbaPlayer> selectAll();
}
