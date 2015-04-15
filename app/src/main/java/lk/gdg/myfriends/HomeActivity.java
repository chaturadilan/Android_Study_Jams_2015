package lk.gdg.myfriends;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends ActionBarActivity {

    EditText editTextFriend;
    TextView textViewFriendsNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editTextFriend = (EditText) findViewById(R.id.editTextHomeFriend);
        textViewFriendsNames = (TextView) findViewById(R.id.textViewHomeFriendNames);
    }


    public void onButtonAddFriendClick(View view){
        String friendName = editTextFriend.getText().toString();
        FriendHelper friendHelper = new FriendHelper(getApplicationContext());
        friendHelper.addFriend(friendName);
        Toast.makeText(getApplicationContext(), "Name saved successfully", Toast.LENGTH_SHORT).show();

    }

    public void onButtonGetFriendClick(View view){
        List<String> friends = new ArrayList<String>();
        FriendHelper friendHelper = new FriendHelper(getApplicationContext());
        friends = friendHelper.getFriends();
        textViewFriendsNames.setText(friends.toString());
    }

    public void onButtonExportClick(View view){

        if(isExternalStorageWritable()){

            List<String> friends = new ArrayList<String>();
            FriendHelper friendHelper = new FriendHelper(getApplicationContext());
            friends = friendHelper.getFriends();

            File downloadFolder = getDownloadDir();
            File file = new File(downloadFolder, "friends123.txt");

            OutputStream outputStream = null;

            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(friends.toString().getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(outputStream != null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


        }


    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public File getDownloadDir() {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "friends");
        if (!file.mkdirs()) {
            Log.e("FILES", "Directory not created");
        }
        return file;
    }
}
