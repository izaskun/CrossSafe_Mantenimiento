package idek.solutions;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ListaListener implements OnItemClickListener {

	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		
		Intent intent = new Intent ("android.intent.action.DESCRIPCION");
		
		DescripcionIncidencia incidencia = new DescripcionIncidencia();
		incidencia.startActivity(intent);
	}

}
