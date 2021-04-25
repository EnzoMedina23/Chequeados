package estructuraTP.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.ChequeoDAO;
import estructuraTP.dao.InvestigacionDAO;
import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Investigacion;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Investigaciones extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Investigaciones(JFrame frame) {
		setLayout(null);
		
		JLabel lblInvestigaciones = new JLabel("Investigaciones");
		lblInvestigaciones.setBounds(336, 11, 118, 14);
		add(lblInvestigaciones);
		
		ActionListener listener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        PanelPrincipalPantalla panel = new PanelPrincipalPantalla(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		
		String[] nombresColumnas = {"Codigo Investigacion", "Categoria", "Palabra Clave", "Titulo", "Tema","Epigrafe", "Contenido", "Fecha" }; 
		
		DefaultTableModel model = new DefaultTableModel(nombresColumnas,0);
		
		InvestigacionDAO Cdao = new InvestigacionDAO();
		ArrayList<Investigacion> resultado = Cdao.consultar();
		
		for ( Investigacion s : resultado) {
			String idInvestigacion = s.getid();
			String categoria = s.getcategoria();
			String palabraClave =s.getclave1();
			String Titulo = s.getTitulo();
			String Tema = s.gettema();
			String Epigrafe = s.getEpigrafe();
			String Contenido = s.getContenido();
			LocalDate Fecha =s.getFecha();
			
		Object [] registro = {idInvestigacion, categoria, palabraClave, Titulo, Tema, Epigrafe, Contenido, Fecha};
		
		model.addRow(registro);		
		
		setLayout(null);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 712, 350);
		add(scrollPane);

		JTable tableInvestigaciones = new JTable();
		scrollPane.setViewportView(tableInvestigaciones);
		tableInvestigaciones.setModel(model);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(28, 433, 89, 23);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(619, 433, 89, 23);
		add(btnEliminar);
		
		textField = new JTextField();
		textField.setBounds(368, 434, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnBuscarTema = new JButton("Buscar tema");
		btnBuscarTema.setBounds(464, 433, 126, 23);
		add(btnBuscarTema);
		
		ActionListener listenerEliminar = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        int fila = tableInvestigaciones.getSelectedRow();
		        
				String valor0 = model.getValueAt(fila, 0).toString();
				int valor = Integer.parseInt(valor0);
				InvestigacionDAO ch = new InvestigacionDAO();
				ch.borrar(valor);
				model.removeRow(fila);
		        

		    }
		};
		ActionListener listenerConsultarTema = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tema = textField.getText();
				InvestigacionDAO cdao = new InvestigacionDAO();
				ArrayList<Investigacion> resultado = cdao.consultarBusqueda(tema);
				DefaultTableModel modelo = new DefaultTableModel(nombresColumnas,0);	
				for ( Investigacion s : resultado)
					{
					String idInvestigacion = s.getid();
					String categoria = s.getcategoria();
					String palabraClave =s.getclave1();
					String Titulo = s.getTitulo();
					String Tema = s.gettema();
					String Epigrafe = s.getEpigrafe();
					String Contenido = s.getContenido();
					LocalDate Fecha =s.getFecha();
					
				Object [] registro = {idInvestigacion, categoria, palabraClave, Titulo, Tema, Epigrafe, Contenido, Fecha};
					modelo.addRow(registro);		
				       
					}
				tableInvestigaciones.setModel(modelo);
				textField.setText(null);
				}
		};
		btnEliminar.addActionListener(listenerEliminar);
		btnBuscarTema.addActionListener(listenerConsultarTema);
		ActionListener listenerModificar = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	boolean mod= true;
		        int fila = tableInvestigaciones.getSelectedRow();
				String valor0 = model.getValueAt(fila, 0).toString();
				int valor = Integer.parseInt(valor0);
				String cate = model.getValueAt(fila, 1).toString();
				String pcs = model.getValueAt(fila, 2).toString();
				String titu = model.getValueAt(fila, 3).toString();
				String tem = model.getValueAt(fila, 4).toString();
				String epigrafe = model.getValueAt(fila, 5).toString();
				String conte = model.getValueAt(fila, 6).toString();
				LocalDate fechass =(LocalDate) model.getValueAt(fila,7);
				
				Investigacion cosas= new Investigacion(valor0, cate, tem,pcs, titu, epigrafe, conte, fechass);
				CrearInvestigaciones panel = new CrearInvestigaciones(frame, mod, cosas);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		btnModificar.addActionListener(listenerModificar);
		
	}
}
