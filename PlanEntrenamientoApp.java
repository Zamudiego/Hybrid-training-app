import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class PlanEntrenamientoApp {

    private static final String ARCHIVO = "plan_entrenamiento.txt";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Plan de Entrenamiento Semanal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 800);
        frame.setLayout(new GridLayout(8, 1, 10, 10));

        // Opciones de selección
        String[] semanas = {"Semana 1", "Semana 2", "Semana 3", "Semana 4","Semana 5", "Semana 6", "Semana 7", "Semana 8"};
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        String[] fuerzaOpciones = {"NA","Clean", "Snatch", "Deadlift", "Bench Press", "Squat"};
		String[] fuerzaConfig = {"NA","5x5", "3x3", "4x3", "3x5", "1x20","3x10"};
        String[] cardioOpciones = {"NA","Correr", "Nadar", "Bicicleta"};
        String[] wodOpciones = {"NA","Metcon", "Gimnásticos", "Cicleo de barra"};

        // Componentes
        JComboBox<String> comboSemana = new JComboBox<>(semanas);
        JComboBox<String> comboDia = new JComboBox<>(dias);
        JComboBox<String> comboFuerza = new JComboBox<>(fuerzaOpciones);
		JComboBox<String> comboFuerzaConfig = new JComboBox<>(fuerzaConfig);
		JTextField CargaFuerza = new JTextField("Porcentaje");
        JComboBox<String> comboCardio = new JComboBox<>(cardioOpciones);
		JTextField Distancia = new JTextField("Distancia");
        JComboBox<String> comboWod = new JComboBox<>(wodOpciones);

        JButton guardarBtn = new JButton("Guardar Entrenamiento");
        JButton leerBtn = new JButton("Ver Entrenamientos Guardados");

        JTextArea resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        // Acción de guardar
        guardarBtn.addActionListener(e -> {
            String linea = String.format(
                "%s - %s | Fuerza: %s Sets/Reps: %s Carga: %s | Cardio: %s Distancia: %s | WOD: %s",
                comboSemana.getSelectedItem(),
                comboDia.getSelectedItem(),
                comboFuerza.getSelectedItem(),
				comboFuerzaConfig.getSelectedItem(),
				CargaFuerza.getText(),
                comboCardio.getSelectedItem(),
				Distancia.getText(),
                comboWod.getSelectedItem()
            );

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
                writer.write(linea);
                writer.newLine();
                JOptionPane.showMessageDialog(frame, "Entrenamiento guardado correctamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error al guardar: " + ex.getMessage());
            }
        });

        // Acción de leer
        leerBtn.addActionListener(e -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
                resultadoArea.setText("");
                String linea;
                while ((linea = reader.readLine()) != null) {
                    resultadoArea.append(linea + "\n");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error al leer el archivo: " + ex.getMessage());
            }
        });

        // Agregar al frame
        frame.add(comboSemana);
        frame.add(comboDia);
        frame.add(comboFuerza);
		frame.add(comboFuerzaConfig);
		frame.add(CargaFuerza);
        frame.add(comboCardio);
		frame.add(Distancia);
        frame.add(comboWod);
        frame.add(guardarBtn);
        frame.add(leerBtn);
        frame.add(scrollPane);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
