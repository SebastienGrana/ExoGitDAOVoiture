package buisness.entities;

public class Moteur {
	
	private Long id;
	private String marque;
	private String modele;
	private Integer cylindree;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public Integer getCylindree() {
		return cylindree;
	}
	public void setCylindree(Integer cylindree) {
		this.cylindree = cylindree;
	}
	public Moteur(Long id, String marque, String modele, Integer cylindree) {
		super();
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.cylindree = cylindree;
	}
	public Moteur() {
		super();
	}
	
	

}
