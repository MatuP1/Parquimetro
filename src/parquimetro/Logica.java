package parquimetro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;

import quick.dbtable.DBTable;
public class Logica {
	private String error = null;
	private Connection cnx=null;
	private DBTable tabla = null;
	public Logica() {System.out.println();}
	public DBTable connectAdmin(String pass) {
		tabla = new DBTable();
	
		if (this.cnx == null)
	    {             
	       try
	       {  //se genera el string que define los datos de la conexion 
	    	  String driver ="com.mysql.cj.jdbc.Driver";
	    	  String servidor = "localhost:3306";
	          String baseDatos = "parquimetros";
	          String usuario = "admin";
	          String clave = pass;
	          String uri = "jdbc:mysql://" + servidor + "/" + baseDatos + 
	          		          "?serverTimezone=America/Argentina/Buenos_Aires";
	          //se intenta establecer la conexion
	          cnx = DriverManager.getConnection(uri, usuario, clave);
	         try {
				tabla.connectDatabase(driver, uri, usuario, clave);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }
	       catch (SQLException ex)
	       {
	    	  error = ex.getMessage();
	          System.out.println("SQLException: " + ex.getMessage());
	          System.out.println("SQLState: " + ex.getSQLState());
	          System.out.println("VendorError: " + ex.getErrorCode());
	       }
	    }
		return tabla;
	}
	public DBTable connectInspector(String pass) {
		tabla = new DBTable();

		if (this.cnx == null)
	    {             
	       try
	       {  //se genera el string que define los datos de la conexion 
	    	  String driver ="com.mysql.cj.jdbc.Driver";
	    	  String servidor = "localhost:3306";
	          String baseDatos = "parquimetros";
	          String usuario = "inspector";
	          String clave = pass;
	          String uri = "jdbc:mysql://" + servidor + "/" + baseDatos + 
	          		          "?serverTimezone=America/Argentina/Buenos_Aires";
	          //se intenta establecer la conexion
	          cnx = DriverManager.getConnection(uri, usuario, clave);
	         try {
				tabla.connectDatabase(driver, uri, usuario, clave);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }
	       catch (SQLException ex)
	       {
	    	  error = ex.getMessage();
	          System.out.println("SQLException: " + ex.getMessage());
	          System.out.println("SQLState: " + ex.getSQLState());
	          System.out.println("VendorError: " + ex.getErrorCode());
	       }
	    }
		return tabla;
	}
	
	public String getError() {
		return error;
	}
	public DBTable getTable() {
		return tabla;
	}
	public Connection getConnection() {
		return cnx;
	}
	public DefaultListModel getListaTablas() {
		DefaultListModel listaTablas = null;
		try {
			if(cnx.isValid(0)) {
			    System.out.println("conexion = 10 puntos pa");
				PreparedStatement consulta = cnx.prepareStatement("show tables;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				consulta.execute();
			    ResultSet resultados = consulta.getResultSet();
			    //creo la lista a devolver
			    listaTablas = new DefaultListModel();
			    while (resultados.next()) {
			        listaTablas.addElement(resultados.getString(1));
			    }
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaTablas;
	}
	public DefaultListModel getValues(String elemento) {
		DefaultListModel atributos = new DefaultListModel();
		try {
			if(cnx.isValid(0)) {
				PreparedStatement consulta = cnx.prepareStatement("describe "+elemento+";", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				consulta.execute();
			    ResultSet resultados = consulta.getResultSet();
			    //creo la lista a devolver
			    while (resultados.next()) {
			        atributos.addElement(resultados.getString(1));
			    }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return atributos;
	}
	public boolean checkAdmin(String pass) {
		boolean accede = false;
		System.out.println("El pass pasado a logica es: "+pass);
		if (pass.equals("admin"))
			accede = true;
		return accede;
	}
	public boolean checkInspector(String legajo, String pass) {
		boolean accede = false;
		ResultSet insp = null;
		System.out.println("El user pasado es "+legajo+"y el pass pasado a logica es "+pass);
		try {
			if(cnx.isValid(200)) {  //EN ESTA LINEA TIRA NULLPOINTER CON cnx
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
	public void desconectar()
	   {
	         try
	         {
	            cnx.close();     
	            cnx=null;
	         }
	         catch (SQLException ex)
	         {
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	         }      
	   }

}
