package idek.solutions.modelos;

import com.google.gson.annotations.SerializedName;

public class Dispositivo {

	@SerializedName("iddispositivo")
	private int dispId;
	@SerializedName("longitud")
    private String dispLongitud;
	@SerializedName("latitud")
    private String disLatitud;
	@SerializedName("localidad")
    private String dispLoc;
	@SerializedName("estado")
    private String dispEst;
	public int getDispId() {
		return dispId;
	}
	public void setDispId(int dispId) {
		this.dispId = dispId;
	}
	public String getDispLongitud() {
		return dispLongitud;
	}
	public void setDispLongitud(String dispLongitud) {
		this.dispLongitud = dispLongitud;
	}
	public String getDisLatitud() {
		return disLatitud;
	}
	public void setDisLatitud(String disLatitud) {
		this.disLatitud = disLatitud;
	}
	public String getDispLoc() {
		return dispLoc;
	}
	public void setDispLoc(String dispLoc) {
		this.dispLoc = dispLoc;
	}
	public String getDispEst() {
		return dispEst;
	}
	public void setDispEst(String dispEst) {
		this.dispEst = dispEst;
	}
	
	
}
