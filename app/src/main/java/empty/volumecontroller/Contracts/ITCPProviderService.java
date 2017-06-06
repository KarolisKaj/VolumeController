package empty.volumecontroller.Contracts;

import java.net.Socket;

/**
 * Created by Karolis on 6/6/2017.
 */

public interface ITCPProviderService {
    Socket getTcpClient(int port);
}
