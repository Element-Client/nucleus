package elemental.reddit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.dean.jraw.RedditClient;
import net.dean.jraw.android.AndroidHelper;
import net.dean.jraw.android.AppInfoProvider;
import net.dean.jraw.android.ManifestAppInfoProvider;
import net.dean.jraw.android.SharedPreferencesTokenStore;
import net.dean.jraw.android.SimpleAndroidLogAdapter;
import net.dean.jraw.http.LogAdapter;
import net.dean.jraw.http.SimpleHttpLogger;
import net.dean.jraw.models.Account;
import net.dean.jraw.oauth.AccountHelper;
import net.dean.jraw.oauth.Credentials;

import java.util.UUID;

import elemental.reddit.account.OnAccountLogin;
import elemental.reddit.account.RedditAccountHelper;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity
{
    //Shared preferences for settings
    protected SharedPreferences settings;
    //Credentials for reddit
    protected Credentials redditCredentials;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Set the settings
        this.settings = getSharedPreferences("elemental", Context.MODE_PRIVATE);
        //On create
        super.onCreate(savedInstanceState);
        //Get the default theme
        String theme = this.settings.getString("theme", "light");
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
        //Create reddit account helper
        final RedditAccountHelper redditAccountHelper = new RedditAccountHelper();
        //Get the username / email edit text
        final EditText userNameEmail = findViewById(R.id.user_email);
        //Get the password
        final EditText password = findViewById(R.id.user_password);
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
                //Log in using JRAW
                redditAccountHelper.login(userNameEmail.getText().toString(), password.getText().toString(), new OnAccountLogin() {
                    @Override
                    public void onLogin()
                    {

                    }
                });
            }
        });

        //Run on register button clicked
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Open web browser to register page
            }
        });
    }
}
