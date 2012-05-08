package idek.solutions;

import idek.solutions.modelos.Incidencia;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DescripcionIncidencia extends Activity {

	Incidencia inci;
	Button b;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int position = getIntent().getIntExtra("POSITION", -1);

		if (position > -1) {
			inci = CrossSafe_Mantenimiento.listaIncidencias[position];
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
		
		Spinner s = (Spinner) findViewById(R.id.cbEstado);
		s.setOnItemSelectedListener(new SpinnerListener());
	    ArrayAdapter adapter = ArrayAdapter.createFromResource(
	            this, R.array.estados, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    s.setAdapter(adapter);
	    
	    b = (Button)findViewById(R.id.bGuardar);
	    
	    b.setOnClickListener(new OnClickListener() 
	    {
			
			public void onClick(View v) {
				
				
				Toast msg= Toast.makeText(getBaseContext(), "Datos guardados", Toast.LENGTH_LONG);
				msg.show();
			}
		});

	}

}
