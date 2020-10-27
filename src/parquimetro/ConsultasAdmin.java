package parquimetro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.sql.Types;
import java.sql.Connection;
//import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import quick.dbtable.*;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable; 


@SuppressWarnings("serial")
public class ConsultasAdmin extends javax.swing.JInternalFrame 
{
   private JPanel pnlConsulta;
   private JTextArea txtConsulta;
   private JButton botonBorrar;
   private JButton btnEjecutar;
   private DBTable tabla;    
   private JScrollPane scrConsulta;
   private Logica log;
   private PrincipalWindow vPrincipal;
   private JPanel panel;
   private JTable table;
   private DBTable table_1;
   
   
   public ConsultasAdmin(PrincipalWindow v) 
   {
      super();
      vPrincipal = v;
      log = vPrincipal.getLogica();
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
            pnlConsulta = new JPanel();
            pnlConsulta.setBounds(0, 0, 784, 186);
            getContentPane().add(pnlConsulta);
            {
               scrConsulta = new JScrollPane();
               pnlConsulta.add(scrConsulta);
               {
                  txtConsulta = new JTextArea();
                  scrConsulta.setViewportView(txtConsulta);
                  txtConsulta.setTabSize(3);
                  txtConsulta.setColumns(80);
                  txtConsulta.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
                  txtConsulta.setText("Ingrese su consulta a la base de datos");
                  txtConsulta.setFont(new java.awt.Font("Monospaced",0,12));
                  txtConsulta.setRows(10);
               }
            }
            {
               btnEjecutar = new JButton();
               pnlConsulta.add(btnEjecutar);
               btnEjecutar.setText("Ejecutar");
               btnEjecutar.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent evt) {
                     btnEjecutarActionPerformed(evt);
                  }
               });
            }
            {
            	botonBorrar = new JButton();
            	pnlConsulta.add(botonBorrar);
            	botonBorrar.setText("Borrar");            
            	botonBorrar.addActionListener(new ActionListener() {
            		public void actionPerformed(ActionEvent arg0) {
            		  txtConsulta.setText("");            			
            		}
            	});
            }	
         }
         {
        	// crea la tabla  
        	tabla = log.connectAdmin();
        	
        	// Agrega la tabla al frame (no necesita JScrollPane como Jtable)

            tabla.setBounds(10, 336 , 754, 325);
            getContentPane().add(tabla);           
            {
            	panel = new JPanel();
            	panel.setBounds(0, 197, 774, 139);
            	getContentPane().add(panel);
            	panel.setLayout(null);
            	           	
            	JLabel lblTablas = new JLabel("Tablas");
            	lblTablas.setHorizontalAlignment(SwingConstants.CENTER);
            	lblTablas.setFont(new Font("Tahoma", Font.PLAIN, 16));
            	lblTablas.setBounds(128, 0, 105, 22);
            	panel.add(lblTablas);
            	
            	JLabel lblNewLabel = new JLabel("Atributos");
            	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
            	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            	lblNewLabel.setBounds(555, 6, 87, 14);
            	panel.add(lblNewLabel);
            	
            	JScrollPane scrollPane = new JScrollPane();
            	scrollPane.setBounds(10, 24, 359, 104);
            	panel.add(scrollPane);
            	
            	JList list = new JList();
            	scrollPane.setViewportView(list);
            	String [] elementos = log.getTables();
            	list.setListData(elementos);
            	JScrollPane scrollPane_1 = new JScrollPane();
            	scrollPane_1.setBounds(379, 24, 385, 104);
            	panel.add(scrollPane_1);
            	
            	JList list_1 = new JList();
            	scrollPane_1.setViewportView(list_1);
            }
           // setea la tabla para s�lo lectura (no se puede editar su contenido)  
           tabla.setEditable(false);       
          
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void thisComponentShown(ComponentEvent evt) 
   {
      log = new Logica();
   }
   
   private void thisComponentHidden(ComponentEvent evt) 
   {
      log.desconectar();
      vPrincipal.volverPanelInicial();
   }

   private void btnEjecutarActionPerformed(ActionEvent evt) 
   {
      this.refrescarTabla();      
   }
  
   private void refrescarTabla()
   {
      try
      {  
    	  
    	 // seteamos la consulta a partir de la cual se obtendr�n los datos para llenar la tabla
    	 tabla.setSelectSql(this.txtConsulta.getText().trim());

    	  // obtenemos el modelo de la tabla a partir de la consulta para 
    	  // modificar la forma en que se muestran de algunas columnas  
    	  tabla.createColumnModelFromQuery(); 
    	  for (int i = 0; i < tabla.getColumnCount(); i++)
    	  { // para que muestre correctamente los valores de tipo TIME (hora)  		   		  
    		 if	 (tabla.getColumn(i).getType()==Types.TIME)  
    		 {    		 
    		    tabla.getColumn(i).setType(Types.CHAR);  
  	       	 }
    		 // cambiar el formato en que se muestran los valores de tipo DATE
    		 if	 (tabla.getColumn(i).getType()==Types.DATE)
    		 {
    		    tabla.getColumn(i).setDateFormat("dd/MM/YYYY");
    		 }
          }  
    	  // actualizamos el contenido de la tabla.   	     	  
    	  tabla.refresh();
    	  // No es necesario establecer  una conexi�n, crear una sentencia y recuperar el 
    	  // resultado en un resultSet, esto lo hace autom�ticamente la tabla (DBTable) a 
    	  // patir de la conexi�n y la consulta seteadas con connectDatabase() y setSelectSql() respectivamente.
          
    	  
    	  
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
      
   }
}
