package cn.cicoding;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
 
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
 
    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }
 
    /*
     *ThreadLocal 用于提供线程局部变量，在多线程环境可以保证各个线程里的变量独立于其它线程里的变量。
     * 也就是说 ThreadLocal 可以为每个线程创建一个【单独的变量副本】
     * 相当于线程的 private static 类型变量。
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
 
    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }
    public static String getDataSource() {
        return contextHolder.get();
    }
    public static void clearDataSource() {
        contextHolder.remove();
    }
 
}