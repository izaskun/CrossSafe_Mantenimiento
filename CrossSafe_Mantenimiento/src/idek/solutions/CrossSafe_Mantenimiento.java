package idek.solutions;

import idek.solutions.modelos.Incidencia;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TabHost;

public class CrossSafe_Mantenimiento extends TabActivity {
	
	final String HOST = "http://192.168.1.101:8888/";
	public static Incidencia[] listaIncidencias;
	public static String android_id;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tab);
	    
	    pushNotification();
	    runJSONParser();
	    
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, Mantenimiento.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("listaIncidencias").setIndicator("Lista Incidencias",
	                      res.getDrawable(R.drawable.ic_tab_incidencias))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, Mapa.class);
	    spec = tabHost.newTabSpec("mapa").setIndicator("Mapa Incidencias",
	                      res.getDrawable(R.drawable.ic_tab_mapas))
	                  .setContent(intent);
	    tabHost.addTab(spec);/*

	    intent = new Intent().setClass(this, SongsActivity.class);
	    spec = tabHost.newTabSpec("songs").setIndicator("Songs",
	                      res.getDrawable(R.drawable.ic_tab_songs))
	                  .setContent(intent);
	    tabHost.addTab(spec);*/

	    tabHost.setCurrentTab(0);
	}
	
	private void pushNotification() {
		Intent registrationIntent = new Intent("com.google.android.c2dm.intent.REGISTER");

		registrationIntent.putExtra("app", PendingIntent.getBroadcast(this, 0, new Intent(), 0));

		registrationIntent.putExtra("sender", "reki03@gmail.com");

		startService(registrationIntent);
		
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
	        android_id = getAndroidId();
	        //Reader r = new InputStreamReader(getJSONData(HOST + "idek_cross/incidencias/" + android_id));
	        Reader r = new InputStreamReader(getAssets().open("incidencias.json"));
	        Log.i("MY INFO", r.toString());
	       
	        listaIncidencias = gson.fromJson(r, Incidencia[].class);
	        

	        Log.i("MY INFO", r.toString());
        }catch(Exception ex){

        }
	}
	
	public String getAndroidId (){
		
		final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

	    final String tmDevice, tmSerial, androidId;
	    tmDevice = "" + tm.getDeviceId();
	    tmSerial = "" + tm.getSimSerialNumber();
	    androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

	    UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
	    String deviceId = deviceUuid.toString();
	    
	    return deviceId;
		
	}
	
	

}
