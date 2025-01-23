package net.luis.as;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Toolbar;

import net.luis.R;
import net.luis.aas.*;

public class AdminAS extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
		
		Toolbar toolbar = findViewById(R.id.toolbar);
		setActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.sachbearbeiter_erfassen) {
			startActivity(new Intent(this, SachbearbeiterErfassenAAS.class));
			return true;
		}
		if (item.getItemId() == R.id.sachbearbeiter_bearbeiten) {
			Intent intent = new Intent(this, SachbearbeiterAuswaehlenAAS.class);
			intent.putExtra("net.luis.naechstesPanel", AdminSachbearbeiterBearbeitenAAS.class);
			startActivity(intent);
			return true;
		}
		if (item.getItemId() == R.id.sachbearbeiter_loeschen) {
			Intent intent = new Intent(this, SachbearbeiterAuswaehlenAAS.class);
			intent.putExtra("net.luis.naechstesPanel", SachbearbeiterLoeschenAAS.class);
			startActivity(intent);
			return true;
		}
		if (item.getItemId() == R.id.fortbildung_zuordnen) {
			startActivity(new Intent(this, FortbildungZuordnenAAS.class));
			return true;
		}
		if (item.getItemId() == R.id.fortbildungs_zuordnung_loeschen) {
			startActivity(new Intent(this, FortbildungsZuordnungLoeschenAAS.class));
			return true;
		}
		if (item.getItemId() == R.id.fortbildungs_zuordnung_anzeigen) {
			Intent intent = new Intent(this, SachbearbeiterAuswaehlenAAS.class);
			intent.putExtra("net.luis.naechstesPanel", FortbildungsZuordnungAnzeigenAAS.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
    }
}