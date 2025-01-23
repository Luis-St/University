package net.luis.aas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.luis.R;
import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterLoeschenK;

public class SachbearbeiterLoeschenAAS extends Activity {

    private final SachbearbeiterLoeschenK kontrolle = new SachbearbeiterLoeschenK();

    private TextView benutzerNameLabel;
    private TextView fehlerLabel;

    private Sachbearbeiter sachbearbeiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sachbearbeiter_loeschen);

        benutzerNameLabel = findViewById(R.id.benutzerNameLabel);
        fehlerLabel = findViewById(R.id.fehlerLabel);

        Button abbruchKnopf = findViewById(R.id.abbruchKnopf);
        abbruchKnopf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button loeschenKnopf = findViewById(R.id.loeschenKnopf);
        loeschenKnopf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    kontrolle.loescheSachbearbeiter(sachbearbeiter.gibBenutzername());
                    finish();
                } catch (Exception error) {
                    fehlerLabel.setText(error.getMessage());
                }
            }
        });
		
		Intent intent = getIntent();
		sachbearbeiter = intent.getSerializableExtra("net.luis.sachbearbeiter", Sachbearbeiter.class);
		benutzerNameLabel.setText(sachbearbeiter.gibBenutzername());
		fehlerLabel.setText("");
    }
}