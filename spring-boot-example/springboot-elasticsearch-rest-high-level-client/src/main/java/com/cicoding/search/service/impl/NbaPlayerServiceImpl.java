package com.cicoding.search.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cicoding.search.dao.NbaPlayerDao;
import com.cicoding.search.model.NbaPlayer;
import com.cicoding.search.service.NbaPlayerService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author www.cicoding.cn
 */
@Slf4j
@Service
public class NbaPlayerServiceImpl implements NbaPlayerService {
    @Resource
    private RestHighLevelClient client;
    @Resource
    private NbaPlayerDao nbaPlayerDao;
    private static final String NBA_INDEX = "nba_latest";

    /**
     * 添加
     *
     * @param player 实体类
     * @param id     编号
     * @return
     * @throws IOException
     */
    @Override
    public boolean addPlayer(NbaPlayer player, String id) throws IOException {
        IndexRequest request = new IndexRequest(NBA_INDEX).id(id).source(beanToMap(player));
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        log.info(JSONObject.toJSON(response).toString());
        return false;
    }

    /**
     * 修改
     *
     * @param player 实体类
     * @param id     编号
     * @return
     * @throws IOException
     */
    @Override
    public boolean updatePlayer(NbaPlayer player, String id) throws IOException {
        UpdateRequest request = new UpdateRequest(NBA_INDEX, id).doc(beanToMap(player));
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        log.info(JSONObject.toJSON(response).toString());
        return true;
    }

    /**
     * 删除
     *
     * @param id 编号
     * @return
     * @throws IOException
     */
    @Override
    public boolean deletePlayer(String id) throws IOException {
        DeleteRequest request = new DeleteRequest(NBA_INDEX, id);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        return true;
    }

    /**
     * 删除全部
     *
     * @return
     * @throws IOException
     */
    @Override
    public boolean deleteAllPlayer() throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest(NBA_INDEX);
        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);
        return true;
    }

    /**
     * 将数据库中的数据全部导入到ES
     *
     * @return
     * @throws IOException
     */
    @Override
    public boolean importAll() throws IOException {
        List<NbaPlayer> list = nbaPlayerDao.selectAll();
        for (NbaPlayer player : list) {
            addPlayer(player, String.valueOf(player.getId()));
        }
        return true;
    }

    /**
     * 通过名字查找球员
     * @param key
     * @param value
     * @return
     * @throws IOException
     */
    @Override
    public List<NbaPlayer> searchMatch(String key, String value) throws IOException {
        SearchRequest request = new SearchRequest(NBA_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(key, value));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(1000);
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info(JSONObject.toJSON(response).toString());
        SearchHit[] hits = response.getHits().getHits();
        List<NbaPlayer> playerList = new ArrayList<>();
        for (SearchHit hit : hits) {
            NbaPlayer player = JSONObject.parseObject(hit.getSourceAsString(), NbaPlayer.class);
            playerList.add(player);
        }
        return playerList;
    }
    /**
     * 通过国家或球队查找球员
     * @param key
     * @param value
     * @return
     * @throws IOException
     */
    @Override
    public List<NbaPlayer> searchTerm(String key, String value) throws IOException {
        SearchRequest request = new SearchRequest(NBA_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery(key, value));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(1000);
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info(JSONObject.toJSON(response).toString());
        SearchHit[] hits = response.getHits().getHits();
        List<NbaPlayer> playerList = new ArrayList<>();
        for (SearchHit hit : hits) {
            NbaPlayer player = JSONObject.parseObject(hit.getSourceAsString(), NbaPlayer.class);
            playerList.add(player);
        }
        return playerList;
    }
    /**
     * 通过字母查找球员
     * @param key
     * @param value
     * @return
     * @throws IOException
     */
    @Override
    public List<NbaPlayer> searchMatchPrefix(String key, String value) throws IOException {
        SearchRequest request = new SearchRequest(NBA_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.prefixQuery(key, value));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(1000);
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info(JSONObject.toJSON(response).toString());
        SearchHit[] hits = response.getHits().getHits();
        List<NbaPlayer> playerList = new ArrayList<>();
        for (SearchHit hit : hits) {
            NbaPlayer player = JSONObject.parseObject(hit.getSourceAsString(), NbaPlayer.class);
            playerList.add(player);
        }
        return playerList;
    }
    /**
     * 根据id查询
     *
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public Map<String, Object> getPlayer(String id) throws IOException {
        GetRequest getRequest = new GetRequest(NBA_INDEX, id);
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        return response.getSource();
    }

    /**
     * 对象转map
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if (beanMap.get(key) != null) {
                    map.put(key + "", beanMap.get(key));
                }
            }
        }
        return map;
    }
}
