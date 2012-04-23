package idek.solutions;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
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
	    
	   /* List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
	    PosicionIncidencias itemizedoverlay = new PosicionIncidencias(drawable, this);
	    
	    GeoPoint point = new GeoPoint((int)(43.35589 * 1e6), (int)(-3.011609 * 1e6));
	    OverlayItem overlayitem = new OverlayItem(point, "Kaixo EH!", "Idek Solutions se encuentra en EH");
	    
	    itemizedoverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedoverlay);*/
	    
	    List<Overlay> mapOverlays = mapView.getOverlays();
		
		// first overlay
		Drawable drawable = getResources().getDrawable(R.drawable.marker);
		SimpleItemizedOverlay itemizedOverlay = new SimpleItemizedOverlay(drawable, mapView);
		
		GeoPoint point = new GeoPoint((int)(51.5174723*1E6),(int)(-0.0899537*1E6));
		OverlayItem overlayItem = new OverlayItem(point, "Tomorrow Never Dies (1997)", 
				"(M gives Bond his mission in Daimler car)");
		itemizedOverlay.addOverlay(overlayItem);
		
		GeoPoint point2 = new GeoPoint((int)(51.515259*1E6),(int)(-0.086623*1E6));
		OverlayItem overlayItem2 = new OverlayItem(point2, "GoldenEye (1995)", 
				"(Interiors Russian defence ministry council chambers in St Petersburg)");		
		itemizedOverlay.addOverlay(overlayItem2);
		
		mapOverlays.add(itemizedOverlay);
		
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
