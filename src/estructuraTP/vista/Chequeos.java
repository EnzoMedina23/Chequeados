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
import estructuraTP.modelo.Chequeo;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Chequeos extends JPanel {
	private JTextField textFieldPC;

	/**
	 * Create the panel.
	 */
	public Chequeos(JFrame frame) {
		setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(372, 325, 89, 23);
		add(btnBuscar);
		
		textFieldPC = new JTextField();
		textFieldPC.setBounds(276, 328, 86, 20);
		add(textFieldPC);
		textFieldPC.setColumns(10);
		setLayout(null);
		setLayout(null);

		
		JLabel lblChequeos = new JLabel("Chequeos");
		lblChequeos.setBounds(189, 23, 63, 14);
		add(lblChequeos);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(471, 325, 91, 23);
		add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(10, 325, 91, 23);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(110, 325, 91, 23);
		add(btnEliminar);
		
		ActionListener listener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        PanelPrincipalPantalla panel = new PanelPrincipalPantalla(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		
		
		
		btnVolver.addActionListener(listener);
		
		String[] nombresColumnas = {"Codigo Chequeo", "Categoria", "Palabra Clave", "Frase", "Medio","Enlace", "Fecha", "Autenticidad" }; 
		
		DefaultTableModel model = new DefaultTableModel(nombresColumnas,0);
		
		ChequeoDAO ldao = new ChequeoDAO();
		ArrayList<Chequeo> resultado = ldao.consultar();
		
		for ( Chequeo s : resultado) {
			String idChequeo = s.getid();
			String categoria = s.getcategoria();
			String palabraClave =s.getClave();
			String Frase = s.getfrase();
			String Medio = s.getmedio();
			String Enlace = s.getenlace();
			LocalDate Fecha =s.getfecha();
			Boolean Autenticidad = s.getestado();
			
		Object [] registro = {idChequeo, categoria,palabraClave,Frase, Medio, Enlace, Fecha, Autenticidad};
		
		model.addRow(registro);		
	       

		setLayout(null);
		}

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 538, 257);
		add(scrollPane);




		JTable tableChequeos = new JTable();
		scrollPane.setViewportView(tableChequeos);
		tableChequeos.setModel(model);
		
		ActionListener listenerModificar = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	boolean mod= true;
		        int fila = tableChequeos.getSelectedRow();
				String valor0 = model.getValueAt(fila, 0).toString();
				int valor = Integer.parseInt(valor0);
				String fechass = model.getValueAt(fila, 2).toString();
				String cate= model.getValueAt(fila, 1).toString();
				String autenti= model.getValueAt(fila, 7).toString();
				CrearChequeos panel = new CrearChequeos(frame, mod, valor, null, cate, autenti, fechass);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		btnModificar.addActionListener(listenerModificar);
		
		ActionListener listenerEliminar = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        int fila = tableChequeos.getSelectedRow();
		        
				String valor0 = model.getValueAt(fila, 0).toString();
				int valor = Integer.parseInt(valor0);
				ChequeoDAO ch = new ChequeoDAO();
				ch.borrar(valor);
				model.removeRow(fila);
		        

		    }
		};
		btnEliminar.addActionListener(listenerEliminar);
		
		ActionListener listenerConsultarPC = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String clave = textFieldPC.getText();
				ChequeoDAO cdao = new ChequeoDAO();
				ArrayList<Chequeo> resultado = cdao.consultarBusqueda(clave);
				DefaultTableModel modelo = new DefaultTableModel(nombresColumnas,0);	
				for ( Chequeo s : resultado)
					{
					String idChequeo = s.getid();
					String categoria = s.getcategoria();
					String palabraClave =s.getClave();
					String Frase = s.getfrase();
					String Medio = s.getmedio();
					String Enlace = s.getenlace();
					LocalDate Fecha =s.getfecha();
					Boolean Autenticidad = s.getestado();
					
				Object [] registro = {idChequeo, categoria,palabraClave,Frase, Medio, Enlace, Fecha, Autenticidad};
					modelo.addRow(registro);		
				       
					}
				tableChequeos.setModel(modelo);
				textFieldPC.setText(null);
				}
		};
		
		btnBuscar.addActionListener(listenerConsultarPC);
	}
}
