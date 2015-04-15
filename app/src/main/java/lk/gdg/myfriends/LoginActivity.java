package lk.gdg.myfriends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class LoginActivity extends ActionBarActivity {

    EditText editTextUsername;
    EditText editTextPassword;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        editTextUsername = (EditText) findViewById(R.id.editTextLoginUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextLoginPassword);

        boolean hasLoggedinPreviously = sharedPreferences.getBoolean("hasLoggedinPreviously", false);
        String username = sharedPreferences.getString("username", "dummyUsername");

        if(hasLoggedinPreviously){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }


    public void onLoginButtonClick(View view){
        Log.d("LOGIN", "Login button was called");

        Parse.initialize(this, "uoi9iGFDqZHY2kd7wujFGZ9yvNQh8qlEd2ukxcDn", "HDmbKQpyQpx6Xi4cKs7D6UUklDZhLNbkePpHwXel");


        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        Log.d("LOGIN", "Username : " + username + ", Password : " + password);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("username", username);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    Toast.makeText(getApplicationContext(), "Wrong username & password", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("score", "Retrieved the object.");
                    if(object.getString("password").equals(password)){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("hasLoggedinPreviously", true);
                        editor.putString("username", username);
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Wrong username & password", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


    }


    public void onRegisterButtonClick(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
