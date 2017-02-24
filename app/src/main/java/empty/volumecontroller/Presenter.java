package empty.volumecontroller;

import android.support.annotation.RequiresPermission;
import android.widget.SeekBar;

import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Contracts.ServerConfig;

/**
 * Created by Karolis on 12/24/2016.
 */

public class Presenter {
    private ControllerActivity _view;
    private Model _model;
    private ILanDiscovery _lanDiscovery;
    public Presenter(ControllerActivity view, ILanDiscovery lanDiscovery)
    {
        _view = view;
        _model = new Model();
        _lanDiscovery = lanDiscovery;
        _lanDiscovery.GetActiveDevices(ServerConfig.UDPPort);
    }

    public void HandleVolumeChanged(int volume)
    {

    }
}
