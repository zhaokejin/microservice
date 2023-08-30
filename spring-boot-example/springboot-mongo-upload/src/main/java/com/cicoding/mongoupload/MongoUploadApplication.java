package com.cicoding.mongoupload;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;

/**
 * https://blog.csdn.net/zhuyu19911016520/article/details/88777844
 */
@SpringBootApplication
public class MongoUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoUploadApplication.class, args);
    }

    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Bean
    public GridFSBucket getGridFSBuckets() {
        MongoDatabase db = mongoDbFactory.getDb();
        return GridFSBuckets.create(db);
    }
}
