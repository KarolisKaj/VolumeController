package empty.volumecontroller.Services;

import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Exchanger;

import empty.volumecontroller.Contracts.IDeviceControllerService;
import empty.volumecontroller.Contracts.ServerConfig;
import empty.volumecontroller.Model.VolumeInformationResponse;
import empty.volumecontroller.Model.VolumeUpdateResponse;

/**
 * Created by WarHorse on 9/9/2017.
 */

public class ComputerControllerService implements IDeviceControllerService {

    @Override
    public CompletableFuture<VolumeUpdateResponse> UpdateDeviceVolume(InetAddress hostIp, int volume) {
        try {
            String requestURL = "http://" + hostIp.getHostAddress() + ":" + ServerConfig.Port + "/api/volume/0f8fad5b-d9cb-469f-a165-70867728950e/" + volume;
            return CompletableFuture.supplyAsync(() -> RestUtilityService.Post(requestURL));
        } catch (Exception e) {
            Log.d("Something", e.getMessage());
        }
        return null;
    }

    @Override
    public CompletableFuture<VolumeInformationResponse> GetDeviceInformation(InetAddress hostIp) {
        return null;
        //CompletableFuture.supplyAsync(() -> {

//        });
    }
}
