package empty.volumecontroller.Services;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import empty.volumecontroller.Contracts.ILanDiscovery;

/**
 * Created by Karolis on 2/24/2017.
 */

public class LanDiscovery implements ILanDiscovery {

    @Override
    public InetAddress GetActiveDevices(int port) {
        DatagramSocket c;
        // Find the server using UDP broadcast
        try {
            //Open a random port to send the package
            c = new DatagramSocket();

            c.setBroadcast(true);
            byte[] sendData = "Volume Controller Service Discovery String.".getBytes();
            //Try the 255.255.255.255 first
            try {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 8888);
                c.send(sendPacket);
                System.out.println(getClass().getName() + ">>> Request packet sent to: 255.255.255.255 (DEFAULT)");
            } catch (Exception e) {
            }
            // Broadcast the message over all the network interfaces
            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface)interfaces.nextElement();
                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue; // Don't want to broadcast to the loopback interface
                }
                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if (broadcast == null) {
                        continue;
                    }
                    // Send the broadcast package!
                    try {

                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, 8888);
                        c.send(sendPacket);
                    } catch (Exception e) {
                    }
                    System.out.println(getClass().getName() + ">>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
                }
            }
            System.out.println(getClass().getName() + ">>> Done looping over all network interfaces. Now waiting for a reply!");
            //Wait for a response
            byte[] recvBuf = new byte[15000];
            DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);

            c.receive(receivePacket);


            //We have a response
            System.out.println(getClass().getName() + ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());


            //Check if the message is correct
            String message = new String(receivePacket.getData()).trim();

            if (message.equals("DISCOVER_FUIFSERVER_RESPONSE")) {

                //DO SOMETHING WITH THE SERVER'S IP (for example, store it in your controller)
                return receivePacket.getAddress();
            }


            //Close the port!
            c.close();

        } catch (IOException ex) {
            Log.d("Error", "Ups, failed to broadcast.");
        }
        return null;
    }

    @Override
    public InetAddress GetLanBroadcastIP() {
        InetAddress ipAddress = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress()) {
                        ipAddress = inetAddress;
                        Log.d("Verbose", "Found local address: " + ipAddress.getHostAddress().toString());
//                        return inetAddress;
                        return ConvertToBroadcast(ipAddress);
                    }
                }
            }
        } catch (SocketException ex) {
            Log.d("Error", "Failed to obtain IP address.");
        }
        return ipAddress;
    }

    private InetAddress ConvertToBroadcast(InetAddress address) {
        try {
            List<InterfaceAddress> addresses = NetworkInterface.getByInetAddress(address).getInterfaceAddresses();
            for (InterfaceAddress inetAddress : addresses) {
                Log.d("Verbose", "Converted into broadcast address: " + inetAddress.getBroadcast().toString());
                return inetAddress.getBroadcast();
            }
        } catch (Exception ex) {
            Log.d("Error", "Can't convert to broadcast!");
        }
        return null;

    }
}
