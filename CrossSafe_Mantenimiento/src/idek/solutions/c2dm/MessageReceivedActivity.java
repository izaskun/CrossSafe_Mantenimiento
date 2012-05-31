package idek.solutions.c2dm;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import idek.solutions.CrossSafe_Mantenimiento;
import idek.solutions.Mantenimiento;
import idek.solutions.R;
import idek.solutions.modelos.Incidencia;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MessageReceivedActivity extends Activity {
	
	Incidencia inci;
	String id_inci;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id_inci = extras.getString("payload");
		}

		CrossSafe_Mantenimiento.getAndroidId(this);
		runJSONParser();
		for (int i = 0; i < CrossSafe_Mantenimiento.listaIncidencias.length; i++) {
			if (CrossSafe_Mantenimiento.listaIncidencias[i].getIncidenceId()==Integer.valueOf(id_inci)) {
				inci = CrossSafe_Mantenimiento.listaIncidencias[i];
			}
		}
		
		setContentView(R.layout.aviso);

		TextView id = (TextView) findViewById(R.id.dispId);
		TextView loc = (TextView) findViewById(R.id.dispLocalidad);
		TextView estado = (TextView) findViewById(R.id.dispStatus);
		TextView desc = (TextView) findViewById(R.id.dispDesc);

		id.setText("Id: " + inci.getIncidenciaDisp().getDispId());

		loc.setText("Localidad: " + inci.getIncidenciaDisp().getDispLoc());

		estado.setText("Estado: " + inci.getIncidenciaDisp().getDispEst());

		desc.setText("Descripcion: " + inci.getIncidenceDesc());
		
	}

	public void runJSONParser() {
		try {
			Gson gson = new Gson();
			Reader r = new InputStreamReader(
					getJSONData(CrossSafe_Mantenimiento.HOST
							+ "idek_cross/incidencias/"
							+ CrossSafe_Mantenimiento.android_id));


			CrossSafe_Mantenimiento.listaIncidencias = gson.fromJson(r,
					Incidencia[].class);

		} catch (Exception ex) {

		}

	}

	public InputStream getJSONData(String url) {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		URI uri;
		InputStream data = null;

		try {
			uri = new URI(url);
			String h = uri.getPath();
			HttpGet method = new HttpGet(uri);
			HttpResponse response = httpClient.execute(method);
			data = response.getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
