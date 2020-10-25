package parquimetro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import batallas.String;
public class Logica {
	private String error = null;
	private Connection cnx=null;
	public Logica(String url) {
		if (this.cnx == null)
	    {             
	       try
	       {  //se genera el string que define los datos de la conexion 
	          String servidor = "localhost:3306";
	          String baseDatos = "batallas";
	          String usuario = "admin";
	          String clave = "admin";
	          String uri = "jdbc:mysql://" + servidor + "/" + baseDatos + 
	          		          "?serverTimezone=America/Argentina/Buenos_Aires";
	          //se intenta establecer la conexion
	          cnx = DriverManager.getConnection(uri, usuario, clave);
	       }
	       catch (SQLException ex)
	       {
	    	  error = ex.getMessage();
	          System.out.println("SQLException: " + ex.getMessage());
	          System.out.println("SQLState: " + ex.getSQLState());
	          System.out.println("VendorError: " + ex.getErrorCode());
	       }
	    }
	}
	public String getError() {
		return error;
	}
	public Connection getConnection() {
		return cnx;
	}
	public Boolean checkAdmin(String pass) {
		Boolean accede = false;
		if (pass == "admin")
			accede=true;
		return accede;
	}
	public boolean checkInspector(String legajo, String pass) {
		boolean accede = false;
		ResultSet insp = null;
		try {
			if(cnx.isValid(200)) {
				insp = cnx.createStatement().executeQuery("select legajo,password from inspectores where legajo="+legajo+" and password=md5("+pass+");");
			}
		} catch (SQLException e) {
			System.out.println("Se agoto el tiempo de espera para la conexion");
			e.printStackTrace();
		}
		if(insp != null) {
			try {
				if (!insp.wasNull())
					accede=true;
			} catch (SQLException e) {
				System.out.println("Error en wasNull de inspector");
				e.printStackTrace();
			}
		}
		return accede;
	}
}