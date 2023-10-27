package grafica.ventanas;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JScrollPane;

import java.awt.LayoutManager;

public class ventanaPrincipal {

	private JFrame frame;
	private ventanaPrincipal vista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaPrincipal window = new ventanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public ventanaPrincipal() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame("Guardería");
		frame.setBounds(100, 100, 892, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Border blackline = BorderFactory.createTitledBorder("Niños");
		JPanel panel = new JPanel();
		panel.setBounds(28, 17,750,300);
		panel.setBorder(blackline);
		panel.setLayout(null);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(10,25,100,20);
		panel.add(lblCedula);
		
		JTextField txtCedula = new JTextField();
		txtCedula.setBounds(60, 25, 80, 20);
	    panel.add(txtCedula);
	    
	    JLabel lblNombre = new JLabel("Nombre");
	    lblNombre.setBounds(150,25,100,20);
		panel.add(lblNombre);
		
		JTextField txtNombre = new JTextField();
		txtNombre.setBounds(210, 25, 100, 20);
	    panel.add(txtNombre);
		
	    JLabel lblApellido = new JLabel("Apellido");
	    lblApellido.setBounds(320,25,100,20);
		panel.add(lblApellido);
		
		JTextField txtApellido = new JTextField();
		txtApellido.setBounds(380, 25, 100, 20);
	    panel.add(txtApellido);
	    
	    JButton btnInsertNinio = new JButton("Insertar");
	    btnInsertNinio.setBounds(500, 25, 100, 20);
	    panel.add(btnInsertNinio);
	    
	    JList listaNombres;
	    String nombres[] = { "Cristian", "Julian", "Milena"};
	    listaNombres = new JList( nombres );
	    
	    JScrollPane scrollLista = new JScrollPane();
	    scrollLista.setBounds(10, 60,220, 80);
	    scrollLista.setViewportView(listaNombres);
	    panel.add(scrollLista);
	    
	    frame.getContentPane().add(panel);
		
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
	}
}