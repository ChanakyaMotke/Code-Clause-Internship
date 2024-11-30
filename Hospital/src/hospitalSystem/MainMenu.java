package hospitalSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hospital Information System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton patientButton = new JButton("Manage Patients");
        JButton appointmentButton = new JButton("Manage Appointments");

        patientButton.setBounds(100, 50, 200, 30);
        appointmentButton.setBounds(100, 100, 200, 30);

        frame.add(patientButton);
        frame.add(appointmentButton);

        frame.setLayout(null);
        frame.setVisible(true);

        // Event Listeners
        patientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PatientManagement();
            }
        });

        appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PatientManagement();
            }
        });
    }
}
