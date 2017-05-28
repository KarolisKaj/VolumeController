package empty.volumecontroller;

import java.net.InetAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Contracts.ServerConfig;


/**
 * Created by Karolis on 12/24/2016.
 */

public class Presenter {
    private ControllerActivity _view;
    private Model _model;
    private ILanDiscovery _lanDiscovery;
    private InetAddress volumeHostAddress;

    public Presenter(ControllerActivity view, ILanDiscovery lanDiscovery)
    {
        _view = view;
        _model = new Model();
        _lanDiscovery = lanDiscovery;


    }

    public void HandleVolumeChanged(int volume)
    {

    }

    public void HandleButtonClick()  {
        try {
            CompletableFuture.supplyAsync(() -> _lanDiscovery.GetServer(ServerConfig.UDPPort)).get();
        }
        catch (Exception ex){}
       // volumeHostAddress = _lanDiscovery.GetServer(ServerConfig.UDPPort);
    }
}
