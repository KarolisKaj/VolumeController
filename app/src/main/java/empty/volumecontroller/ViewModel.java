package empty.volumecontroller;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Karolis on 6/2/2017.
 */

public class ViewModel {

    private Observable _deviceNameObservable = new Observable();
    public void setDeviceName(String name)
    {
        _deviceNameObservable.notifyObservers(name);
    }
    public void subscribeToDeviceNameChange(Observer observer)
    {
        _deviceNameObservable.addObserver(observer);
    }
    
    private Observable _searchButtonObservable = new Observable();
    public void searchButtonInvoked(String name)
    {
        _searchButtonObservable.notifyObservers(name);
    }
    public void subscribeTosearchButtonInvoked(Observer observer)
    {
        _searchButtonObservable.addObserver(observer);
    }
}
