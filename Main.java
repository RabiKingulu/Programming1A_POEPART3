import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to Quick Chat");
        try {
            MessageApp.startChat();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred.");
        }
    }
}
