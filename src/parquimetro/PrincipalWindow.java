package parquimetro;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;


public class PrincipalWindow extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	protected static final int AnchoVentana = 800, AltoVentana = 800;
	
	private PanelInicial panelInicio;
	protected JPanel panelPrincipal;
	private Logica logica; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PrincipalWindow frame = new PrincipalWindow();
		frame.setVisible(true);
	};
	
	
	public PrincipalWindow() {
		
		super("Parquimetros");
	
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, AnchoVentana, AltoVentana);
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		logica = new Logica();

		panelInicio = new PanelInicial(this);
		setContentPane(panelInicio);
		

	}
	
	public int getAncho() {
		return AnchoVentana;
	}
	
	public int getAlto() {
		return AltoVentana;
	}
	
	public Logica getLogica() {
		return logica;
	}
	
	public void cambiarPaneles(JPanel nuevo) {
		setContentPane(nuevo);
	}
	
	public void cambiarFrame(JInternalFrame frame) {
		this.setContentPane(frame);
	}
	
	public void volverPanelInicial() {
		this.setContentPane(panelInicio);
	}
	
	
}
