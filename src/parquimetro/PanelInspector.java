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

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import quick.dbtable.DBTable;

public class PanelInspector extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private PrincipalWindow vPrincipal;
	private Logica logica;
	private DBTable multas;
	private DBTable table_ubicaciones;
	private DBTable table_parquimetros;
	
	public PanelInspector(PrincipalWindow v) {
		vPrincipal = v;
		logica = v.getLogica();
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
	        	JTextPane textPatentes = new JTextPane();
	     		textPatentes.addKeyListener(new KeyAdapter() {
	     			@Override
	     			public void keyTyped(KeyEvent e) {
	     			}
	     		});
	     		textPatentes.setBounds(10, 34, 137, 200);
	     		getContentPane().add(textPatentes);
	     		
	     		JSeparator separator = new JSeparator();
	     		separator.setBounds(170, 133, 293, 2);
	     		getContentPane().add(separator);
	     		
	     		JScrollPane scrollPane = new JScrollPane();
	     		scrollPane.setEnabled(false);
	     		scrollPane.setBounds(170, 34, 293, 91);
	     		getContentPane().add(scrollPane);
	     		
	     		table_ubicaciones = new DBTable();
	     		scrollPane.setViewportView(table_ubicaciones);
	     		
	     		JScrollPane scrollPane_1 = new JScrollPane();
	     		scrollPane_1.setEnabled(false);
	     		scrollPane_1.setBounds(170, 161, 293, 98);
	     		getContentPane().add(scrollPane_1);
	     		
	     		table_parquimetros = new DBTable();
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
	     		
	     		JButton btnPatentes = new JButton("Ingresar Patentes");
	     		btnPatentes.addMouseListener(new MouseAdapter() {
	     			@Override
	     			public void mouseClicked(MouseEvent e) {
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
	     		table_ubicaciones = logica.getTable();    
	          /*
	     		 try
	   	      {  
	      	 // seteamos la consulta a partir de la cual se obtendr�n los datos para llenar la tabla
	      	 table_ubicaciones.setSelectSql("SELECT * FROM Ubicaciones;".trim());

	      	  // obtenemos el modelo de la tabla a partir de la consulta para 
	      	  // modificar la forma en que se muestran de algunas columnas  
	      	  table_ubicaciones.createColumnModelFromQuery(); 
	      	  for (int i = 0; i < table_ubicaciones.getColumnCount(); i++)
	      	  { // para que muestre correctamente los valores de tipo TIME (hora)  		   		  
	      		 if	 (table_ubicaciones.getColumn(i).getType()==Types.TIME)  
	      		 {    		 
	      		    table_ubicaciones.getColumn(i).setType(Types.CHAR);  
	    	       	 }
	      		 // cambiar el formato en que se muestran los valores de tipo DATE
	      		 if	 (table_ubicaciones.getColumn(i).getType()==Types.DATE)
	      		 {
	      		    table_ubicaciones.getColumn(i).setDateFormat("dd/MM/YYYY");
	      		 }
	            }  
	      	  // actualizamos el contenido de la tabla.   	     	  
	      	  table_ubicaciones.refresh();
	   	       }
	   	      catch (SQLException ex)
	   	      {
	   	         // en caso de error, se muestra la causa en la consola
	   	         System.out.println("SQLException: " + ex.getMessage());
	   	         System.out.println("SQLState: " + ex.getSQLState());
	   	         System.out.println("VendorError: " + ex.getErrorCode());
	   	         JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
	   	                                       ex.getMessage() + "\n", 
	   	                                       "Error al ejecutar la consulta.",
	   	                                       JOptionPane.ERROR_MESSAGE);
	   	         
	   	      }
	   	 
	   		 */
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
