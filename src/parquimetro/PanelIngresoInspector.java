package parquimetro;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class PanelIngresoInspector extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private PrincipalWindow vPrincipal;
	private Logica logica;
	private int Ancho,Alto;
	private String user,pass;
	private JPasswordField passwordField;
	private JTextField userField;

	public PanelIngresoInspector(PrincipalWindow v){
		vPrincipal = v;
		Ancho = v.getAncho();
		Alto = v.getAlto();
		
		
		this.setBounds(0, 0, Ancho, Alto);
		this.setLayout(new GridBagLayout());
		logica = vPrincipal.getLogica();	
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel panelUser = new JPanel();
		JPanel panelPassword = new JPanel();
		panelUser.setLayout(new BorderLayout());
		panelPassword.setLayout(new BorderLayout());
		
		OyentePass LPass = new OyentePass();
		
		userField = new JTextField(30);
		
		passwordField = new JPasswordField(30);
		passwordField.addKeyListener(LPass);
		
		JLabel labelUser = new JLabel("Ingrese legajo: ");
		JLabel labelPass = new JLabel("Ingrese contraseņa: ");
		
		panelUser.add(labelUser,BorderLayout.WEST);
		panelUser.add(userField,BorderLayout.CENTER);
		
		panelPassword.add(labelPass,BorderLayout.WEST);
		panelPassword.add(passwordField,BorderLayout.CENTER);
		
		panel.add(panelUser,BorderLayout.NORTH);
		panel.add(panelPassword,BorderLayout.CENTER);
		
		JButton btnBack = new JButton("Volver al Inicio");
		btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               vPrincipal.volverPanelInicial();
            }
         });
		
		this.add(panel);
		this.add(btnBack);
	}
	
	private class OyentePass implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			pass = new String(passwordField.getPassword());
			user = new String(userField.getText());
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(logica.checkInspector(user,pass)) { 
				logica.desconectar();	
				ConsultasInspector pi = new ConsultasInspector(vPrincipal,user);
				vPrincipal.cambiarFrame(pi);
				}else {
					JOptionPane.showMessageDialog(null, "Legajo o Contraseņa incorrecta","Mensaje Error", JOptionPane.WARNING_MESSAGE);
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
