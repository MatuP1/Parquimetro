package parquimetro;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private int Ancho,Alto;
	
	public PanelInicial(PrincipalWindow v) {
		vPrincipal = v;
		Ancho=v.getAncho();
		Alto=v.getAlto();
		
		this.setLayout(new GridLayout(0, 1, 0, 0));
		this.setBounds(0, 0, Ancho, Alto);
		
		JLabel lblUsuario = new JLabel(lblUsuarioString);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblUsuario);
		
		OyenteBotones Listener = new OyenteBotones();
		
		JButton btnAdmin = new JButton(btnAdminString);
		btnAdmin.addActionListener(Listener);
		btnAdmin.setActionCommand("Admin");
		this.add(btnAdmin);
		
		JButton btnInspector = new JButton(btnInspectorString);
		btnInspector.addActionListener(Listener);
		btnInspector.setActionCommand("Inspector");
		this.add(btnInspector);
		
	}
	
	
	private class OyenteBotones implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			switch (s) {
			case ("Admin"): {
				
				PanelAdmin pa = new PanelAdmin(vPrincipal);
				vPrincipal.cambiarPaneles(pa);
			}
			case ("Inspector"): {
				
				PanelInspector pi = new PanelInspector(vPrincipal);
				vPrincipal.cambiarPaneles(pi);
			}
			}
		}
	}
	

}
