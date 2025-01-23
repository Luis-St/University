package net.luis.aas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.luis.R;
import net.luis.k.FortbildungZuordnenK;

public class FortbildungZuordnenAAS extends Activity {

    private final FortbildungZuordnenK kontrolle = new FortbildungZuordnenK();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortbildung_zuordnen);

        Button schliessenButton = findViewById(R.id.schliessenButton);
        schliessenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}