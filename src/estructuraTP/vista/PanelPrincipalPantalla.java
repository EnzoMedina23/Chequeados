package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.ArticuloDAO;
import estructuraTP.dao.ExplicacionDAO;
import estructuraTP.dao.InvestigacionDAO;
import estructuraTP.modelo.Articulo;
import estructuraTP.modelo.Explicacion;
import estructuraTP.modelo.Investigacion;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PanelPrincipalPantalla extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelPrincipalPantalla(JFrame frame) {
		setLayout(null);
		
		JLabel lblChequeados = new JLabel("CHEQUEADOS");
		lblChequeados.setBounds(195, 23, 90, 14);
		add(lblChequeados);
		String[] nombresColumnas = { "Titulo", "Epigrafe", "Contenido", "Fecha Creacion" }; 
		
		DefaultTableModel modelo = new DefaultTableModel(nombresColumnas,0);
		
		Object [] nombres = nombresColumnas;
		ArticuloDAO i = new ArticuloDAO();
		ArrayList<Articulo> cosas= i.consultarAlto();
		for(Articulo c: cosas)
		{
			String tit= c.getTitulo();
			String epigra= c.getEpigrafe();
			String Conte=  c.getContenido();
			LocalDate fech= c.getFecha();
			Object [] registro = {tit, epigra, Conte, fech};
			
			modelo.addRow(registro);		
		}
		/*
		ExplicacionDAO f= new ExplicacionDAO();
		ArrayList<Explicacion> cosas1= f.altoImpac();
		for(Explicacion h: cosas1)
		{
			String tits= h.getTitulo();
			String epigras= h.getEpigrafe();
			String Contes=  h.getContenido();
			LocalDate fechs= h.getFecha();
			Object [] registro = {tits, epigras, Contes, fechs};
			
			modelo.addRow(registro);		 
		}*/

		setLayout(null);
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 39, 516, 328);
		add(scrollPane);




		JTable table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);

}
}
