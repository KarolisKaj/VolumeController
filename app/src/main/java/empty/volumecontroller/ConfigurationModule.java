package empty.volumecontroller;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

/**
 * Created by Karolis on 6/2/2017.
 */

public class ConfigurationModule extends AbstractModule {
    @Override
    protected void configure() {

        Guice.createInjector();
    }
}
