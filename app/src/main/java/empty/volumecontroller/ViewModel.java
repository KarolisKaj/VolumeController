package empty.volumecontroller;

import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import empty.volumecontroller.Utilities.NotifyingObserver;

/**
 * Created by Karolis on 6/2/2017.
 */

public class ViewModel {

    private Observable _deviceNameObservable = new NotifyingObserver();
    public void setDeviceName(String name)
    {
        _deviceNameObservable.notifyObservers(name);
    }
    public void subscribeToDeviceNameChange(Observer observer)
    {
        _deviceNameObservable.addObserver(observer);
    }
    private Observable _volumeObservable = new NotifyingObserver();
    public void setVolume(int volume)
    {
        _volumeObservable.notifyObservers(volume);
    }
    public void subscribeToVolumeChange(Observer observer)
    {
        _volumeObservable.addObserver(observer);
    }

    private Observable _searchButtonObservable = new NotifyingObserver();
    public void searchButtonInvoked()
    {
        _searchButtonObservable.notifyObservers();
    }
    public void subscribeTosearchButtonInvokeChange(Observer observer)
    {
        _searchButtonObservable.addObserver(observer);
    }

    private Observable _isButtonInteractableObservable = new NotifyingObserver();
    public void setIsButtonInteractable(boolean value)
    {
        _isButtonInteractableObservable.notifyObservers(value);
    }
    public void subscribeToIsButtonInteractableChange(Observer observer)
    {
        _isButtonInteractableObservable.addObserver(observer);
    }
}
