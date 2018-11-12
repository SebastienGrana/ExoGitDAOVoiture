package buisness.entities;

public class Voiture {
	
	private Long id;
	private String marque;
	private String modele;
	private Moteur moteur;
	private Frein frein;
	private String color;
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
	public Moteur getMoteur() {
		return moteur;
	}
	public void setMoteur(Moteur moteur) {
		this.moteur = moteur;
	}
	public Frein getFrein() {
		return frein;
	}
	public void setFrein(Frein frein) {
		this.frein = frein;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public Voiture(Long id, String marque, String modele, Moteur moteur, Frein frein, String color) {
		super();
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.moteur = moteur;
		this.frein = frein;
		this.color = color;
	}
	public Voiture() {
		super();
	}
	
	

}
