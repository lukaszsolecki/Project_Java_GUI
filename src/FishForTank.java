import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FishForTank extends JFrame{
    private JPanel panel1;
    private JButton exitButton;
    private JButton checkButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTable table1;
public FishForTank() {
    super("Fish");
    this.setContentPane(this.panel1);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(650, 500);
    setLocationRelativeTo(null);
    setResizable(false);

    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "SPECIES", "NAME","TANK_SIZE", "MIN_PH", "MAX_PH"}, 0);
    table1.setModel(model);
    //loadFishData(model);

    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            FishForTank.this.dispose();
            new Main().setVisible(true);
        }
    });
    checkButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadFishData(model);
        }
    });
}
    private void loadFishData(DefaultTableModel model) {
        int tankSize=Integer.parseInt(textField1.getText());
        int pH=Integer.parseInt(textField2.getText());
        String query = "SELECT ID, SPECIES, NAME, TANK_SIZE, MIN_PH, MAX_PH FROM FISH WHERE TANK_SIZE <= ? AND MIN_PH <= ? AND MAX_PH >= ?";

        try (Connection conn = ConnectToDataBase.Connector("root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, tankSize);
            pstmt.setInt(2, pH);
            pstmt.setInt(3, pH);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("ID"),
                            rs.getString("SPECIES"),
                            rs.getString("NAME"),
                            rs.getString("TANK_SIZE"),
                            rs.getString("MIN_PH"),
                            rs.getString("MAX_PH")
                    });
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania danych: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
