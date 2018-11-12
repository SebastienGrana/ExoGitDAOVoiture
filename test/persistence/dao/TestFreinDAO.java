package persistence.dao;

import static org.junit.Assert.assertNotEquals;
import java.util.List;
import buisness.entities.Frein;
import persistence.dao.FreinDAO;
import persistence.pere.TU_Pere;

public class TestFreinDAO extends TU_Pere{
	FreinDAO freinDAO;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		freinDAO = new FreinDAO();
	}

	public void testCreate() throws Exception {

		Frein frein = new Frein(99l, "Robert Automobile", "H444");
		frein = freinDAO.create(frein);
		
		Frein freinFromDB = freinDAO.findById(frein.getId());
		assertEquals(frein.getId(), freinFromDB.getId());
	}
	
	
	
	public void testFindById() throws Exception {

		Long wantedId = 1l;
		String wantedMarque = "Bendix";
		String wantedModel = "160R";

		Frein freinFromDB = freinDAO.findById(wantedId);
		assertEquals(wantedId, freinFromDB.getId());
		assertEquals(wantedMarque, freinFromDB.getMarque());
		assertEquals(wantedModel, freinFromDB.getModele());
	}
	
	
	public void testFindList() throws Exception {
		List<Frein> list = freinDAO.findList();
		int realNb = getInserter().select("select count(id) from Frein").getDataAsInt();

		assertEquals(list.size(),realNb);

		Frein frein = new Frein(99l, "Robert Automobile", "H444");
		Frein freinFromDB = freinDAO.create(frein);

		list = freinDAO.findList();
		realNb = getInserter().select("select count(id) from Frein").getDataAsInt();

		//test la taille de la liste
		assertEquals(list.size(),realNb);

	}
	public void testUpdateById() throws Exception {
		Frein frein = new Frein(99l, "Robert Automobile", "H444");
		frein = freinDAO.create(frein);
		
		frein.setMarque("Clement Mechanique");
		freinDAO.updateById(frein);

		Frein freinUpdated = freinDAO.findById(frein.getId());

		assertEquals("Clement Mechanique", freinUpdated.getMarque());

	}
	public void testDeleteById() {
		
		Long idDeleted = 1l;
		Integer idDeletedNull = null;
		
		//test si on supprimer bien la species
		try {
			Frein frein = new Frein(99l, "Robert Automobile", "H444");
			frein = freinDAO.create(frein);
			
			idDeleted = frein.getId();
			freinDAO.deleteById(idDeleted);
			assertNull(null);
		} catch (Exception e) {
			assertNull(e);

		}

		//test si bien non présent dans la liste
		try {
			List<Frein> list = freinDAO.findList();
			for (Frein frein : list) {
				assertNotEquals(idDeleted, frein.getId());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//test si erreur
		try {
			freinDAO.deleteById(idDeletedNull);
			assertNotNull(null);
		} catch (Exception e) {
			assertNotNull(e);

		}
	}
}
