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

	String selected;
	int position;
	final String HOST = "http://192.168.1.101:8888/";
	public static String android_id;
	
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		
		position = pos;
		selected = parent.getItemAtPosition(position).toString();
		
		if (selected != "Averiado") {
			/*Guardar en la BD*/
			CrossSafe_Mantenimiento.listaIncidencias[position].getIncidenciaDisp().setDispEst(selected);
			
			enviarJSON();
		}
		Log.i("IKER","OK");
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

		
	}
	
	public void enviarJSON(){
       
		//DataObject obj = new DataObject();
		 android_id = CrossSafe_Mantenimiento.android_id;
		 
		try{
			Gson gson = new Gson();
			String json = gson.toJson(CrossSafe_Mantenimiento.listaIncidencias[position].getIncidenciaDisp());
			
			FileOutputStream idek = new FileOutputStream(HOST + "idek_cross/incidencias/" + android_id);
	        //Writer w = new OutputStreamWriter(idek);
	        //gson.toJson(w, Incidencia[].class);

	        //Log.i("MY INFO", w.toString());
        }catch(Exception ex){

        }
	
	}

}
