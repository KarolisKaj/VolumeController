package empty.volumecontroller.Services;

import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import empty.volumecontroller.Contracts.ServerConfig;

/**
 * Created by WarHorse on 9/9/2017.
 */

public class RestUtilityService {
    public static <T> T Post(String url) {
        return Request(url, true);
    }

    public static <T> T Get(String url) {
        return Request(url, false);
    }


    // TODO : sort out json convertion
    private static <T> T Request(String url, boolean isPost) {
        try {
            Log.d("Something", url);

            URLConnection connection = new URL(url).openConnection();
            if (isPost)
                connection.setDoOutput(true); //POST
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            InputStream response = connection.getInputStream();
            StringBuilder responseStrBuilder = new StringBuilder();
            responseStrBuilder.append(response);
            JSONObject json = new JSONObject(responseStrBuilder.toString());
            Log.d("Json", json.toString());

            return (T) json;
        } catch (Exception ex) {

        }
        return null;
    }
}
