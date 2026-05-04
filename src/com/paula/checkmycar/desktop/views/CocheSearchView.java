package com.paula.checkmycar.desktop.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.paula.checkmc.model.CocheCriteria;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.Marca;
import com.paula.checkmc.model.Modelo;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.TipoCombustible;
import com.paula.checkmc.model.TipoMotor;
import com.paula.checkmc.model.TipoTransmision;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.service.MarcaService;
import com.paula.checkmc.service.ModeloService;
import com.paula.checkmc.service.TipoCombustibleService;
import com.paula.checkmc.service.TipoMotorService;
import com.paula.checkmc.service.TipoTransmisionService;
import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.paula.checkmc.service.impl.MarcaServiceImpl;
import com.paula.checkmc.service.impl.ModeloServiceImpl;
import com.paula.checkmc.service.impl.TipoCombustibleServiceImpl;
import com.paula.checkmc.service.impl.TipoMotorServiceImpl;
import com.paula.checkmc.service.impl.TipoTransmisionServiceImpl;
import com.paula.checkmycar.desktop.views.renderer.MarcaCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.ModeloCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.TipoCombustibleCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.TipoMotorCBRenderer;
import com.paula.checkmycar.desktop.views.renderer.TipoTransmisionCBRender;
import com.paula.checkmycar.desktop.views.tableModel.CocheTableModel;

public class CocheSearchView extends View {

    private JTextField matriculaTF, numeroBastidorTF, precioMinTF, precioMaxTF;
    private JComboBox<Marca> marcaComboBox;
    private JComboBox<Modelo> modeloComboBox;
    private JComboBox<TipoCombustible> tipoCombustibleComboBox;
    private JComboBox<TipoTransmision> tipoTransmisionComboBox;
    private JComboBox<TipoMotor> tipoMotorComboBox;

    private JTable resultadosTable;
    private CocheTableModel tableModel;

    private CocheService cocheService;
    private MarcaService marcaService;
    private ModeloService modeloService;
    private TipoCombustibleService tipoCombustibleService;
    private TipoTransmisionService tipoTransmisionService;
    private TipoMotorService tipoMotorService;

    private JLabel totalResultadosLabel;
    private JButton buscarButton;

    public CocheSearchView() {
        initialize();
        initServices();
        postInitialize();
    }

    private void initialize() {

        setName("Búsqueda de coches");
        setLayout(new BorderLayout());

        JPanel criteriosPanel = new JPanel(new GridBagLayout());
        add(criteriosPanel, BorderLayout.NORTH);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        c.gridx=0; c.gridy=y; criteriosPanel.add(new JLabel("Matrícula:"), c);
        matriculaTF = new JTextField(); c.gridx=1; criteriosPanel.add(matriculaTF, c);

        c.gridx=2; criteriosPanel.add(new JLabel("Nº Bastidor:"), c);
        numeroBastidorTF = new JTextField(); c.gridx=3; criteriosPanel.add(numeroBastidorTF, c);

        y++;
        c.gridx=0; c.gridy=y; criteriosPanel.add(new JLabel("Marca:"), c);
        marcaComboBox = new JComboBox<>(); c.gridx=1; criteriosPanel.add(marcaComboBox, c);

        c.gridx=2; criteriosPanel.add(new JLabel("Modelo:"), c);
        modeloComboBox = new JComboBox<>(); c.gridx=3; criteriosPanel.add(modeloComboBox, c);

        y++;
        c.gridx=0; c.gridy=y; criteriosPanel.add(new JLabel("Combustible:"), c);
        tipoCombustibleComboBox = new JComboBox<>(); c.gridx=1; criteriosPanel.add(tipoCombustibleComboBox, c);

        c.gridx=2; criteriosPanel.add(new JLabel("Motor:"), c);
        tipoMotorComboBox = new JComboBox<>(); c.gridx=3; criteriosPanel.add(tipoMotorComboBox, c);

        y++;
        c.gridx=0; c.gridy=y; criteriosPanel.add(new JLabel("Transmisión:"), c);
        tipoTransmisionComboBox = new JComboBox<>(); c.gridx=1; criteriosPanel.add(tipoTransmisionComboBox, c);

        y++;
        c.gridx=0; c.gridy=y; criteriosPanel.add(new JLabel("Precio mín:"), c);
        precioMinTF = new JTextField(); c.gridx=1; criteriosPanel.add(precioMinTF, c);

        c.gridx=2; criteriosPanel.add(new JLabel("Precio máx:"), c);
        precioMaxTF = new JTextField(); c.gridx=3; criteriosPanel.add(precioMaxTF, c);

        y++;
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        c.gridx=0; c.gridy=y; c.gridwidth=4;
        criteriosPanel.add(botonesPanel, c);

        JButton limpiarButton = new JButton("Limpiar");
        buscarButton = new JButton("Buscar");

        botonesPanel.add(limpiarButton);
        botonesPanel.add(buscarButton);

        limpiarButton.addActionListener(e -> limpiar());
        buscarButton.addActionListener(e -> buscar());

        JPanel resultadosPanel = new JPanel(new BorderLayout());
        add(resultadosPanel, BorderLayout.CENTER);

        resultadosTable = new JTable();
        resultadosPanel.add(new JScrollPane(resultadosTable), BorderLayout.CENTER);

        JPanel footer = new JPanel();
        totalResultadosLabel = new JLabel("0 resultados");
        footer.add(totalResultadosLabel);
        add(footer, BorderLayout.SOUTH);

        marcaComboBox.addActionListener(e -> cargarModelos());
    }

