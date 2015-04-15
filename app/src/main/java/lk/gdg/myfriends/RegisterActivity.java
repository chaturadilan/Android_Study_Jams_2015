package lk.gdg.myfriends;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseObject;


public class RegisterActivity extends ActionBarActivity {

    EditText editTextUsername;
    EditText editTextPassword;
    EditText editTextName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = (EditText) findViewById(R.id.editTextRegisterUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextRegisterPassword);
        editTextName = (EditText) findViewById(R.id.editTextRegisterName);
    }


    public void onRegisterButtonClick(View view){

        Parse.initialize(this, "uoi9iGFDqZHY2kd7wujFGZ9yvNQh8qlEd2ukxcDn", "HDmbKQpyQpx6Xi4cKs7D6UUklDZhLNbkePpHwXel");

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String name = editTextName.getText().toString();

        ParseObject userObject = new ParseObject("User");
        userObject.put("username", username);
        userObject.put("password", password);
        userObject.put("name", name);
        userObject.saveInBackground();

        Log.d("REGISTER", "Username : " + username + ", Password : " + password +
                ", Name : " + name);
    }


}
