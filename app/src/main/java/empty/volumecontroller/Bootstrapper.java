package empty.volumecontroller;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by Karolis on 6/1/2017.
 */

public class Bootstrapper implements IBootstrapper {
    private Injector _injector;

    public void Create() {
    //    _injector = Guice.createInjector(new ConfigurationModule());
    }

    public void Start() {
    }
}
