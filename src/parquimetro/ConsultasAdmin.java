package parquimetro;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.sql.Types;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 


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
   private JPanel panelListas;
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
        	tabla = log.getTable();

            tabla.setBounds(10, 336 , 754, 325);
            getContentPane().add(tabla);           
            {
            	panelListas = new JPanel();
            	panelListas.setBounds(0, 197, 774, 139);
            	getContentPane().add(panelListas);
            	panelListas.setLayout(null);
            	           	
            	JLabel lblTablas = new JLabel("Tablas");
            	lblTablas.setHorizontalAlignment(SwingConstants.CENTER);
            	lblTablas.setFont(new Font("Tahoma", Font.PLAIN, 16));
            	lblTablas.setBounds(128, 0, 105, 22);
            	panelListas.add(lblTablas);
            	
            	JLabel lblNewLabel = new JLabel("Atributos");
            	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
            	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            	lblNewLabel.setBounds(555, 6, 87, 14);
            	panelListas.add(lblNewLabel);
            	
            	JScrollPane scrollPane = new JScrollPane();
            	scrollPane.setBounds(10, 24, 359, 104);
            	panelListas.add(scrollPane);
            	DefaultListModel modeloLista;
            	modeloLista = log.getListaTablas();
            	JList listaTablas = new JList(modeloLista);
            	scrollPane.setViewportView(listaTablas);
            	JScrollPane scrollPane_1 = new JScrollPane();
            	scrollPane_1.setBounds(379, 24, 385, 104);
            	panelListas.add(scrollPane_1);
            	
            	
            	
            	listaTablas.addMouseListener(new MouseAdapter() {
            		@Override
            		public void mouseClicked(MouseEvent e) {
            			DefaultListModel atributos = new DefaultListModel(); 
            			String actual = (String) listaTablas.getSelectedValue();
            			atributos=log.getValues(actual);
            			JList listaAtr = new JList(atributos);
                    	scrollPane_1.setViewportView(listaAtr);
            		}
            	});
            	
            }
           tabla.setEditable(false);       
          
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
      log.desconectar();
   }

   private void btnEjecutarActionPerformed(ActionEvent evt) 
   {
      this.refrescarTabla();      
   }
  
   private void refrescarTabla()
   {
      try
      {  
    	 Statement consulta = log.getConnection().createStatement();
    	 consulta.execute(this.txtConsulta.getText().trim());
    	 ResultSet rs_consulta = consulta.getResultSet();

    	  tabla.refresh(rs_consulta);; 
    	  for (int i = 0; i < tabla.getColumnCount(); i++)
    	  {  		   		  
    		 if	 (tabla.getColumn(i).getType()==Types.TIME)  
    		 {    		 
    		    tabla.getColumn(i).setType(Types.CHAR);  
  	       	 }
    		 if	 (tabla.getColumn(i).getType()==Types.DATE)
    		 {
    		    tabla.getColumn(i).setDateFormat("dd/MM/YYYY");
    		 }
          }  
  
       }
      catch (SQLException ex)
      {
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
