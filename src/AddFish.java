import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFish extends JFrame{
    private JPanel panel1;
    private JButton exitButton;
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    public AddFish(){
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFish.this.dispose();
                new Main().setVisible(true);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String species = textField1.getText();
                String name = textField2.getText();
                int tankSize = Integer.parseInt(textField3.getText());
                int minPh = Integer.parseInt(textField4.getText());
                int maxPh = Integer.parseInt(textField5.getText());

                Connection conn = ConnectToDataBase.Connector("root", "");
                if (conn != null) {
                    try {
                        String sqlFish = "INSERT INTO fish (SPECIES, NAME, TANK_SIZE, MIN_PH, MAX_PH) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement pstmtFish = conn.prepareStatement(sqlFish)) {
                            pstmtFish.setString(1, species);
                            pstmtFish.setString(2, name);
                            pstmtFish.setInt(3, tankSize);
                            pstmtFish.setInt(4, minPh);
                            pstmtFish.setInt(5, maxPh);

                            int affectedRowsFish = pstmtFish.executeUpdate();
                            if (affectedRowsFish == 0) {
                                throw new SQLException("Blad dodawania gatunku.");
                            }
                        }

                        JOptionPane.showMessageDialog(null, "Add successfull.", "Sccess", JOptionPane.INFORMATION_MESSAGE);
                        AddFish.this.dispose();
                        new Main().setVisible(true);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Błąd podczas dodawania gatunku: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nie udało się połączyć z bazą danych.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
