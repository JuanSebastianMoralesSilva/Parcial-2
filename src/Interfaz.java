import javax.swing.*;
import java.util.ArrayList;
public class Interfaz extends JFrame {
    private JPanel contentPane;
    private JComboBox<String> trainerComboBox;
    private JButton nuevoTrainerBtn;
    private JList<String> equipoList;
    private JButton capturarBtn;
    private JButton editarBtn;
    private JButton liberarBtn;
    private JTextArea medallasArea;
    private JComboBox<Medal> medallaComboBox;
    private JButton ganarMedallaBtn;
    private JTextField buscarIdField;
    private JButton buscarBtn;
    private ArrayList<Pokemon> pokedexG;
    private ArrayList<Trainer> listaTrainers;
    private Trainer trainerActual;
    private DefaultListModel<String> modeloListaEquipo;
    public Interfaz() {
        setTitle("Gestión Pokémon - Primera Generación");
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        pokedexG = Pokedex.cargarPrimeraGeneracion();
        listaTrainers = Main.trainersIniciales(pokedexG);
        trainerActual = listaTrainers.get(0);
        modeloListaEquipo = new DefaultListModel<>();
        equipoList.setModel(modeloListaEquipo);
        for (Medal m : Medal.values()) {
            medallaComboBox.addItem(m);
        }
        actualizarComboTrainers();
        actualizarVistaTrainer();
        trainerComboBox.addActionListener(e -> {
            int idx = trainerComboBox.getSelectedIndex();
            if (idx >= 0 && idx < listaTrainers.size()) {
                trainerActual = listaTrainers.get(idx);
                actualizarVistaTrainer();
            }
        });
        nuevoTrainerBtn.addActionListener(e -> registrarNuevoTrainer());
        capturarBtn.addActionListener(e -> capturarPokemon());
        editarBtn.addActionListener(e -> editarPokemon());
        liberarBtn.addActionListener(e -> liberarPokemon());
        ganarMedallaBtn.addActionListener(e -> otorgarMedalla());
        buscarBtn.addActionListener(e -> buscarPorId());
    }
    private void actualizarComboTrainers() {
        trainerComboBox.removeAllItems();
        for (Trainer t : listaTrainers) {
            trainerComboBox.addItem(t.name + " (" + t.getId() + ")");
        }
        trainerComboBox.setSelectedIndex(listaTrainers.indexOf(trainerActual));
    }
    private void actualizarVistaTrainer() {
        modeloListaEquipo.clear();
        for (Pokemon p : trainerActual.getPokemons()) {
            modeloListaEquipo.addElement("#" + p.getId() + " - " + p.name + " | Nv. " + p.getLevel() + " | Tipos: " + p.getTypes());
        }
        StringBuilder sb = new StringBuilder();
        if (trainerActual.getMedal().isEmpty()) {
            sb.append("Aún no tiene medallas.");
        } else {
            for (Medal m : trainerActual.getMedal()) {
                sb.append("• ").append(m.name()).append("\n");
            }
        }
        medallasArea.setText(sb.toString());
    }
    private void registrarNuevoTrainer() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del nuevo entrenador:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        String id = JOptionPane.showInputDialog(this, "ID del entrenador (ej. TR-05):");
        if (id == null || id.trim().isEmpty()) return;
        Trainer nuevo = new Trainer(nombre.trim(), id.trim(), new ArrayList<>());
        listaTrainers.add(nuevo);
        trainerActual = nuevo;
        actualizarComboTrainers();
        actualizarVistaTrainer();
    }
    private void capturarPokemon() {
        String[] opciones = {"Elegir de Pokédex (1-151)", "Crear Personalizado"};
        int eleccion = JOptionPane.showOptionDialog(this, "¿Cómo deseas agregar el Pokémon?",
                "Capturar Pokémon", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);
        if (eleccion == 0) {
            String input = JOptionPane.showInputDialog(this, "Número de Pokédex (1 - 151):");
            if (input == null) return;
            try {
                int num = Integer.parseInt(input.trim());
                if (num >= 1 && num <= pokedexG.size()) {
                    Pokemon base = pokedexG.get(num - 1);
                    type[] tiposArray = base.getTypes().toArray(new type[0]);
                    Pokemon capturado = new Pokemon(base.name, base.getId(), base.getLevel(), trainerActual, tiposArray);
                    trainerActual.getPokemons().add(capturado);
                    actualizarVistaTrainer();
                } else {
                    JOptionPane.showMessageDialog(this, "Número fuera de rango (1-151).");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingresa un número válido.");
            }
        } else if (eleccion == 1) {
            try {
                String idStr = JOptionPane.showInputDialog(this, "ID del nuevo Pokémon:");
                if (idStr == null) return;
                int id = Integer.parseInt(idStr.trim());
                String nombre = JOptionPane.showInputDialog(this, "Nombre del Pokémon:");
                if (nombre == null || nombre.trim().isEmpty()) return;
                String nivelStr = JOptionPane.showInputDialog(this, "Nivel inicial:");
                if (nivelStr == null) return;
                int nivel = Integer.parseInt(nivelStr.trim());
                type tipo1 = (type) JOptionPane.showInputDialog(this, "Selecciona el Tipo principal:",
                        "Tipo 1", JOptionPane.QUESTION_MESSAGE, null, type.values(), type.NORMAL);
                type tipo2 = (type) JOptionPane.showInputDialog(this, "Selecciona el otro tipo:",
                        "Tipo 2", JOptionPane.QUESTION_MESSAGE, null, type.values(), type.NORMAL);
                actualizarVistaTrainer();
                if (tipo1 == null) return;
                Pokemon nuevoPoke = new Pokemon(nombre.trim(), id, nivel, trainerActual, tipo1, tipo2);
                trainerActual.getPokemons().add(nuevoPoke);
                actualizarVistaTrainer();
                if (tipo2 == null) {
                    nuevoPoke = nuevoPoke;
                actualizarVistaTrainer();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID y Nivel deben ser números enteros.");
            }
        }
    }
    private void editarPokemon() {
        int idx = equipoList.getSelectedIndex();
        if (idx == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona primero un Pokémon de la lista.");
            return;
        }
        Pokemon seleccionado = trainerActual.getPokemons().get(idx);
        String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", seleccionado.name);
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            seleccionado.name = nuevoNombre.trim();
        }
        String nuevoNivelStr = JOptionPane.showInputDialog(this, "Nuevo nivel:", seleccionado.getLevel());
        if (nuevoNivelStr != null) {
            try {
                seleccionado.setLevel(Integer.parseInt(nuevoNivelStr.trim()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Nivel inválido, se conservó el anterior.");
            }
        }
        actualizarVistaTrainer();
    }
    private void liberarPokemon() {
        int idx = equipoList.getSelectedIndex();
        if (idx == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona el Pokémon que deseas liberar.");
            return;
        }
        Pokemon eliminado = trainerActual.getPokemons().remove(idx);
        JOptionPane.showMessageDialog(this, eliminado.name + " fue liberado.");
        actualizarVistaTrainer();
    }
    private void otorgarMedalla() {
        Medal elegida = (Medal) medallaComboBox.getSelectedItem();
        if (elegida != null) {
            if (!trainerActual.getMedal().contains(elegida)) {
                trainerActual.getMedal().add(elegida);
                actualizarVistaTrainer();
            } else {
                JOptionPane.showMessageDialog(this, trainerActual.name + " ya tiene la " + elegida.name());
            }
        }
    }
    private void buscarPorId() {
        try {
            int idBuscado = Integer.parseInt(buscarIdField.getText().trim());
            for (int i = 0; i < trainerActual.getPokemons().size(); i++) {
                Pokemon p = trainerActual.getPokemons().get(i);
                if (p.getId() == idBuscado) {
                    equipoList.setSelectedIndex(i);
                    JOptionPane.showMessageDialog(this, "Encontrado:\n" + p);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "No se encontró el ID #" + idBuscado + " en este equipo.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Escribe un ID numérico válido en el buscador.");
        }
    }
}