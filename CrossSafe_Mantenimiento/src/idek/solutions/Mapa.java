package idek.solutions;

import idek.solutions.modelos.Dispositivo;
import idek.solutions.modelos.Incidencia;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


public class Mapa extends MapActivity{

	 MapView mapView;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Drawable drawable = null;
		SimpleItemizedOverlay abiertoOverlay, cerradoOverlay;
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mapa);
	    
	    mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	       
	    List<Overlay> mapOverlays = mapView.getOverlays();
		   
	    Incidencia [] lista = CrossSafe_Mantenimiento.listaIncidencias;
	    
	    drawable = getResources().getDrawable(R.drawable.marker);
	    cerradoOverlay = new SimpleItemizedOverlay(drawable, mapView);
	    drawable = getResources().getDrawable(R.drawable.marker2);
	    abiertoOverlay = new SimpleItemizedOverlay(drawable, mapView);
		
		
		for (int i = 0; i < lista.length; i++) {
			Incidencia inci = lista[i];
			Dispositivo dis = inci.getIncidenciaDisp();
			int latitud = (int)Float.parseFloat(dis.getDisLatitud());
			int longitud = (int)Float.parseFloat(dis.getDispLongitud());
			GeoPoint point = new GeoPoint((int)(latitud*1E6),(int)(longitud*1E6));
			OverlayItem overlayItem = new OverlayItem(point,inci.getIncidenciaHora(), inci.getIncidenceDesc());
			if (inci.getIncidenciaEst().equals("cerrado")) {
				cerradoOverlay.addOverlay(overlayItem);
			} else {
				abiertoOverlay.addOverlay(overlayItem);
			}
			
		}
		
		// Nos muestra la posición actual del empleado 
		final MyLocationOverlay myLocation = new MyLocationOverlay(this, mapView);
		mapOverlays.add(myLocation);
        myLocation.disableCompass();
        myLocation.enableMyLocation();
        myLocation.runOnFirstFix(new Runnable() {
            public void run() {
                mapView.getController().animateTo(myLocation.getMyLocation());
            }
        });
				
		mapOverlays.add(cerradoOverlay);
		mapOverlays.add(abiertoOverlay);
		cerradoOverlay.populateNow();
		abiertoOverlay.populateNow();
		mapView.invalidate();
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
