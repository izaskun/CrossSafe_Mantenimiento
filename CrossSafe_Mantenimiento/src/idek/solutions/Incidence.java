package idek.solutions;

public class Incidence {
    
	private int incidenceId;
    private String incidenceName;
    private String incidenceStatus;
    
    public String getIncidenceName() {
        return incidenceName;
    }
    public void setIncidenceName(String orderName) {
        this.incidenceName = orderName;
    }
    public String getIncidenceStatus() {
        return incidenceStatus;
    }
    public void setIncidenceStatus(String orderStatus) {
        this.incidenceStatus = incidenceStatus;
    }
	public int getIncidenceId() {
		return incidenceId;
	}
	public void setIncidenceId(int incidenceId) {
		this.incidenceId = incidenceId;
	}
}