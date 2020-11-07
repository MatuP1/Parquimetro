package parquimetro;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class PanelIngresoInspector extends javax.swing.JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private PrincipalWindow vPrincipal;
	private Logica logica;
	private int Ancho,Alto;
	private JTextField userField;
	private JPasswordField passwordField;
	
	public PanelIngresoInspector(PrincipalWindow v) {
		vPrincipal = v;
		Ancho = v.getAncho();
		Alto = v.getAlto();
		logica = vPrincipal.getLogica();
		
		this.setBounds(0, 0, Ancho, Alto);
        setVisible(true);
        this.setTitle("Ingreso Inspector");
        this.setClosable(false);
        this.setMaximizable(false);
        getContentPane().setLayout(null);
        
        userField = new JTextField(30);
        userField.setBounds(200, 293, 422, 20);
        getContentPane().add(userField);
        
        passwordField = new JPasswordField(30);
        passwordField.setBounds(200, 338, 422, 20);
        getContentPane().add(passwordField);
        
        JLabel lblLegajo = new JLabel("Ingrese Legajo: ");
        lblLegajo.setBounds(45, 296, 108, 14);
        getContentPane().add(lblLegajo);
        
        JLabel lblContraseña = new JLabel("Ingrese Contraseña: ");
        lblContraseña.setBounds(45, 341, 123, 14);
        getContentPane().add(lblContraseña);
        
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	String pass = new String(passwordField.getPassword());
    			String user = new String(userField.getText());
    			if(logica.checkInspector(user,pass)) { 
    				logica.desconectar();	
    				ConsultasInspector pi = new ConsultasInspector(vPrincipal,user);
    				vPrincipal.cambiarFrame(pi);
    			}else {
    				JOptionPane.showMessageDialog(null, "Legajo o Contraseña incorrecta","Mensaje Error", JOptionPane.WARNING_MESSAGE);
    			}
            }
         });
        
        btnIngresar.setBounds(516, 384, 89, 23);
        getContentPane().add(btnIngresar);
        
        JButton btnBack = new JButton("Volver al Inicio");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               vPrincipal.volverPanelInicial();
            }
         });
        btnBack.setBounds(214, 384, 123, 23);
        getContentPane().add(btnBack);
        this.addComponentListener(new ComponentAdapter() {
           public void componentHidden(ComponentEvent evt) {
              thisComponentHidden(evt);
           }
           public void componentShown(ComponentEvent evt) {
              thisComponentShown(evt);
           }
        });
		
	}
	
	 private void thisComponentShown(ComponentEvent evt) {
	 }
	   
	 private void thisComponentHidden(ComponentEvent evt) {
	    
	 }

}
