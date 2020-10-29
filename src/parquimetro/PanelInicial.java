package parquimetro;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class PanelInicial extends JPanel {
	
	private static final long serialVersionUID = 1L;
	static final String lblUsuarioString = "Elija un Usuario";
	static final String btnAdminString = "Admin";
	static final String btnInspectorString = "Inspector";
	
	private PrincipalWindow vPrincipal;
	private int Ancho,Alto;
	private JPasswordField passwordField;
	
	public PanelInicial(PrincipalWindow v) {
		vPrincipal = v;
		Ancho=v.getAncho();
		Alto=v.getAlto();
		
		this.setLayout(new GridLayout(0, 1, 0, 0));
		this.setBounds(0, 0, 928, 512);
		
		JLabel lblUsuario = new JLabel(lblUsuarioString);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblUsuario);
		
		OyenteAdmin LAdmin = new OyenteAdmin();
		
		JButton btnAdmin = new JButton(btnAdminString);
		btnAdmin.addActionListener(LAdmin);		
		this.add(btnAdmin);
		
		OyenteInspector LInspector = new OyenteInspector();
		
		JButton btnInspector = new JButton(btnInspectorString);
		btnInspector.addActionListener(LInspector);
		this.add(btnInspector);
		
	}
	
	
	private class OyenteAdmin implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
				PanelIngresoAdmin pia = new PanelIngresoAdmin(vPrincipal);
				vPrincipal.cambiarPaneles(pia);
			}
		}
	
	private class OyenteInspector implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				PanelIngresoInspector pii = new PanelIngresoInspector(vPrincipal);
				vPrincipal.getLogica().connectAdmin("admin");
				vPrincipal.cambiarPaneles(pii);
			}
		}
	

}
