package com.cicoding.esdemo;

import com.cicoding.esdemo.es.bean.NbaPlayer;
import com.cicoding.esdemo.es.utils.JsonUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author cidoing
 * @version 1.0
 * @date 2020/9/24
 * @since 2020/9/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchRestTemplateNativeTest {


    @Autowired
    protected ElasticsearchRestTemplate template;

    /**
     * 查询Nba
     * 翻译成sql:
     * select displayName,playYear, age from  nba_latest where playYear > 3 and age > 30
     * GET nba_latest/_search
     * {
     *   "query": {
     *     "bool": {
     *       "must": [
     *         {
     *           "range": {
     *             "age": {
     *               "gt": 20
     *             }
     *           }
     *         },
     *         {
     *           "range": {
     *             "playYear": {
     *               "gt": 3
     *             }
     *           }
     *         }
     *       ]
     *     }
     *   },
     *   "_source": [
     *     "displayName",
     *     "age",
     *     "playYear"
     *   ]
     * }
     */
    @Test
    public void testBoolQuery() {
        NativeSearchQuery searchQuery =
                new NativeSearchQueryBuilder()
                        .withQuery(QueryBuilders.boolQuery()
                                .must(QueryBuilders.rangeQuery("age").gt(20))) // age > 20
                        .withQuery(QueryBuilders.boolQuery()
                                .must(QueryBuilders.rangeQuery("playYear").gt(3))) // playYear > 3
                        .withFields("age", "displayName", "playYear") /// 声明返回结果列表
                        .withPageable(PageRequest.of(1,10)) // 分页查询
                        .build();
        SearchHits<NbaPlayer> search = template.search(searchQuery, NbaPlayer.class);
        printEntity(search);
    }

    /**
     * match query 会对查询条件进行分词 类似 like
     *
     * term query 不会分词相当于精确匹配
     *
     */
    @Test
    public void testMatchQuery() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();


        queryBuilder
                .withQuery(QueryBuilders.matchQuery("displayName", "格雷森"))
                .withFields("displayName")
                .withPageable(PageRequest.of(1,4))

        ;

        // 高亮显示名称
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder
                .preTags("<span class='highlight'>")
                .field("displayName")
                .postTags("</span>");
        queryBuilder .withHighlightBuilder(highlightBuilder);
        SearchHits<NbaPlayer> search = template.search(queryBuilder.build(), NbaPlayer.class);

        printEntity(search);
        /**
         * [{
         *         "id": 205,
         *         "displayName": "德雷蒙德 格林"
         *     }, {
         *         "id": 209,
         *         "displayName": "布雷克 格里芬"
         *     }, {
         *         "id": 200,
         *         "displayName": "特雷维安 格拉汉姆"
         *     }, {
         *         "id": 191,
         *         "displayName": "乔纳森 吉布森"
         *     }
         * ]
         */

    }

    protected void printEntity(SearchHits<NbaPlayer> search) {
//        System.out.println(JsonUtils.toJson(search));
//        List<SearchHit<NbaPlayer>> searchHits = search.getSearchHits();
//        List<Object> collect = searchHits.stream().map(s -> {
//            NbaPlayer content = s.getContent();
//            List<String> displayName = s.getHighlightField("displayName");
//            content.setDisplayName(displayName.get(0));
//            return content;
//        }).collect(Collectors.toList());
//        System.out.println(JsonUtils.toJson(collect));
        System.out.println(JsonUtils.toJson(SearchHitSupport.unwrapSearchHits(search.getSearchHits())));
    }




    @Test
    public void testScrollPage(){
        int pageNum = 1;
        int pageSize = 20;

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();


        queryBuilder
                .withQuery(QueryBuilders.matchQuery("displayName", "格雷森"))
                .withFields("displayName")
                .withPageable(PageRequest.of(1,4));

        SearchScrollHits<NbaPlayer> nba_latest = template.searchScrollStart(TimeUnit.SECONDS.toMillis(1),
                queryBuilder.build(),
                NbaPlayer.class,
                IndexCoordinates.of("nba_latest"));


    }
}
