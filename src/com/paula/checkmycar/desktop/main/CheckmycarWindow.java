package com.paula.checkmycar.desktop.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.paula.checkmycar.desktop.controller.CitasOpenMenuController;
import com.paula.checkmycar.desktop.controller.ClienteOpenMenuController;
import com.paula.checkmycar.desktop.controller.CocheOpenMenuController;
import com.paula.checkmycar.desktop.controller.EmpleadoOpenMenuController;
import com.paula.checkmycar.desktop.controller.OpenHomeViewController;
import com.paula.checkmycar.desktop.controller.OpenLoginController;
import com.paula.checkmycar.desktop.controller.PiezaOpenMenuController;
import com.paula.checkmycar.desktop.controller.PresupuestoOpenMenuController;
import com.paula.checkmycar.desktop.views.View;

/*
 * Ventana principal de la aplicación 
 */
public class CheckmycarWindow {

	private static CheckmycarWindow instance = null;

	JFrame frame;
	private JTabbedPane contentTabbedPane;
	private JPanel centerPanel;
	private JButton citasButton;
	private JButton clienteButton;
	private JButton empleadoButton;
	private JButton cocheButton;
	private JButton ordenButton;
	private JButton presupuestoButton;
	private JButton inicioButton;
	private JButton loginButton;
	private JButton citaButton;
	private JButton piezaButton;

