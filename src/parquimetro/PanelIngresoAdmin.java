package parquimetro;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
		passwordField.addKeyListener(LPass);
		
		JLabel labelIngreso = new JLabel("Ingrese su contraseña");
		
		panel.add(labelIngreso);
		panel.add(passwordField);
		
		this.add(panel);
		
		JButton btnBack = new JButton("Volver a Inicio");
		btnBack.addMouseListener(new MouseAdapter() {
 			@Override
 			public void mouseClicked(MouseEvent e) {
 				vPrincipal.volverPanelInicial();
 			}
 		});
		
		this.add(btnBack);
	}
	
	
	private class OyentePass implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			pass = new String(passwordField.getPassword());
			System.out.println("El pass es: " + pass);
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(logica.checkAdmin(pass)) {
				System.out.println("Checkadmin devolvio true");
				logica.connectAdmin(pass);
				ConsultasAdmin ca = new ConsultasAdmin(vPrincipal);
				vPrincipal.cambiarFrame(ca);
				}else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta","Mensaje Error", JOptionPane.WARNING_MESSAGE);
				}
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
