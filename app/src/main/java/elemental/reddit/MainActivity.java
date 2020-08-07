package elemental.reddit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.IOException;
import java.util.UUID;


public class MainActivity extends AppCompatActivity
{
    public static String AUTH_URL = "https://www.reddit.com/api/v1/authorize?client_id=_sDG6vsDibq0-g&response_type=code&state=2fs&redirect_uri=https://www.reddit.com/login/&duration=permanent&scope=identity edit flair history modconfig modflair modlog modposts modwiki mysubreddits privatemessages read report save submit subscribe vote wikiedit wikiread";
    public static String REGISTER_URL = "https://reddit.com/register?redirect_uri="+AUTH_URL;
    //Shared preferences for settings
    protected SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Set the settings
        this.settings = getSharedPreferences("elemental", Context.MODE_PRIVATE);
        //On create
        super.onCreate(savedInstanceState);
        //Get the default theme
        String theme = this.settings.getString("theme", "dark");
        //Check if theme is dark
        if(theme.equals("dark"))
        {
            //Check if not logged in
            if(!this.settings.getBoolean("logged_in", false))
            {
                //Set dark log in
                setContentView(R.layout.activity_main_dark);
                //Not logged in function
                userNotLoggedIn();
            }
        }
        else
        {
            if(!this.settings.getBoolean("logged_in", false))
            {
                //Set light log in
                setContentView(R.layout.activity_main_light);
                //Not logged in function
                userNotLoggedIn();
            }
        }
    }

    protected void userNotLoggedIn()
    {
        //Get the login button
        Button login = findViewById(R.id.login_button);
        //Get the register button
        Button register = findViewById(R.id.register_button);

        //Run on login button clicked
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Create an intent
                Intent login = new Intent(Intent.ACTION_VIEW);
                //Set the URI to the authentication url
                login.setData(Uri.parse("https://www.reddit.com/login?redirect_url="+AUTH_URL));
                //Start the intent
                startActivity(login);
            }
        });

        //Run on register button clicked
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Create an intent
                Intent registerIntent = new Intent(Intent.ACTION_VIEW);
                //Set the URI to the signup page
                registerIntent.setData(Uri.parse(REGISTER_URL));
                //Start the activity
                startActivity(registerIntent);
            }
        });
    }


}
