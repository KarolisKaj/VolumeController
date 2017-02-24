package empty.volumecontroller.Contracts;

import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Karolis on 2/24/2017.
 */

public interface ILanDiscovery {
    InetAddress GetActiveDevices(int port);
    InetAddress GetLanBroadcastIP();
}
