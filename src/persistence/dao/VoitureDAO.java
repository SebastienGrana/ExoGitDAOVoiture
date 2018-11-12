package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import buisness.entities.Frein;
import buisness.entities.Moteur;
import buisness.entities.Voiture;
import persistence.manager.JDBCManager;

public class VoitureDAO implements IDAO<Voiture>{

	public static final String sqlInsertAutoMobile = "INSERT INTO AutoMobile (marque, modele, moteur_id, frein_id) values (?,?,?,?)";
	public static final String sqlSelectOneAutoMobile = "SELECT * FROM AutoMobile WHERE id = ?";
	public static final String sqlSelectAllAutoMobile = "SELECT * FROM AutoMobile";
	public static final String sqlUpdateAutoMobile = "UPDATE AutoMobile SET  marque = ?, modele = ?, moteur_id = ?, frein_id = ? WHERE id = ?";
	public static final String sqlDeleteAutoMobile = "DELETE FROM AutoMobile WHERE id = ?";

	@Override
	public Voiture create(Voiture pT) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlInsertAutoMobile,PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, pT.getMarque());
		preparedStatement.setString(2, pT.getModele());		
		preparedStatement.setLong(3, pT.getMoteur().getId());
		preparedStatement.setLong(4, pT.getFrein().getId());

		preparedStatement.execute();
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		long id = 0;
		while(resultSet.next()) {
			id = resultSet.getLong("GENERATED_KEY");
		}
		pT.setId(id);
		JDBCManager.getInstance().closeConnection();
		return pT;
	}

	@Override
	public Voiture findById(long pId) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlSelectOneAutoMobile);
		preparedStatement.setLong(1, pId);
		ResultSet st = preparedStatement.executeQuery();

		MoteurDAO moteurDAO = new MoteurDAO();
		FreinDAO freinDAO = new FreinDAO();

		Voiture voiture = null;
		while(st.next()) {
			Long id = st.getLong("id");
			String marque = st.getString("marque");
			String modele = st.getString("modele");

			Moteur moteur = moteurDAO.findById(st.getLong("moteur_id"));
			Frein frein = freinDAO.findById(st.getLong("frein_id"));
			/* ... */
			voiture = new Voiture(id, marque, modele, moteur, frein);
		}
		JDBCManager.getInstance().closeConnection();
		return voiture;
	}

	@Override
	public List<Voiture> findList() throws Exception {
		List<Voiture> list = new ArrayList<>();
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlSelectAllAutoMobile);
		ResultSet st = preparedStatement.executeQuery();

		MoteurDAO moteurDAO = new MoteurDAO();
		FreinDAO freinDAO = new FreinDAO();
		Voiture voiture = null;
		while(st.next()) {
			Long id = st.getLong("id");
			String marque = st.getString("marque");
			String modele = st.getString("modele");

			Moteur moteur = moteurDAO.findById(st.getLong("moteur_id"));
			Frein frein = freinDAO.findById(st.getLong("frein_id"));
			/* ... */
			voiture = new Voiture(id, marque, modele, moteur, frein);
			list.add(voiture);
		}
		JDBCManager.getInstance().closeConnection();
		return list;
	}

	@Override
	public void updateById(Voiture pT) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlUpdateAutoMobile ,PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, pT.getMarque());
		preparedStatement.setString(2, pT.getModele());
		preparedStatement.setLong(3, pT.getMoteur().getId());
		preparedStatement.setLong(4, pT.getFrein().getId());
		preparedStatement.setLong(5, pT.getId());
		preparedStatement.execute();

		JDBCManager.getInstance().closeConnection();
	}

	@Override
	public void deleteById(long pId) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();	
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlDeleteAutoMobile);
		preparedStatement.setLong(1, pId);
		preparedStatement.execute();
		JDBCManager.getInstance().closeConnection();

	}


}
