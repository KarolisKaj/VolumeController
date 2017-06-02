package empty.volumecontroller;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import empty.volumecontroller.Listeners.ButtonListener;
import empty.volumecontroller.Listeners.SeekBarListener;

/**
 * Created by Karolis on 6/2/2017.
 */

public class View {
    private ControllerActivity _view;
    public void View(ControllerActivity view) {
        _view = view;
        CreateEvents();
    }
    private void CreateEvents() {
        ((SeekBar) _view.findViewById(R.id.seekBar)).setOnSeekBarChangeListener(new SeekBarListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //  _presenter.HandleVolumeChanged(progress);
            }
        });

        ((Button) _view.findViewById(R.id.button)).setOnClickListener(new ButtonListener() {
            public void onClick(android.view.View v) {
                //    _presenter.HandleButtonClick();
            }
        });
    }

    public void setDeviceName(String name) {
        ((TextView) _view.findViewById(R.id.textDevice)).setText(name);
    }

    public String getDeviceName() {
        return (String) ((TextView) _view.findViewById(R.id.textDevice)).getText();
    }

    public void setButtonInteractable(boolean isInteractable) {
        ((Button) _view.findViewById(R.id.button)).setClickable(isInteractable);
    }
}
