import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteFish extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JButton exitButton;
    private JButton deleteButton;
public DeleteFish() {

    this.setContentPane(this.panel1);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 300);
    setLocationRelativeTo(null);
    setResizable(false);

    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DeleteFish.this.dispose();
            new Main().setVisible(true);
        }
    });
    deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                String id=textField1.getText();

                Connection conn = ConnectToDataBase.Connector("root", "");
                if (conn != null) {
                    try {
                        String sqlFish = "DELETE FROM fish WHERE ID = ?";
                        try (PreparedStatement pstmtFish = conn.prepareStatement(sqlFish)) {
                            pstmtFish.setString(1, id);


                            int affectedRowsFish = pstmtFish.executeUpdate();
                            if (affectedRowsFish == 0) {
                                throw new SQLException("Usunięcie ryby nie powiodło się.");
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Usunięto", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                        DeleteFish.this.dispose();
                        new Main().setVisible(true);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Błąd podczas usuwania: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nie udało się połączyć z bazą danych.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }

        }
    });
}
}
