package parquimetro;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import quick.dbtable.DBTable;

public class PanelInspector extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private PrincipalWindow vPrincipal;
	private Logica logica;
	private DBTable multas;
	private DBTable table_ubicaciones;
	private DBTable table_parquimetros;
	private String legajoInsp;
	private String passInsp;
	private JTextPane textPatentes;
	private String[] patentes;
	private int horaPrimerMulta = 0;
	private int minutosPrimerMulta = 0;
	
	public PanelInspector(PrincipalWindow v, String legajo, String pass) {
		vPrincipal = v;
		passInsp = pass;
		legajoInsp = legajo;
		logica = v.getLogica();
		multas = logica.connectInspector("inspector");
		initGUI();
		
	}
	
	private void initGUI() 
	   {
	      try {
	         setPreferredSize(new Dimension(800, 600));
	         this.setBounds(0, 0, 800, 600);
	         setVisible(true);
	         this.setTitle("Consultas (Utilizando DBTable)");
	         this.setClosable(true);
	         this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	         this.setMaximizable(true);
	         this.addComponentListener(new ComponentAdapter() {
	            public void componentHidden(ComponentEvent evt) {
	               thisComponentHidden(evt);
	            }
	            public void componentShown(ComponentEvent evt) {
	               thisComponentShown(evt);
	            }
	         });
	         getContentPane().setLayout(null);
	         {
		     	JButton btnMultas = new JButton("Generar Multas");
		     	textPatentes = new JTextPane();
	     		JButton btnPatentes = new JButton("Ingresar Patentes");
	     		btnPatentes.addActionListener(new ActionListener() {
	                  public void actionPerformed(ActionEvent evt) {
	                	  btnMultas.setEnabled(true);
	                	  patentes = textPatentes.getText().split("\n");
	                	  textPatentes.setText("Se ingresaron las patentes correctamente, si desea ingresar mas patentes destruya este texto");
	                  }
	            });
	     		btnPatentes.setEnabled(false);
	     		btnPatentes.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     		btnPatentes.setHorizontalAlignment(SwingConstants.LEFT);
	     		btnPatentes.setBounds(10, 236, 137, 21);
	     		getContentPane().add(btnPatentes);
	     		
	     		JLabel lblNewLabel = new JLabel("Patentes");
	     		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	     		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	     		lblNewLabel.setBounds(10, 9, 137, 14);
	     		getContentPane().add(lblNewLabel);
	     		
	     		btnMultas.setEnabled(false);
	     		btnMultas.setFont(new Font("Tahoma", Font.PLAIN, 14));
	     		btnMultas.setBounds(10, 327, 764, 23);
	     		getContentPane().add(btnMultas);
	     		
	        	
	     		textPatentes.addKeyListener(new KeyAdapter() {
	     			@Override
	     			public void keyTyped(KeyEvent e) {
	     				btnPatentes.setEnabled(true);
	     			}
	     		});
	     		textPatentes.setBounds(10, 34, 137, 200);
	     		textPatentes.setEnabled(false);
	     		textPatentes.setText("Bienvenido Inspector");
	     		getContentPane().add(textPatentes);
	     		
	     		JSeparator separator = new JSeparator();
	     		separator.setBounds(170, 172, 567, -1);
	     		getContentPane().add(separator);
	     		
	     		JScrollPane scrollPaneUbicaciones = new JScrollPane();
	     		table_parquimetros = new DBTable();
	     		table_ubicaciones= new DBTable();
	     		multas =new DBTable();
	     		scrollPaneUbicaciones.setEnabled(true);
	     		scrollPaneUbicaciones.setBounds(170, 34, 567, 137);
	     		getContentPane().add(scrollPaneUbicaciones);

	     		try {
	     			String sql="Select distinct U.calle,U.altura,U.tarifa from asociado_con as ID NATURAL JOIN ubicaciones as U where ID.legajo ="+legajoInsp;
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
	     				
	     				String calle,altura;
	     				boolean falsisimo = false;
	     				textPatentes.setEnabled(falsisimo);
	     				textPatentes.setText("Ingrese las patentes deseadas separadas por un salto de linea (Enter)");
	     				btnMultas.setEnabled(falsisimo);
	    				int fila = table_ubicaciones.getSelectedRow();
	    				calle = table_ubicaciones.getValueAt(fila, 0).toString();
	    				altura= table_ubicaciones.getValueAt(fila, 1).toString();
	    				System.out.println("la calle es:"+calle+" y la altura: "+altura);
	     				String sql_parq="SELECT DISTINCT P.id_parq,P.numero,P.calle,P.altura from asociado_con as ID NATURAL JOIN ubicaciones as U NATURAL JOIN parquimetros AS P where ID.legajo ="+legajoInsp+" AND P.calle=\""+calle+"\" AND P.altura="+altura;
	     				System.out.println(sql_parq);
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
     				textPatentes.setEnabled(true);
     				textPatentes.setText("Ingrese las patentes deseadas separadas por un salto de linea (Enter)");
     				btnMultas.setEnabled(false);
     			}
     		});
 				table_ubicaciones.setEditable(false);
	     		scrollPaneUbicaciones.setViewportView(table_ubicaciones);
	     		
	     		JScrollPane scrollPaneParquimetros = new JScrollPane();
	     		scrollPaneParquimetros.setEnabled(true);
	     		scrollPaneParquimetros.setBounds(170, 214, 567, 102);
	     		getContentPane().add(scrollPaneParquimetros);
	     		
	     		table_parquimetros.setEditable(false);
	     		scrollPaneParquimetros.setViewportView(table_parquimetros);
	     		
	     		JLabel lblNewLabel_1 = new JLabel("Parquimetros");
	     		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	     		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	     		lblNewLabel_1.setBounds(170, 182, 567, 21);
	     		getContentPane().add(lblNewLabel_1);
	     		
	     		JLabel lblNewLabel_2 = new JLabel("Ubicaciones");
	     		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
	     		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	     		lblNewLabel_2.setBounds(170, 11, 567, 21);
	     		getContentPane().add(lblNewLabel_2);
	     		
	     		 JScrollPane scrollPaneMultas = new JScrollPane();
		         scrollPaneMultas.setBounds(10, 361, 764, 198);
		         getContentPane().add(scrollPaneMultas);
		         btnMultas.addActionListener(new ActionListener() {
	                 public void actionPerformed(ActionEvent evt) {
	                	int fila = table_ubicaciones.getSelectedRow();
	                	Calendar calendario = new GregorianCalendar();
	                	int horaActual, minutos, segundos,ainoActual,mesActual,diaActual;
	                	ainoActual = calendario.get(Calendar.YEAR);
	                	mesActual = calendario.get(Calendar.MONTH);
	                	diaActual = calendario.get(Calendar.DAY_OF_MONTH);
	                	horaActual =calendario.get(Calendar.HOUR_OF_DAY);
	                	minutos = calendario.get(Calendar.MINUTE);
	                	segundos = calendario.get(Calendar.SECOND);
	                	String fecha = ainoActual+"-"+mesActual+"-"+diaActual;
	                	String horario = horaActual+":"+minutos+":"+segundos;
	                	setHoraPrimerMulta(horaActual,minutos);
	                	String calle = table_ubicaciones.getValueAt(fila,0).toString();
	                	String altura =table_ubicaciones.getValueAt(fila,1).toString();
	                	fila = table_parquimetros.getSelectedRow();
	                	String id_parq=table_parquimetros.getValueAt(fila, 0).toString();
            	try {
		                
                		if(!logica.checkUbicacion(legajoInsp,calle,altura,horaActual,minutos)) {
	    					JOptionPane.showMessageDialog(null, "Ubicacion no permitida en este horario","Mensaje Error", JOptionPane.WARNING_MESSAGE);
	                    }
	                    else {
		                    logica.registrarAcceso(legajoInsp,id_parq,fecha,horario);
		                	logica.generarMultas(legajoInsp,calle,altura,fecha,horario,patentes);
		                	String sql_multas="SELECT M.numero, M.fecha, M.hora, AC.calle, AC.altura, M.patente, AC.legajo from multa as M NATURAL JOIN asociado_con as AC WHERE ("+horaActual+" > "+horaPrimerMulta+" OR ("+horaActual+"="+horaPrimerMulta+" AND "+minutos+">="+minutosPrimerMulta+"));";
		                	Statement st_multas = logica.getConnection().createStatement();
		                	st_multas.execute(sql_multas);
		                	ResultSet rs_multas=st_multas.getResultSet();
		                	multas.refresh(rs_multas);
		                	 for (int i = 0; i < multas.getColumnCount(); i++)
		               	  { // para que muestre correctamente los valores de tipo TIME (hora)  		   		  
		               		 if	 (multas.getColumn(i).getType()==Types.TIME)  
		               		 {    		 
		               		    multas.getColumn(i).setType(Types.CHAR);  
		             	       	 }
		               		 // cambiar el formato en que se muestran los valores de tipo DATE
		               		 if	 (multas.getColumn(i).getType()==Types.DATE)
		               		 {
		               		    multas.getColumn(i).setDateFormat("dd/MM/YYYY");
		               		 }
		                    }  
	                    }
                	}
                   	catch (SQLException e) {
                		// TODO Auto-generated catch block
                		e.printStackTrace();
                	}
	              }
	           });
		     scrollPaneMultas.setViewportView(multas);   
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }

	   private void thisComponentShown(ComponentEvent evt) 
	   {
	   }
	   
	   private void thisComponentHidden(ComponentEvent evt) 
	   {
	      vPrincipal.volverPanelInicial();
	      logica.desconectar();
	   }
	   private void setHoraPrimerMulta(int hora, int minutos) {
		   if(horaPrimerMulta != 0) {
			   horaPrimerMulta = hora;
			   minutosPrimerMulta = minutos;
		   }
       }
}
