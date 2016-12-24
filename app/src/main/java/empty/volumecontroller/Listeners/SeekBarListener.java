package empty.volumecontroller.Listeners;

import android.media.MediaPlayer;
import android.widget.SeekBar;

import java.util.Observable;

/**
 * Created by Karolis on 12/24/2016.
 */

public class SeekBarListener extends Observable implements SeekBar.OnSeekBarChangeListener  {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
       notifyObservers();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {

    }
}
