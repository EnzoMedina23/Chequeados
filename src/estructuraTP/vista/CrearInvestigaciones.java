package estructuraTP.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import estructuraTP.dao.ChequeoDAO;
import estructuraTP.dao.InvestigacionDAO;
import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Investigacion;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class CrearInvestigaciones extends JPanel {
	private JTextField textFieldfecha;

	/**
	 * Create the panel.
	 */
	public CrearInvestigaciones(JFrame frame, Boolean mod, Investigacion cc) {
		setLayout(null);
		
		
		//LocalDate f = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		JLabel lblCrearInvestigaciones = new JLabel("Crear Investigaciones");
		lblCrearInvestigaciones.setBounds(213, 11, 157, 14);
		add(lblCrearInvestigaciones);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(213, 345, 91, 23);
		add(btnVolver);
		
		ActionListener listener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        PanelPrincipalPantalla panel = new PanelPrincipalPantalla(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		
		btnVolver.addActionListener(listener);
		
		JButton btnAgregar = new JButton("Crear investigacion");
		btnAgregar.setBounds(343, 345, 202, 23);
		add(btnAgregar);
		
		
		JTextField textFieldTema = new JTextField();
		textFieldTema.setBounds(48, 33, 168, 20);
		add(textFieldTema);
		textFieldTema.setColumns(10);
		
		JTextField textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(58, 73, 168, 20);
		add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JTextField textFieldEpigrafe = new JTextField();
		textFieldEpigrafe.setBounds(58, 131, 168, 31);
		add(textFieldEpigrafe);
		textFieldEpigrafe.setColumns(10);
		
		JTextField textFieldContenido = new JTextField();
		textFieldContenido.setBounds(77, 197, 150, 43);
		add(textFieldContenido);
		textFieldContenido.setColumns(10);
		
		JLabel lblTema = new JLabel("Tema");
		lblTema.setBounds(10, 36, 46, 14);
		add(lblTema);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(10, 76, 46, 14);
		add(lblTitulo);
		
		JLabel lblEpigrafe = new JLabel("Epigrafe");
		lblEpigrafe.setBounds(10, 139, 62, 14);
		add(lblEpigrafe);
		
		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(10, 211, 96, 14);
		add(lblContenido);
		
		JTextField textFieldClave = new JTextField();
		textFieldClave.setBounds(92, 251, 168, 20);
		add(textFieldClave);
		textFieldClave.setColumns(10);
		
		JLabel lblPalabraClave = new JLabel("Palabra Clave");
		lblPalabraClave.setBounds(10, 254, 183, 14);
		add(lblPalabraClave);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 279, 73, 14);
		add(lblCategoria);
		
		JComboBox<String> boxCategoria2 = new JComboBox<String>();
		boxCategoria2.setBounds(77, 275, 132, 23);
		add(boxCategoria2);
		
		JButton btnActualizarInvestigacion = new JButton("Actualizar investigacion");
		btnActualizarInvestigacion.setBounds(343, 345, 202, 23);
		add(btnActualizarInvestigacion);
		boxCategoria2.addItem("Economia");
		boxCategoria2.addItem("Justicia");
		boxCategoria2.addItem("Politica");
		boxCategoria2.addItem("Sociedad");
		
		textFieldfecha = new JTextField();
		textFieldfecha.setBounds(57, 108, 86, 20);
		//add(textFieldfecha);
		textFieldfecha.setColumns(10);
		
		ActionListener listenerGuardar = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String tema = textFieldTema.getText();
		        String titulo= textFieldTitulo.getText();
		        String epigrafe= textFieldEpigrafe.getText();
		        String contenid= textFieldContenido.getText();
		        String clavel= textFieldClave.getText();
		        String categoria2 = (String) boxCategoria2.getSelectedItem();
		    	LocalDate fechaDate = LocalDate.now();
		        Investigacion c = new Investigacion(null, categoria2, tema, clavel, titulo, epigrafe, contenid, fechaDate);
		        InvestigacionDAO cd= new InvestigacionDAO();
		        cd.guardar(c);
		        Investigaciones panel = new Investigaciones(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		        }
		};
		btnAgregar.addActionListener(listenerGuardar);
		
		ActionListener listenerModi = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String tema = textFieldTema.getText();
		        String titulo= textFieldTitulo.getText();
		        String epigrafe= textFieldEpigrafe.getText();
		        String contenid= textFieldContenido.getText();
		        String clavel= textFieldClave.getText();
		        String categoria2 = (String) boxCategoria2.getSelectedItem();
		        Investigacion c = new Investigacion(cc.getid(), categoria2, tema, clavel, titulo, epigrafe, contenid, null);
		        InvestigacionDAO cd= new InvestigacionDAO();
		        cd.modificar(c);
		        Investigaciones panel = new Investigaciones(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		        }
		};
		btnActualizarInvestigacion.addActionListener(listenerModi);
		
		if(mod==true)
		{
			btnAgregar.setVisible(false);
			btnActualizarInvestigacion.setVisible(true);
			textFieldTema.setText(cc.gettema());
			textFieldTitulo.setText(cc.getTitulo());
			textFieldEpigrafe.setText(cc.getEpigrafe());
			textFieldContenido.setText(cc.getContenido());
			textFieldClave.setText(cc.getclave1());
			boxCategoria2.setSelectedItem(cc.getcategoria());
			
		}
		else
		{
			btnActualizarInvestigacion.setVisible(false);
			btnAgregar.setVisible(true);
		}
		
	}
}
