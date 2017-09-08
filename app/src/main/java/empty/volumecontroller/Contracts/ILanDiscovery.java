package empty.volumecontroller.Contracts;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.Future;

/**
 * Created by Karolis on 2/24/2017.
 */

public interface ILanDiscovery {
    String Broadcast(int port);
    InetAddress ListenTillReceivedInfoFromServer();
}
