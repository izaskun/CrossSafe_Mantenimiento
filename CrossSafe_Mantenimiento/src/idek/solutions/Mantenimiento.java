package idek.solutions;


import idek.solutions.modelos.Dispositivo;
import idek.solutions.modelos.Incidencia;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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
    
	public static OrderAdapter m_adapter;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getListView().setOnItemClickListener(this);
 
    }
    
	
	@Override
	public void onResume() {
		super.onResume();
		runJSONParser();
		 m_adapter = new OrderAdapter(this, R.layout.mantenimiento, CrossSafe_Mantenimiento.listaIncidencias);
	     setListAdapter(m_adapter);
	     //m_adapter.notifyDataSetChanged();
	     
	}
	
public InputStream getJSONData(String url){
		
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
	
	public void runJSONParser(){
        try{
	        Log.i("MY INFO", "Json Parser started..");
	        Gson gson = new Gson();
	        CrossSafe_Mantenimiento.getAndroidId(this);
	        Reader r = new InputStreamReader(getJSONData(CrossSafe_Mantenimiento.HOST + "idek_cross/incidencias/" + CrossSafe_Mantenimiento.android_id));
	        //Reader r = new InputStreamReader(getAssets().open("incidencias.json"));
	        Log.i("MY INFO", r.toString());
	       
	        CrossSafe_Mantenimiento.listaIncidencias = gson.fromJson(r, Incidencia[].class);
	        

	        Log.i("MY INFO", r.toString());
        }catch(Exception ex){

        }
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
                        
                        if (o.getIncidenciaEst().equals("cerrado")) {
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


