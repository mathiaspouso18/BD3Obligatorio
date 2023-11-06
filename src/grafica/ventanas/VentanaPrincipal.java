package grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import grafica.controladores.ControladorAltaJuguetes;
import grafica.controladores.ControladorAltaNinio;
import grafica.controladores.ControladorBajaNinio;
import grafica.controladores.ControladorBuscarDescripcion;
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
import java.rmi.RemoteException;
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
	private JTextField txtCedula2;
	private JTable tblJuguetesNinio;
	DefaultTableModel modelJuguetes;
	private JTextField txtNumeroJuguete;
	private JTextField txtDescripcionObtenida;
	private JTextField txtCedula3;
	private JTextField txtCedula4;

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
		setBounds(100, 100, 844, 715);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		
		ControladorAltaNinio can = new ControladorAltaNinio(vista);
		ControladorAltaJuguetes caj = new ControladorAltaJuguetes(vista);
		ControladorListadoNinios cln = new ControladorListadoNinios(vista);
		ControladorListadoJuguetes clj = new ControladorListadoJuguetes(vista);
		ControladorBuscarDescripcion cbj = new ControladorBuscarDescripcion(vista);
		ControladorBajaNinio cbn = new ControladorBajaNinio(vista);

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnlNinios = new JPanel();
		pnlNinios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ni\u00F1os", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(pnlNinios);
		pnlNinios.setLayout(null);
		
		JPanel pnlCentral = new JPanel();
		pnlCentral.setBounds(11, 24, 793, 641);
		pnlNinios.add(pnlCentral);
		pnlCentral.setLayout(null);
		
		JPanel pnlJuguetes = new JPanel();
		pnlJuguetes.setBorder(new TitledBorder(null, "Juguetes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlJuguetes.setBounds(0, 196, 782, 434);
		pnlCentral.add(pnlJuguetes);
		pnlJuguetes.setLayout(null);
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
        
		String[] columnasJuguetes = {"Número", "Cédula", "Descripcion"};
		for(String s: columnasJuguetes) {
			modelJuguetes.addColumn(s);
		}
		
		JPanel pnlAltaJuguete = new JPanel();
		pnlAltaJuguete.setBorder(new TitledBorder(null, "Alta Juguete", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAltaJuguete.setBounds(10, 21, 751, 84);
		pnlJuguetes.add(pnlAltaJuguete);
		pnlAltaJuguete.setLayout(null);
		
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
		
		txtCedula3 = new JTextField();
		txtCedula3.setColumns(10);
		txtCedula3.setBounds(172, 22, 111, 20);
		pnlAltaJuguete.add(txtCedula3);
		
		JLabel lblErrorJuguete = new JLabel("");
		lblErrorJuguete.setBounds(413, 53, 299, 14);
		pnlAltaJuguete.add(lblErrorJuguete);
		
		lblErrorJuguete.setForeground(new Color(255, 0, 0));
		
				JPanel pnlListadoJuguetes = new JPanel();
				pnlListadoJuguetes.setBounds(10, 261, 751, 162);
				pnlJuguetes.add(pnlListadoJuguetes);
				
				pnlListadoJuguetes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Listado Juguetes Ni\u00F1o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				pnlListadoJuguetes.setLayout(null);
				
				JScrollPane scrollTablaJuguetes = new JScrollPane();
				scrollTablaJuguetes.setBounds(10, 20, 422, 131);
				pnlListadoJuguetes.add(scrollTablaJuguetes);
				
				tblJuguetesNinio = new JTable();
				tblJuguetesNinio.setShowGrid(false);
				tblJuguetesNinio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblJuguetesNinio.setFillsViewportHeight(true);
				tblJuguetesNinio.setModel(modelJuguetes);		
				scrollTablaJuguetes.setViewportView(tblJuguetesNinio);
				JButton btnBuscar = new JButton("Listar juguetes");
				btnBuscar.setBounds(442, 49, 135, 23);
				pnlListadoJuguetes.add(btnBuscar);
				
				JLabel lblErrorJuguete2 = new JLabel("");
				lblErrorJuguete2.setForeground(Color.RED);
				lblErrorJuguete2.setBounds(442, 123, 299, 14);
				pnlListadoJuguetes.add(lblErrorJuguete2);
				
				txtCedula4 = new JTextField();
				txtCedula4.setColumns(10);
				txtCedula4.setBounds(491, 18, 86, 20);
				pnlListadoJuguetes.add(txtCedula4);
				
				JLabel lblCedula2_1 = new JLabel("Cedula");
				lblCedula2_1.setBounds(442, 21, 128, 14);
				pnlListadoJuguetes.add(lblCedula2_1);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Descripcion de juguetes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(10, 116, 751, 134);
				pnlJuguetes.add(panel);
				panel.setLayout(null);
				
				JLabel lblCedula2 = new JLabel("Cedula");
				lblCedula2.setBounds(10, 31, 94, 14);
				panel.add(lblCedula2);
				
				txtCedula2 = new JTextField();
				txtCedula2.setBounds(174, 28, 111, 20);
				panel.add(txtCedula2);
				txtCedula2.setColumns(10);
				
				JLabel lblNumeroJuguete = new JLabel("Numero de juguete");
				lblNumeroJuguete.setBounds(10, 59, 136, 14);
				panel.add(lblNumeroJuguete);
				
				txtNumeroJuguete = new JTextField();
				txtNumeroJuguete.setColumns(10);
				txtNumeroJuguete.setBounds(174, 56, 111, 20);
				panel.add(txtNumeroJuguete);
				
				txtDescripcionObtenida = new JTextField();
				txtDescripcionObtenida.setEditable(false);
				txtDescripcionObtenida.setColumns(10);
				txtDescripcionObtenida.setBounds(174, 87, 275, 20);
				panel.add(txtDescripcionObtenida);
				
				JLabel lblDescripcionJuguete = new JLabel("Descripcion");
				lblDescripcionJuguete.setBounds(10, 90, 136, 14);
				panel.add(lblDescripcionJuguete);
				
				JLabel lblErrorJuguete3 = new JLabel("");
				lblErrorJuguete3.setForeground(Color.RED);
				lblErrorJuguete3.setBounds(295, 59, 299, 14);
				panel.add(lblErrorJuguete3);
				
				JButton btnBuscarDescripcion = new JButton("Obtener descripcion");
				btnBuscarDescripcion.setBounds(295, 27, 154, 23);
				btnBuscarDescripcion.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String cedula = txtCedula2.getText();
						String numero = txtNumeroJuguete.getText();
						
						if(!cedula.equals("") && !numero.equals("")) {
							txtDescripcionObtenida.setText("");
							int c = Integer.valueOf(cedula);
							int n = Integer.valueOf(numero);
							try {
								String descripcion = cbj.BuscarDecripcion(c, n);
								txtDescripcionObtenida.setText(descripcion);
								lblErrorJuguete3.setText("");
							}catch(JuguetesException je) {
								lblErrorJuguete3.setForeground(Color.RED);
								lblErrorJuguete3.setText(je.getMensajeJuguetesExcep());
							} catch (NiñosException ne) {
								lblErrorJuguete3.setForeground(Color.RED);
								lblErrorJuguete3.setText(ne.getMensajeNiñosExcep());
							} catch (PersistenciaException e1) {
								e1.printStackTrace();
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
							lblErrorJuguete3.setText("Los campos no pueden estar vacíos");
							lblErrorJuguete3.setForeground(Color.RED);
							lblErrorJuguete3.setVisible(true);
						}
					}
				});
				panel.add(btnBuscarDescripcion);
				
				
				btnBuscar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String cedula = txtCedula4.getText();
						
						if(!cedula.equals("")) {
							int c = Integer.valueOf(cedula);
							try {
								modelJuguetes.getDataVector().removeAllElements(); 
								modelJuguetes.fireTableDataChanged();
								ArrayList<String[]> datosJuguetes = new ArrayList<String []>();	
								lblErrorJuguete2.setText("");
								datosJuguetes = clj.ListadoJuguetes(c);
								for(String [] d: datosJuguetes) {
									modelJuguetes.addRow(d);
								}
							}catch(JuguetesException je) {
								lblErrorJuguete2.setForeground(Color.RED);
								lblErrorJuguete2.setText(je.getMensajeJuguetesExcep());
							} catch (NiñosException ne) {
								lblErrorJuguete2.setForeground(Color.RED);
								lblErrorJuguete2.setText(ne.getMensajeNiñosExcep());
							} catch (PersistenciaException e1) {
								e1.printStackTrace();
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
							lblErrorJuguete2.setText("Debe ingresar la cedula del niño");
							lblErrorJuguete2.setForeground(Color.RED);
							lblErrorJuguete2.setVisible(true);
						}
					}
				});
		btnInsertarJuguete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String descripcion = txtDescripcion.getText();
				String cedula = txtCedula3.getText();
				
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
		
		String[] columnas = {"Cédula", "Nombre", "Apellido"};
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
		
		JPanel pnlAltaNinio = new JPanel();
		pnlAltaNinio.setLayout(null);
		pnlAltaNinio.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Alta Ni\u00F1o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlAltaNinio.setBounds(0, 0, 782, 185);
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
		btnInsertarNinio.setBounds(186, 18, 136, 23);
		pnlAltaNinio.add(btnInsertarNinio);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(10, 139, 312, 20);
		pnlAltaNinio.add(lblError);
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setText("");
		
		JScrollPane scrollTablaNinios = new JScrollPane();
		scrollTablaNinios.setBounds(332, 26, 429, 133);
		pnlAltaNinio.add(scrollTablaNinios);
		
		tblNinios = new JTable();
		scrollTablaNinios.setViewportView(tblNinios);
		tblNinios.setFillsViewportHeight(true);
		tblNinios.setShowGrid(false);
		tblNinios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblNinios.setModel(model);
		
		JButton btnListarNiños = new JButton("Listar niños");
		btnListarNiños.setBounds(186, 43, 136, 23);
		pnlAltaNinio.add(btnListarNiños);
		JButton btnQuitarNiño = new JButton("Quitar niño");
		btnQuitarNiño.setBounds(10, 97, 135, 23);
		pnlAltaNinio.add(btnQuitarNiño);
		btnQuitarNiño.setForeground(new Color(255, 0, 0));
		
					
					btnQuitarNiño.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String cedula = txtCedula.getText();
							if(!cedula.equals("")) {
								int _ced = Integer.valueOf(cedula);
								try {
									cbn.BajaNinio(_ced);
									lblError.setText("Niño eliminado de la base de datos");
									txtCedula.setText("");
									modelJuguetes.getDataVector().removeAllElements(); 
									modelJuguetes.fireTableDataChanged();
									lblError.setForeground(Color.GREEN);
									lblError.setVisible(true);
								}
								catch(NiñosException ne) {
									lblError.setText(ne.getMensajeNiñosExcep());
									lblError.setForeground(Color.RED);
									lblError.setVisible(true);
								} catch (PersistenciaException e1) {
									e1.printStackTrace();
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}else {
								lblError.setText("Debe ingresar la cedula del niño");
								lblError.setForeground(Color.RED);
								lblError.setVisible(true);
							}
							
						}
					});
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
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
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
					} catch (RemoteException e1) {
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
