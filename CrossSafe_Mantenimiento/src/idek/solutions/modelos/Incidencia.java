package idek.solutions.modelos;


import com.google.gson.annotations.SerializedName;

public class Incidencia {
    
	@SerializedName("idincidencia")
	private int incidenciaId;
	@SerializedName("descripcion")
    private String incidenciaDesc;
	@SerializedName("hora")
    private String incidenciaHora;
	@SerializedName("dispositivo")
    private Dispositivo incidenciaDisp;
	@SerializedName("estado")
    private String incidenciaEst;
    
    public String getIncidenceDesc() {
        return incidenciaDesc;
    }
    public void setIncidenceDesc(String orderDesc) {
        this.incidenciaDesc = orderDesc;
    }
  
	public int getIncidenceId() {
		return incidenciaId;
	}
	public void setIncidenceId(int incidenceId) {
		this.incidenciaId = incidenceId;
	}
	public String getIncidenciaHora() {
		return incidenciaHora;
	}
	public void setIncidenciaHora(String incidenciaHora) {
		this.incidenciaHora = incidenciaHora;
	}
	public Dispositivo getIncidenciaDisp() {
		return incidenciaDisp;
	}
	public void setIncidenciaDisp(Dispositivo incidenciaDisp) {
		this.incidenciaDisp = incidenciaDisp;
	}
	public String getIncidenciaEst() {
		return incidenciaEst;
	}
	public void setIncidenciaEst(String incidenciaEst) {
		this.incidenciaEst = incidenciaEst;
	}
}