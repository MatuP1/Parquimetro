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

	     		JButton btnPatentes = new JButton("Ingresar Patentes");
	     		btnPatentes.addMouseListener(new MouseAdapter() {
	     			@Override
	     			public void mouseClicked(MouseEvent e) {
	     				String pat=textPatentes.getText();
	     				
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
	     		
	     		JButton btnMultas = new JButton("Generar Multas");
	     		btnMultas.setEnabled(false);
	     		btnMultas.setFont(new Font("Tahoma", Font.PLAIN, 14));
	     		btnMultas.setBounds(10, 282, 453, 23);
	     		getContentPane().add(btnMultas);
	     		
	        	textPatentes = new JTextPane();
	     		textPatentes.addKeyListener(new KeyAdapter() {
	     			@Override
	     			public void keyTyped(KeyEvent e) {
	     				btnPatentes.setEnabled(true);
	     			}
	     		});
	     		textPatentes.setBounds(10, 34, 137, 200);
	     		textPatentes.setEnabled(false);
	     		getContentPane().add(textPatentes);
	     		
	     		JSeparator separator = new JSeparator();
	     		separator.setBounds(170, 133, 293, 2);
	     		getContentPane().add(separator);
	     		
	     		JScrollPane scrollPane = new JScrollPane();
	     		table_parquimetros = new DBTable();
	     		table_ubicaciones= new DBTable();
	     		
	     		scrollPane.setEnabled(true);
	     		scrollPane.setBounds(170, 34, 293, 91);
	     		getContentPane().add(scrollPane);

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
	     				textPatentes.setText("");
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
     				textPatentes.setText("");
     			}
     		});
 				table_ubicaciones.setEditable(false);
	     		scrollPane.setViewportView(table_ubicaciones);
	     		
	     		JScrollPane scrollPane_1 = new JScrollPane();
	     		scrollPane_1.setEnabled(true);
	     		scrollPane_1.setBounds(170, 161, 293, 98);
	     		getContentPane().add(scrollPane_1);
	     		
	     		table_parquimetros.setEditable(false);
	     		scrollPane_1.setViewportView(table_parquimetros);
	     		
	     		JLabel lblNewLabel_1 = new JLabel("Parquimetros");
	     		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	     		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	     		lblNewLabel_1.setBounds(198, 133, 239, 21);
	     		getContentPane().add(lblNewLabel_1);
	     		
	     		JLabel lblNewLabel_2 = new JLabel("Ubicaciones");
	     		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
	     		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	     		lblNewLabel_2.setBounds(170, 11, 293, 21);
	     		getContentPane().add(lblNewLabel_2);
	     		
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

}
