package persistence.dao;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import buisness.entities.Moteur;
import persistence.pere.TU_Pere;

public class TestMoteurDAO extends TU_Pere{
	MoteurDAO moteurDAO;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		moteurDAO = new MoteurDAO();
	}

	public void testCreate() throws Exception {

		Moteur moteur = new Moteur(99l, "Simon AUTO", "M656" , 150);
		moteur = moteurDAO.create(moteur);
		
		Moteur moteurFromDB = moteurDAO.findById(moteur.getId());
		assertEquals(moteur.getId(), moteurFromDB.getId());
	}
	public void testFindById() throws Exception {

		Long wantedId = 1l;
		String wantedMarque = "Subaru";
		String wantedModel = "TVL";
		Integer cylindree = 1600;

		Moteur moteurFromDB = moteurDAO.findById(wantedId);
		
		assertEquals(wantedId, moteurFromDB.getId());
		assertEquals(wantedMarque, moteurFromDB.getMarque());
		assertEquals(wantedModel, moteurFromDB.getModele());
		assertEquals(cylindree, moteurFromDB.getCylindree());
	}
	
	public void testFindList() throws Exception {
		List<Moteur> list = moteurDAO.findList();
		int realNb = getInserter().select("select count(id) from Moteur").getDataAsInt();

		assertEquals(list.size(),realNb);

		Moteur moteur = new Moteur(99l, "Simon AUTO", "M656" , 150);
		moteur = moteurDAO.create(moteur);

		list = moteurDAO.findList();
		realNb = getInserter().select("select count(id) from Moteur").getDataAsInt();

		//test la taille de la liste
		assertEquals(list.size(),realNb);

	}
	public void testUpdateById() throws Exception {
		Moteur moteur = new Moteur(99l, "Simon AUTO", "M656" , 150);
		moteur = moteurDAO.create(moteur);
		
		moteur.setMarque("Clement Mechanique");
		moteurDAO.updateById(moteur);

		Moteur moteurUpdated = moteurDAO.findById(moteur.getId());

		assertEquals("Clement Mechanique", moteurUpdated.getMarque());

	}
	public void testDeleteById() {
		
		Long idDeleted = 1l;
		Integer idDeletedNull = null;
		
		//test si on supprimer bien la species
		try {
			Moteur moteur = new Moteur(99l, "Simon AUTO", "M656" , 150);
			moteur = moteurDAO.create(moteur);
			
			idDeleted = moteur.getId();
			moteurDAO.deleteById(idDeleted);
			assertNull(null);
		} catch (Exception e) {
			assertNull(e);

		}

		//test si bien non présent dans la liste
		try {
			List<Moteur> list = moteurDAO.findList();
			for (Moteur moteur : list) {
				assertNotEquals(idDeleted, moteur.getId());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//test si erreur
		try {
			moteurDAO.deleteById(idDeletedNull);
			assertNotNull(null);
		} catch (Exception e) {
			assertNotNull(e);

		}
	}
}
