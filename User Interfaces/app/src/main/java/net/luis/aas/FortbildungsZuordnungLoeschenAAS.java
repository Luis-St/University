package net.luis.aas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.luis.R;
import net.luis.k.FortbildungsZuordnungLoeschenK;

public class FortbildungsZuordnungLoeschenAAS extends Activity {

    private FortbildungsZuordnungLoeschenK kontrolle = new FortbildungsZuordnungLoeschenK();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortbildungs_zuordnung_loeschen);

        Button schliessenButton = findViewById(R.id.schliessenButton);
        schliessenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}