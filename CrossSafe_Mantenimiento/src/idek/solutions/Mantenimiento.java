package idek.solutions;


import java.util.ArrayList;
import android.app.ListActivity;
import android.app.ProgressDialog;
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
import android.widget.TextView;

public class Mantenimiento extends ListActivity implements OnItemClickListener{
    
	private ProgressDialog m_ProgressDialog = null; 
    private ArrayList<Incidence> m_incidences = null;
    private OrderAdapter m_adapter;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getListView().setOnItemClickListener(this);
        m_incidences =  getIncidences();
        m_adapter = new OrderAdapter(this, R.layout.row, m_incidences);
        setListAdapter(m_adapter);
       
    }
    
    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		
		Intent intent = new Intent ("android.intent.action.DESCRIPCION");
		startActivity(intent);
	}
    
    
    public class OrderAdapter extends ArrayAdapter<Incidence> {

        private ArrayList<Incidence> items;

        public OrderAdapter(Context context, int textViewResourceId, ArrayList<Incidence> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.row, null);
                }
                Incidence o = items.get(position);
                if (o != null) {
                		TextView it = (TextView) v.findViewById(R.id.idText);
                        TextView nt = (TextView) v.findViewById(R.id.nametext);
                        TextView st = (TextView) v.findViewById(R.id.statusText);
                        if (it != null) {
                            it.setText("Id: "+o.getIncidenceId());                            
                        }
                        if (nt != null) {
                              nt.setText("Name: "+o.getIncidenceName());                            
                        }
                        if(st != null){
                              st.setText("Status: "+ o.getIncidenceStatus());
                        }
                }
                return v;
        }
    }
    
    
    private ArrayList<Incidence> getIncidences(){
        try{
            m_incidences = new ArrayList<Incidence>();
            Incidence o1 = new Incidence();
            o1.setIncidenceId(0);
            o1.setIncidenceName("Rakel");
            o1.setIncidenceStatus("Pending");
            Incidence o2 = new Incidence();
            o2.setIncidenceId(1);
            o2.setIncidenceName("SF Advertisement");
            o2.setIncidenceStatus("Completed");
            m_incidences.add(o1);
            m_incidences.add(o2);
               Thread.sleep(2000);
            Log.i("ARRAY", ""+ m_incidences.size());
          } catch (Exception e) { 
            Log.e("BACKGROUND_PROC", e.getMessage());
          }
          return m_incidences;
      }
   }


