package samplesharedpreferences.com.sendotp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by neeraj on 10/2/18.
 */

public interface SendOtpSingup {
        @GET("sendhttp.php")
        Call<String> reposForUser(
                @Query("sender") String sender,
                @Query("route") String route,
                @Query("mobiles")  String mobiles,
                @Query("authkey") String authkey,
                @Query("country") Number country,
                @Query("message") String message


        );


    }

