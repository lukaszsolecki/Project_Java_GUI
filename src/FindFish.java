import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindFish extends JFrame{
    private JPanel panel1;
    private JButton exitButton;
    private JButton searchButton;
    private JTextField textField1;
    private JTable table1;

    public FindFish() {
        super("Fish");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "SPECIES", "NAME","TANK_SIZE", "MIN_PH", "MAX_PH"}, 0);

        table1.setModel(model);
        //loadFishData(model);

    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            FindFish.this.dispose();
            new Main().setVisible(true);
        }
    });
    searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadFishData(model);
        }
    });
}
    private void loadFishData(DefaultTableModel model) {
        String species=textField1.getText();
        String query = "SELECT ID, SPECIES, NAME, TANK_SIZE, MIN_PH, MAX_PH FROM FISH WHERE SPECIES LIKE ?";

        try (Connection conn = ConnectToDataBase.Connector("root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, species);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Usunięcie istniejących wierszy, jeśli istnieją, aby uniknąć duplikatów
                //model.setRowCount(0);

                // Dodanie nagłówków kolumn
                //model.setColumnIdentifiers(new String[]{"ID", "SPECIES", "NAME", "TANK_SIZE", "MIN_PH", "MAX_PH"});

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
