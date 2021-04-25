package estructuraTP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import com.toedter.calendar.JCalendar;

import estructuraTP.vista.Chequeos;
import estructuraTP.vista.CrearChequeos;
import estructuraTP.vista.CrearExplicaciones;
import estructuraTP.vista.CrearInvestigaciones;
import estructuraTP.vista.Explicaciones;
import estructuraTP.vista.Investigaciones;
import estructuraTP.vista.PanelPrincipalPantalla;
import estructuraTP.vista.PotExplicaciones;
import estructuraTP.vista.Repositorio;

public class APP {

	public static void main(String[] args) {
		//Convencion c = new Convencion();
		JFrame frame = new JFrame();
		
		JMenuBar menubar= new JMenuBar();
		JMenu archivo = new JMenu("Archivo");
		JMenu Chequeos = new JMenu("Chequeos");
		JMenu Explicaciones = new JMenu("Explicaciones");
		JMenu Investigaciones = new JMenu("Investigaciones");
		JMenuItem Inicio = new JMenuItem("Inicio");
		JMenuItem Salir = new JMenuItem("Salir");
		JMenuItem CrearChequeos = new JMenuItem("Ver sugerencias");
		JMenuItem CrearExplicaciones = new JMenuItem("Crear Explicaciones");
		JMenuItem VerExplicaciones = new JMenuItem("Ver Explicaciones");
		JMenuItem CrearInves = new JMenuItem("Crear Investigaciones");
		JMenuItem VerInves = new JMenuItem("Ver Investigaciones");
		JMenuItem VerChequeos = new JMenuItem("Ver Chequeos");
		
		menubar.add(archivo);
		menubar.add(Chequeos);
		menubar.add(Explicaciones);
		menubar.add(Investigaciones);
		archivo.add(Inicio);
		archivo.add(Salir);
		Chequeos.add(CrearChequeos);
		Chequeos.add(VerChequeos);
		Explicaciones.add(CrearExplicaciones);
		Explicaciones.add(VerExplicaciones);
		Investigaciones.add(CrearInves);
		Investigaciones.add(VerInves);
		
		frame.setJMenuBar(menubar);

		
		
		ActionListener listenerInicio = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        PanelPrincipalPantalla panel = new PanelPrincipalPantalla(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		
		Inicio.addActionListener(listenerInicio);
		
		Salir.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
			frame.setVisible(false);
			frame.dispose();
			}
			});
		
		ActionListener listenerChequeos = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Chequeos panel = new Chequeos(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		
		VerChequeos.addActionListener(listenerChequeos);
		ActionListener listenerExplicaciones = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Explicaciones panel = new Explicaciones(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		
		VerExplicaciones.addActionListener(listenerExplicaciones);
		ActionListener listenerCrearChequeos = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Repositorio panel = new Repositorio(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		CrearChequeos.addActionListener(listenerCrearChequeos);
		
		ActionListener listenerCrearExpli = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        PotExplicaciones panel = new PotExplicaciones(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		CrearExplicaciones.addActionListener(listenerCrearExpli);
		
		ActionListener listenerCrearInves = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        CrearInvestigaciones panel = new CrearInvestigaciones(frame, false, null);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		CrearInves.addActionListener(listenerCrearInves);
		
		ActionListener listenerInves = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Investigaciones panel = new Investigaciones(frame);
		        frame.setContentPane(panel);
		        frame.validate();
		    }
		};
		VerInves.addActionListener(listenerInves);
		
	
		
		
		frame.setBounds(100, 100, 750, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelPrincipalPantalla panel = new PanelPrincipalPantalla(frame);
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
	

	
}
