package com.base.mvp;

import java.io.Serializable;

/**
 * created by zhengweis on 2018/11/20.
 * description：
 */
public class INetBean implements Serializable {
    private int ErrCode;
    private String ErrMsg;
    private Object Response;

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

    public Object getResponse() {
        return Response;
    }

    public void setResponse(Object response) {
        Response = response;
    }
}
