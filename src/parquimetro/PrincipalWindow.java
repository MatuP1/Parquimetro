package parquimetro;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;


public class PrincipalWindow extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	protected static final int AnchoVentana = 800, AltoVentana = 800;
	
	protected PanelAdmin panelAdmin;
	private PanelInspector panelInspector;
	private PanelInicial panelInicio;
	protected JPanel panelPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalWindow frame = new PrincipalWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public PrincipalWindow() {
		
		super("Parquimetros");
	
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, AnchoVentana, AltoVentana);
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);

		panelInicio = new PanelInicial(this);
		setContentPane(panelInicio);
		

	}
	
	public int getAncho() {
		return AnchoVentana;
	}
	
	public int getAlto() {
		return AltoVentana;
	}
	
	public void cambiarPaneles(JPanel nuevo) {
		setContentPane(nuevo);
	}
	
	
}
