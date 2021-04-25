package estructuraTP.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.ChequeoDAO;
import estructuraTP.dao.RepositorioDAO;
import estructuraTP.modelo.Chequeo;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class PotExplicaciones extends JPanel {

	/**
	 * Create the panel.
	 */
	public PotExplicaciones(JFrame frame) {

		JComboBox<String> boxCategoria = new JComboBox<String>();
		boxCategoria.setBounds(166, 38, 111, 17);
		add(boxCategoria);
		boxCategoria.addItem("Economia");
		boxCategoria.addItem("Justicia");
		boxCategoria.addItem("Politica");
		boxCategoria.addItem("Sociedad");

		JLabel lblC = new JLabel("Potenciales Explicaciones");
		lblC.setBounds(260, 11, 193, 14);
		add(lblC);

		String[] nombresColumnas = { "Enlace", "ID Chequeos relacionados", "Categoria" };

		DefaultTableModel model = new DefaultTableModel(nombresColumnas, 0);

		ArrayList<String> enlaces = new ArrayList<String>();
		ChequeoDAO ldao = new ChequeoDAO();
		ArrayList<Chequeo> losChequeos = ldao.consultar();
		ArrayList<Chequeo> aseguir = ldao.consultar();
		for (Chequeo c : losChequeos) {
			int cont = 0;
			int cont1 = 0;
			String enlacc = c.getenlace();
			String idChequeosx = "";
			for (String f : enlaces) {
				if (enlacc.equals(f))
					cont1++;
			}
			String categoria = c.getcategoria();
			if (cont1 == 0) {
				for (Chequeo g : aseguir) {
					String enlacc2 = g.getenlace();
					if (losChequeos.indexOf(c) != aseguir.indexOf(g)) {
						if (enlacc.equals(enlacc2)) {
							if (idChequeosx == "") {
								idChequeosx = idChequeosx + c.getid() + "," + g.getid();
								enlaces.add(enlacc);

							} else {
								idChequeosx = idChequeosx + "," + g.getid();

							}
							cont++;
						}

					}

				}
			}
			if (cont > 0) {

				Object[] registro = { enlacc, cont+1, categoria };

				model.addRow(registro);

			}
			setLayout(null);
		}

		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 95, 656, 317);
		add(scrollPane);

		JTable tableRepositorio = new JTable();
		scrollPane.setViewportView(tableRepositorio);
		tableRepositorio.setModel(model);

		JButton btnCrearExplicacion = new JButton("Crear Explicacion");
		btnCrearExplicacion.setBounds(44, 423, 135, 23);
		add(btnCrearExplicacion);

		JLabel lblBuscarPorCategoria = new JLabel("Buscar por categoria:");
		lblBuscarPorCategoria.setBounds(21, 39, 135, 14);
		add(lblBuscarPorCategoria);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(287, 33, 89, 23);
		add(btnBuscar);

		ActionListener listenerCrear = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = tableRepositorio.getSelectedRow();
				String enlace = model.getValueAt(fila, 0).toString();
				CrearExplicaciones panel = new CrearExplicaciones(frame, false, null, enlace);
				frame.setContentPane(panel);
				frame.validate();

			}
		};
		btnCrearExplicacion.addActionListener(listenerCrear);
		/*
		 * for(Chequeo f : losChequeos) { if(losChequeos.indexOf(f) ==3) {
		 * lblC.setText(String.valueOf(losChequeos.indexOf(f))); } }
		 * 
		 */

		ActionListener listenerCate = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] nombresColumnas = { "Enlace", "ID Chequeos relacionados", "Categoria" };

				DefaultTableModel model1 = new DefaultTableModel(nombresColumnas, 0);
				String cate0 = (String) boxCategoria.getSelectedItem();

				ArrayList<String> enlaces = new ArrayList<String>();
				ChequeoDAO ldao = new ChequeoDAO();
				ArrayList<Chequeo> losChequeos = ldao.consultar();
				ArrayList<Chequeo> aseguir = ldao.consultar();
				for (Chequeo c : losChequeos) {
					int cont = 0;
					int cont1 = 0;
					String enlacc = c.getenlace();
					String idChequeosx = "";
					for (String f : enlaces) {
						if (enlacc.equals(f))
							cont1++;
					}
					String categoria = c.getcategoria();
					if (cont1 == 0) {
						for (Chequeo g : aseguir) {
							String enlacc2 = g.getenlace();
							if (losChequeos.indexOf(c) != aseguir.indexOf(g)) {
								if (enlacc.equals(enlacc2)) {
									if (idChequeosx == "") {
										idChequeosx = idChequeosx + c.getid() + "," + g.getid();
										enlaces.add(enlacc);

									} else {
										idChequeosx = idChequeosx + "," + g.getid();

									}
									cont++;
								}

							}

						}
					}
					if (cont > 0) {
						if (categoria.equals(cate0)) {
							Object[] registro = { enlacc, idChequeosx, categoria };

							model1.addRow(registro);
						}
					}
					setLayout(null);
				}
				tableRepositorio.setModel(model1);

			}
		};
		btnBuscar.addActionListener(listenerCate);
	}
}
