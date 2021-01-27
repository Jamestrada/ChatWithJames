package com.jamestrada.chatwithjames.Fragments;

import com.jamestrada.chatwithjames.Notifications.MyResponse;
import com.jamestrada.chatwithjames.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAgbONxAI:APA91bHEZVm_X2bJLHIWKSNCwjYK1sm7--3sgI0EUfM3a_PZu1zc8rCOy8qXm7SqZ_BJXu7p80VYp798tCb_uIV89g6pcacta9V0OLP33f0YgDCOT671WKNfiS7C-CYFYd9PpHsmbkHx"
    })
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
