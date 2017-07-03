package empty.volumecontroller;

import com.google.inject.Inject;

import java.net.InetAddress;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Contracts.ServerConfig;


/**
 * Created by Karolis on 12/24/2016.
 */
public class Presenter {
    private ILanDiscovery _lanDiscovery;
    private InetAddress volumeHostAddress;
    private ViewModel _viewModel;

    @Inject
    public Presenter(ILanDiscovery lanDiscovery, ViewModel viewModel) {
        _viewModel = viewModel;
        _lanDiscovery = lanDiscovery;
        CreateEvents();
    }

    public void HandleVolumeChanged(int volume) {

    }

    public void HandleButtonClick() {
        try {
            _viewModel.setIsButtonInteractable(false);
            CompletableFuture.supplyAsync(() -> _lanDiscovery.GetServer(ServerConfig.UDPPort)).get();
            _viewModel.setDeviceName("Boom");

        } catch (Exception ex) {
        } finally {
            _viewModel.setIsButtonInteractable(true);
        }
        // volumeHostAddress = _lanDiscovery.GetServer(ServerConfig.UDPPort);
    }

    private void CreateEvents() {
        _viewModel.subscribeToVolumeChange((o, arg) -> {
            // TODO: Call API to change volume on device accordingly
        });
        _viewModel.subscribeTosearchButtonInvokeChange((o, arg) -> HandleButtonClick());
    }
}
