package estructuraTP.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.ChequeoDAO;
import estructuraTP.dao.RepositorioDAO;
import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Sugerencia;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Repositorio extends JPanel {

	/**
	 * Create the panel.
	 */
	public Repositorio(JFrame frame) {
String[] nombresColumnas = {"ID", "Frase", "Medio de publicacion", "Enlace", "Fecha" }; 
		
		DefaultTableModel model = new DefaultTableModel(nombresColumnas,0);
			

		RepositorioDAO ldao = new RepositorioDAO();
		ArrayList<Sugerencia> resultado = ldao.consultar();
		
		for ( Sugerencia s : resultado) {
			int idSuge = s.getid();
			String Frase = s.getfrase();
			String Medio = s.getmedio();
			String Enlace = s.getenlace();
			LocalDate Fecha =s.getfecha();
			
		Object [] registro = {idSuge, Frase, Medio, Enlace, Fecha};
		
		model.addRow(registro);		
	       

		setLayout(null);
		}
		setLayout(null);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 34, 559, 305);
		add(scrollPane);

		JTable tableRepositorio = new JTable();
		scrollPane.setViewportView(tableRepositorio);
		tableRepositorio.setModel(model);
		
		JLabel lblRepositorioDeSugerencias = new JLabel("Repositorio de sugerencias");
		lblRepositorioDeSugerencias.setBounds(253, 11, 162, 14);
		add(lblRepositorioDeSugerencias);
		
		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.setBounds(344, 350, 98, 23);
		add(btnVerificar);
		
		JButton btnDescartar = new JButton("Descartar");
		btnDescartar.setBounds(452, 350, 105, 23);
		add(btnDescartar);
		
		ActionListener listenerDescartar = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        int fila = tableRepositorio.getSelectedRow();
		        
				String valor0 = model.getValueAt(fila, 0).toString();
				int valor = Integer.parseInt(valor0);
				RepositorioDAO ch = new RepositorioDAO();
				ch.borrar(valor);
				model.removeRow(fila);
		        

		    }
		};
		btnDescartar.addActionListener(listenerDescartar);
		ActionListener listenerCrear = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        int fila = tableRepositorio.getSelectedRow();
				String valor0 = model.getValueAt(fila, 0).toString();
				int valor = Integer.parseInt(valor0);
				String frase= model.getValueAt(fila, 1).toString();
				String medio= model.getValueAt(fila, 2).toString();
				String enlace= model.getValueAt(fila, 3).toString();
				String fecha= model.getValueAt(fila, 4).toString();
				CrearChequeos panel = new CrearChequeos(frame, false, valor,frase, medio, enlace, fecha);
		        frame.setContentPane(panel);
		        frame.validate();
				RepositorioDAO ch = new RepositorioDAO();
				ch.borrar(valor);
				model.removeRow(fila);
		        

		    }
		};
		
		btnVerificar.addActionListener(listenerCrear);
		
		

	}
}
