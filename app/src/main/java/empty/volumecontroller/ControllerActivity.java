package empty.volumecontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import java.util.Observable;
import java.util.Observer;

public class ControllerActivity extends AppCompatActivity {
private ControlModel _model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        _model = new ControlModel((SeekBar)findViewById(R.id.seekBar));

    }

}
