package cn.cicoding.cloud.user.entity;

/**
 * 返回给前端消息时的类
 * @version 2.0
 * @date 2019-11-11 11:30:59
 * @author: zhaokejin
 */
public class ResultSet {

    /**
     * 返回的状态码
     */
    private Integer resultCode;

    /**
     * 返回的消息
     */
    private String resultMsg;

    /**
     * 返回的数据
     */
    private Object data;

    public ResultSet() {
    }

    public ResultSet(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ResultSet(Integer resultCode, String resultMsg, Object data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
