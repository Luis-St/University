package net.luis.aas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import net.luis.R;
import net.luis.k.SachbearbeiterErfassenK;

public class SachbearbeiterErfassenAAS extends Activity {

    private final SachbearbeiterErfassenK kontrolle = new SachbearbeiterErfassenK();

    private EditText benutzerTextFeld;
    private EditText passwortTextFeld;
    private RadioButton normalAuswahlKnopf;
    private RadioButton adminAuswahlKnopf;
    private RadioGroup berechtigungsGruppe;
    private TextView fehlerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sachbearbeiter_erfassen);

        benutzerTextFeld = findViewById(R.id.benutzerTextFeld);
        passwortTextFeld = findViewById(R.id.passwortTextFeld);
        normalAuswahlKnopf = findViewById(R.id.normalAuswahlKnopf);
        adminAuswahlKnopf = findViewById(R.id.adminAuswahlKnopf);
        berechtigungsGruppe = findViewById(R.id.berechtigungsGruppe);
        fehlerLabel = findViewById(R.id.fehlerLabel);

        Button abbruchKnopf = findViewById(R.id.abbruchKnopf);
        abbruchKnopf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button speichernKnopf = findViewById(R.id.speichernKnopf);
        speichernKnopf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!benutzerTextFeld.getText().toString().isEmpty() && !passwortTextFeld.getText().toString().isEmpty()) {
                    try {
                        kontrolle.erzeugeSachbearbeiter(benutzerTextFeld.getText().toString(), passwortTextFeld.getText().toString(), !normalAuswahlKnopf.isChecked());
                        finish();
                    } catch (Exception fehler) {
                        fehlerLabel.setText(fehler.getMessage());
                    }
                } else {
                    fehlerLabel.setText("Benutzername oder Passwort leer");
                }
            }
        });
		
		fehlerLabel.setText("");
    }
}