    private void limpiar() {
        matriculaTF.setText("");
        numeroBastidorTF.setText("");
        precioMinTF.setText("");
        precioMaxTF.setText("");

        marcaComboBox.setSelectedIndex(0);
        modeloComboBox.setSelectedIndex(0);
        tipoCombustibleComboBox.setSelectedIndex(0);
        tipoMotorComboBox.setSelectedIndex(0);
        tipoTransmisionComboBox.setSelectedIndex(0);

        tableModel.setData(new ArrayList<>());
        totalResultadosLabel.setText("0 resultados");
    }

    private void buscar() {
        CocheCriteria criteria = new CocheCriteria();

        String matricula = matriculaTF.getText().trim();
        if (!matricula.isEmpty()) criteria.setMatricula(matricula);

        String bastidor = numeroBastidorTF.getText().trim();
        if (!bastidor.isEmpty()) criteria.setNumeroBastidor(bastidor);

        Marca marca = (Marca) marcaComboBox.getSelectedItem();
        if (marca != null && marca.getId() != null) criteria.setMarcaId(marca.getId());

        Modelo modelo = (Modelo) modeloComboBox.getSelectedItem();
        if (modelo != null && modelo.getId() != null) criteria.setModeloId(modelo.getId());

        TipoCombustible combustible = (TipoCombustible) tipoCombustibleComboBox.getSelectedItem();
        if (combustible != null && combustible.getId() != null) criteria.setTipoCombustibleId(combustible.getId());

        TipoMotor motor = (TipoMotor) tipoMotorComboBox.getSelectedItem();
        if (motor != null && motor.getId() != null) criteria.setTipoMotorId(motor.getId());

        TipoTransmision transmision = (TipoTransmision) tipoTransmisionComboBox.getSelectedItem();
        if (transmision != null && transmision.getId() != null) criteria.setTipoTransmisionId(transmision.getId());

        try {
            String precioMin = precioMinTF.getText().trim();
            if (!precioMin.isEmpty()) criteria.setPrecioMin(Double.parseDouble(precioMin));

            String precioMax = precioMaxTF.getText().trim();
            if (!precioMax.isEmpty()) criteria.setPrecioMax(Double.parseDouble(precioMax));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio introducido no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Results<CocheDTO> results = cocheService.findByCriteria(criteria, 1, 1000);
        List<CocheDTO> resultados = results.getPage();
        tableModel.setData(resultados);
        totalResultadosLabel.setText(resultados.size() + " resultados");
    }

    private void cargarModelos() {
        Marca marca = (Marca) marcaComboBox.getSelectedItem();

        DefaultComboBoxModel<Modelo> model = new DefaultComboBoxModel<>();

        Modelo placeholder = new Modelo();
        placeholder.setId(null);
        placeholder.setNombre("Seleccionar");
        model.addElement(placeholder);

        if (marca != null && marca.getId() != null) {
            List<Modelo> modelos = modeloService.findByMarca(marca.getId());
            for (Modelo m : modelos) model.addElement(m);
        }

        modeloComboBox.setModel(model);
    }

    private void initServices() {
        cocheService = new CocheServiceImpl();
        marcaService = new MarcaServiceImpl();
        modeloService = new ModeloServiceImpl();
        tipoCombustibleService = new TipoCombustibleServiceImpl();
        tipoTransmisionService = new TipoTransmisionServiceImpl();
        tipoMotorService = new TipoMotorServiceImpl();
    }

    private void postInitialize() {

        tableModel = new CocheTableModel();
        resultadosTable.setModel(tableModel);

        marcaComboBox.setRenderer(new MarcaCBRenderer());
        modeloComboBox.setRenderer(new ModeloCBRenderer());
        tipoCombustibleComboBox.setRenderer(new TipoCombustibleCBRenderer());
        tipoMotorComboBox.setRenderer(new TipoMotorCBRenderer());
        tipoTransmisionComboBox.setRenderer(new TipoTransmisionCBRender());

     
        DefaultComboBoxModel<Marca> marcaModel = new DefaultComboBoxModel<>();
        Marca marcaPlaceholder = new Marca();
        marcaPlaceholder.setId(null);
        marcaPlaceholder.setNombre("Seleccionar");
        marcaModel.addElement(marcaPlaceholder);
        for (Marca m : marcaService.findAll()) marcaModel.addElement(m);
        marcaComboBox.setModel(marcaModel);

     
        DefaultComboBoxModel<TipoCombustible> combustibleModel = new DefaultComboBoxModel<>();
        TipoCombustible combustiblePlaceholder = new TipoCombustible();
        combustiblePlaceholder.setId(null);
        combustiblePlaceholder.setNombre("Seleccionar");
        combustibleModel.addElement(combustiblePlaceholder);
        for (TipoCombustible tc : tipoCombustibleService.findAll()) combustibleModel.addElement(tc);
        tipoCombustibleComboBox.setModel(combustibleModel);

      
        DefaultComboBoxModel<TipoMotor> motorModel = new DefaultComboBoxModel<>();
        TipoMotor motorPlaceholder = new TipoMotor();
        motorPlaceholder.setId(null);
        motorPlaceholder.setNombre("Seleccionar");
        motorModel.addElement(motorPlaceholder);
        for (TipoMotor tm : tipoMotorService.findAll()) motorModel.addElement(tm);
        tipoMotorComboBox.setModel(motorModel);

        
        DefaultComboBoxModel<TipoTransmision> transmisionModel = new DefaultComboBoxModel<>();
        TipoTransmision transmisionPlaceholder = new TipoTransmision();
        transmisionPlaceholder.setId(null);
        transmisionPlaceholder.setNombre("Seleccionar");
        transmisionModel.addElement(transmisionPlaceholder);
        for (TipoTransmision tt : tipoTransmisionService.findAll()) transmisionModel.addElement(tt);
        tipoTransmisionComboBox.setModel(transmisionModel);
    }

	
}