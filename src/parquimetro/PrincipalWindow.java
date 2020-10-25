package parquimetro;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PrincipalWindow extends javax.swing.JFrame{
	public PrincipalWindow() {
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblUsuario = new JLabel("Eliga un usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblUsuario);
		
		JButton btnAdmin = new JButton("Admin");
		getContentPane().add(btnAdmin);
		
		JButton btnInspector = new JButton("Inspector");
		getContentPane().add(btnInspector);
	}

}
