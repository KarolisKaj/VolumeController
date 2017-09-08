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
    private Observable _volumeObservable = new Observable();
    public void setVolume(int volume)
    {
        _volumeObservable.notifyObservers(volume);
    }
    public void subscribeToVolumeChange(Observer observer)
    {
        _volumeObservable.addObserver(observer);
    }

    private Observable _searchButtonObservable = new Observable();
    public void searchButtonInvoked()
    {
        _searchButtonObservable.notifyObservers(null);
    }
    public void subscribeTosearchButtonInvokeChange(Observer observer)
    {
        _searchButtonObservable.addObserver(observer);
    }

    private Observable _isButtonInteractableObservable = new Observable();
    public void setIsButtonInteractable(boolean value)
    {
        _isButtonInteractableObservable.notifyObservers(value);
    }
    public void subscribeToIsButtonInteractableChange(Observer observer)
    {
        _isButtonInteractableObservable.addObserver(observer);
    }
}
