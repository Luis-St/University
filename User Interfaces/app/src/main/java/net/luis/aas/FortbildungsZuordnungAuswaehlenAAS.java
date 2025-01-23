package net.luis.aas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.luis.R;
import net.luis.k.FortbildungsZuordnungAuswaehlenK;

public class FortbildungsZuordnungAuswaehlenAAS extends Activity {

    private FortbildungsZuordnungAuswaehlenK kontrolle = new FortbildungsZuordnungAuswaehlenK();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortbildungs_zuordnung_auswaehlen);

        Button schliessenButton = findViewById(R.id.schliessenButton);
        schliessenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}