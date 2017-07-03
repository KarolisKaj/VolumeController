package empty.volumecontroller;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.inject.Inject;

import java.util.Observable;
import java.util.Observer;

import empty.volumecontroller.Listeners.ButtonListener;
import empty.volumecontroller.Listeners.SeekBarListener;

/**
 * Created by Karolis on 6/2/2017.
 */

public class View {
    private ControllerActivity _view;
    private ViewModel _viewModel;

    @Inject
    public void View(ControllerActivity view, ViewModel viewModel) {
        _view = view;
        _viewModel = viewModel;
        CreateEvents();
    }

    private void CreateEvents() {
        ((SeekBar) _view.findViewById(R.id.seekBar)).setOnSeekBarChangeListener(new SeekBarListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _viewModel.setVolume(progress);
            }
        });

        _view.findViewById(R.id.button).setOnClickListener(new ButtonListener() {
            public void onClick(android.view.View v) {
                _viewModel.searchButtonInvoked();
            }
        });

        _viewModel.subscribeToDeviceNameChange((o, arg) -> setDeviceName((String) arg));
        _viewModel.subscribeToVolumeChange((o, arg) -> {
            if (((SeekBar) _view.findViewById(R.id.seekBar)).getProgress() == (int) arg)
                ((SeekBar) _view.findViewById(R.id.seekBar)).setProgress((int) arg);
        });
    }

    public void setDeviceName(String name) {
        ((TextView) _view.findViewById(R.id.textDevice)).setText(name);
    }

    public void setButtonInteractable(boolean isInteractable) {
        ((Button) _view.findViewById(R.id.button)).setClickable(isInteractable);
    }
}
