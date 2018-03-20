package com.analyticdata.zebpayapp.networking.request;

import com.android.volley.Request;
import com.android.volley.Response;

public class UserProfileRequest implements IRequest {

    private Response.Listener sucessListener;
    private Response.ErrorListener errorListener;

    public UserProfileRequest(Response.Listener sucessListener, Response.ErrorListener errorListener) {
        this.sucessListener = sucessListener;
        this.errorListener = errorListener;

    }
/*
    @Override
    public String getReqParams() {
        return "";
    }
*/

    @Override
    public int getMethod() {
        return  Request.Method.GET;
    }

    @Override
    public String getAction() {
        return " ";
    }

    @Override
    public Response.ErrorListener getErrorListener() {
        return errorListener;
    }

    @Override
    public Response.Listener<String> getSuccessListener() {
        return sucessListener;
    }

    @Override
    public String getTag() {
        return null;
    }

    @Override
    public boolean isHandleError() {
        return false;
    }
}
