package empty.volumecontroller.Contracts;

import java.net.InetAddress;
import java.util.concurrent.CompletableFuture;

import empty.volumecontroller.Model.VolumeInformationResponse;
import empty.volumecontroller.Model.VolumeUpdateResponse;

/**
 * Created by WarHorse on 9/9/2017.
 */

public interface IDeviceControllerService {
    CompletableFuture<VolumeUpdateResponse> UpdateDeviceVolume(InetAddress hostIp, int volume);
    CompletableFuture<VolumeInformationResponse> GetDeviceInformation(InetAddress hostIp);

}
