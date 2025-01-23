package net.luis.aas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import net.luis.R;
import net.luis.Sachbearbeiter;
import net.luis.as.AdminAS;
import net.luis.as.NormalAS;
import net.luis.k.LoginK;

public class LoginAAS extends Activity {
	
	private EditText benutzerFeld;
	private EditText passwortFeld;
	private CheckBox adminCheckBox;
	private TextView nachrichtLabel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(net.luis.R.layout.activity_login);
		
		benutzerFeld = findViewById(R.id.benutzerFeld);
		passwortFeld = findViewById(R.id.passwortFeld);
		adminCheckBox = findViewById(R.id.adminCheckBox);
		nachrichtLabel = findViewById(R.id.nachrichtLabel);
		Button loginButton = findViewById(R.id.loginButton);
		
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				verarbeiteLogin();
			}
		});
	}
	
	private void verarbeiteLogin() {
		String benutzername = benutzerFeld.getText().toString();
		String passwort = passwortFeld.getText().toString();
		boolean istAdmin = adminCheckBox.isChecked();
		
		if (benutzername.isEmpty() || passwort.isEmpty()) {
			nachrichtLabel.setText("Benutzername und Passwort dürfen nicht leer sein.");
			return;
		}
		
		try {
			Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(benutzername);
			if (sachbearbeiter == null) {
				throw new IllegalStateException("Benutzer nicht gefunden.");
			}
			if (LoginK.passwortPasstZuBenutzername(sachbearbeiter.gibBenutzername(), passwort)) {
				if (LoginK.gewaehlteBerechtigungPasstZuSachbearbeiter(sachbearbeiter.gibBenutzername(), istAdmin)) {
					if (istAdmin) {
						if (LoginK.istAdmin(sachbearbeiter.gibBenutzername())) {
							nachrichtLabel.setText("Eingeloggt als Admin: " + sachbearbeiter.gibBenutzername());
							startActivity(new Intent(this, AdminAS.class));
						} else {
							throw new IllegalStateException("Nicht berechtigt als Admin einzuloggen.");
						}
					} else {
						nachrichtLabel.setText("Eingeloggt als Benutzer: " + sachbearbeiter.gibBenutzername());
						startActivity(new Intent(this, NormalAS.class));
					}
				} else {
					throw new IllegalStateException("Berechtigung passt nicht zum Benutzer.");
				}
			} else {
				nachrichtLabel.setText("Falsches Passwort!");
			}
		} catch (Exception e) {
			nachrichtLabel.setText(e.getMessage());
			e.printStackTrace();
		}
	}
}