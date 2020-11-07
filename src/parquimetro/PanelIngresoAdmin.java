package parquimetro;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class PanelIngresoAdmin extends javax.swing.JInternalFrame{
	private static final long serialVersionUID = 1L;
	
	private PrincipalWindow vPrincipal;
	private Logica logica;
	private int Ancho,Alto;
	private JPasswordField passwordField;
	
	public PanelIngresoAdmin(PrincipalWindow v) {
		vPrincipal = v;
		Ancho = v.getAncho();
		Alto = v.getAlto();
		logica =v.getLogica();
		
         setVisible(true);
         this.setTitle("Ingreso Administrador");
         this.setClosable(false);
         this.setMaximizable(false);
         this.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent evt) {
               thisComponentHidden(evt);
            }
            public void componentShown(ComponentEvent evt) {
               thisComponentShown(evt);
            }
         });
		
		this.setBounds(0, 0, Ancho, Alto);
		getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(206, 360, 385, 20);
		getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Ingrese su Contraseña");
		lblNewLabel.setBounds(60, 363, 136, 14);
		getContentPane().add(lblNewLabel);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pass = new String(passwordField.getPassword());
				if(logica.checkAdmin(pass)) {
					logica.connectAdmin(pass);
					ConsultasAdmin ca = new ConsultasAdmin(vPrincipal);
					vPrincipal.cambiarFrame(ca);
				}else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Ingrese la contraseña nuevamente","Mensaje Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnIngresar.setBounds(484, 409, 89, 23);
		getContentPane().add(btnIngresar);
		
		JButton btnBack = new JButton("Volver al Inicio");
		btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               vPrincipal.volverPanelInicial();
            }
         });
		btnBack.setBounds(220, 409, 120, 23);
		getContentPane().add(btnBack);
	}
	
	private void thisComponentShown(ComponentEvent evt) {
	}
	   
	private void thisComponentHidden(ComponentEvent evt) {
	
	}
}
