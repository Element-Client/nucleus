package elemental.reddit.account;


import android.accounts.AccountManager;

import com.github.jreddit.entity.User;
import com.github.jreddit.utils.restclient.PoliteHttpRestClient;
import com.github.jreddit.utils.restclient.RestClient;

import net.dean.jraw.RedditClient;
import net.dean.jraw.android.SharedPreferencesTokenStore;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.OAuthData;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.oauth.StatefulAuthHelper;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class RedditAccountHelper
{

    public void login(String username, String password, OnAccountLogin onAccountLogin)
    {
        
    }


}
