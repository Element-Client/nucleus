package elemental.reddit;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import net.dean.jraw.RedditClient;
import net.dean.jraw.android.AndroidHelper;
import net.dean.jraw.android.AppInfoProvider;
import net.dean.jraw.android.ManifestAppInfoProvider;
import net.dean.jraw.android.SharedPreferencesTokenStore;
import net.dean.jraw.android.SimpleAndroidLogAdapter;
import net.dean.jraw.http.LogAdapter;
import net.dean.jraw.http.SimpleHttpLogger;
import net.dean.jraw.oauth.AccountHelper;
import net.dean.jraw.oauth.StatefulAuthHelper;

import java.util.UUID;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class ElementalApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        //Get useragent and oauth2 data
        AppInfoProvider appInfoProvider = new ManifestAppInfoProvider(getApplicationContext());
        //Create a device id
        UUID uuid = UUID.randomUUID();
        //Create token store
        SharedPreferencesTokenStore tokenStore = new SharedPreferencesTokenStore(getApplicationContext());
        //Load token store
        tokenStore.load();
        //Automatically save new tokens
        tokenStore.setAutoPersist(true);
        //Create an account helper
        AccountHelper helper = AndroidHelper.accountHelper(appInfoProvider, uuid, tokenStore);
        //On switch for account
        helper.onSwitch(new Function1<RedditClient, Unit>() {
            @Override
            public Unit invoke(RedditClient redditClient) {
                //Create log adapter
                LogAdapter logAdapter = new SimpleAndroidLogAdapter(Log.INFO);
                //Set the logger
                redditClient.setLogger(new SimpleHttpLogger(SimpleHttpLogger.DEFAULT_LINE_LENGTH, logAdapter));

                return null;
            }
        });

    }
}
