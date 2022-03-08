package com.example.digitallibraryadmin.ApiLibrary;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
public class CurrentSubscription {
    public int id;
    public String startDate;
    public String endDate;
    public int storage;
}
