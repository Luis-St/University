package net.luis.aas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.luis.R;
import net.luis.k.FortbildungAuswaehlenK;

public class FortbildungAuswaehlenAAS extends Activity {
	
	public static final FortbildungAuswaehlenAAS INSTANZ = new FortbildungAuswaehlenAAS();
	
    private final FortbildungAuswaehlenK kontrolle = new FortbildungAuswaehlenK();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortbildung_auswaehlen);

        Button schliessenButton = findViewById(R.id.schliessenButton);
        schliessenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}