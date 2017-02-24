package empty.volumecontroller.Services;

import android.util.Log;

import java.net.InetAddress;
import java.net.InterfaceAddress;
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
    public InetAddress GetActiveDevices(String port) {
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
                    if (!inetAddress.isLoopbackAddress() && inetAddress.isLinkLocalAddress()) {
                        ConvertToBroadcast(inetAddress);
                        Log.d("Verbose", "Found local address: " + inetAddress.getHostAddress().toString());
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
