package com.analyticdata.zebpayapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.analyticdata.zebpayapp.R;
import com.analyticdata.zebpayapp.UIUtils.UIUtils;
import com.analyticdata.zebpayapp.networking.RequestHandler;
import com.analyticdata.zebpayapp.networking.pojo.Item;
import com.analyticdata.zebpayapp.networking.pojo.StackUserProfile;
import com.analyticdata.zebpayapp.networking.request.UserProfileRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mTextView;
    private RecyclerView mProfileList;
    private String TAG="MainActivity";
    private ProfileAdapter mProfileAdapter;
    ArrayList<Item> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProfileList=findViewById(R.id.listDetails);
        mTextView=findViewById(R.id.getResponse);


        getServiceCall();
        mTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProfileAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                mProfileAdapter.getFilter().filter(s.toString());
            }
        });

    }

    @Override
    public void onClick(View v) {
        getServiceCall();
    }

    private class SuccessListener implements Response.Listener {

        @Override
        public void onResponse(Object response) {
            StackUserProfile leaderResponse = UIUtils.getInstance().getPojoObject(String.valueOf(response), StackUserProfile.class);
            adapter=leaderResponse.getUserProfileDetails();
            mProfileAdapter=new ProfileAdapter(MainActivity.this,adapter);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mProfileList.setLayoutManager(mLayoutManager);
            mProfileList.setAdapter(mProfileAdapter);
            Log.d(TAG,"Value"+leaderResponse.getUserProfileDetails());
        }


    }
    private class ErrorListener implements Response.ErrorListener {


        @Override
        public void onErrorResponse(VolleyError error) {

            Log.d(TAG,"Value"+error);
        }
    }

    public void getServiceCall()
    {
        RequestHandler.getInstance(MainActivity.this).handleRequest(new UserProfileRequest(new SuccessListener(),
                new ErrorListener()));
    }
    @Override
    protected void onResume() {
        super.onResume();
       // mProfileList.setAdapter(mProfileAdapter);
    }


}
