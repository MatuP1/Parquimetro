package parquimetro;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelInicial extends JPanel {
	
	private static final long serialVersionUID = 1L;
	static final String lblUsuarioString = "Elija un Usuario";
	static final String btnAdminString = "Admin";
	static final String btnInspectorString = "Inspector";
	
	private PrincipalWindow vPrincipal;
	
	public PanelInicial(PrincipalWindow v) {
		vPrincipal = v;
		
		this.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblUsuario = new JLabel(lblUsuarioString);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblUsuario);
		
		JButton btnAdmin = new JButton(btnAdminString);
		this.add(btnAdmin);
		
		JButton btnInspector = new JButton(btnInspectorString);
		this.add(btnInspector);
	}
	
	

}
