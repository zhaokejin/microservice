package cn.cicoding.aop;

import java.util.Objects;

public class UserBean {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}