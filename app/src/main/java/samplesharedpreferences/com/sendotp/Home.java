package samplesharedpreferences.com.sendotp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by neeraj on 10/2/18.
 */

public class Home extends AppCompatActivity {
    EditText emailId, password, name, mobile, age;
    Button show;
    SharedPreferences sharedpreferences;


    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String PASS = "pass";
    public static final String Mob = "Mob";
    public static final String AGE="agekey";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);



        show = (Button) findViewById(R.id.signUp);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = findViewById(R.id.username);
                emailId = findViewById(R.id.email);
                password = findViewById(R.id.password);
                mobile = findViewById(R.id.mobile);
                age = findViewById(R.id.age);
                if(sharedpreferences.contains("nameKey")){
                    name.setText(sharedpreferences.getString(Name,""));

                }
                if(sharedpreferences.contains("emailKey")){
                    emailId.setText(sharedpreferences.getString(Email,""));

                }
                if(sharedpreferences.contains("pass")){
                    password.setText(sharedpreferences.getString(PASS,""));

                }
                if(sharedpreferences.contains("Mob")){
                    mobile.setText(sharedpreferences.getString(Mob,""));

                }
                if(sharedpreferences.contains("agekey")){
                    age.setText(sharedpreferences.getString(AGE,""));

                }


            }
        });



    }
    }
