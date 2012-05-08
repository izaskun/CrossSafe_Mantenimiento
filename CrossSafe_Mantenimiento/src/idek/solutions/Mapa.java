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
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mapa);
	    
	    mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	       
	    List<Overlay> mapOverlays = mapView.getOverlays();

		Drawable drawable = getResources().getDrawable(R.drawable.marker);
		SimpleItemizedOverlay itemizedOverlay = new SimpleItemizedOverlay(drawable, mapView);
		
		Incidencia [] lista = CrossSafe_Mantenimiento.listaIncidencias;
		
		for (int i = 0; i < lista.length; i++) {
			Incidencia inci = lista[i];
			Dispositivo dis = inci.getIncidenciaDisp();
			int latitud = (int)Float.parseFloat(dis.getDisLatitud());
			int longitud = (int)Float.parseFloat(dis.getDispLongitud());
			GeoPoint point = new GeoPoint((int)(latitud*1E6),(int)(longitud*1E6));
			OverlayItem overlayItem = new OverlayItem(point,inci.getIncidenciaHora(), inci.getIncidenceDesc());
			
			itemizedOverlay.addOverlay(overlayItem);
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
				
		mapOverlays.add(itemizedOverlay);
		itemizedOverlay.populateNow();
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
