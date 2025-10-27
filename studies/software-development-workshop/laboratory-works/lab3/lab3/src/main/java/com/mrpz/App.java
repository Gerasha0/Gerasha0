package com.mrpz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Частота слів у файлі");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 370);
        frame.setMinimumSize(new Dimension(420, 320));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(16, 16));
        panel.setBorder(new EmptyBorder(18, 18, 18, 18));
        panel.setBackground(new Color(245, 247, 250));

        JPanel topPanel = new JPanel(new GridLayout(2, 3, 8, 8));
        topPanel.setOpaque(false);

        JTextField inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setBackground(Color.WHITE);
        inputField.setToolTipText("Шлях до вхідного файлу");
        JButton openButton = new JButton("Вибрати...");
        openButton.setToolTipText("Вибрати вхідний текстовий файл");

        JTextField outputField = new JTextField();
        outputField.setEditable(false);
        outputField.setBackground(Color.WHITE);
        outputField.setToolTipText("Шлях до вихідного файлу");
        JButton saveButton = new JButton("Вибрати...");
        saveButton.setToolTipText("Вибрати файл для збереження результату");

        topPanel.add(new JLabel("Вхідний файл:", SwingConstants.RIGHT));
        topPanel.add(inputField);
        topPanel.add(openButton);
        topPanel.add(new JLabel("Вихідний файл:", SwingConstants.RIGHT));
        topPanel.add(outputField);
        topPanel.add(saveButton);

        JButton processButton = new JButton("Обробити та зберегти");
        processButton.setEnabled(false);
        processButton.setBackground(new Color(33, 150, 243));
        processButton.setForeground(Color.WHITE);
        processButton.setFocusPainted(false);
        processButton.setFont(processButton.getFont().deriveFont(Font.BOLD, 15f));
        processButton.setToolTipText("Обробити файл та зберегти результат");

        JTextArea logArea = new JTextArea(7, 50);
        logArea.setEditable(false);
        logArea.setBackground(new Color(250, 250, 250));
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Журнал дій"));

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(processButton, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        final File[] inputFile = {null};
        final File[] outputFile = {null};

        openButton.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser(new File("text-files"));
            int res = chooser.showOpenDialog(frame);
            if (res == JFileChooser.APPROVE_OPTION) {
                inputFile[0] = chooser.getSelectedFile();
                inputField.setText(inputFile[0].getAbsolutePath());
                logArea.append("Відкрито файл: " + inputFile[0].getName() + "\n");
                processButton.setEnabled(outputFile[0] != null);
            }
        });

        saveButton.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser();
            int res = chooser.showSaveDialog(frame);
            if (res == JFileChooser.APPROVE_OPTION) {
                outputFile[0] = chooser.getSelectedFile();
                outputField.setText(outputFile[0].getAbsolutePath());
                logArea.append("Вибрано файл для збереження: " + outputFile[0].getName() + "\n");
                processButton.setEnabled(inputFile[0] != null);
            }
        });

        processButton.addActionListener((ActionEvent e) -> {
            if (inputFile[0] == null || outputFile[0] == null) {
                logArea.append("Оберіть обидва файли!\n");
                return;
            }
            logArea.append("Починається обробка...\n");
            Map<String, Integer> freq = new LinkedHashMap<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile[0]), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("[^\\p{IsAlphabetic}’']+");
                    for (String word : words) {
                        word = word.trim().toLowerCase(Locale.ROOT);
                        if (!word.isEmpty()) {
                            freq.put(word, freq.getOrDefault(word, 0) + 1);
                        }
                    }
                }
                logArea.append("Файл прочитано. Знайдено слів: " + freq.size() + "\n");
            } catch (IOException ex) {
                logArea.append("Помилка читання: " + ex.getMessage() + "\n");
                return;
            }
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile[0]), StandardCharsets.UTF_8))) {
                for (Map.Entry<String, Integer> entry : freq.entrySet()) {
                    writer.write(entry.getKey() + ": " + entry.getValue());
                    writer.newLine();
                }
                logArea.append("Результат записано у файл: " + outputFile[0].getName() + "\n");
            } catch (IOException ex) {
                logArea.append("Помилка запису: " + ex.getMessage() + "\n");
            }
            logArea.append("Обробку завершено.\n");
        });
    }
}
