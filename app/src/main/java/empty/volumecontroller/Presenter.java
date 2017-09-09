package empty.volumecontroller;

import java.net.InetAddress;
import java.util.concurrent.CompletableFuture;

import empty.volumecontroller.Contracts.IDeviceControllerService;
import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Contracts.ServerConfig;


/**
 * Created by Karolis on 12/24/2016.
 */
public class Presenter {
    private ILanDiscovery _lanDiscovery;
    private ViewModel _viewModel;
    private InetAddress _hostIp;
    private IDeviceControllerService _deviceControllerService;

    public Presenter(ILanDiscovery lanDiscovery, IDeviceControllerService deviceControllerService, ViewModel viewModel) {
        _viewModel = viewModel;
        _lanDiscovery = lanDiscovery;
        _deviceControllerService = deviceControllerService;
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
                    _viewModel.setDeviceName(_hostIp.getHostAddress());
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
            _deviceControllerService.UpdateDeviceVolume(_hostIp, (int)arg).whenCompleteAsync((value, error) ->
            {

            });
            // TODO: Call API to change volume on device accordingly
        });
        _viewModel.subscribeTosearchButtonInvokeChange((o, arg) -> {
            HandleButtonClick();
        });
    }
}
