package cn.cicoding.service;

import cn.cicoding.entity.DocBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Iterator;
import java.util.List;

public interface IElasticService {

    void createIndex();

    void deleteIndex(String index);

    void save(DocBean docBean);

    void saveAll(List<DocBean> list);

    Iterator<DocBean> findAll();

    Page<DocBean> findByContent(String content);

    Page<DocBean> findByFirstCode(String firstCode);

    Page<DocBean> findBySecordCode(String secordCode);

    Page<DocBean> query(String key);

    List<DocBean> highlightQuery(String key,Integer pageNum,Integer pageSize);

    public List<DocBean> highlight(String key,Integer pageNum,Integer pageSize);
}

