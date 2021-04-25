package estructuraTP.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import estructuraTP.dao.ExplicacionDAO;
import estructuraTP.dao.InvestigacionDAO;
import estructuraTP.modelo.Explicacion;
import estructuraTP.modelo.Investigacion;

import javax.swing.JLabel;

public class CrearExplicaciones extends JPanel {
	private JTextField textFieldfecha;
	private JTextField textFieldEpigrafe;

	/**
	 * Create the panel.
	 * @param explic 
	 * @param mod 
	 */
	public CrearExplicaciones(JFrame frame, boolean mod, Explicacion explic, String enlaccc) {
		setLayout(null);
		
		
		
		JLabel lblCrearExpliaciones = new JLabel("Crear Explicaciones");
		lblCrearExpliaciones.setBounds(154, 11, 182, 14);
		add(lblCrearExpliaciones);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(369, 318, 91, 23);
		add(btnVolver);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(10, 46, 46, 14);
		add(lblTitulo);
		
		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(10, 114, 96, 14);
		add(lblContenido);
		
		JLabel lblFecha = new JLabel("Epigrafe");
		lblFecha.setBounds(10, 71, 46, 14);
		add(lblFecha);
		
		textFieldfecha = new JTextField();
		textFieldfecha.setBounds(100, 71, 86, 20);
		//add(textFieldfecha);
		textFieldfecha.setColumns(10);
		
		
		JTextField textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(100, 43, 168, 20);
		add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JTextField textFieldContenido = new JTextField();
		textFieldContenido.setBounds(100, 99, 150, 43);
		add(textFieldContenido);
		textFieldContenido.setColumns(10);
		
		
		JButton btnAgregar = new JButton("Crear explicacion");
		btnAgregar.setBounds(134, 318, 202, 23);
		add(btnAgregar);
		
		textFieldEpigrafe = new JTextField();
		textFieldEpigrafe.setBounds(100, 68, 86, 20);
		add(textFieldEpigrafe);
		textFieldEpigrafe.setColumns(10);
		
		JButton btnMoficarExplicacion = new JButton("Moficar explicacion");
		btnMoficarExplicacion.setBounds(134, 318, 202, 23);
		add(btnMoficarExplicacion);
		
		
		ActionListener listener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        PanelPrincipalPantalla panel = new PanelPrincipalPantalla(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		
		btnVolver.addActionListener(listener);
		
		ActionListener listenerGuardar = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String titulo= textFieldTitulo.getText();
		        String contenid= textFieldContenido.getText();
		        String epigrafe= textFieldEpigrafe.getText();
		        Explicacion c = new Explicacion(null, titulo, epigrafe, contenid, LocalDate.now());
		        ExplicacionDAO cd= new ExplicacionDAO();
		        cd.guardar(c, enlaccc);
		        Explicaciones panel = new Explicaciones(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		        }
		};
		btnAgregar.addActionListener(listenerGuardar);
		
		ActionListener listenerModi = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String titulo= textFieldTitulo.getText();
		        String contenid= textFieldContenido.getText();
		        String epigrafe= textFieldEpigrafe.getText();
		        Explicacion c = new Explicacion(explic.getIdExplicacion(), titulo, epigrafe, contenid, null);
		        ExplicacionDAO cd= new ExplicacionDAO();
		        cd.modificar(c);
		        Explicaciones panel = new Explicaciones(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		        }
		};
		btnMoficarExplicacion.addActionListener(listenerModi);
		if(mod)
		{
			btnAgregar.setVisible(false);
			btnMoficarExplicacion.setVisible(true);
			textFieldTitulo.setText(explic.getTitulo());
			textFieldEpigrafe.setText(explic.getEpigrafe());
			textFieldContenido.setText(explic.getContenido());
		}
		else
		{
			btnAgregar.setVisible(true);
			btnMoficarExplicacion.setVisible(false);
			
		}
		
	}
}
