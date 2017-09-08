package empty.volumecontroller;

import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Contracts.ITCPProviderService;
import empty.volumecontroller.Services.LanDiscovery;
import empty.volumecontroller.Services.TCPProvider;


/**
 * Created by Karolis on 6/1/2017.
 */

public class Bootstrapper {


    public void Create(ControllerActivity activity) {
        ITCPProviderService itcpProviderService = new TCPProvider();
        ILanDiscovery landDiscovery = new LanDiscovery(itcpProviderService);

        ViewModel vm = new ViewModel();
        View view = new View(activity, vm);
        Presenter presenter = new Presenter(landDiscovery, vm);
    }
}
