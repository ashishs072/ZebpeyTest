package com.analyticdata.zebpayapp.networking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StackUserProfile {
    @JsonProperty("items")
    private ArrayList<Item> userProfileDetails;


    public ArrayList<Item> getUserProfileDetails() {
        return userProfileDetails;
    }

    public void setUserProfileDetails(ArrayList<Item> userProfileDetails) {
        this.userProfileDetails = userProfileDetails;
    }
}