	/**
	 * Launch the application.
	 */
	//    EventQueue.invokeLater(() -> {
	//        new LoginWindow();
	 
	    
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {
					try {
						UIManager.setLookAndFeel( "com.formdev.flatlaf.themes.FlatMacLightLaf");
						
						getInstance().frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */

	public CheckmycarWindow() {

		initialize();
		postInitialize();
	}

	/*
	 * Método para obtener la instancia única de MainWindow (Singleton)
	 */
	public static CheckmycarWindow getInstance() {
		if (instance == null) {
			instance = new CheckmycarWindow();
		}
		return instance;
	}

	/**
	 * Contenido del frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		mainPanel.add(northPanel, BorderLayout.NORTH);
		GridBagLayout gbl_northPanel = new GridBagLayout();
		gbl_northPanel.columnWidths = new int[] { 65, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_northPanel.rowHeights = new int[] { 41, 0 };
		gbl_northPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_northPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		northPanel.setLayout(gbl_northPanel);

		inicioButton = new JButton("");
		inicioButton.setIcon(
				new ImageIcon(CheckmycarWindow.class.getResource("/Iconos/ChatGPT Image 25 abr 2026, 23_36_24.png")));
		GridBagConstraints gbc_inicioButton = new GridBagConstraints();
		gbc_inicioButton.insets = new Insets(0, 0, 0, 5);
		gbc_inicioButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_inicioButton.gridx = 0;
		gbc_inicioButton.gridy = 0;
		northPanel.add(inicioButton, gbc_inicioButton);
		
		piezaButton = new JButton("");
		piezaButton.setIcon(new ImageIcon(CheckmycarWindow.class.getResource("/icons/32x32/mantenimiento.png")));
		GridBagConstraints gbc_piezaButton = new GridBagConstraints();
		gbc_piezaButton.insets = new Insets(0, 0, 0, 5);
		gbc_piezaButton.gridx = 2;
		gbc_piezaButton.gridy = 0;
		northPanel.add(piezaButton, gbc_piezaButton);

		loginButton = new JButton("");
		loginButton.setHorizontalAlignment(SwingConstants.RIGHT);
		loginButton.setIcon(new ImageIcon(CheckmycarWindow.class
				.getResource("/nuvola/32x32/1764_identity_identity_kgpg_authentication_kgpg_authentication.png")));
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.anchor = GridBagConstraints.EAST;
		gbc_loginButton.gridx = 31;
		gbc_loginButton.gridy = 0;
		northPanel.add(loginButton, gbc_loginButton);

		centerPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		contentTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		centerPanel.add(contentTabbedPane);

		JPanel southPanel = new JPanel();
		mainPanel.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		mainPanel.add(westPanel, BorderLayout.WEST);

		JPanel menuPanel = new JPanel();
		westPanel.add(menuPanel);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

		clienteButton = new JButton("Clientes");
		clienteButton.setIcon(new ImageIcon(CheckmycarWindow.class.getResource("/icons/16x16/usuario.png")));
		clienteButton.setHorizontalAlignment(SwingConstants.LEFT);
		clienteButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		clienteButton.setMaximumSize(new Dimension(150, 30));
		menuPanel.add(clienteButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		empleadoButton = new JButton("Empleados");
		empleadoButton.setIcon(new ImageIcon(CheckmycarWindow.class.getResource("/icons/16x16/mecanico.png")));
		empleadoButton.setHorizontalAlignment(SwingConstants.LEFT);
		empleadoButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		empleadoButton.setMaximumSize(new Dimension(150, 30));
		menuPanel.add(empleadoButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		cocheButton = new JButton("Coches");
		cocheButton.setIcon(new ImageIcon(CheckmycarWindow.class.getResource("/icons/16x16/coche.png")));
		cocheButton.setHorizontalAlignment(SwingConstants.LEFT);
		cocheButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		cocheButton.setMaximumSize(new Dimension(150, 30));
		menuPanel.add(cocheButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		ordenButton = new JButton("Ordenes de trabajo");
		ordenButton.setIcon(new ImageIcon(CheckmycarWindow.class.getResource("/icons/16x16/mantenimiento.png")));
		ordenButton.setHorizontalAlignment(SwingConstants.LEFT);
		ordenButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		ordenButton.setMaximumSize(new Dimension(150, 30));
		menuPanel.add(ordenButton);

		presupuestoButton = new JButton("Presupuestos");
		presupuestoButton.setIcon(new ImageIcon(CheckmycarWindow.class.getResource("/icons/16x16/presupuesto.png")));
		presupuestoButton.setHorizontalAlignment(SwingConstants.LEFT);
		presupuestoButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		presupuestoButton.setMaximumSize(new Dimension(150, 30));
		Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 5));
		menuPanel.add(rigidArea_1);
		menuPanel.add(presupuestoButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		citaButton = new JButton("citas ");
		citaButton.setMaximumSize(new Dimension(150, 30));
		citaButton.setIcon(new ImageIcon(CheckmycarWindow.class.getResource("/nuvola/16x16/1529_gaming_gaming_game_blue_controller_folder_game_games_blue_controller_folder_games.png")));
		citaButton.setHorizontalAlignment(SwingConstants.LEFT);
		citaButton.setAlignmentX(0.0f);
		menuPanel.add(citaButton);

		JPanel eastPanel = new JPanel();
		mainPanel.add(eastPanel, BorderLayout.EAST);

	}

	/**
	 * Método para añadir pestaña con botón de cerrar con icono
	 */
	public void addView(String title, View view) {

		JPanel tabTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		tabTitlePanel.setOpaque(false);

		// JPanel panel = new JPanel(new BorderLayout());
		// JXTitledPanel tiledPanel = new JXTitledPanel();
		// tiledPanel.setTitle(view.getName());

		JLabel titleLabel = new JLabel(title);

		JButton closeButton = new JButton();
		ImageIcon closeIcon = null;
		try {
			closeIcon = new ImageIcon(CheckmycarWindow.class.getResource("/nuvola/16x16/close.png"));
		} catch (Exception e) {
			closeButton.setText("✕");
			closeButton.setFont(closeButton.getFont().deriveFont(10f));
		}
		if (closeIcon != null) {
			closeButton.setIcon(closeIcon);
		}

		closeButton.setPreferredSize(new Dimension(16, 16));
		closeButton.setMargin(new Insets(0, 0, 0, 0));
		closeButton.setBorderPainted(false);
		closeButton.setContentAreaFilled(false);
		closeButton.setFocusPainted(false);
		closeButton.setRolloverEnabled(true);
		closeButton.setToolTipText("Cerrar pestaña");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = contentTabbedPane.indexOfComponent(view);
				if (index != -1) {
					contentTabbedPane.remove(index);
				}
			}
		});
		tabTitlePanel.add(titleLabel);
		tabTitlePanel.add(Box.createHorizontalStrut(3));
		tabTitlePanel.add(closeButton);
		contentTabbedPane.addTab(title, view);
		int tabIndex = contentTabbedPane.indexOfComponent(view);
		contentTabbedPane.setTabComponentAt(tabIndex, tabTitlePanel);
		contentTabbedPane.setSelectedIndex(tabIndex);

		contentTabbedPane.revalidate();
		contentTabbedPane.repaint();
		centerPanel.revalidate();
		centerPanel.repaint();
	}

	private void postInitialize() {

		clienteButton.addActionListener(new ClienteOpenMenuController(clienteButton));
		cocheButton.addActionListener(new CocheOpenMenuController(cocheButton));
		presupuestoButton.addActionListener(new PresupuestoOpenMenuController(presupuestoButton));
		empleadoButton.addActionListener(new EmpleadoOpenMenuController(empleadoButton));
		inicioButton.addActionListener(new OpenHomeViewController());
		loginButton.addActionListener(new OpenLoginController());
		citaButton.addActionListener(new CitasOpenMenuController(citaButton));
		piezaButton.addActionListener(new PiezaOpenMenuController(piezaButton));
		
		addView("Inicio", new com.paula.checkmycar.desktop.views.HomeView());
	}

}