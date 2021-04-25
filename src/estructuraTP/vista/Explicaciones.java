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
import estructuraTP.dao.ExplicacionDAO;
import estructuraTP.dao.InvestigacionDAO;
import estructuraTP.modelo.Explicacion;
import estructuraTP.modelo.Investigacion;

import javax.swing.JLabel;

public class Explicaciones extends JPanel {

	/**
	 * Create the panel.
	 */
	public Explicaciones(JFrame frame) {
		setLayout(null);
		
		JLabel lblExplicaciones = new JLabel("Explicaciones");
		lblExplicaciones.setBounds(299, 11, 91, 14);
		add(lblExplicaciones);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(557, 391, 91, 23);
		add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(32, 391, 89, 23);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(137, 391, 89, 23);
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
		
		String[] nombresColumnas = { "Codigo Explicacion", "Titulo","Epigrafe", "Contenido", "Fecha"}; 
		
		DefaultTableModel model = new DefaultTableModel(nombresColumnas,0);

		ExplicacionDAO Cdao = new ExplicacionDAO();
		ArrayList<Explicacion> resultado = Cdao.consultar();
		
		for ( Explicacion s : resultado) {
			String idExplicacion = s.getIdExplicacion();
			String Titulo = s.getTitulo();
			String epigrafe= s.getEpigrafe();
			String Contenido = s.getContenido();
			LocalDate Fecha =s.getFecha();
		
		Object [] nombres = {idExplicacion, Titulo,epigrafe, Contenido, Fecha};
		
		model.addRow(nombres);		
		setLayout(null);
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 665, 341);
		add(scrollPane);


		JTable tableExpli = new JTable();
		scrollPane.setViewportView(tableExpli);
		tableExpli.setModel(model);
		
		ActionListener listenerEliminar = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        int fila = tableExpli.getSelectedRow();
		        
				String valor0 = model.getValueAt(fila, 0).toString();
				int valor = Integer.parseInt(valor0);
				ExplicacionDAO ch = new ExplicacionDAO();
				ch.borrar(valor);
				model.removeRow(fila);
		        

		    }
		};
		btnEliminar.addActionListener(listenerEliminar);
		
		ActionListener listenerModificar = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	boolean mod= true;
		        int fila = tableExpli.getSelectedRow();
				String valor0 = model.getValueAt(fila, 0).toString();
				int valor = Integer.parseInt(valor0);
				String titul = model.getValueAt(fila,1).toString();
				String epigraf = model.getValueAt(fila,2).toString();
				String contenid = model.getValueAt(fila,3).toString();

				Explicacion explic = new Explicacion(valor0, titul,epigraf, contenid, null);
				CrearExplicaciones panel = new CrearExplicaciones(frame, mod, explic, null);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		btnModificar.addActionListener(listenerModificar);

	}
}
