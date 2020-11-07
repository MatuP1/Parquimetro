package parquimetro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;

import quick.dbtable.DBTable;
public class Logica {
	private String error = null;
	private Connection cnx=null;
	private DBTable tabla = null;
	public Logica() {}
	public DBTable connectAdmin(String pass) {
		tabla = new DBTable();
	
		if (this.cnx == null)
	    {             
	       try
	       { 
	    	  String driver ="com.mysql.cj.jdbc.Driver";
	    	  String servidor = "localhost:3306";
	          String baseDatos = "parquimetros";
	          String usuario = "admin";
	          String clave = pass;
	          String uri = "jdbc:mysql://" + servidor + "/" + baseDatos + 
	          		          "?serverTimezone=America/Argentina/Buenos_Aires";
	         
	          cnx = DriverManager.getConnection(uri, usuario, clave);
	         try {
				tabla.connectDatabase(driver, uri, usuario, clave);
			} catch (ClassNotFoundException e) {
				
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
	       {
	    	  String driver ="com.mysql.cj.jdbc.Driver";
	    	  String servidor = "localhost:3306";
	          String baseDatos = "parquimetros";
	          String usuario = "inspector";
	          String clave = pass;
	          String uri = "jdbc:mysql://" + servidor + "/" + baseDatos + 
	          		          "?serverTimezone=America/Argentina/Buenos_Aires";
	       
	          cnx = DriverManager.getConnection(uri, usuario, clave);
	         try {
				tabla.connectDatabase(driver, uri, usuario, clave);
			} catch (ClassNotFoundException e) {
				
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
				PreparedStatement consulta = cnx.prepareStatement("show tables;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				consulta.execute();
			    ResultSet resultados = consulta.getResultSet();
			    listaTablas = new DefaultListModel();
			    while (resultados.next()) {
			        listaTablas.addElement(resultados.getString(1));
			    }
		    }
		} catch (SQLException e) {
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
			    while (resultados.next()) {
			        atributos.addElement(resultados.getString(1));
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atributos;
	}
	
	public boolean checkAdmin(String pass) {
		boolean accede = false;
		if (pass.equals("admin"))
			accede = true;
		return accede;
	}
	
	public boolean checkInspector(String legajo, String pass) {
		boolean accede = false;
		ResultSet insp = null;
		try {
			if(cnx.isValid(1000)) {
				insp = cnx.createStatement().executeQuery("select legajo,password from inspectores where legajo="+legajo+" and password=md5("+pass+");");
			}
		} catch (SQLException e) {
			System.out.println("Se agoto el tiempo de espera para la conexion");
			e.printStackTrace();
		}
		if(insp != null) {
			try {
				if (insp.next()) {
					accede=true;
				}
			} catch (SQLException ex) {
					System.out.println("Error en next de inspector");
					System.out.println("SQLException: " + ex.getMessage());
		            System.out.println("SQLState: " + ex.getSQLState());
		            System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
		return accede;
	}
	
	public boolean checkUbicacion(String legajo,String calle,String altura,int hora,int minutos) {
		boolean pertenece = false;
		Calendar calendario = new GregorianCalendar();
		String turno="";
		String [] semana = {"do","lu","ma","mi","ju","vi","sa"};
		String dia=semana[calendario.get(Calendar.DAY_OF_WEEK)-1];
		if(hora>7 && hora<14) {
			turno ="m";
		}
		if(hora>=14 && (hora<20 || (hora==20 && minutos==00))) {
			turno ="t";
		}
		try {
			ResultSet rs = cnx.createStatement().executeQuery("SELECT AD.legajo FROM asociado_con as AD where turno ="+"\""+turno+"\""+" AND dia="+"\""+dia+"\""+" AND calle="+"\""+calle+"\""+" AND altura="+altura+" AND legajo="+legajo+";");
			if (rs.next())
				pertenece=true;
		} catch (SQLException ex) {
				System.out.println("checkUbicacion1");
				System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
		}
		return pertenece;
	}
	
	public void desconectar(){
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
	
	public void registrarAcceso(String legajoInsp, String id_parq, String fecha, String horario) {
		try {
			cnx.createStatement().execute("INSERT INTO accede VALUES("+legajoInsp+","+id_parq+","+"\""+fecha+"\""+","+"\""+horario+"\""+");");
		} catch (SQLException ex) {
				System.out.println("registrarAcceso1");
				System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	}
	
	public boolean generarMultas(String legajoInsp, String calle, String altura, String fecha,String horario, String[] patentes) {
		boolean correcto=true;
		String [] patentesValidas = new String[patentes.length];
		try {
			int j=0;
			for(int i=0; i<patentes.length;i++) {
				ResultSet rs = cnx.createStatement().executeQuery("SELECT DISTINCT A.patente FROM tarjetas as A where A.patente="+"\""+patentes[i]+"\""+";");
				if(rs.next()) {
					patentesValidas[j]=patentes[i];
					j++;
				}
				else {
					correcto = false;
				}
			}
		int cantPatentesValidas = j;
		ResultSet rs_autosEstacionados = cnx.createStatement().executeQuery("SELECT DISTINCT T.patente FROM tarjetas as T NATURAL JOIN estacionados as E NATURAL JOIN parquimetros as P WHERE p.calle="+"\""+calle+"\""+" AND p.altura="+altura+";");
		String patenteActual="";
		int i=0;
		Calendar calendario = new GregorianCalendar();
		String turno="";
		int hora =calendario.get(Calendar.HOUR_OF_DAY);
    	int minutos = calendario.get(Calendar.MINUTE);
		String [] semana = {"do","lu","ma","mi","ju","vi","sa"};
		String dia=semana[calendario.get(Calendar.DAY_OF_WEEK)-1];
		if(hora>7 && hora<14) {
			turno ="m";
		}
		if(hora>=14 && (hora<20 || (hora==20 && minutos==00))) {
			turno ="t";
		}
		ResultSet rs_aux=cnx.createStatement().executeQuery("Select A.id_asociado_con FROM asociado_con as A WHERE legajo="+legajoInsp+" AND calle="+"\""+calle+"\""+" AND altura="+altura+" AND dia="+"\""+dia+"\""+" AND turno="+"\""+turno+"\""+";");
		int id_asociado=0;
		if(rs_aux.next())
			id_asociado=rs_aux.getInt(1);
		boolean esta=false;
		boolean hayautos =rs_autosEstacionados.next();
		
		if(!hayautos) { //Si no hay autos estacionados, genera multas a todos los autos registrados por el inspector
			for(int cont=0; cont<cantPatentesValidas;cont++) {
				String patente=patentesValidas[cont].toUpperCase();
				cnx.createStatement().execute("INSERT INTO multa(fecha,hora,patente,id_asociado_con) VALUES("+"\""+fecha+"\""+","+"\""+horario+"\""+","+"\""+patente+"\""+","+id_asociado+");");	
			}
		}
		i=0;
		while(hayautos && i<cantPatentesValidas) {//Si hay autos estacionados, checkea que los autos esten estacionados sino les hace una multa	
			esta=false;
			while(!esta && hayautos ) {
				patenteActual=rs_autosEstacionados.getString(1);
				if(patentesValidas[i].equalsIgnoreCase(patenteActual)) {
					esta = true;
				}
				hayautos=rs_autosEstacionados.next();
			}
			
			if(!esta) {
				String patente=patentes[i].toUpperCase();
				cnx.createStatement().execute("INSERT INTO multa(fecha,hora,patente,id_asociado_con) VALUES("+"\""+fecha+"\""+","+"\""+horario+"\""+","+"\""+patente+"\""+","+id_asociado+");");
			}
			i++;
			rs_autosEstacionados = cnx.createStatement().executeQuery("SELECT DISTINCT T.patente FROM tarjetas as T NATURAL JOIN estacionados as E NATURAL JOIN parquimetros as P WHERE p.calle="+"\""+calle+"\""+" AND p.altura="+altura+";");
			hayautos=rs_autosEstacionados.next();
		}
		
		} catch (SQLException ex) {
				System.out.println("generarMultas1");
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
		}

		return correcto;
	}
	public int getPatentes() {
		int patentes = 0;
		ResultSet rs_aux;
		if(tabla!=null) {
			try {
				rs_aux=cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("Select distinct patente from tarjetas");
				rs_aux.last();
				patentes = rs_aux.getRow();
				System.out.println("La cantidad de patenes tiene que ser 9 y es: "+patentes);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return patentes;
	}

}
