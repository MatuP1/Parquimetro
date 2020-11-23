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
	static final String btnUsuarioString = "Usuario";
	
	private PrincipalWindow vPrincipal;
	
	public PanelInicial(PrincipalWindow v) {
		vPrincipal = v;
		
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
		
		OyenteUsuario LUsuario = new OyenteUsuario();
		
		JButton btnUsuario = new JButton(btnUsuarioString);
		btnUsuario.addActionListener(LUsuario);
		this.add(btnUsuario);
		
	}
	
	
	private class OyenteAdmin implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				PanelIngresoAdmin pia = new PanelIngresoAdmin(vPrincipal);
				vPrincipal.cambiarFrame(pia);
			}
		}
	
	private class OyenteInspector implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				vPrincipal.getLogica().connectAdmin("admin");
				PanelIngresoInspector pii = new PanelIngresoInspector(vPrincipal);
				vPrincipal.cambiarFrame(pii);
			}
		}
	
	private class OyenteUsuario implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				vPrincipal.getLogica().connectAdmin("admin");
				PanelIngresoUsuario piu = new PanelIngresoUsuario(vPrincipal);
				vPrincipal.cambiarFrame(piu);
			}
		}
	

}
