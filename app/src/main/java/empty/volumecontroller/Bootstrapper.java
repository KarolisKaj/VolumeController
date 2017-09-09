package empty.volumecontroller;

import empty.volumecontroller.Contracts.IDeviceControllerService;
import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Services.ComputerControllerService;
import empty.volumecontroller.Services.LanDiscovery;


/**
 * Created by Karolis on 6/1/2017.
 */

public class Bootstrapper {


    public void Create(ControllerActivity activity) {
        ILanDiscovery landDiscovery = new LanDiscovery();
        IDeviceControllerService deviceControllerService = new ComputerControllerService();

        ViewModel vm = new ViewModel();
        View view = new View(activity, vm);
        Presenter presenter = new Presenter(landDiscovery, deviceControllerService, vm);
    }
}
