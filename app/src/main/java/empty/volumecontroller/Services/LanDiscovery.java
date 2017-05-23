package empty.volumecontroller.Services;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Contracts.ServerConfig;

/**
 * Created by Karolis on 2/24/2017.
 */

public class LanDiscovery implements ILanDiscovery {

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
                        Log.d("Error", "Well that happend. Exception.");
                    }
                }
            }

            byte[] recvBuf = new byte[15000];
            DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length,InetAddress.getByName("0.0.0.0"), port);
            c.receive(receivePacket); // Wait a lil

            String message = new String(receivePacket.getData()).trim();

            if (message.equals(ServerConfig.ServerResponseString)) {
                return receivePacket.getAddress();
            }
        } catch (IOException ex) {
            Log.d("Error", "Ups, failed to broadcast.");
        } finally {
            if (c != null)
                c.close();
        }
        throw new IllegalArgumentException("No server found. Shutting down.");
    }
}
