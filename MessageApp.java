import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;

public class MessageApp {
    private static int messageCount = 0;
    
    public static String[] sentMessages = new String[100];
    public static String[] disregardedMessages = new String[100];
    public static String[] storedMessages = new String[100];
    public static String[] messageHashes = new String[100];
    public static String[] messageIds = new String[100];
    public static String[] recipients = new String[100];
    
    public static int arrayCounter = 0;

    public static String generateMessageId() {
        return "MSG" + System.currentTimeMillis();
    }

    public static String generateMessageHash(String messageId, int count, String message) {
        String[] words = message.trim().split("\\s+");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : first;
        return messageId.substring(0, 2) + ":" + count + ":" + first.toUpperCase() + last.toUpperCase();
    }

    public static void startChat() {
        populateTestData();

        while (true) {
            String menuText = "Welcome to Quick Chat:\n" +
                              "Select transition:\n" +
                              "Option 1 - Select Quick Chat\n" +
                              "Option 2 - Send Quick Chat\n" +
                              "Option 3 - Quit\n" +
                              "Option 4 - Stored Messages Report Menu\n\n" +
                              "Enter your choice (1, 2, 3 or 4):";
            
            String choiceInput = JOptionPane.showInputDialog(menuText);

            if (choiceInput == null) {
                JOptionPane.showMessageDialog(null, "Quitting Quickchat. Goodbye!");
                return;
            }

            int choice;
            try {
                choice = Integer.parseInt(choiceInput.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Feature coming soon.");
                    break;

                case 2:
                    handleNewMessageInput();
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Quitting Quickchat. Goodbye!");
                    return;

                case 4:
                    handleReportSubMenu();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter 1, 2, 3 or 4.");
                    break;
            }
        }
    }

    private static void handleNewMessageInput() {
        
    }

    private static void handleReportSubMenu() {
        String subMenuText = "Stored Messages Report Sub-Menu:\n" +
                             "a. Display sender and recipient of stored messages\n" +
                             "b. Display longest stored message\n" +
                             "c. Search for a message ID\n" +
                             "d. Search all messages for a recipient\n" +
                             "e. Delete a message using hash\n" +
                             "f. Display full report\n\n" +
                             "Enter choice (a-f):";
        
        String input = JOptionPane.showInputDialog(subMenuText);
        if (input == null) return;
        
        JOptionPane.showMessageDialog(null, "Sub-menu selection recognized: " + input);
    }

    public static void populateTestData() {
        
    }
}
