package cn.cicoding.utils;

/**
 * 返回结果封装类
 * @author zhaokejin
 */
public class ResultSet {
    /**
     * 操作失败
     */
	public static final int RESULT_CODE_FALSE = 0;
    /**
     * 操作成功
     */
	public static final int RESULT_CODE_TRUE = 1;
	public static final int RESULT_CODE_OK =1;
    /**
     * 操作出现错误
     */
	public static final int RESULT_CODE_ERROR = -1;
    /**
     * 当前操作成功，需要进行下一步操作。resultMsg中指示了应该进行什么操作。
     */
	public static final int RESULT_CODE_NEED_NEXT_STEP =2;

	private Integer resultCode = RESULT_CODE_TRUE;
	private String resultMsg;
	private Object data;

	public ResultSet() {
	}

	public ResultSet(int resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public ResultSet(int resultCode, String resultMsg, Object data) {
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
