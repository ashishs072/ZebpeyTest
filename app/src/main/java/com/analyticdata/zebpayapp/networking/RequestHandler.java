package com.analyticdata.zebpayapp.networking;

import android.content.Context;
import com.analyticdata.zebpayapp.networking.request.UserProfileRequest;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;



public class RequestHandler {

    //private static final String URL = "http://disposeandrecycleservice.azurewebsites.net/DisposalService.svc/getrecyclingdetails";
    private static final String URL =  "https://api.stackexchange.com/2.2/users?order=desc&sort=reputation&site=stackoverflow";

    private static RequestHandler mRequestHandler;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    private RequestHandler() {

    }

    public static RequestHandler getInstance(Context context) {
        RequestHandler.mContext = context;
//        mContext = context;
        if (mRequestHandler == null) {
            mRequestHandler = new RequestHandler();
        }
        return mRequestHandler;
    }


    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
            // mRequestQueue = CustomVolley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    private <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void handleRequest(UserProfileRequest req) {

        VolleyManager volleyReq = new VolleyManager(mContext, req.getMethod(), constructUrl(req.getAction()), req);
        volleyReq.setTag(req.getTag());
        volleyReq.setShouldCache(false);
        volleyReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 30, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        addToRequestQueue(volleyReq);

    }

    private String constructUrl(String action) {
        //String construtedUrl = URL;
        String construtedUrl = URL + action;
       /* if (action.equalsIgnoreCase("SendEneryCodeMail")) {
            construtedUrl = ENERGYCODE_EMAIL_URL + action;
        }*/
        //CLog.i("mRequestHandler Construted URL >>> " + construtedUrl);
//        System.out.println("Construct URL====" + construtedUrl);
        return construtedUrl;
    }

}
