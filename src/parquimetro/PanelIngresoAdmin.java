package parquimetro;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JPasswordField;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PanelIngresoAdmin extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private PrincipalWindow vPrincipal;
	private Logica logica;
	private int Ancho,Alto;
	private String password,pass;
	private JPasswordField passwordField;

	
	public PanelIngresoAdmin(PrincipalWindow v) {
		vPrincipal = v;
		Ancho = v.getAncho();
		Alto = v.getAlto();
		
		
		this.setBounds(0, 0, Ancho, Alto);
		this.setLayout(new GridBagLayout());
		logica = vPrincipal.getLogica();		
		JPanel panel = new JPanel();
		OyentePass LPass = new OyentePass();
		panel.setBounds(0, 0, 200, 200);
		
		passwordField = new JPasswordField(30);
		//String password = new String(passwordField.getPassword());
		passwordField.addKeyListener(LPass);
		
		JLabel labelIngreso = new JLabel("Ingrese su contraseña");
		
		panel.add(labelIngreso);
		panel.add(passwordField);
		
		//System.out.println("El pass es: " + password);
		this.add(panel);	
	}
	
	
	private class OyentePass implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			pass = new String(passwordField.getPassword());
			System.out.println("El pass es: " + pass);
			if(e.getKeyCode() == KeyEvent.VK_ENTER && logica.checkAdmin(pass)) { //TENGO EL PROBLEMA DE QUE PASSWORD ES NULL
				System.out.println("Checkadmin devolvio true");
				PanelAdmin pa = new PanelAdmin(vPrincipal);
				vPrincipal.cambiarPaneles(pa);
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
		
	}
	

}
