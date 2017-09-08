package empty.volumecontroller.Services;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;


import empty.volumecontroller.Contracts.ITCPProviderService;


/**
 * Created by Karolis on 6/1/2017.
 */

public class TCPProvider implements ITCPProviderService {

    public TCPProvider(){}
    private ConcurrentHashMap _tcpClients = new ConcurrentHashMap();

    @Override
    public Socket getTcpClient(int port) {
        if (_tcpClients.containsKey(port))
            return (Socket) _tcpClients.get(port);
        Socket connectionSocket;
        try {
            connectionSocket = new ServerSocket(port).accept();
            _tcpClients.put(port, connectionSocket);
            return connectionSocket;
        }
        catch (Exception ex)
        {
            // TODO: Log
        }
        return null;
    }
}
