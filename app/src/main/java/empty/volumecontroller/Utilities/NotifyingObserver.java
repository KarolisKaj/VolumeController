package empty.volumecontroller.Utilities;

import java.util.Observable;

/**
 * Created by WarHorse on 9/8/2017.
 */

public class NotifyingObserver extends Observable {
    public NotifyingObserver() {  setChanged(); }
}
