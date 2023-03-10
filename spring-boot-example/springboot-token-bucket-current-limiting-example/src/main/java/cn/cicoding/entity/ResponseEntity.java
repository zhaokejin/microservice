package cn.cicoding.entity;
 
public class ResponseEntity<T> {
 
    private int code;
 
    private String message;
 
    private T result;
 
    public ResponseEntity(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }
 
    public ResponseEntity(String message, T result) {
        this.code = 200;
        this.message = message;
        this.result = result;
    }
 
    public ResponseEntity(T result) {
        this.code = 200;
        this.message = "Success";
        this.result = result;
    }
 
    public ResponseEntity() {
        this.code = 200;
        this.message = "Success";
    }
 
    public ResponseEntity(Integer code, String msg) {
        this.code = code;
        this.message = msg;
        this.result = null;
    }
 
    public int getCode() {
        return code;
    }
 
    public void setCode(int code) {
        this.code = code;
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public T getResult() {
        return result;
    }
 
    public void setResult(T result) {
        this.result = result;
    }
 
    @Override
    public String toString() {
        return "ResponseEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
 
}