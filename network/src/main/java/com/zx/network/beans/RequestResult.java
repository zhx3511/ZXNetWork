package com.zx.network.beans;

public class RequestResult<T> {
    private int stat;
    private T data;

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestResult{" +
                "stat=" + stat +
                ", data=" + data.toString() +
                '}';
    }
}
