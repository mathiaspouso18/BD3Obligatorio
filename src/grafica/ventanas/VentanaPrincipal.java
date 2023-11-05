package grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import grafica.controladores.ControladorAltaJuguetes;
import grafica.controladores.ControladorAltaNinio;
import grafica.controladores.ControladorListadoJuguetes;
import grafica.controladores.ControladorListadoNinios;
import logica.excepciones.JuguetesException;
import logica.excepciones.NiñosException;
import logica.excepciones.PersistenciaException;

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
		setBounds(100, 100, 844, 646);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		
		ControladorAltaNinio can = new ControladorAltaNinio(vista);
		ControladorAltaJuguetes caj = new ControladorAltaJuguetes(vista);
		ControladorListadoNinios cln = new ControladorListadoNinios(vista);
		ControladorListadoJuguetes clj = new ControladorListadoJuguetes(vista);

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnlNinios = new JPanel();
		pnlNinios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ni\u00F1os", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(pnlNinios);
		pnlNinios.setLayout(null);
		
		JPanel pnlCentral = new JPanel();
		pnlCentral.setBounds(11, 24, 793, 580);
		pnlNinios.add(pnlCentral);
		pnlCentral.setLayout(null);

		JPanel pnlListadoJuguetes = new JPanel();
		
		JPanel pnlJuguetes = new JPanel();
		pnlJuguetes.setBorder(new TitledBorder(null, "Juguetes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlJuguetes.setBounds(0, 311, 782, 268);
		pnlCentral.add(pnlJuguetes);
		pnlJuguetes.setLayout(null);
		
		pnlListadoJuguetes.setBounds(10, 116, 751, 134);
		pnlJuguetes.add(pnlListadoJuguetes);
		
		pnlListadoJuguetes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Listado Juguetes Ni\u00F1o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlListadoJuguetes.setLayout(null);
		
		JScrollPane scrollTablaJuguetes = new JScrollPane();
		scrollTablaJuguetes.setBounds(10, 20, 422, 103);
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
		JButton btnBuscar = new JButton("Listar juguetes");
		btnBuscar.setBounds(442, 28, 135, 23);
		pnlListadoJuguetes.add(btnBuscar);
		JButton btnQuitarJuguetes = new JButton("Quitar juguetes");
		btnQuitarJuguetes.setBounds(442, 62, 128, 23);
		pnlListadoJuguetes.add(btnQuitarJuguetes);
		
		JLabel lblErrorJuguete = new JLabel("");
		lblErrorJuguete.setBounds(442, 96, 299, 14);
		pnlListadoJuguetes.add(lblErrorJuguete);
		
		lblErrorJuguete.setForeground(new Color(255, 0, 0));
		btnQuitarJuguetes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//lstModelJuguetesNinios.clear();
			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCedulaBuscar.getText();
				
				if(!cedula.equals("")) {
					int c = Integer.valueOf(cedula);
					try {
						modelJuguetes.getDataVector().removeAllElements(); 
						modelJuguetes.fireTableDataChanged();
						ArrayList<String[]> datosJuguetes = new ArrayList<String []>();	
						lblErrorJuguete.setText("");
						datosJuguetes = clj.ListadoJuguetes(c);
						for(String [] d: datosJuguetes) {
							modelJuguetes.addRow(d);
						}
					}catch(JuguetesException je) {
						lblErrorJuguete.setForeground(Color.RED);
						lblErrorJuguete.setText(je.getMensajeJuguetesExcep());
					} catch (NiñosException ne) {
						lblErrorJuguete.setForeground(Color.RED);
						lblErrorJuguete.setText(ne.getMensajeNiñosExcep());
					} catch (PersistenciaException e1) {
						e1.printStackTrace();
					}
				}else {
					lblErrorJuguete.setText("Debe ingresar la cedula del niño");
					lblErrorJuguete.setForeground(Color.RED);
					lblErrorJuguete.setVisible(true);
				}
			}
		});
		
		JPanel pnlAltaJuguete = new JPanel();
		pnlAltaJuguete.setBorder(new TitledBorder(null, "Alta Juguete", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAltaJuguete.setBounds(10, 21, 661, 84);
		pnlJuguetes.add(pnlAltaJuguete);
		pnlAltaJuguete.setLayout(null);
		
		txtCedulaBuscar = new JTextField();
		txtCedulaBuscar.setBounds(172, 22, 111, 20);
		pnlAltaJuguete.add(txtCedulaBuscar);
		txtCedulaBuscar.setColumns(10);
		
		JLabel lblCedula_1 = new JLabel("Cedula");
		lblCedula_1.setBounds(10, 25, 111, 14);
		pnlAltaJuguete.add(lblCedula_1);
		
		JLabel lblDescripcion = new JLabel("Descripcion del juguete");
		lblDescripcion.setBounds(10, 53, 152, 14);
		pnlAltaJuguete.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(172, 53, 210, 20);
		pnlAltaJuguete.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		JButton btnInsertarJuguete = new JButton("Agregar");
		btnInsertarJuguete.setBounds(293, 21, 89, 23);
		pnlAltaJuguete.add(btnInsertarJuguete);
		btnInsertarJuguete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String descripcion = txtDescripcion.getText();
				String cedula = txtCedulaBuscar.getText();
				
				if(!descripcion.equals("") && !cedula.equals("")) {
					int _ced = Integer.valueOf(cedula);
					try {
						caj.AltaJuguete(_ced, descripcion);
						txtDescripcion.setText("");
						lblErrorJuguete.setText("Juguete ingresado con éxito");
						lblErrorJuguete.setForeground(Color.GREEN);
						lblErrorJuguete.setVisible(true);
					} catch (JuguetesException je) {
						lblErrorJuguete.setText(je.getMensajeJuguetesExcep());
						lblErrorJuguete.setForeground(Color.RED);
					} catch (NiñosException ne) {
						lblErrorJuguete.setText(ne.getMensajeNiñosExcep());
						lblErrorJuguete.setForeground(Color.RED);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					lblErrorJuguete.setText("Los campos no pueden estar vacios");
					lblErrorJuguete.setVisible(true);
				}
			}
		});
		
		JPanel pnlListadoNinios = new JPanel();
		pnlListadoNinios.setBorder(new TitledBorder(null, "Listado Ni\u00F1os", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlListadoNinios.setBounds(0, 146, 782, 160);
		pnlCentral.add(pnlListadoNinios);
		pnlListadoNinios.setLayout(null);
		
		JScrollPane scrollTablaNinios = new JScrollPane();
		scrollTablaNinios.setBounds(10, 20, 554, 125);
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
		
		scrollTablaNinios.setViewportView(tblNinios);
		
		JButton btnListarNiños = new JButton("Listar niños");
		btnListarNiños.setBounds(574, 122, 136, 23);
		btnListarNiños.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String[]> datos = new ArrayList<String []>();
				
			    model.getDataVector().removeAllElements(); 
			    model.fireTableDataChanged();
				
				try {
					datos = cln.ListadoNinios();
					for(String [] d: datos) {
						model.addRow(d);
					}
				}catch(NiñosException ne) {

				} catch (PersistenciaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		pnlListadoNinios.add(btnListarNiños);
		
		JPanel pnlAltaNinio = new JPanel();
		pnlAltaNinio.setLayout(null);
		pnlAltaNinio.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Alta Ni\u00F1o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlAltaNinio.setBounds(0, 0, 782, 135);
		pnlCentral.add(pnlAltaNinio);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(10, 22, 60, 14);
		pnlAltaNinio.add(lblCedula);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 47, 60, 14);
		pnlAltaNinio.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 72, 60, 14);
		pnlAltaNinio.add(lblApellido);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(80, 19, 86, 20);
		pnlAltaNinio.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(80, 44, 86, 20);
		pnlAltaNinio.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(80, 69, 86, 20);
		pnlAltaNinio.add(txtApellido);
		txtApellido.setColumns(10);
		
		JButton btnInsertarNinio = new JButton("Insertar");
		btnInsertarNinio.setBounds(186, 18, 86, 23);
		pnlAltaNinio.add(btnInsertarNinio);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(424, 104, 336, 20);
		pnlAltaNinio.add(lblError);
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setText("");
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
						txtCedula.setText("");
						txtNombre.setText("");
						txtApellido.setText("");
						lblError.setText("Niño ingresado con éxito");
						lblError.setForeground(Color.GREEN);
						lblError.setVisible(true);
					} catch (NiñosException ne) {
						lblError.setText(ne.getMensajeNiñosExcep());
						lblError.setForeground(Color.RED);

					} catch (PersistenciaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					lblError.setForeground(Color.RED);
					lblError.setText("Los campos no pueden estar vacios");
					lblError.setVisible(true);
				}
				
			}
		});
	}
}
