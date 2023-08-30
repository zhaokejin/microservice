package cn.cicoding.service.impl;

import cn.cicoding.HighlightResultHelper;
import cn.cicoding.dao.ElasticRepository;
import cn.cicoding.entity.DocBean;
import cn.cicoding.service.IElasticService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("elasticService")
public class ElasticServiceImpl implements IElasticService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;
    @Autowired
    private ElasticRepository elasticRepository;

    @Autowired
    private HighlightResultHelper highlightResultHelper;

    private Pageable pageable = PageRequest.of(0,10);

    @Override
    public void createIndex() {
        elasticsearchTemplate.createIndex(DocBean.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchTemplate.deleteIndex(index);
    }

    @Override
    public void save(DocBean docBean) {
        elasticRepository.save(docBean);
    }

    @Override
    public void saveAll(List<DocBean> list) {
        elasticRepository.saveAll(list);
    }

    @Override
    public Iterator<DocBean> findAll() {
        return elasticRepository.findAll().iterator();
    }

    @Override
    public Page<DocBean> findByContent(String content) {
        return elasticRepository.findByContent(content,pageable);
    }

    @Override
    public Page<DocBean> findByFirstCode(String firstCode) {
        return elasticRepository.findByFirstCode(firstCode,pageable);
    }

    @Override
    public Page<DocBean> findBySecordCode(String secordCode) {
        return elasticRepository.findBySecordCode(secordCode,pageable);
    }

    @Override
    public Page<DocBean> query(String key) {
        return elasticRepository.findByContent(key,pageable);
    }

    @Override
    public List<DocBean> highlightQuery(String key,Integer pageNum,Integer pageSize) {
        //创建builder
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        HighlightBuilder.Field field = new HighlightBuilder.Field("content");
        field.preTags("<span>");
        field.postTags("</span>");
        HighlightBuilder.Field[] fields = {field};
        if(StringUtils.isNotBlank(key)){
            builder.must(QueryBuilders.multiMatchQuery(key,"content"));
        }

        //设置排序
        FieldSortBuilder sort = SortBuilders.fieldSort("id").order(SortOrder.DESC);
//        SortBuilder sortBuilder = new FieldSortBuilder("type");
//        sortBuilder.order(SortOrder.DESC);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
//                .withQuery(queryBuilder)
                .withHighlightFields(field)
                .withSort(sort)
//                .withSort(sortBuilder)
                .build();
        List<DocBean> list = new ArrayList<>();
        Iterable<DocBean> iterable = elasticRepository.search(searchQuery);
        if(iterable != null){
            iterable.forEach(list::add);
        }
        return list;


//        System.out.println(key+","+pageNum+","+pageSize);
//        //高亮显示规则
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.preTags("<span style='color:red'>");
//        highlightBuilder.field("content");
//        highlightBuilder.postTags("</span>");
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.multiMatchQuery(key,"content"))
//                .withHighlightBuilder(highlightBuilder)
//                .withPageable(PageRequest.of(pageNum - 1,pageSize))
//                .build();
//        System.out.println("查询的语句:" + searchQuery.getQuery().toString());
//        return elasticRepository.search(searchQuery);
    }

    @Override
    public List<DocBean> highlight(String key,Integer pageNum,Integer pageSize) {
        BoolQueryBuilder boolQueryBuilder= QueryBuilders.boolQuery();
//                .should(QueryBuilders.matchQuery("content",key));
        if(StringUtils.isNotBlank(key)){
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(key,"content"));
        }
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightFields(new HighlightBuilder.Field("content"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>")).build();
        AggregatedPage<DocBean> page = elasticsearchTemplate.queryForPage(nativeSearchQuery, DocBean.class, highlightResultHelper);
        return page.getContent();
    }

}

