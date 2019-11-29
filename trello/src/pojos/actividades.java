package pojos;

public class actividades {

    private	String name;
	private boolean listo;
	private int id_tarjeta;
	private int id_actividad;
	private String name_actividad;
	
	public boolean getListo() {
		return listo;
	}
	public void setListo(boolean listo) {
		this.listo = listo;
	}
	
	public int getId_tarjeta() {
		return id_tarjeta;
	}
	public void setId_tarjeta(int id_tarjeta) {
		this.id_tarjeta = id_tarjeta;
	}
	public int getId_actividad() {
		return id_actividad;
	}
	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
	}
	
	public String getName_actividad() {
		return name_actividad;
	}
	public void setName_actividad(String name_actividad) {
		this.name_actividad = name_actividad;
	}
	
}
