package empty.volumecontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import empty.volumecontroller.Contracts.ILanDiscovery;

public class ControllerActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        Bootstrapper bootstrapper = new Bootstrapper();
        bootstrapper.Create(this);
    }
}
