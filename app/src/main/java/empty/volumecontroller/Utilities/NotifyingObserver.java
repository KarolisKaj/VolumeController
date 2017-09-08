package empty.volumecontroller.Utilities;

import java.util.Observable;

/**
 * Created by WarHorse on 9/8/2017.
 */

public class NotifyingObserver extends Observable {
    public NotifyingObserver() {  setChanged(); }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
        setChanged();
    }
}
