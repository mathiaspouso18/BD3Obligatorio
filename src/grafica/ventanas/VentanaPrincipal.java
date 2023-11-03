package grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import grafica.controladores.ControladorAltaNinio;
import grafica.controladores.ControladorListadoJuguetes;
import grafica.controladores.ControladorListadoNinios;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDescripcion;
	VentanaPrincipal vista;
	private JTable tblNinios;
	DefaultTableModel model;
	private JTextField txtCedulaBuscar;
	private JTable tblJuguetesNinio;
	DefaultTableModel modelJuguetes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public VentanaPrincipal() throws Exception {
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("Guardería");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 468);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		
		ControladorAltaNinio can = new ControladorAltaNinio(vista);
		ControladorListadoNinios cln = new ControladorListadoNinios(vista);
		ControladorListadoJuguetes clj = new ControladorListadoJuguetes(vista);

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnlNinios = new JPanel();
		pnlNinios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ni\u00F1os", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(pnlNinios);
		pnlNinios.setLayout(null);
		
		JPanel pnlCentral = new JPanel();
		pnlCentral.setBounds(11, 21, 874, 399);
		pnlNinios.add(pnlCentral);
		pnlCentral.setLayout(null);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(5, 9, 60, 14);
		pnlCentral.add(lblCedula);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(70, 6, 86, 20);
		txtCedula.setColumns(10);
		pnlCentral.add(txtCedula);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(5, 34, 60, 14);
		pnlCentral.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(70, 31, 86, 20);
		pnlCentral.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(5, 59, 60, 14);
		pnlCentral.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(70, 56, 86, 20);
		pnlCentral.add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(530, 11, 336, 20);
		pnlCentral.add(lblError);
		
		JButton btnInsertarNinio = new JButton("Insertar");
		btnInsertarNinio.setBounds(165, 5, 86, 23);
		btnInsertarNinio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String cedula = txtCedula.getText();
				
				if(!cedula.equals("") && !nombre.equals("") && !apellido.equals("")) {
					int ced = Integer.valueOf(cedula);
					try {
						can.AltaNinio(ced, nombre, apellido);
						String[] newNiño = new String[3];
						newNiño[0] = String.valueOf(ced);
						newNiño[1] = nombre;
						newNiño[2] = apellido;
						model.addRow(newNiño);
						txtCedula.setText("");
						txtNombre.setText("");
						txtApellido.setText("");
						lblError.setText("");
						lblError.setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					lblError.setText("Los campos no pueden estar vacios");
					lblError.setVisible(true);
				}
				
			}
		});
		pnlCentral.add(btnInsertarNinio);

		JPanel pnlListadoJuguetes = new JPanel();
		JButton btnInsertarJuguete = new JButton("Agregar");
		JButton btnQuitarJuguetes = new JButton("Quitar juguetes");

		JLabel lblErrorJuguete = new JLabel("");
		
		JPanel pnlJuguetes = new JPanel();
		pnlJuguetes.setBorder(new TitledBorder(null, "Juguetes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlJuguetes.setBounds(5, 160, 836, 233);
		pnlCentral.add(pnlJuguetes);
		pnlJuguetes.setLayout(null);
		
		JLabel lblDescripcion = new JLabel("Descripcion del juguete");
		lblDescripcion.setBounds(10, 61, 152, 14);
		pnlJuguetes.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(151, 58, 186, 20);
		pnlJuguetes.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		btnInsertarJuguete.setBounds(347, 57, 89, 23);
		btnInsertarJuguete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String descripcion = txtDescripcion.getText();
				
				if(!descripcion.equals("")) {
					
					
					
					lblErrorJuguete.setText("");
					lblErrorJuguete.setVisible(false);
					txtDescripcion.setText("");
					
				}else {
				}
			}
		});
		pnlJuguetes.add(btnInsertarJuguete);
		
		lblErrorJuguete.setForeground(new Color(255, 0, 0));
		lblErrorJuguete.setBounds(446, 25, 284, 14);
		pnlJuguetes.add(lblErrorJuguete);
		
		pnlListadoJuguetes.setBounds(10, 86, 426, 134);
		pnlJuguetes.add(pnlListadoJuguetes);
		
		pnlListadoJuguetes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Listado Juguetes Ni\u00F1o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlListadoJuguetes.setLayout(null);
		btnQuitarJuguetes.setBounds(261, 20, 128, 23);
		btnQuitarJuguetes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//lstModelJuguetesNinios.clear();
			}
		});
		
		pnlListadoJuguetes.add(btnQuitarJuguetes);
		
		txtCedulaBuscar = new JTextField();
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(347, 24, 89, 23);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCedulaBuscar.getText();
				
				if(!cedula.equals("")) {
					int c = Integer.valueOf(cedula);
					try {
						ArrayList<String[]> datosJuguetes = new ArrayList<String []>();	
						lblError.setText("");
						datosJuguetes = clj.ListadoJuguetes(c);
						
						if(datosJuguetes.size() == 0) {
							lblErrorJuguete.setText("No hay juguetes para la cedula ingresada");
							lblErrorJuguete.setVisible(true);
						}else {
							lblErrorJuguete.setText("");
							lblErrorJuguete.setVisible(false);
						}
						for(String [] d: datosJuguetes) {
							modelJuguetes.addRow(d);
						}
					}catch(Exception ve) {
					}
					
					lblErrorJuguete.setText("");
					lblErrorJuguete.setVisible(false);
					
				}else {
					lblErrorJuguete.setText("Debe ingresar la cedula del niño");
					lblErrorJuguete.setVisible(true);
				}
			}
		});
		pnlJuguetes.add(btnBuscar);
		
		JScrollPane scrollTablaJuguetes = new JScrollPane();
		scrollTablaJuguetes.setBounds(10, 20, 241, 103);
		pnlListadoJuguetes.add(scrollTablaJuguetes);
		
		tblJuguetesNinio = new JTable();
		tblJuguetesNinio.setShowGrid(false);
		tblJuguetesNinio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblJuguetesNinio.setFillsViewportHeight(true);
		modelJuguetes = new DefaultTableModel(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas no sean editables
            }
        };
        
		String[] columnasJuguetes = {"Cédula", "Descripcion"};
		for(String s: columnasJuguetes) {
			modelJuguetes.addColumn(s);
		}
		tblJuguetesNinio.setModel(modelJuguetes);		
		scrollTablaJuguetes.setViewportView(tblJuguetesNinio);
		
		txtCedulaBuscar.setBounds(151, 25, 111, 20);
		pnlJuguetes.add(txtCedulaBuscar);
		txtCedulaBuscar.setColumns(10);
		
		JLabel lblCedula_1 = new JLabel("Cedula");
		lblCedula_1.setBounds(10, 28, 111, 14);
		pnlJuguetes.add(lblCedula_1);
		
		JPanel pnlListadoNinios = new JPanel();
		pnlListadoNinios.setBorder(new TitledBorder(null, "Listado Ni\u00F1os", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlListadoNinios.setBounds(261, 3, 259, 135);
		pnlCentral.add(pnlListadoNinios);
		pnlListadoNinios.setLayout(null);
		
		JScrollPane scrollTablaNinios = new JScrollPane();
		scrollTablaNinios.setBounds(10, 20, 238, 105);
		pnlListadoNinios.add(scrollTablaNinios);
		
		String[] columnas = {"Cédula", "Nombre", "Apellido"};
		
		tblNinios = new JTable();
		tblNinios.setFillsViewportHeight(true);
		tblNinios.setShowGrid(false);
		tblNinios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas no sean editables
            }
        };
        
		for(String s: columnas) {
			model.addColumn(s);
		}
		tblNinios.setModel(model);
		
		ArrayList<String[]> datos = new ArrayList<String []>();
		
		try {
			lblError.setText("");
			datos = cln.ListadoNinios();
			for(String [] d: datos) {
				model.addRow(d);
			}
		}catch(Exception ve) {
		}
		
		scrollTablaNinios.setViewportView(tblNinios);
	}
}
