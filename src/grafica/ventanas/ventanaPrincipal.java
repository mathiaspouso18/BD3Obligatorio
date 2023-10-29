package grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import grafica.controladores.ControladorAltaNinio;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDescripcion;
	VentanaPrincipal vista;

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
		setBounds(100, 100, 921, 441);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		
		ControladorAltaNinio can = new ControladorAltaNinio(vista);

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnlNinios = new JPanel();
		pnlNinios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ni\u00F1os", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(pnlNinios);
		pnlNinios.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(11, 21, 874, 366);
		pnlNinios.add(panel);
		panel.setLayout(null);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(5, 9, 60, 14);
		panel.add(lblCedula);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(70, 6, 86, 20);
		txtCedula.setColumns(10);
		panel.add(txtCedula);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(5, 34, 60, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(70, 31, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(5, 59, 60, 14);
		panel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setEnabled(false);
		txtApellido.setBounds(70, 56, 86, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		DefaultListModel lstModelNinios = new DefaultListModel();
		DefaultListModel lstModelJuguetesNinios = new DefaultListModel();

		JLabel lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(5, 85, 336, 20);
		panel.add(lblError);
		
		JButton btnInsertarNinio = new JButton("Insertar");
		btnInsertarNinio.setEnabled(false);
		btnInsertarNinio.setBounds(255, 5, 86, 23);
		btnInsertarNinio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String cedula = txtCedula.getText();
				
				if(!cedula.equals("") && !nombre.equals("") && !apellido.equals("")) {
					lstModelNinios.addElement(cedula + " - " + nombre + " " + apellido);
					txtCedula.setText("");
					txtNombre.setText("");
					txtApellido.setText("");
					lblError.setText("");
					lblError.setVisible(false);
				}else {
					lblError.setText("Los campos no pueden estar vacios");
					lblError.setVisible(true);
				}
				
			}
		});
		panel.add(btnInsertarNinio);

		JPanel panel_2 = new JPanel();
		JButton btnInsertarJuguete = new JButton("Agregar");
		JButton btnQuitarJuguetes = new JButton("Quitar juguetes");
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(163, 5, 89, 23);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCedula.getText();
				
				if(!cedula.equals("")) {
					lblError.setText("");
					lblError.setVisible(false);
					txtNombre.setEnabled(true);
					txtApellido.setEnabled(true);
					btnInsertarNinio.setEnabled(true);
					btnInsertarJuguete.setEnabled(true);
					txtDescripcion.setEnabled(true);
					btnQuitarJuguetes.setEnabled(true);
					
				}else {
					lblError.setText("Debe ingresar una cedula para buscar");
					lblError.setVisible(true);
				}
				
			}
		});
		panel.add(btnBuscar);

		JLabel lblErrorJuguete = new JLabel("");
		JList lstJuguetesNinios = new JList(lstModelJuguetesNinios);
		JList lstNinios = new JList(lstModelNinios);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Juguetes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(5, 160, 836, 169);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDescripcion = new JLabel("Descripcion del juguete");
		lblDescripcion.setBounds(10, 24, 152, 14);
		panel_1.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		txtDescripcion.setBounds(162, 21, 186, 20);
		panel_1.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		btnInsertarJuguete.setEnabled(false);
		btnInsertarJuguete.setBounds(10, 57, 89, 23);
		btnInsertarJuguete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String descripcion = txtDescripcion.getText();
				
				if(!descripcion.equals("")) {
					lblErrorJuguete.setText("");
					lblErrorJuguete.setVisible(false);
					lstModelJuguetesNinios.addElement(descripcion);
					txtDescripcion.setText("");
					
				}else {
					Integer index = lstNinios.getSelectedIndex();
					if(descripcion.equals("")) {
						lblErrorJuguete.setText("Debe ingresar una descripcion");
					}
					if(index == -1) {
						lblErrorJuguete.setText("Debe seleccionar un niño de la lista");
					}
					lblErrorJuguete.setVisible(true);
				}
			}
		});
		panel_1.add(btnInsertarJuguete);
		
		lblErrorJuguete.setForeground(new Color(255, 0, 0));
		lblErrorJuguete.setBounds(162, 61, 284, 14);
		panel_1.add(lblErrorJuguete);
		
		panel_2.setBounds(389, 11, 426, 134);
		panel_1.add(panel_2);
		
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Listado Juguetes Ni\u00F1o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setLayout(null);
		
		btnQuitarJuguetes.setEnabled(false);
		btnQuitarJuguetes.setBounds(261, 20, 128, 23);
		btnQuitarJuguetes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lstModelJuguetesNinios.clear();
			}
		});
		
		panel_2.add(btnQuitarJuguetes);
		
		JScrollPane scrollLista = new JScrollPane();
		scrollLista.setBounds(10, 20, 241, 103);
		panel_2.add(scrollLista);
		
		scrollLista.setViewportView(lstJuguetesNinios);
		lstJuguetesNinios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Listado Ni\u00F1os", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(395, 9, 446, 135);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 238, 105);
		panel_3.add(scrollPane);
		
		scrollPane.setViewportView(lstNinios);
	}
}
