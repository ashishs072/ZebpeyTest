package com.analyticdata.zebpayapp.networking.request;
import com.android.volley.Response;


public interface IRequest {


    int getMethod();


    String getAction();


    Response.ErrorListener getErrorListener();


    Response.Listener<String> getSuccessListener();


    String getTag();

    boolean isHandleError();
}

