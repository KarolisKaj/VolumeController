package empty.volumecontroller;

import android.widget.SeekBar;

import java.util.Observable;

import empty.volumecontroller.Listeners.SeekBarListener;

/**
 * Created by Karolis on 12/24/2016.
 */

public class ControlModel extends Observable
{
    public SeekBarListener seekBarListener;
    public ControlModel(SeekBar seekBar)
    {
        seekBarListener = new   SeekBarListener(){public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {notifyObservers(seekBar); }};
        seekBar.setOnSeekBarChangeListener(seekBarListener);
    }
}
