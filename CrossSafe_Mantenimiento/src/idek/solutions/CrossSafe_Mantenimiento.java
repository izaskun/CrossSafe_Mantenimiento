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

import android.app.Activity;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

public class CrossSafe_Mantenimiento extends TabActivity {
	
	public final static String AUTH = "authentication";
	public static String android_id;
	public static final String HOST = "http://192.168.1.102:8888/WS/";
	public static Incidencia[] listaIncidencias;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, Mantenimiento.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("listaIncidencias")
				.setIndicator("Lista Incidencias",
						res.getDrawable(R.drawable.ic_tab_incidencias))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, Mapa.class);
		spec = tabHost
				.newTabSpec("mapa")
				.setIndicator("Mapa Incidencias",
						res.getDrawable(R.drawable.ic_tab_mapas))
				.setContent(intent);
		tabHost.addTab(spec);/*
							 * 
							 * intent = new Intent().setClass(this,
							 * SongsActivity.class); spec =
							 * tabHost.newTabSpec("songs").setIndicator("Songs",
							 * res.getDrawable(R.drawable.ic_tab_songs))
							 * .setContent(intent); tabHost.addTab(spec);
							 */

		tabHost.setCurrentTab(0);
		register();
		showRegistrationId();
	}
	
	public void register() {
		Log.w("IKER", "start registration process");
		Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
		intent.putExtra("app",
				PendingIntent.getBroadcast(this, 0, new Intent(), 0));
		// Sender currently not used
		intent.putExtra("sender", "ideksolutions@gmail.com");
		startService(intent);
	}

	public void showRegistrationId() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		String string = prefs.getString(AUTH, "n/a");
		//Toast.makeText(this, string, Toast.LENGTH_LONG).show();
		Log.d("IKER", string);

	}
	
public static void getAndroidId (Context c){
		
		final TelephonyManager tm = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);

	    final String tmDevice, tmSerial, androidId;
	    tmDevice = "" + tm.getDeviceId();
	    tmSerial = "" + tm.getSimSerialNumber();
	    androidId = "" + android.provider.Settings.Secure.getString(c.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

	    UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
	    String deviceId = deviceUuid.toString();
	    
	     CrossSafe_Mantenimiento.android_id = deviceId;
	     Log.d("IKER", "DeviceID: " + deviceId);
		
	}

}
