package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;

public class TestDataBase {

	IDataSet dataSet = null;

	protected Connection getConnection() throws Exception {
		Class.forName(DataBaseManager.driver);
		Connection con = DriverManager.getConnection(DataBaseManager.dburl, DataBaseManager.dbuser,
				DataBaseManager.dbpassword);
		return con;
	}

	protected IDataSet getDataSet(String filename) throws Exception {
		IDataSet dataSet = new XlsDataSet(new FileInputStream(new File(filename)));
		return dataSet;
	}

	protected String getSqlForResetID(String[] tableNames){
		String sql="";
		for(int i=0;i<tableNames.length;i++){
			sql +="DBCC CHECKIDENT (" + tableNames[i] + ",RESEED, 0);";

		}
		return sql;
	}

	protected void loadTestData(String fileName) throws Exception {
		dataSet = getDataSet(fileName);
	}

	public void setTestData(String fileName) {
		try {
			loadTestData(fileName);
			setTestData();
		} catch (Exception e) {

		}
	}

	public void removeTestData(String fileName) {
		try {
			loadTestData(fileName);
			removeTestData();
		} catch (Exception e) {

		}
	}

	public void setTestData() {
		IDatabaseConnection connection = null;
		try {
			if (dataSet != null) {
				String sql=getSqlForResetID(dataSet.getTableNames());

				Connection con = getConnection();
				connection = new DatabaseConnection(con);

				Statement stmt = con.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();

				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
			}
		}
	}

	public void removeTestData() {
		IDatabaseConnection connection = null;
		try {
			if (dataSet != null) {
				Connection con = getConnection();
				connection = new DatabaseConnection(con);
				DatabaseOperation.DELETE_ALL.execute(connection, dataSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
			}
		}

	}
}
