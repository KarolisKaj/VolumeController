package empty.volumecontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import empty.volumecontroller.Listeners.SeekBarListener;
import empty.volumecontroller.Services.LanDiscovery;

public class ControllerActivity extends AppCompatActivity {
    private Presenter _presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        _presenter = new Presenter(this, new LanDiscovery());
        CreateEvents();

    }

    private void CreateEvents()
    {
        ((SeekBar)findViewById(R.id.seekBar)).setOnSeekBarChangeListener(new SeekBarListener()
        {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                _presenter.HandleVolumeChanged(progress);
            }
        });
    }

}
