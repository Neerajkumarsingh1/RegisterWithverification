package samplesharedpreferences.com.sendotp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // SignUpResponse signUpResponsesData;
    EditText emailId, password, name, mobile, age;
    Button signUp;
    SharedPreferences sharedpreferences;
    String otp;

    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String PASS = "pass";
    public static final String Mob = "Mob";
    public static final String AGE="agekey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init the EditText and Button
        name = findViewById(R.id.username);
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mobile = findViewById(R.id.mobile);
        age = findViewById(R.id.age);
        signUp = (Button) findViewById(R.id.signUp);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        // implement setOnClickListener event on sign up Button
        signUp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

            String n=name.getText().toString();
            String email=emailId.getText().toString();
            String mobi=mobile.getText().toString();
            String ag=age.getText().toString();
            String pas=password.getText().toString();

             SharedPreferences.Editor editor=sharedpreferences.edit();
             editor.putString(Name,n);
             editor.putString(Email,email);
             editor.putString(Mob,mobi);
             editor.putString(AGE,ag);
             editor.putString(PASS,pas);
             editor.commit();




   // validate the fields and call sign method to implement the api
                if (validate(name) && validateEmail() && validate(password) && validateMobile()) {

                    String mob = mobile.getText().toString();
                    Random random = new Random();
                    int i = random.nextInt(999999);
                    //int number = -782;
                    otp = new Integer(i).toString();

                    Gson gson = new GsonBuilder().setLenient().create();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://api.msg91.com/api/")
                            .addConverterFactory(GsonConverterFactory.create(gson)).build();

                    SendOtpSingup otpSingup = retrofit.create(SendOtpSingup.class);

                    Call<String> call = otpSingup.reposForUser("NSGIND", "4", mob, "197052A3ecHRtdISu95a7a8685", 91, "Your Otp is " + otp);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(MainActivity.this, response.body(), Toast.LENGTH_LONG).show();


                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();


                        }



                    });


                    Intent intent = new Intent(MainActivity.this, Verify.class);
                    intent.putExtra("OTP",otp);
                    startActivity(intent);

                }
               /* Intent intent=new Intent(MainActivity.this, Verify.class);
               //intent.putExtra("Otp",otp);
                startActivity(intent);*/
            }


        });

    }





    private boolean validateEmail() {
        String email = emailId.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            emailId.setError("Email is not valid.");
            emailId.requestFocus();
            return false;
        }

        return true;
    }

    private boolean validateMobile() {
        String mob = mobile.getText().toString().trim();
        if (mob != "null" && mob.length() != 10) {
            mobile.setError("Enter 10 digit Mobile Number");
            mobile.requestFocus();
            ;
            return false;

        }

        return true;
    }


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validate(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returns true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }



}

