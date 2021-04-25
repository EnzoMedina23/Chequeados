package estructuraTP.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import estructuraTP.dao.ChequeoDAO;
import estructuraTP.modelo.Chequeo;

import javax.swing.JComboBox;

public class CrearChequeos extends JPanel {
	private JTextField textFieldFrase;
	private JTextField textFieldMedio;
	private JTextField textFieldEnlace;
	private JTextField textFieldFecha;
	private JTextField textFieldClave;

	/**
	 * Create the panel.
	 * @param mod 
	 * @param valor 
	 */
	public CrearChequeos(JFrame frame, boolean mod, int valor, String fras, String medios, String enla, String fechas) {
		setLayout(null);
		setLayout(null);
		
		JCalendar calendar;
		 
		// Instanciar Componente
		calendar = new JCalendar();
		 
		// Ubicar y agregar al panel
		calendar.setBounds(115, 133, 186, 150);
		 
		add(calendar);
		
		//LocalDate f = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		calendar.setWeekOfYearVisible(false);
		
		JLabel lblCrearchequeos = new JLabel("CrearChequeos");
		lblCrearchequeos.setBounds(171, 30, 91, 14);
		add(lblCrearchequeos);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(408, 407, 91, 23);
		add(btnVolver);
		
		JLabel lblFrase = new JLabel("Frase");
		lblFrase.setBounds(10, 61, 46, 14);
		add(lblFrase);
		
		JLabel lblMedioDePublicacion = new JLabel("Medio de publicacion");
		lblMedioDePublicacion.setBounds(10, 86, 123, 14);
		add(lblMedioDePublicacion);
		
		JLabel lblEnlace = new JLabel("Enlace");
		lblEnlace.setBounds(10, 111, 46, 14);
		add(lblEnlace);
		
		JLabel lblFechaDeFrase = new JLabel("Fecha de frase");
		lblFechaDeFrase.setBounds(10, 136, 111, 14);
		add(lblFechaDeFrase);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 302, 91, 14);
		add(lblCategoria);
		
		JLabel lblPalabraClave = new JLabel("Palabra Clave");
		lblPalabraClave.setBounds(10, 327, 111, 14);
		add(lblPalabraClave);
		
		JLabel lblResultadoVerificacion = new JLabel("Resultado verificacion");
		lblResultadoVerificacion.setBounds(10, 352, 142, 14);
		add(lblResultadoVerificacion);
		
		textFieldFrase = new JTextField();
		textFieldFrase.setBounds(66, 58, 86, 20);
		add(textFieldFrase);
		textFieldFrase.setColumns(10);
		
		textFieldMedio = new JTextField();
		textFieldMedio.setBounds(138, 83, 86, 20);
		add(textFieldMedio);
		textFieldMedio.setColumns(10);
		
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(115, 133, 86, 20);
		//add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JComboBox<String> boxCategoria = new JComboBox<String>();
		boxCategoria.setBounds(85, 301, 111, 17);
		add(boxCategoria);
		boxCategoria.addItem("Economia");
		boxCategoria.addItem("Justicia");
		boxCategoria.addItem("Politica");
		boxCategoria.addItem("Sociedad");
		
		
		textFieldClave = new JTextField();
		textFieldClave.setBounds(95, 324, 86, 20);
		add(textFieldClave);
		textFieldClave.setColumns(10);
		
		JComboBox<String> boxVerificacion = new JComboBox<String>();
		boxVerificacion.setBounds(135, 351, 111, 17);
		add(boxVerificacion);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(36, 407, 89, 23);
		add(btnGuardar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(135, 407, 89, 23);
		add(btnModificar);
		boxVerificacion.addItem("true");
		boxVerificacion.addItem("false");
		
		ActionListener listener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        PanelPrincipalPantalla panel = new PanelPrincipalPantalla(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		ActionListener listenerGuardar = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String frase = textFieldFrase.getText();
		        String medi= textFieldMedio.getText();
		        String clave= textFieldClave.getText();
		        String fecha1= textFieldFecha.getText();
		        String categoria1 = (String) boxCategoria.getSelectedItem();
		        String verifi = (String) boxVerificacion.getSelectedItem();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    	
		    	//convert String to LocalDate
		    	LocalDate fechaDate = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        boolean estad=false;
		        if(verifi=="Verdadero")
		        	estad=true;
		        Chequeo nuevo = new Chequeo(null,categoria1, clave, frase, medi, enla, fechaDate, estad);
		        ChequeoDAO cd= new ChequeoDAO();
		        cd.guardar(nuevo);
		        Chequeos panel = new Chequeos(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		        }
		};
		ActionListener listenerModif = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        String clave= textFieldClave.getText();
		        String categoria1 = (String) boxCategoria.getSelectedItem();
		        String verifi = (String) boxVerificacion.getSelectedItem();
		        
		        boolean estad=false;
		        if(verifi=="Verdadero")
		        	estad=true;
		        Chequeo c = new Chequeo(null,categoria1, clave, clave, clave, clave, null, estad);
		        ChequeoDAO cd= new ChequeoDAO();
		        cd.modificar(c,valor);
		        Chequeos panel = new Chequeos(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		        }
		};
		
		
		
		btnGuardar.addActionListener(listenerGuardar);
		btnModificar.addActionListener(listenerModif);
		btnVolver.addActionListener(listener);
		
		if(mod == true)
		{
			btnModificar.setVisible(true);
			btnGuardar.setVisible(false);
			lblFrase.setVisible(false);
			textFieldFrase.setVisible(false);			
			lblMedioDePublicacion.setVisible(false);
			textFieldMedio.setVisible(false);
			lblEnlace.setVisible(false);
			lblFechaDeFrase.setVisible(false);
			textFieldFecha.setVisible(false);
			textFieldClave.setText(fechas);
			calendar.setVisible(false);
			boxCategoria.setSelectedItem(medios);
			boxVerificacion.setSelectedItem(enla);
		}
		else if (mod== false)
		{
			btnModificar.setVisible(false);
			btnGuardar.setVisible(true);
			lblFrase.setVisible(true);
			textFieldFrase.setVisible(true);			
			lblMedioDePublicacion.setVisible(true);
			textFieldMedio.setVisible(true);
			lblEnlace.setVisible(true);
			lblFechaDeFrase.setVisible(true);
			textFieldFecha.setVisible(true);
			calendar.setVisible(true);
			textFieldFrase.setText(fras);
			textFieldMedio.setText(medios);

			textFieldFecha.setText(fechas);
			int anio = Integer.parseInt(fechas.substring(0,4));
			int mes= Integer.parseInt(fechas.substring(5,7));
			int dia= Integer.parseInt(fechas.substring(8,10));

			Calendar calendario = new GregorianCalendar(anio, mes-1, dia);
			calendar.setDate(calendario.getTime());
			
			JLabel lblEnlaceResp = new JLabel(enla);
			lblEnlaceResp.setBounds(100, 111, 86, 20);
			add(lblEnlaceResp);
		}
	}
}
