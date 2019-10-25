package com.zx.network.beans;

public class LotteryNetRequestResult<T> {

    private String reason;
    private T result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "NewsNetRequestResult{" +
                "reason='" + reason + '\'' +
                ", result=" + result.toString() +
                ", error_code=" + error_code +
                '}';
    }
}
