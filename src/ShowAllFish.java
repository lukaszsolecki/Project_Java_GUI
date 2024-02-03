import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowAllFish extends JFrame{
    private JPanel panel1;
    private JButton exitButton;
    private JTable table1;
public ShowAllFish() {
    super("Fish");
    this.setContentPane(this.panel1);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(450, 500);
    setLocationRelativeTo(null);
    setResizable(false);

    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "SPECIES", "NAME","TANK_SIZE", "MIN_PH", "MAX_PH"}, 0);
    table1.setModel(model);
    loadFishData(model);

    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShowAllFish.this.dispose();
            new Main().setVisible(true);
        }
    });

}
    private void loadFishData(DefaultTableModel model) {
        String query = "SELECT ID, SPECIES, NAME, TANK_SIZE, MIN_PH, MAX_PH FROM FISH";
        try (Connection conn = ConnectToDataBase.Connector("root", "");
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd podczas ładowania danych: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
