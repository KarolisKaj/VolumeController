package empty.volumecontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import empty.volumecontroller.Listeners.ButtonListener;
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

        ((Button)findViewById(R.id.button)).setOnClickListener(new ButtonListener(){
            public void onClick(View v)
            {
                _presenter.HandleButtonClick();
            }
        });
    }

    public void setDeviceName(String name)
    {
        ((TextView)findViewById(R.id.textDevice)).setText(name);
    }

    public String getDeviceName()
    {
      return   (String)((TextView)findViewById(R.id.textDevice)).getText();
    }

    public void setButtonInteractable(boolean isInteractable)
    {
        ((Button)findViewById(R.id.button)).setClickable(isInteractable);
    }
}
