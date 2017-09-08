package empty.volumecontroller.Services;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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

    ServerSocket _connectionSocket;
    public LanDiscovery()
    { try{
        _connectionSocket = new ServerSocket(TCPPort);
    }catch (Exception e){}}


    @Override
    public String Broadcast(int port) {
        try (DatagramSocket c = new DatagramSocket()){
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
        }
        return null;
    }

    public InetAddress ListenTillReceivedInfoFromServer() {
        try(Socket connectionSocket = _connectionSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));) {
            String clientSentence = inFromClient.readLine();

            if (clientSentence.equals(ServerConfig.ServerResponseString)) {
                return connectionSocket.getInetAddress();
            }
        } catch (Exception ex) {
            Log.d("Error", "Ups, TCP failed.");
        }
        return null;
    }
}
