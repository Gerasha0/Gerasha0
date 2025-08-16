package com.mrpz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static Archive archive = new Archive();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // --- Сучасний стиль ---
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
        Color white = Color.WHITE;
        Color blue = new Color(220, 235, 250);
        Font font = new Font("Segoe UI", Font.PLAIN, 16);
        UIManager.put("Panel.background", white);
        UIManager.put("TabbedPane.selected", blue);
        UIManager.put("TabbedPane.background", white);
        UIManager.put("Button.background", blue);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("List.background", blue);
        UIManager.put("List.font", font);
        UIManager.put("TextArea.background", white);
        UIManager.put("TextArea.font", font);
        UIManager.put("ScrollPane.background", white);
        UIManager.put("OptionPane.background", white);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("TextField.font", font);
        UIManager.put("ComboBox.font", font);

        JFrame frame = new JFrame("Факультатив - ООП Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 550);
        frame.getContentPane().setBackground(white);

    JTabbedPane tabs = new JTabbedPane();
    tabs.setFont(font.deriveFont(Font.BOLD, 16f));
    tabs.setBackground(white);
    tabs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Вкладка "Викладачі" ---
    JPanel teacherPanel = new JPanel(new BorderLayout());
    teacherPanel.setBackground(white);
        DefaultListModel<Teacher> teacherListModel = new DefaultListModel<>();
    JList<Teacher> teacherList = new JList<>(teacherListModel);
    teacherList.setBackground(blue);
    teacherList.setFont(font);
    teacherList.setSelectionBackground(new Color(180, 210, 240));
    teacherList.setFixedCellHeight(32);
    JTextArea teacherStatus = new JTextArea();
    teacherStatus.setEditable(false);
    teacherStatus.setBackground(white);
    teacherStatus.setFont(font);
    teacherStatus.setMargin(new Insets(10,10,10,10));
    JButton addTeacherBtn = new JButton("Додати викладача");
    JButton setGradeBtn = new JButton("Виставити оцінку");
    addTeacherBtn.setBackground(blue);
    setGradeBtn.setBackground(blue);
    addTeacherBtn.setFocusPainted(false);
    setGradeBtn.setFocusPainted(false);
    addTeacherBtn.setFont(font);
    setGradeBtn.setFont(font);
    addTeacherBtn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
    setGradeBtn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        setGradeBtn.setEnabled(false);

        addTeacherBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(frame, "Ім'я викладача:");
            if (name != null && !name.isEmpty()) {
                Teacher t = new Teacher(name);
                teachers.add(t);
                teacherListModel.addElement(t);
            }
        });

        teacherList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Teacher selected = teacherList.getSelectedValue();
                if (selected != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Викладач: ").append(selected.getName()).append("\n");
                    sb.append("Активні курси:\n");
                    boolean hasCourses = false;
                    for (Course c : courses) {
                        if (c.getTeacher().equals(selected)) {
                            hasCourses = true;
                            sb.append("- ").append(c.getName()).append("\n");
                            if (!c.getStudents().isEmpty()) {
                                sb.append("  Студенти та оцінки:\n");
                                for (Student s : c.getStudents()) {
                                    Integer grade = archive.getGrade(c, s);
                                    sb.append("    ").append(s.getName());
                                    if (grade != null) sb.append(" (Оцінка: ").append(grade).append(")");
                                    sb.append("\n");
                                }
                            } else {
                                sb.append("  Студентів немає\n");
                            }
                        }
                    }
                    if (!hasCourses) sb.append("  Немає активних курсів\n");
                    teacherStatus.setText(sb.toString());
                    setGradeBtn.setEnabled(true);
                } else {
                    teacherStatus.setText("");
                    setGradeBtn.setEnabled(false);
                }
            }
        });

        setGradeBtn.addActionListener(e -> {
            Teacher selected = teacherList.getSelectedValue();
            if (selected == null) return;
            // Вибір курсу цього викладача
            java.util.List<Course> teacherCourses = new ArrayList<>();
            for (Course c : courses) if (c.getTeacher().equals(selected)) teacherCourses.add(c);
            if (teacherCourses.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "У викладача немає курсів!");
                return;
            }
            Course course = (Course) JOptionPane.showInputDialog(frame, "Оберіть курс:", "Курс",
                    JOptionPane.QUESTION_MESSAGE, null, teacherCourses.toArray(), teacherCourses.get(0));
            if (course == null || course.getStudents().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "У цьому курсі немає студентів!");
                return;
            }
            Student student = (Student) JOptionPane.showInputDialog(frame, "Оберіть студента:", "Студент",
                    JOptionPane.QUESTION_MESSAGE, null, course.getStudents().toArray(), course.getStudents().get(0));
            String gradeStr = JOptionPane.showInputDialog(frame, "Оцінка (0-100):");
            try {
                int grade = Integer.parseInt(gradeStr);
                archive.addGrade(course, student, grade);
                JOptionPane.showMessageDialog(frame, "Оцінка " + grade + " виставлена студенту " + student + " за курс " + course.getName());
                teacherList.clearSelection();
                teacherList.setSelectedValue(selected, true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Некоректна оцінка!");
            }
        });

    JPanel teacherBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    teacherBtnPanel.setBackground(white);
        teacherBtnPanel.add(addTeacherBtn);
        teacherBtnPanel.add(setGradeBtn);
        teacherPanel.add(new JScrollPane(teacherList), BorderLayout.WEST);
        teacherPanel.add(new JScrollPane(teacherStatus), BorderLayout.CENTER);
        teacherPanel.add(teacherBtnPanel, BorderLayout.SOUTH);
        tabs.addTab("Викладачі", teacherPanel);

        // --- Вкладка "Студенти" ---
    JPanel studentPanel = new JPanel(new BorderLayout());
    studentPanel.setBackground(white);
        DefaultListModel<Student> studentListModel = new DefaultListModel<>();
    JList<Student> studentList = new JList<>(studentListModel);
    studentList.setBackground(blue);
    studentList.setFont(font);
    studentList.setSelectionBackground(new Color(180, 210, 240));
    studentList.setFixedCellHeight(32);
    JTextArea studentStatus = new JTextArea();
    studentStatus.setEditable(false);
    studentStatus.setBackground(white);
    studentStatus.setFont(font);
    studentStatus.setMargin(new Insets(10,10,10,10));
    JButton addStudentBtn = new JButton("Додати студента");
    addStudentBtn.setBackground(blue);
    addStudentBtn.setFocusPainted(false);
    addStudentBtn.setFont(font);
    addStudentBtn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        addStudentBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(frame, "Ім'я студента:");
            if (name != null && !name.isEmpty()) {
                Student s = new Student(name);
                students.add(s);
                studentListModel.addElement(s);
            }
        });

        studentList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Student selected = studentList.getSelectedValue();
                if (selected != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Студент: ").append(selected.getName()).append("\n");
                    sb.append("Активні курси:\n");
                    boolean hasCourses = false;
                    for (Course c : courses) {
                        if (c.getStudents().contains(selected)) {
                            hasCourses = true;
                            sb.append("- ").append(c.getName())
                              .append(" (Викладач: ").append(c.getTeacher().getName()).append(")");
                            Integer grade = archive.getGrade(c, selected);
                            if (grade != null) sb.append(" (Оцінка: ").append(grade).append(")");
                            sb.append("\n");
                        }
                    }
                    if (!hasCourses) sb.append("  Немає активних курсів\n");
                    studentStatus.setText(sb.toString());
                } else {
                    studentStatus.setText("");
                }
            }
        });

        studentPanel.add(new JScrollPane(studentList), BorderLayout.WEST);
        studentPanel.add(new JScrollPane(studentStatus), BorderLayout.CENTER);
        studentPanel.add(addStudentBtn, BorderLayout.SOUTH);
        tabs.addTab("Студенти", studentPanel);

        // --- Вкладка "Курси" ---
    JPanel coursePanel = new JPanel(new BorderLayout());
    coursePanel.setBackground(white);
        DefaultListModel<Course> courseListModel = new DefaultListModel<>();
    JList<Course> courseList = new JList<>(courseListModel);
    courseList.setBackground(blue);
    courseList.setFont(font);
    courseList.setSelectionBackground(new Color(180, 210, 240));
    courseList.setFixedCellHeight(32);
    JTextArea courseStatus = new JTextArea();
    courseStatus.setEditable(false);
    courseStatus.setBackground(white);
    courseStatus.setFont(font);
    courseStatus.setMargin(new Insets(10,10,10,10));
    JButton addCourseBtn = new JButton("Додати курс");
    JButton enrollBtn = new JButton("Записати студента на курс");
    addCourseBtn.setBackground(blue);
    enrollBtn.setBackground(blue);
    addCourseBtn.setFocusPainted(false);
    enrollBtn.setFocusPainted(false);
    addCourseBtn.setFont(font);
    enrollBtn.setFont(font);
    addCourseBtn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
    enrollBtn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        enrollBtn.setEnabled(false);

        addCourseBtn.addActionListener(e -> {
            if (teachers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Спочатку додайте викладача!");
                return;
            }
            String name = JOptionPane.showInputDialog(frame, "Назва курсу:");
            if (name != null && !name.isEmpty()) {
                Teacher teacher = (Teacher) JOptionPane.showInputDialog(frame, "Оберіть викладача:", "Викладач",
                        JOptionPane.QUESTION_MESSAGE, null, teachers.toArray(), teachers.get(0));
                if (teacher != null) {
                    Course c = new Course(name, teacher);
                    courses.add(c);
                    courseListModel.addElement(c);
                }
            }
        });

        courseList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Course selected = courseList.getSelectedValue();
                if (selected != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Курс: ").append(selected.getName()).append("\n");
                    sb.append("Викладач: ").append(selected.getTeacher().getName()).append("\n");
                    sb.append("Студенти та оцінки:\n");
                    if (!selected.getStudents().isEmpty()) {
                        for (Student s : selected.getStudents()) {
                            Integer grade = archive.getGrade(selected, s);
                            sb.append("- ").append(s.getName());
                            if (grade != null) sb.append(" (Оцінка: ").append(grade).append(")");
                            sb.append("\n");
                        }
                    } else {
                        sb.append("  Студентів немає\n");
                    }
                    courseStatus.setText(sb.toString());
                    enrollBtn.setEnabled(true);
                } else {
                    courseStatus.setText("");
                    enrollBtn.setEnabled(false);
                }
            }
        });

        enrollBtn.addActionListener(e -> {
            Course selected = courseList.getSelectedValue();
            if (selected == null) return;
            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Додайте студентів!");
                return;
            }
            Student student = (Student) JOptionPane.showInputDialog(frame, "Оберіть студента:", "Студент",
                    JOptionPane.QUESTION_MESSAGE, null, students.toArray(), students.get(0));
            if (student != null && !selected.getStudents().contains(student)) {
                selected.addStudent(student);
                JOptionPane.showMessageDialog(frame, "Студент " + student + " записаний на курс " + selected.getName());
                courseList.clearSelection();
                courseList.setSelectedValue(selected, true);
            }
        });

    JPanel courseBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    courseBtnPanel.setBackground(white);
        courseBtnPanel.add(addCourseBtn);
        courseBtnPanel.add(enrollBtn);
        coursePanel.add(new JScrollPane(courseList), BorderLayout.WEST);
        coursePanel.add(new JScrollPane(courseStatus), BorderLayout.CENTER);
        coursePanel.add(courseBtnPanel, BorderLayout.SOUTH);
        tabs.addTab("Курси", coursePanel);



        // --- Вкладка "Оцінки" ---
    JPanel gradePanel = new JPanel();
    gradePanel.setLayout(new BoxLayout(gradePanel, BoxLayout.Y_AXIS));
    gradePanel.setBackground(white);
    JButton viewArchiveBtn = new JButton("Переглянути архів");
    viewArchiveBtn.setBackground(blue);
    viewArchiveBtn.setFocusPainted(false);
    viewArchiveBtn.setFont(font);
    viewArchiveBtn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        viewArchiveBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Course c : archive.getAllGrades().keySet()) {
                sb.append("Курс: ").append(c.getName()).append(" (Викладач: ").append(c.getTeacher()).append(")\n");
                for (Student s : archive.getAllGrades().get(c).keySet()) {
                    sb.append("  Студент: ").append(s.getName())
                      .append(", Оцінка: ").append(archive.getGrade(c, s)).append("\n");
                }
            }
            if (sb.isEmpty()) sb.append("Архів порожній\n");
            JOptionPane.showMessageDialog(frame, sb.toString());
        });
        gradePanel.add(viewArchiveBtn);
        tabs.addTab("Оцінки", gradePanel);

        frame.add(tabs);
        frame.setVisible(true);
    }
}
