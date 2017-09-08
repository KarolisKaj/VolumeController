package empty.volumecontroller;

import java.net.InetAddress;
import java.util.concurrent.CompletableFuture;

import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Contracts.ServerConfig;


/**
 * Created by Karolis on 12/24/2016.
 */
public class Presenter {
    private ILanDiscovery _lanDiscovery;
    private InetAddress volumeHostAddress;
    private ViewModel _viewModel;
    private InetAddress _hostIp;

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
            CompletableFuture.supplyAsync(() -> {
                CompletableFuture.supplyAsync(() -> _lanDiscovery.ListenTillReceivedInfoFromServer()).whenCompleteAsync((value, error) -> {
                    _hostIp = value;
                });
                return _lanDiscovery.Broadcast(ServerConfig.UDPPort);
            }).whenCompleteAsync((value, error) -> _viewModel.setIsButtonInteractable(true));
        } catch (Exception ex) {
            System.out.print("");
        } finally {
        }
    }

    private void CreateEvents() {
        _viewModel.subscribeToVolumeChange((o, arg) -> {
            _viewModel.setDeviceName("Boom");
            // TODO: Call API to change volume on device accordingly
        });
        _viewModel.subscribeTosearchButtonInvokeChange((o, arg) -> {
            HandleButtonClick();
        });
    }
}
