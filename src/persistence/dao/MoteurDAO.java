package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import buisness.entities.Moteur;
import persistence.manager.JDBCManager;

public class MoteurDAO implements IDAO<Moteur>{

	public static final String sqlSelectAllMoteur = "SELECT * FROM Moteur";
	public static final String sqlInsertMoteur = "INSERT INTO Moteur (marque, modele, cylindree) values (?,?,?)";
	public static final String sqlUpdateMoteur = "UPDATE Moteur SET  marque = ?, modele = ?, cylindree = ? WHERE id = ?";
	public static final String sqlDeleteMoteur = "DELETE FROM Moteur WHERE id = ?";
	public static final String sqlSelectOneMoteur = "SELECT * FROM Moteur WHERE id = ?";



	@Override
	public Moteur create(Moteur pT) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlInsertMoteur ,PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, pT.getMarque());
		preparedStatement.setString(2, pT.getModele());
		preparedStatement.setInt(3, pT.getCylindree());
		preparedStatement.execute();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		long id = 0;
		while(resultSet.next()) {
			id = resultSet.getLong("GENERATED_KEY");
			//System.out.println("new key is "+id);
		}
		pT.setId(id);
		JDBCManager.getInstance().closeConnection();
		return pT;
	}

	@Override
	public Moteur findById(long pId) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlSelectOneMoteur);
		preparedStatement.setLong(1, pId);
		ResultSet st = preparedStatement.executeQuery();

		Moteur moteur = null;
		while(st.next()) {
			Long id = st.getLong("id");
			String marque = st.getString("marque");
			String modele = st.getString("modele");
			Integer cylindree = st.getInt("cylindree");
			/* ... */
			moteur = new Moteur(id, marque, modele, cylindree);
		}
		JDBCManager.getInstance().closeConnection();
		return moteur;
	}

	@Override
	public List<Moteur> findList() throws Exception {
		List<Moteur> list = new ArrayList<>();
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlSelectAllMoteur);
		ResultSet st = preparedStatement.executeQuery();

		Moteur moteur = null;
		while(st.next()) {
			Long id = st.getLong("id");
			String marque = st.getString("marque");
			String modele = st.getString("modele");
			Integer cylindree = st.getInt("cylindree");
			/* ... */
			moteur = new Moteur(id, marque, modele, cylindree);
			list.add(moteur);
		}
		JDBCManager.getInstance().closeConnection();
		return list;
	}

	@Override
	public void updateById(Moteur pT) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlUpdateMoteur,PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, pT.getMarque());
		preparedStatement.setString(2, pT.getModele());
		preparedStatement.setInt(3, pT.getCylindree());
		preparedStatement.setLong(4, pT.getId());
		preparedStatement.execute();

		JDBCManager.getInstance().closeConnection();

	}

	@Override
	public void deleteById(long pId) throws Exception {
		if(pId >= 0) {
			Connection cnx = JDBCManager.getInstance().openConection();	
			PreparedStatement preparedStatement = cnx.prepareStatement(sqlDeleteMoteur);
			preparedStatement.setLong(1, pId);
			preparedStatement.execute();
			JDBCManager.getInstance().closeConnection();
		}

	}


}
