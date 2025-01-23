package net.luis.aas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import net.luis.R;
import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterAuswaehlenK;

import java.util.Arrays;

public class SachbearbeiterAuswaehlenAAS extends Activity {
	
    private final SachbearbeiterAuswaehlenK kontrolle = new SachbearbeiterAuswaehlenK();
	
    private Spinner benutzerAuswahl;
	private Class<?> naechstesPanel;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sachbearbeiter_auswaehlen);

        benutzerAuswahl = findViewById(R.id.benutzerAuswahl);

        Button abbruchKnopf = findViewById(R.id.abbruchKnopf);
        abbruchKnopf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button weiterKnopf = findViewById(R.id.weiterKnopf);
        weiterKnopf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				if (benutzerAuswahl.getSelectedItem() == null) {
					return;
				}
				Sachbearbeiter sachbearbeiter = kontrolle.gibSachbearbeiter(String.valueOf(benutzerAuswahl.getSelectedItem()));
				Intent intent = new Intent(SachbearbeiterAuswaehlenAAS.this, naechstesPanel);
				intent.putExtra("net.luis.sachbearbeiter", sachbearbeiter);
				startActivity(intent);
                finish();
            }
        });
		
		Intent intent = getIntent();
		naechstesPanel = intent.getSerializableExtra("net.luis.naechstesPanel", Class.class);
		Log.i("SachbearbeiterAuswaehlenAAS", "naechstesPanel: " + naechstesPanel);
		String[] elements = kontrolle.gibSachbearbeiterNamen();
		Log.i("SachbearbeiterAuswaehlenAAS", "Sachbearbeiter: " + Arrays.toString(elements));
		
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, elements);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		benutzerAuswahl.setAdapter(adapter);
    }
}