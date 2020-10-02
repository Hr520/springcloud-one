package com.web.util;



public class ResponseEntity<T> {

    /**
     * state ：状态 date : 数据
     */
    private String state;
    private String msg;
    private T data;
    private Long time;
    public Long getTime(){return time;}
    public ResponseEntity<T> setTime(Long time){
        this.time=time;
        return this;
    }



	public String getMsg() {
        return msg;
    }

    public ResponseEntity<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getState() {
        return state;
    }

    public ResponseEntity<T> setState(String state) {
        this.state = state;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseEntity<T> setData(T data) {
        this.data = data;
        return this;
    }
}