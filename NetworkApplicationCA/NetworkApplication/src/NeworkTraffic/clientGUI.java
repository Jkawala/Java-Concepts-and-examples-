package NeworkTraffic;

/**
 *
 * @author joswel bautista, James Kawala, Lloyd Portes
 */
import java.io.*;
import java.net.*;
import javax.swing.*;

public class clientGUI extends javax.swing.JFrame {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String message = "";
    private Socket connection;
    private int port = 2121;
    private String serverIP;
    final static String secretKey = "cake";
    EcryptionMessages encyrDecry = new EcryptionMessages();

    public clientGUI(String ip) {

        initComponents();

        this.setTitle("Client");
        this.setVisible(true);
        connectionStatus.setVisible(true);
        serverIP = ip;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        messageBox = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageDisplay = new javax.swing.JTextArea();
        Title = new javax.swing.JLabel();
        connectionStatus = new javax.swing.JLabel();
        text = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(55, 5, 59));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(null);

        messageBox.setToolTipText("text\tType your message here...");
        messageBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageBoxActionPerformed(evt);
            }
        });
        jPanel1.add(messageBox);
        messageBox.setBounds(10, 370, 410, 40);

        sendButton.setBackground(new java.awt.Color(204, 204, 255));
        sendButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        jPanel1.add(sendButton);
        sendButton.setBounds(420, 370, 80, 40);

        messageDisplay.setColumns(20);
        messageDisplay.setRows(5);
        jScrollPane1.setViewportView(messageDisplay);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 80, 490, 280);

        Title.setFont(new java.awt.Font("Myriad Pro", 1, 48)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setText("Client Interface");
        jPanel1.add(Title);
        Title.setBounds(70, 20, 350, 40);

        connectionStatus.setForeground(new java.awt.Color(255, 255, 255));
        connectionStatus.setText("...");
        jPanel1.add(connectionStatus);
        connectionStatus.setBounds(10, 50, 300, 40);
        jPanel1.add(text);
        text.setBounds(0, 0, 530, 440);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(542, 484));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void messageBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageBoxActionPerformed

        sendMessage(messageBox.getText());
        messageBox.setText("");
    }//GEN-LAST:event_messageBoxActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed

        sendMessage(messageBox.getText());
        messageBox.setText("");
    }//GEN-LAST:event_sendButtonActionPerformed

    public void startRunning() {
        try {
            connectionStatus.setText("Attempting Connection ...");
            try {
                connection = new Socket(InetAddress.getByName(serverIP), port);
            } catch (IOException ioEception) {
                JOptionPane.showMessageDialog(null, "Server Might Be Down!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            connectionStatus.setText("Connected to: " + connection.getInetAddress().getHostName());
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());

            whileChatting();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void whileChatting() throws IOException {
        messageBox.setEditable(true);
        do {
            try {
                message = (String) in.readObject();
                messageDisplay.append("\n" + message);
            } catch (ClassNotFoundException classNotFoundException) {
            }
        } while (!message.equals("Client - END"));
    }

    private void sendMessage(String message) {
        try {

            messageDisplay.append("\nME(Client) - " + message);
            String encryptedmsg = encyrDecry.encrptionator(message, secretKey);
            System.out.println(encryptedmsg);
            out.writeObject("                                                             (encrpted):" + encryptedmsg);
            EcryptionMessages encyrDecry = new EcryptionMessages();
            message = encyrDecry.decryptionator(encryptedmsg, secretKey);
            out.writeObject("                                                             Client(decrypted) - " + message);
            out.flush();
        } catch (IOException ioException) {
            messageDisplay.append("\n Failed To Send Message");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JLabel connectionStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField messageBox;
    private javax.swing.JTextArea messageDisplay;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel text;
    // End of variables declaration//GEN-END:variables
}
