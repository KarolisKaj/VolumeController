package empty.volumecontroller.Services;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;

import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Contracts.ServerConfig;

import static empty.volumecontroller.Contracts.ServerConfig.TCPPort;

/**
 * Created by Karolis on 2/24/2017.
 */

public class LanDiscovery implements ILanDiscovery {

    private TCPProvider _tcpProvider;

    public void LanDiscovery(TCPProvider tcpProvider) {
        _tcpProvider = tcpProvider;
    }

    @Override
    public InetAddress GetServer(int port) {
        DatagramSocket c = null;
        try {
            c = new DatagramSocket();
            c.setBroadcast(true);
            byte[] sendData = ServerConfig.ClientRequestString.getBytes();

            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();
                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue;
                }
                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if (broadcast == null) {
                        continue;
                    }
                    try {

                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, port);
                        c.send(sendPacket);
                    } catch (Exception e) {
                        Log.d("Error", "Well that happen. Exception.");
                    }
                }
            }

        } catch (IOException ex) {
            Log.d("Error", "Ups, failed to broadcast.");
        } finally {
            if (c != null)
                c.close();
        }
        throw new IllegalArgumentException("No server found. Shutting down.");
    }

    private InetAddress StartTCPListener() {
        try {
            Socket connectionSocket = _tcpProvider.getTcpClient(TCPPort);
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            String clientSentence = inFromClient.readLine();

            if (clientSentence.equals(ServerConfig.ServerResponseString)) {
                return connectionSocket.getInetAddress();
            }
        } catch (Exception ex) {
        }
        throw new IllegalArgumentException("No server found. Shutting down.");
    }
}
