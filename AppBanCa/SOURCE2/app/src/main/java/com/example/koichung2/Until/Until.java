package com.example.koichung2.Until;

import com.google.gson.JsonObject;

public class Until {
    public static JsonObject jsonObject;

    public static void jsonBody(){
        if(jsonObject != null){
            jsonObject = null;
        }
        jsonObject = new JsonObject();
        jsonObject.addProperty("userAPI", "madara");
        jsonObject.addProperty("passAPI", "madara");
    }
}
