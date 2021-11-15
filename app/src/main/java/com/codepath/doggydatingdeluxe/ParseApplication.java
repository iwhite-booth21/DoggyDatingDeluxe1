package com.codepath.doggydatingdeluxe;
import com.parse.Parse;
import com.parse.ParseObject;
import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ParseApplication extends Application{
       public void onCreate(){
           super.onCreate();

           ParseObject.registerSubclass(Message.class);

           // Use for monitoring Parse network traffic
           OkHttpClient.Builder builder = new OkHttpClient.Builder();
           HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
           // Can be Level.BASIC, Level.HEADERS, or Level.BODY
           httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
           // any network interceptors must be added with the Configuration Builder given this syntax
           builder.networkInterceptors().add(httpLoggingInterceptor);


           // Register Parse Models
           ParseObject.registerSubclass(Post.class);
           Parse.initialize(new Parse.Configuration.Builder(this)
                   .applicationId("CLS4prRYF8J9ZIkIUZYRf1EfnLZohroioqDZbhAW")
                   .clientKey("f8NsMTi6EIhX0XY7BT06ujJ99cE0bUJ4ZOw1Lms6")
                   .server("https://parseapi.back4app.com")
                   .build()
           );
       }

}
