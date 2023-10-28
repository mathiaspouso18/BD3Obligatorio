package grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
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

public class ventanaPrincipal2 extends JFrame {

	private JPanel contentPane;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaPrincipal2 frame = new ventanaPrincipal2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventanaPrincipal2() {
		setAlwaysOnTop(true);
		setTitle("Guardería");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 437);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnlNinios = new JPanel();
		pnlNinios.setBorder(new TitledBorder(null, "Ninios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pnlNinios);
		pnlNinios.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(11, 21, 444, 115);
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
		txtNombre.setBounds(70, 31, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(5, 59, 60, 14);
		panel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(70, 56, 86, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		DefaultListModel listModel = new DefaultListModel();
		
		JButton btnInsertarNinio = new JButton("Insertar");
		btnInsertarNinio.setBounds(70, 81, 86, 23);
		btnInsertarNinio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String cedula = txtCedula.getText();
				
				if(!cedula.equals("") && !nombre.equals("") && !apellido.equals("")) {
					listModel.addElement(cedula + " - " + nombre + " " + apellido);
					lblError.setText("");
					lblError.setVisible(false);
				}else {
					lblError.setText("Los campos no pueden estar vacios");
					lblError.setVisible(true);
				}
				
			}
		});
		panel.add(btnInsertarNinio);
		
		JList list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    JScrollPane scrollLista = new JScrollPane();
	    scrollLista.setViewportView(list);
	    scrollLista.setBounds(173, 8, 243, 96);
		panel.add(scrollLista);
		
		JPanel pnlJuguetes = new JPanel();
		pnlJuguetes.setBounds(0, 147, 471, 144);
		pnlNinios.add(pnlJuguetes);
		pnlJuguetes.setBorder(new TitledBorder(null, "Juguetes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlJuguetes.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 21, 444, 115);
		pnlJuguetes.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDescripcion = new JLabel("Descripcion del juguete");
		lblDescripcion.setBounds(0, 0, 133, 14);
		panel_1.add(lblDescripcion);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(173, 0, 243, 20);
		panel_1.add(textField);
		textField.setColumns(100);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(10, 90, 434, 14);
		panel_1.add(lblError);
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setVisible(false);
	}
}
