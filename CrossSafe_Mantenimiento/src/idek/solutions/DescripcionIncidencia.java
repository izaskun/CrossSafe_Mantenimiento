package idek.solutions;

import idek.solutions.modelos.Incidencia;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DescripcionIncidencia extends Activity {

	Incidencia inci;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int position = getIntent().getIntExtra("POSITION", -1);

		if (position > -1) {
			inci = CrossSafe_MantenimientoActivity.listaIncidencias[position];
		}

		setContentView(R.layout.descinci);

		TextView id = (TextView) findViewById(R.id.dispId);
		TextView loc = (TextView) findViewById(R.id.dispLocalidad);
		TextView estado = (TextView) findViewById(R.id.dispStatus);
		TextView desc = (TextView) findViewById(R.id.dispDesc);

		id.setText("Id: " + inci.getIncidenciaDisp().getDispId());

		loc.setText("Localidad: " + inci.getIncidenciaDisp().getDispLoc());

		estado.setText("Estado: " + inci.getIncidenciaDisp().getDispEst());

		desc.setText("Descripcion: " + inci.getIncidenceDesc());

	}

}
