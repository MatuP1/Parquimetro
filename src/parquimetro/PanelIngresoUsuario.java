package parquimetro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import quick.dbtable.DBTable;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

public class PanelIngresoUsuario extends javax.swing.JInternalFrame{
	private static final long serialVersionUID = 1L;
	
	private PrincipalWindow vPrincipal;
	private Logica logica;
	private int Ancho,Alto;
	
	private DBTable table_tarjetas;
	private DBTable table_ubicaciones;
	private DBTable table_parquimetros;
	private DBTable table_res;
	private int id_tarj;
	private int id_parq;
	
	public PanelIngresoUsuario(PrincipalWindow v) {
		vPrincipal = v;
		Ancho = v.getAncho();
		Alto = v.getAlto();
		logica = vPrincipal.getLogica();
		
		logica.connectParquimetro();
		
		//this.setBounds(0, 0, Ancho, Alto);
		this.setBounds(0, 0, 800, 800);
        setVisible(true);
        this.setTitle("Ingreso Usuario");
        this.setClosable(false);
        this.setMaximizable(false);
        getContentPane().setLayout(null);
        
        table_parquimetros = new DBTable();
 		table_ubicaciones= new DBTable();
 		table_tarjetas =new DBTable();
 		table_res = new DBTable();
 		
        JScrollPane scrollPaneUbicaciones = new JScrollPane();
 		scrollPaneUbicaciones.setEnabled(true);
 		scrollPaneUbicaciones.setBounds(111, 34, 567, 137);
 		getContentPane().add(scrollPaneUbicaciones);
 		
 		JScrollPane scrollPaneParquimetros = new JScrollPane();
 		scrollPaneParquimetros.setEnabled(true);
 		scrollPaneParquimetros.setBounds(111, 210, 567, 137);
 		getContentPane().add(scrollPaneParquimetros);
 		
 		JScrollPane scrollPaneTarjetas = new JScrollPane();
 		scrollPaneTarjetas.setEnabled(true);
 		scrollPaneTarjetas.setBounds(111, 391, 567, 137);
 		getContentPane().add(scrollPaneTarjetas);
 		
 		JButton btnIngreso = new JButton("Ingresar/Salir de Estacionamiento");
 		btnIngreso.setBounds(479, 565, 199, 55);
 		btnIngreso.setEnabled(false);
 		getContentPane().add(btnIngreso);
 		
 		JButton btnBack = new JButton("Volver al Inicio");
 		btnBack.setBounds(111, 565, 199, 55);
 		btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               vPrincipal.volverPanelInicial();
            }
         });
 		getContentPane().add(btnBack);
 		
 		
 		JLabel lblNewLabel_1 = new JLabel("Parquimetros");
 		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
 		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
 		lblNewLabel_1.setBounds(111, 182, 567, 21);
 		getContentPane().add(lblNewLabel_1);
 		
     	JLabel lblNewLabel_2 = new JLabel("Ubicaciones");
     	lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
     	lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
     	lblNewLabel_2.setBounds(111, 11, 567, 21);
     	getContentPane().add(lblNewLabel_2);
     	
 		JLabel lblNewLabel_3 = new JLabel("Tarjetas");
     	lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
     	lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
     	lblNewLabel_3.setBounds(111, 363, 567, 21);
     	getContentPane().add(lblNewLabel_3);
 		
     	
 		try {
 			String sql="Select * from ubicaciones"; 
 			Statement st=logica.getConnection().createStatement();
 			ResultSet rs=st.executeQuery(sql);
 			table_ubicaciones.refresh(rs);
 			
 		}
 		catch (SQLException e1) {
				// en caso de error, se muestra la causa en la consola
			         System.out.println("SQLException: " + e1.getMessage());
			         System.out.println("SQLState: " + e1.getSQLState());
			         System.out.println("VendorError: " + e1.getErrorCode());
			         JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(vPrincipal),
			                                       e1.getMessage() + "\n", 
			                                       "Error al ejecutar la consulta.",
			                                       JOptionPane.ERROR_MESSAGE);
			         
			}
 		table_ubicaciones.addMouseListener(new MouseAdapter() {
 			@Override
 			public void mouseClicked(MouseEvent e) {
 				table_tarjetas.setVisible(false);
 				String calle,altura;
 				btnIngreso.setEnabled(false);
				int fila = table_ubicaciones.getSelectedRow();
				calle = table_ubicaciones.getValueAt(fila, 0).toString();
				altura= table_ubicaciones.getValueAt(fila, 1).toString();
 				String sql_parq="SELECT DISTINCT P.id_parq,P.numero,P.calle,P.altura from  ubicaciones as U NATURAL JOIN parquimetros AS P WHERE P.calle=\""+calle+"\" AND P.altura="+altura;
 				try {
				Statement st_parq = logica.getConnection().createStatement();
				ResultSet rs_parq = st_parq.executeQuery(sql_parq);
				table_parquimetros.refresh(rs_parq);
				} catch (SQLException e1) {
	 				// en caso de error, se muestra la causa en la consola
			         System.out.println("SQLException: " + e1.getMessage());
			         System.out.println("SQLState: " + e1.getSQLState());
			         System.out.println("VendorError: " + e1.getErrorCode());
			         JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(vPrincipal),
			                                       e1.getMessage() + "\n", 
			                                       "Error al ejecutar la consulta.",
			                                       JOptionPane.ERROR_MESSAGE);
				}
 			
 			}
 		});
 			
 		table_parquimetros.addMouseListener(new MouseAdapter() {
 			@Override
 			public void mouseClicked(MouseEvent e) {	
 				btnIngreso.setEnabled(false);
 				String sql_tarj="SELECT * FROM tarjetas";
 				try {
				Statement st_tarj = logica.getConnection().createStatement();
				ResultSet rs_tarj = st_tarj.executeQuery(sql_tarj);
				table_tarjetas.refresh(rs_tarj);
				table_tarjetas.setVisible(true);
				} catch (SQLException e1) {
	 				// en caso de error, se muestra la causa en la consola
			         System.out.println("SQLException: " + e1.getMessage());
			         System.out.println("SQLState: " + e1.getSQLState());
			         System.out.println("VendorError: " + e1.getErrorCode());
			         JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(vPrincipal),
			                                       e1.getMessage() + "\n", 
			                                       "Error al ejecutar la consulta.",
			                                       JOptionPane.ERROR_MESSAGE);
				}
 			
 			}
 		});
     		
     	table_tarjetas.addMouseListener(new MouseAdapter() {
     		@Override
     		public void mouseClicked(MouseEvent e) {	
     			btnIngreso.setEnabled(true);
     		}
     	});
    		table_ubicaciones.setEditable(false);
         	scrollPaneUbicaciones.setViewportView(table_ubicaciones);
         		
         	table_parquimetros.setEditable(false);
         	scrollPaneParquimetros.setViewportView(table_parquimetros); 		
         	
         	table_tarjetas.setEditable(false);
         	scrollPaneTarjetas.setViewportView(table_tarjetas);
         
         	 btnIngreso.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent evt) {
                	int fila_tarj = table_tarjetas.getSelectedRow();
                	int fila_parq = table_parquimetros.getSelectedRow();
                	
                	id_tarj = (int)table_tarjetas.getValueAt(fila_tarj, 0);
                	id_parq = (int)table_parquimetros.getValueAt(fila_parq, 0);
                	
                	ResultSet res = logica.conexion_parq(id_tarj, id_parq);
                	try {
                		table_res = new DBTable();
						table_res.refresh(res);
                	} catch (SQLException e) {
                		e.printStackTrace();
					}
                	
                	int fila_res=table_res.getSelectedRow();
                	String tipo_ope = table_res.getValueAt(fila_res, 0).toString();
                	String tipo_res = table_res.getValueAt(fila_res, 1).toString();
                	int minutos_restantes = (int)table_res.getValueAt(fila_res, 2); //Es devuelto en minutos
      
                	if(tipo_ope.equals("apertura") && tipo_res.equals("Ok")) {
                		JOptionPane.showMessageDialog(null, "Se abrio con exito un estacionamiento y le quedan "+minutos_restantes+" minutos restantes.");
                	}else if(tipo_ope.equals("apertura") && tipo_res.equals("Fail")) {
                		JOptionPane.showMessageDialog(null, "No se pudo abrir un estacionamiento con dicha tarjeta");
                	}else if(tipo_ope.equals("cierre")) {
                		String minutos=tipo_res;
                		int saldo=minutos_restantes;
                		JOptionPane.showMessageDialog(null, "Se cerro con exito un estacionamiento,estuvo estacionado "+minutos+" minutos y el saldo restante es de "+saldo+" pesos.");
                	}
                	
        	
              }
           });
         	
     		
        
	}
	
	private void thisComponentShown(ComponentEvent evt) {
	}
	   
    private void thisComponentHidden(ComponentEvent evt) {    
	}
}
