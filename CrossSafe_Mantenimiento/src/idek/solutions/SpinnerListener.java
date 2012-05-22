package idek.solutions;

import idek.solutions.modelos.Dispositivo;
import idek.solutions.modelos.Incidencia;

import java.io.*;
import com.google.gson.Gson;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class SpinnerListener implements OnItemSelectedListener{

	
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		DescripcionIncidencia.selected= parent.getItemAtPosition(pos).toString();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}