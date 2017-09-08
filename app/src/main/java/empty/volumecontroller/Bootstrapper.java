package empty.volumecontroller;

import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Services.LanDiscovery;


/**
 * Created by Karolis on 6/1/2017.
 */

public class Bootstrapper {


    public void Create(ControllerActivity activity) {
        ILanDiscovery landDiscovery = new LanDiscovery();

        ViewModel vm = new ViewModel();
        View view = new View(activity, vm);
        Presenter presenter = new Presenter(landDiscovery, vm);
    }
}
