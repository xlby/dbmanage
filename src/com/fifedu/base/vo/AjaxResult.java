package com.fifedu.base.vo;

/**
 * ajax请求结果对象
 * Created by super on 2016/1/21.
 */
public class AjaxResult {
    public AjaxResult(){
        this.status = "0";
        this.message = "OK";
        this.data = null;
    }

    private String status ;
    private String message;
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
