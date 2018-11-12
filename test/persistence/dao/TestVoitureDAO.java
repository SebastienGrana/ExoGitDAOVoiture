package persistence.dao;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import buisness.entities.Frein;
import buisness.entities.Moteur;
import buisness.entities.Voiture;
import persistence.pere.TU_Pere;

public class TestVoitureDAO extends TU_Pere {

	VoitureDAO voitureDAO;
	FreinDAO freinDAO;
	MoteurDAO moteurDAO;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		voitureDAO = new VoitureDAO();

		freinDAO = new FreinDAO();
		moteurDAO = new MoteurDAO();
	}

	public void testCreate() throws Exception {
		Moteur moteur = new Moteur(99l, "Simon AUTO", "M656", 150);
		Frein frein = new Frein(99l, "Robert Automobile", "H444");
		moteur = moteurDAO.create(moteur);
		frein = freinDAO.create(frein);

		Voiture voiture = new Voiture((long) 99, "B�bere R�par", "H265", moteur, frein, "Bleu");
		voiture = voitureDAO.create(voiture);

		Voiture voitureFromList = voitureDAO.findById(voiture.getId());

		assertEquals(voiture.getMarque(), voitureFromList.getMarque());
		assertEquals(voiture.getModele(), "H265");
		assertEquals(voiture.getId(), voitureFromList.getId());
		assertNotNull(voiture.getColor());
		assertEquals(voiture.getColor(), voitureFromList.getId());
	}

	public void testFindById() {

		try {
			Voiture voitureFromList = voitureDAO.findById(1);
			assertNull(null);
			assertEquals("Bouzouki", voitureFromList.getMarque());
			assertEquals("GTX", voitureFromList.getModele());
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}

		try {
			Long idNull = null;
			Voiture voitureFindById = voitureDAO.findById(idNull);
			assertNotNull(null);
		} catch (Exception e) {
			assertNotNull(e);
		}

	}

	public void testFindList() throws Exception {

		// test la taille de la liste
		List<Voiture> voitures = voitureDAO.findList();
		int realNb = getInserter().select("select count(id) from AutoMobile").getDataAsInt();
		assertEquals(voitures.size(), realNb);

		// test la taille de la liste
		Moteur moteur = new Moteur(99l, "Simon AUTO", "M656", 150);
		Frein frein = new Frein(99l, "Robert Automobile", "H444");
		moteur = moteurDAO.create(moteur);
		frein = freinDAO.create(frein);

		Voiture voiture = new Voiture((long) 99, "B�bere R�par", "H265", moteur, frein, "Jaune");
		voiture = voitureDAO.create(voiture);
		assertEquals("Jaune", voiture.getColor());
		assertNotNull(voiture.getColor());

		voitures = voitureDAO.findList();
		realNb = getInserter().select("select count(id) from AutoMobile").getDataAsInt();

		assertEquals(voitures.size(), realNb);
	}

	public void testUpdateById() {

		try {
			Moteur moteur = new Moteur(99l, "Simon AUTO", "M656", 150);
			Frein frein = new Frein(99l, "Robert Automobile", "H444");
			moteur = moteurDAO.create(moteur);
			frein = freinDAO.create(frein);

			Voiture voiture = new Voiture((long) 99, "B�bere R�par", "H265", moteur, frein, "Vert");
			voiture = voitureDAO.create(voiture);
			assertEquals("Vert", voiture.getColor());
			voiture.setMarque("Jean Claude Mekka");
			voiture.setColor("Bleu");
			voitureDAO.updateById(voiture);

			Voiture voitureUpdated = voitureDAO.findById(voiture.getId());

			assertEquals("Jean Claude Mekka", voitureUpdated.getMarque());
			assertEquals("Bleu", voitureUpdated.getColor());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDeleteById() {
		Long idDeleted = 1l;
		Integer idDeletedNull = null;

		// test si on supprimer bien la species
		try {
			Moteur moteur = new Moteur(99l, "Simon AUTO", "M656", 150);
			Frein frein = new Frein(99l, "Robert Automobile", "H444");
			moteur = moteurDAO.create(moteur);
			frein = freinDAO.create(frein);

			Voiture voiture = new Voiture((long) 99, "B�bere R�par", "H265", moteur, frein, "Jaune");
			voiture = voitureDAO.create(voiture);
			idDeleted = voiture.getId();

			voitureDAO.deleteById(idDeleted);
			assertNull(null);
		} catch (Exception e) {
			assertNull(e);

		}

		// test si bien non pr�sent dans la liste
		try {
			List<Voiture> list = voitureDAO.findList();
			for (Voiture voiture : list) {
				assertNotEquals(idDeleted, voiture.getId());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// test si erreur
		try {
			voitureDAO.deleteById(idDeletedNull);
			assertNotNull(null);
		} catch (Exception e) {
			assertNotNull(e);

		}
	}
}
