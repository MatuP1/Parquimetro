package parquimetro;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
@SuppressWarnings("serial")
public class UnidadInspector extends javax.swing.JInternalFrame
{
	private JTextField numPatente;
	private JTextField numUbicacion;
	public UnidadInspector() {
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblPatente = new JLabel("Patente");
		getContentPane().add(lblPatente, "2, 4, 5, 1");
		
		numPatente = new JTextField();
		getContentPane().add(numPatente, "8, 4, fill, default");
		numPatente.setColumns(10);
		
		JLabel lblUbicacion = new JLabel("Ubicacion");
		getContentPane().add(lblUbicacion, "2, 12, 5, 1");
		
		numUbicacion = new JTextField();
		getContentPane().add(numUbicacion, "8, 12, fill, default");
		numUbicacion.setColumns(10);
		
		JLabel lblParquimetro = new JLabel("Parquimetro");
		lblParquimetro.setEnabled(false);
		getContentPane().add(lblParquimetro, "2, 14, 5, 1");
		
		JComboBox listaParq = new JComboBox();
		listaParq.setEnabled(false);
		getContentPane().add(listaParq, "8, 14, fill, default");
	}
}
