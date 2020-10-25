package parquimetro;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelInspector extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private PrincipalWindow vPrincipal;
	private int Ancho,Alto;
	
	public PanelInspector(PrincipalWindow v) {
		vPrincipal = v;
		Ancho=v.getAncho();
		Alto=v.getAlto();
		
		this.setLayout(new GridLayout(0, 1, 0, 0));
		this.setBounds(0, 0, Ancho, Alto);
		
		JLabel lblUsuario = new JLabel("Soy PANELInspector");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblUsuario);
		
	
	}

}
