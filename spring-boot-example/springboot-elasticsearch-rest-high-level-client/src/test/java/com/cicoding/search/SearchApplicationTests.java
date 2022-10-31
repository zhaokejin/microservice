package com.cicoding.search;

import com.alibaba.fastjson.JSONObject;
import com.cicoding.search.dao.NbaPlayerDao;
import com.cicoding.search.model.NbaPlayer;
import com.cicoding.search.service.NbaPlayerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class SearchApplicationTests {
    @Autowired(required = false)
    private NbaPlayerDao nbaPlayerDao;
    @Autowired(required = false)
    private NbaPlayerService nbaPlayerService;

    @Test
    public void selectAll() {
        log.info(JSONObject.toJSON(nbaPlayerDao.selectAll()).toString());
    }

    @Test
    public void addPlayer() throws IOException {
        NbaPlayer nbaPlayer = new NbaPlayer();
        nbaPlayer.setId(999);
        nbaPlayer.setDisplayName("丁真");
        nbaPlayerService.addPlayer(nbaPlayer,"999");
    }
    @Test
    public void getPlayer() throws IOException{
        Map<String,Object> player=nbaPlayerService.getPlayer("999");
        log.info(JSONObject.toJSON(player).toString()); }
    @Test
    public void updatePlayer()throws IOException{
        NbaPlayer nbaPlayer = new NbaPlayer();
        nbaPlayer.setId(999);
        nbaPlayer.setDisplayName("丁真888");
        nbaPlayerService.updatePlayer(nbaPlayer,"999");
    }
    @Test
    public void deletePlayer()throws  IOException{
        nbaPlayerService.deletePlayer("999");
        nbaPlayerService.deleteAllPlayer();
    }
}
