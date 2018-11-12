package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import buisness.entities.Frein;
import buisness.entities.Moteur;
import persistence.manager.JDBCManager;

public class FreinDAO implements IDAO<Frein>{

	public static final String sqlInsertFrein = "INSERT INTO Frein (marque, modele) values (?,?)";
	public static final String sqlSelectOneFrein = "SELECT * FROM Frein WHERE id = ?";
	public static final String sqlSelectAllFrein = "SELECT * FROM Frein";
	public static final String sqlUpdateFrein = "UPDATE Frein SET  marque = ?, modele = ? WHERE id = ?";
	public static final String sqlDeleteFrein = "DELETE FROM Frein WHERE id = ?";

	@Override
	public Frein create(Frein pT) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlInsertFrein ,PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, pT.getMarque());
		preparedStatement.setString(2, pT.getModele());
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
	public Frein findById(long pId) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlSelectOneFrein);
		preparedStatement.setLong(1, pId);
		ResultSet st = preparedStatement.executeQuery();

		Frein frein = null;
		while(st.next()) {
			Long id = st.getLong("id");
			String marque = st.getString("marque");
			String modele = st.getString("modele");
			/* ... */
			frein = new Frein(id, marque, modele);
		}
		JDBCManager.getInstance().closeConnection();
		return frein;
	}

	@Override
	public List<Frein> findList() throws Exception {
		List<Frein> list = new ArrayList<>();
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlSelectAllFrein);
		ResultSet st = preparedStatement.executeQuery();

		Frein frein = null;
		while(st.next()) {
			Long id = st.getLong("id");
			String marque = st.getString("marque");
			String modele = st.getString("modele");
			/* ... */
			frein = new Frein(id, marque, modele);
			list.add(frein);
		}
		JDBCManager.getInstance().closeConnection();
		return list;
	}

	@Override
	public void updateById(Frein pT) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlUpdateFrein,PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, pT.getMarque());
		preparedStatement.setString(2, pT.getModele());
		preparedStatement.setLong(3, pT.getId());
		preparedStatement.execute();

		JDBCManager.getInstance().closeConnection();

	}

	@Override
	public void deleteById(long pId) throws Exception {
		if(pId >= 0) {
			Connection cnx = JDBCManager.getInstance().openConection();	
			PreparedStatement preparedStatement = cnx.prepareStatement(sqlDeleteFrein);
			preparedStatement.setLong(1, pId);
			preparedStatement.execute();
			JDBCManager.getInstance().closeConnection();
		}

	}


}
