package empty.volumecontroller;

import java.net.InetAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Contracts.ServerConfig;

import static java.util.concurrent.CompletableFuture.supplyAsync;

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

    public void HandleButtonClick(){
       // CompletableFuture.supplyAsync(() -> _lanDiscovery.GetServer(ServerConfig.UDPPort)).get();

       // volumeHostAddress = _lanDiscovery.GetServer(ServerConfig.UDPPort);
    }
}
