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
        if (arrayCounter >= 100) {
            JOptionPane.showMessageDialog(null, "Storage full.");
            return;
        }

        String recipient = "";
        while (true) {
            recipient = JOptionPane.showInputDialog("Enter recipient number (+27 and 12 chars) or developer number:");
            if (recipient == null) return;
            if ((recipient.startsWith("+27") && recipient.length() == 12) || (recipient.length() == 10 && !recipient.startsWith("+27"))) {
                break;
            }
            JOptionPane.showMessageDialog(null, "Invalid format. Try again.");
        }

        String message = JOptionPane.showInputDialog("Enter message (250 chars max):");
        if (message == null) return;
        if (message.length() > 250) {
            JOptionPane.showMessageDialog(null, "Message too long.");
            return;
        }

        String subMenu = "1. Send\n2. Disregard\n3. Store";
        String subInput = JOptionPane.showInputDialog(subMenu);
        if (subInput == null) return;

        String msgId = generateMessageId();
        int currentCount = ++messageCount;
        String msgHash = generateMessageHash(msgId, currentCount, message);

        recipients[arrayCounter] = recipient;
        messageIds[arrayCounter] = msgId;
        messageHashes[arrayCounter] = msgHash;

        if (subInput.equals("1")) {
            sentMessages[arrayCounter] = message;
            JOptionPane.showMessageDialog(null, "Sent successfully.");
        } else if (subInput.equals("2")) {
            disregardedMessages[arrayCounter] = message;
            JOptionPane.showMessageDialog(null, "Disregarded.");
        } else if (subInput.equals("3")) {
            storedMessages[arrayCounter] = message;
            storeMessageToTextFile(msgId, recipient, message, msgHash);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid processing option.");
            return;
        }

        arrayCounter++;
    }

    private static void storeMessageToTextFile(String Id, String recipient, String message, String hash) {
        try (FileWriter file = new FileWriter("stored_message.txt", true)) {
            file.write("MessageId: " + Id + "\nRecipient: " + recipient + "\nMessage: " + message + "\nHash: " + hash + "\n----\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File error.");
        }
    }

    public static void populateTestData() {
        recipients[0] = "+27834557896";
        messageIds[0] = "MSG001";
        sentMessages[0] = "Did you get the cake?";
        messageHashes[0] = "MS:1:DIDCAKE";
        arrayCounter++;

        recipients[1] = "+27838884567";
        messageIds[1] = "MSG002";
        storedMessages[1] = "Where are you? You are late! I have asked you to be on time.";
        messageHashes[1] = "MS:2:WHERETIME";
        arrayCounter++;

        recipients[2] = "+27834484567";
        messageIds[2] = "MSG003";
        disregardedMessages[2] = "Yohoooo, I am at your gate.";
        messageHashes[2] = "MS:3:YOHOOOGATE";
        arrayCounter++;

        recipients[3] = "0838884567";
        messageIds[3] = "0838884567";
        sentMessages[3] = "It is dinner time !";
        messageHashes[3] = "MS:4:ITTIME";
        arrayCounter++;

        recipients[4] = "+27838884567";
        messageIds[4] = "MSG005";
        storedMessages[4] = "Ok, I am leaving without you.";
        messageHashes[4] = "MS:5:OKYOU";
        arrayCounter++;
    }

