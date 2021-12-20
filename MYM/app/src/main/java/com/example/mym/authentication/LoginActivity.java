package com.example.mym.authentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mym.model.user.User;
import com.example.mym.server.Constants;
import com.example.mym.server.Server;
import com.example.mym.session.MainActivity;
import com.example.mym.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    Server server;
    int i=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String logout = getIntent().getStringExtra("logout");
        System.out.println(logout);
        if( logout != null && logout.equals("logout")){
            SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
            sharedPref.edit().clear().apply();
        }
        if(isValidSession()){
            this.getSupportLoaderManager().initLoader(i++,null,this).forceLoad();
        }
        else setContentView(R.layout.login);

    }
    public void authenticateLogin(View view) {
        this.getSupportLoaderManager().initLoader(i++,null,this).forceLoad();
    }
    public void authenticateSignUp(View view) {
        Intent signUpActivity = new Intent(this, SignUpActivity.class);
        startActivity(signUpActivity);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if(!isValidSession()){
            EditText userNameEditText = findViewById(R.id.userName);
            EditText passwordEditText = findViewById(R.id.password);
            String userName = userNameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            HashMap<String,String> bodyRequest = new HashMap<String,String>();
            bodyRequest.put("userName",userName);
            bodyRequest.put("password",password);
            server = new Server(this, Constants.SIGN_IN, "POST",bodyRequest);
        }
        else {
            HashMap<String,String> bodyRequest = new HashMap<String,String>();
            bodyRequest.put("userName",session()[0]);
            bodyRequest.put("password",session()[1]);
            server = new Server(this, Constants.SIGN_IN, "POST",bodyRequest);
        }
        return server;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String res) {
        try {
            if(server.getStatusCode() == 0) {
                Toast.makeText(this, "can not connect to server", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(server.getStatusCode() == 404) {
                Toast.makeText(this, "wrong username or password", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(server.getStatusCode() == 401){
                Toast.makeText(this, "unKnown error", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                JSONObject jsonObject = new JSONObject(res);
                ArrayList<User> friends = new ArrayList<User>();
                JSONArray jsonArray = jsonObject.getJSONArray("friends");
                for(int i=0;i<jsonArray.length();i++){
                    User user = new User(
                            jsonArray.getJSONObject(i).getString("_id"),
                            jsonArray.getJSONObject(i).getString("userName"),
                            jsonArray.getJSONObject(i).getString("firstName"),
                            jsonArray.getJSONObject(i).getString("lastName"),
                            jsonArray.getJSONObject(i).getString("password_hash"),
                            jsonArray.getJSONObject(i).getString("registered_at"),
                            jsonArray.getJSONObject(i).getString("avatar"),
                            null
                    );
                    friends.add(user);
                }
                User user = new User(
                        jsonObject.getString("_id"),
                        jsonObject.getString("userName"),
                        jsonObject.getString("firstName"),
                        jsonObject.getString("lastName"),
                        jsonObject.getString("password_hash"),
                        jsonObject.getString("registered_at"),
                        jsonObject.getString("avatar"),
                        friends
                );
                if(!isValidSession()){
                    SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userName", user.getUserName());
                    editor.putString("password", user.getPassword_hash());
                    editor.apply();
                }

                Intent mainActivity = new Intent(this, MainActivity.class);
                mainActivity.putExtra("user",user);
                startActivity(mainActivity);
                this.finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
    boolean isValidSession(){
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        String userName = sharedPref.getString("userName", "");
        String password = sharedPref.getString("password", "");
        return !userName.equals("") && !password.equals("");
    }
    String[] session(){
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        String s[] = new String[2];
        s[0] = sharedPref.getString("userName", "");
        s[1] = sharedPref.getString("password", "");
        return s;
    }
}