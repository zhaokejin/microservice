package cn.cicoding.gateway;

import lombok.Data;

@Data
public class AccessLog {
    private String schema;
    private String requestMethod;
    private String requestContentType;
    private String requestPath;
    private String targetServer;
    private Long requestTime;
    private String ip;
    private String userId;
    private Long responseTime;
    private String responseData;
    private String requestBody;

}
