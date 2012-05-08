package idek.solutions;


import idek.solutions.modelos.Dispositivo;
import idek.solutions.modelos.Incidencia;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Mantenimiento extends ListActivity implements OnItemClickListener{
    
	private OrderAdapter m_adapter;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getListView().setOnItemClickListener(this);
        m_adapter = new OrderAdapter(this, R.layout.mantenimiento, CrossSafe_Mantenimiento.listaIncidencias);
        setListAdapter(m_adapter);
       
    }
    
    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		
		Intent intent = new Intent ("android.intent.action.DESCRIPCION");
		intent.putExtra("POSITION", position);
		startActivity(intent);
	}
    
    
    public class OrderAdapter extends ArrayAdapter<Incidencia> {

        private Incidencia[] items;

        public OrderAdapter(Context context, int textViewResourceId, Incidencia[] items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.mantenimiento, null);
                }
                Incidencia o = items[position];
                if (o != null) {
                		TextView it = (TextView) v.findViewById(R.id.hora);
                        TextView nt = (TextView) v.findViewById(R.id.statusText);
                        ImageView imagen = (ImageView) v.findViewById(R.id.icon);
                        
                        it.setText("Hora: "+o.getIncidenciaHora());                            
                        
                        nt.setText("Estado: "+o.getIncidenciaEst());
                        
                        int resource;
                        
                        if (o.getIncidenciaEst().equals("Abierto")) {
                        	resource = R.drawable.marker;
						} else {
							resource = R.drawable.marker2;	
						}
                        
                        imagen.setImageResource(resource);
                }
                return v;
        }
    }
    
   }


