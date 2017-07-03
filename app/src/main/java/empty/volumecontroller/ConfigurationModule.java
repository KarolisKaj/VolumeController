package empty.volumecontroller;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

import empty.volumecontroller.Contracts.ILanDiscovery;
import empty.volumecontroller.Contracts.ITCPProviderService;
import empty.volumecontroller.Services.LanDiscovery;
import empty.volumecontroller.Services.TCPProvider;

/**
 * Created by Karolis on 6/2/2017.
 */

public class ConfigurationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ViewModel.class).to(ViewModel.class);
        bind(ITCPProviderService.class).to(TCPProvider.class);
        bind(ILanDiscovery.class).to(LanDiscovery.class);
        bind(Presenter.class).to(Presenter.class);
        bind(View.class).to(View.class);
    }
}
