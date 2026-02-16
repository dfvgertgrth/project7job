import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class JobApplication {
    String name, email, jobTitle, qualification;

    JobApplication(String name, String email, String jobTitle, String qualification) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.qualification = qualification;
    }
}

public class Cedov7 extends JFrame {
    private JTextField tfName, tfEmail, tfJobTitle, tfQualification;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<JobApplication> applications = new ArrayList<>();

    public Cedov7() {
        setTitle("💼 Система поиска работы");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("💼 Онлайн Портал Вакансий", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setOpaque(true);
        heading.setBackground(new Color(33, 47, 61));
        heading.setForeground(Color.white);
        heading.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(heading, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 12, 12));
        formPanel.setBorder(BorderFactory.createTitledBorder("📋 Подать заявку"));
        formPanel.setBackground(new Color(245, 245, 245));

        tfName = new JTextField();
        tfEmail = new JTextField();
        tfJobTitle = new JTextField();
        tfQualification = new JTextField();

        formPanel.add(new JLabel("Имя соискателя:"));
        formPanel.add(tfName);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(tfEmail);
        formPanel.add(new JLabel("Должность:"));
        formPanel.add(tfJobTitle);
        formPanel.add(new JLabel("Образование:"));
        formPanel.add(tfQualification);

        JButton btnApply = new JButton("📨 Отправить");
        JButton btnClear = new JButton("❌ Очистить");
        styleButton(btnApply);
        styleButton(btnClear);
        formPanel.add(btnApply);
        formPanel.add(btnClear);
        add(formPanel, BorderLayout.WEST);

        model = new DefaultTableModel(new String[]{"Имя", "Email", "Должность", "Образование"}, 0);
        table = new JTable(model);
        table.setRowHeight(22);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setBorder(BorderFactory.createTitledBorder("📊 Список заявок"));
        add(tablePane, BorderLayout.CENTER);

        btnApply.addActionListener(e -> applyJob());
        btnClear.addActionListener(e -> clearForm());

        // Пример данных
        applications.add(new JobApplication("Иван Петров", "ivan@mail.ru", "Java-разработчик", "Высшее"));
        applications.add(new JobApplication("Анна Смирнова", "anna@mail.ru", "Веб-дизайнер", "Среднее специальное"));
        updateTable();
    }

    private void applyJob() {
        String name = tfName.getText().trim();
        String email = tfEmail.getText().trim();
        String jobTitle = tfJobTitle.getText().trim();
        String qualification = tfQualification.getText().trim();

        if (name.isEmpty() || email.isEmpty() || jobTitle.isEmpty() || qualification.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Пожалуйста, заполните все поля!", 
                "Предупреждение", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        applications.add(new JobApplication(name, email, jobTitle, qualification));
        updateTable();
        clearForm();
        
        JOptionPane.showMessageDialog(this, 
            "✅ Заявка успешно отправлена!", 
            "Успех", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTable() {
        model.setRowCount(0);
        for (JobApplication ja : applications) {
            model.addRow(new Object[]{ja.name, ja.email, ja.jobTitle, ja.qualification});
        }
    }

    private void clearForm() {
        tfName.setText("");
        tfEmail.setText("");
        tfJobTitle.setText("");
        tfQualification.setText("");
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.white);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cedov7().setVisible(true));
    }
}