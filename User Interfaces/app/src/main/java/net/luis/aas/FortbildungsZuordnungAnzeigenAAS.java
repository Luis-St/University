package net.luis.aas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import net.luis.R;
import net.luis.Sachbearbeiter;
import net.luis.k.FortbildungsZuordnungAnzeigenK;

public class FortbildungsZuordnungAnzeigenAAS extends Activity {

    private static final String[] EMPTY_DATA = new String[0];

    private final FortbildungsZuordnungAnzeigenK kontrolle = new FortbildungsZuordnungAnzeigenK();

    private TextView sachbearbeiterNameLabel;
    private RadioGroup fortbildungsTypGruppe;
    private ListView fortbildungsListe;

    private Sachbearbeiter sachbearbeiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortbildungs_zuordnung_anzeigen);

        sachbearbeiterNameLabel = findViewById(R.id.sachbearbeiterNameLabel);
        fortbildungsTypGruppe = findViewById(R.id.fortbildungsTypGruppe);
        fortbildungsListe = findViewById(R.id.fortbildungsListe);
        RadioButton belegtAuswahlKnopf = findViewById(R.id.belegtAuswahlKnopf);
        RadioButton bestandenAuswahlKnopf = findViewById(R.id.bestandenAuswahlKnopf);
        Button zurueckKnopf = findViewById(R.id.zurueckKnopf);

        bestandenAuswahlKnopf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fortbildungsListe.setAdapter(new ArrayAdapter<>(FortbildungsZuordnungAnzeigenAAS.this, android.R.layout.simple_list_item_1, kontrolle.gibBestandeneFortbildung(sachbearbeiter.gibBenutzername())));
            }
        });

        belegtAuswahlKnopf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fortbildungsListe.setAdapter(new ArrayAdapter<>(FortbildungsZuordnungAnzeigenAAS.this, android.R.layout.simple_list_item_1, kontrolle.gibBelegteFortbildung(sachbearbeiter.gibBenutzername())));
            }
        });

        zurueckKnopf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
		
		Intent intent = getIntent();
		sachbearbeiter = intent.getSerializableExtra("net.luis.sachbearbeiter", Sachbearbeiter.class);
		sachbearbeiterNameLabel.setText(sachbearbeiter.gibBenutzername());
		fortbildungsTypGruppe.clearCheck();
		fortbildungsListe.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, EMPTY_DATA));
    }
}