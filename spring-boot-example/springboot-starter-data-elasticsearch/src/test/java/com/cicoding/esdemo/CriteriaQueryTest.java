package com.cicoding.esdemo;

import com.cicoding.esdemo.es.bean.NbaPlayer;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

/**
 * @author cidoing
 * @version 1.0
 * @date 2020/9/24
 * @since 2020/9/24
 */
public class CriteriaQueryTest extends ElasticsearchRestTemplateNativeTest{

    /**
     * select displayName,playYear, age from  nba_latest where playYear > 3 and age > 30
     */
    @Test
    public void  testRangeQuery(){
        CriteriaQuery criteriaQuery =
                new CriteriaQuery(
                        Criteria.where("playYear")
                                .greaterThan(3)
                                .and( Criteria.where("age")
                                        .greaterThan(20)));
        criteriaQuery.addFields("displayName","playYear","age");
        criteriaQuery.setPageable(PageRequest.of(1,20));
        doSearch(criteriaQuery);
    }

    @Test
    public void testMatchQuery(){
        Criteria criteria = Criteria.where("displayName").fuzzy("格雷森");
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        criteriaQuery.addFields("displayName","playYear","age");
        criteriaQuery.setPageable(PageRequest.of(1,20));
        doSearch(criteriaQuery);
    }

    private void doSearch(CriteriaQuery criteriaQuery){
        SearchHits<NbaPlayer> nba_latest = template.search(criteriaQuery, NbaPlayer.class, IndexCoordinates.of("nba_latest"));
        printEntity(nba_latest);
    }
}
