package com.zx.network.beans;

public class NewsNetRequestResult<T> {

    private String reason;
    private RequestResult<T> result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RequestResult<T> getResult() {
        return result;
    }

    public void setResult(RequestResult<T> result) {
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
