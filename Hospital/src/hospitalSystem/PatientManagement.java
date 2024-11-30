package hospitalSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientManagement {
    JFrame frame;

    public PatientManagement() {
        frame = new JFrame("Patient Management");
        frame.setSize(600, 400);

        JButton addPatient = new JButton("Add Patient");
        JButton viewPatients = new JButton("View Patients");

        addPatient.setBounds(100, 50, 200, 30);
        viewPatients.setBounds(100, 100, 200, 30);

        frame.add(addPatient);
        frame.add(viewPatients);

        frame.setLayout(null);
        frame.setVisible(true);

        // Add Patient Listener
        addPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });

        // View Patients Listener
        viewPatients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPatients();
            }
        });
    }

    // Add Patient
    private void addPatient() {
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField contactField = new JTextField();
        JTextArea historyArea = new JTextArea();

        Object[] fields = {
            "Name:", nameField,
            "Age:", ageField,
            "Gender:", genderField,
            "Contact:", contactField,
            "Medical History:", historyArea
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Add Patient", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO Patients (name, age, gender, contact_number, medical_history) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nameField.getText());
                stmt.setInt(2, Integer.parseInt(ageField.getText()));
                stmt.setString(3, genderField.getText());
                stmt.setString(4, contactField.getText());
                stmt.setString(5, historyArea.getText());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Patient added successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // View Patients
    private void viewPatients() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Patients";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            StringBuilder patientList = new StringBuilder("Patients:\n");
            while (rs.next()) {
                patientList.append("ID: ").append(rs.getInt("patient_id"))
                        .append(", Name: ").append(rs.getString("name"))
                        .append(", Age: ").append(rs.getInt("age"))
                        .append(", Contact: ").append(rs.getString("contact_number"))
                        .append("\n");
            }
            JOptionPane.showMessageDialog(frame, patientList.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
