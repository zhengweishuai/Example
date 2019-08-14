package com.http;

import java.io.Serializable;

/**
 * created by zhengweis on 2019/8/13.
 * description：
 */
public class BaseResponse<T> implements Serializable {

    private int ErrCode;
    private String ErrMsg;
    private T Response;

    public int getErrCode() {
        return ErrCode;
    }

    public void setErrCode(int errCode) {
        ErrCode = errCode;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public T getResponse() {
        return Response;
    }

    public void setResponse(T response) {
        Response = response;
    }
}
