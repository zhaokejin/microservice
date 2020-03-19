package cn.cicoding;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * 自定义分片算法
 *
 * @author zkj
 *
 */
public class MyCustomShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String tableName : collection) {
            if (tableName.endsWith(preciseShardingValue.getValue() % 4 + "")) {
                System.out.println(tableName + "<<<<<<<<<<<<<<<<tableName");
                return tableName;
            }
        }
        throw new IllegalArgumentException();
    }

}
