import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    private JPanel Jpanel1;
    private JButton addFishButton;
    private JButton findFishForYourButton;
    private JButton findFishButton;
    private JButton fishDatabaseButton;
    private JButton deleteFishButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    public Main(){
        super("Fish");
        this.setContentPane(this.Jpanel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        addFishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.this.dispose();
                new AddFish().setVisible(true);
            }
        });
        deleteFishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.this.dispose();
                new DeleteFish().setVisible(true);
            }
        });
        fishDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.this.dispose();
                new ShowAllFish().setVisible(true);
            }
        });
        findFishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.this.dispose();
                new FindFish().setVisible(true);
            }
        });
        findFishForYourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.this.dispose();
                new FishForTank().setVisible(true);
            }
        });
    }
}
