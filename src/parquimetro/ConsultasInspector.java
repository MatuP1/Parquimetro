package parquimetro;

import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import quick.dbtable.DBTable;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class ConsultasInspector extends JInternalFrame {
	private PrincipalWindow vPrincipal;
	private Logica logica;
	private DBTable multas;
	public ConsultasInspector(PrincipalWindow pw) {
		vPrincipal = pw;
		logica = vPrincipal.getLogica();
		multas = logica.connectInspector("inspector");
		getContentPane().setLayout(null);
		
		JTextPane textPatentes = new JTextPane();
		textPatentes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		textPatentes.setBounds(10, 34, 137, 200);
		getContentPane().add(textPatentes);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(170, 133, 293, 2);
		getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(170, 34, 293, 91);
		getContentPane().add(scrollPane);
		
		JList list_ubicaciones = new JList();
		scrollPane.setViewportView(list_ubicaciones);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(170, 161, 293, 98);
		getContentPane().add(scrollPane_1);
		
		JList list_parquimetros = new JList();
		scrollPane_1.setViewportView(list_parquimetros);
		
		JLabel lblNewLabel_1 = new JLabel("Parquimetros");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(198, 133, 239, 21);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ubicaciones");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(170, 11, 293, 21);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnPatentes = new JButton("Ingresar Patentes");
		btnPatentes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnPatentes.setEnabled(false);
		btnPatentes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPatentes.setHorizontalAlignment(SwingConstants.LEFT);
		btnPatentes.setBounds(10, 236, 137, 21);
		getContentPane().add(btnPatentes);
		
		JLabel lblNewLabel = new JLabel("Patentes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 9, 137, 14);
		getContentPane().add(lblNewLabel);
		
		JButton btnMultas = new JButton("Generar Multas");
		btnMultas.setEnabled(false);
		btnMultas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMultas.setBounds(10, 282, 453, 23);
		getContentPane().add(btnMultas);
	}
}
