package com.pplive.ppcloud.request;


public class ResultBean {
    public static final Integer OK = 0;

    private Object data;

    private Integer err;

    private String msg;

    public ResultBean() {
    }

    public ResultBean(Object data, Integer err) {
        this.data = data;
        this.err = err;
    }

    public ResultBean(Object data, Integer err, String msg) {
        this.data = data;
        this.err = err;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getErr() {
        return err;
    }

    public void setErr(Integer err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultBean [data=" + data + ", err=" + err + "]";
    }
}
