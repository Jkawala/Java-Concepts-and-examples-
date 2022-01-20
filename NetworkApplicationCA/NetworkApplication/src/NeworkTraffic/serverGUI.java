package NeworkTraffic;

/**
 *
 * @author joswel bautista, James Kawala, Lloyd Portes
 */
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class serverGUI extends javax.swing.JFrame {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket connection;
    private ServerSocket server;
    private int port = 2121;
    private int totalClients = 100;
    final static String secretKey = "cake";
    EcryptionMessages encyrDecry = new EcryptionMessages();

    public serverGUI() {

        initComponents();
        this.setTitle("Server");
        this.setVisible(true);
        connectionStatus.setVisible(true);
    }

    public void startRunning() {
        try {
            server = new ServerSocket(port, totalClients);
            while (true) {
                try {
                    connectionStatus.setText(" Waiting for Client to Connect...");
                    connection = server.accept();
                    connectionStatus.setText(" Now Connected to " + connection.getInetAddress().getHostName());

                    out = new ObjectOutputStream(connection.getOutputStream());
                    out.flush();
                    in = new ObjectInputStream(connection.getInputStream());

                    whileChatting();

                } catch (EOFException eofException) {
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void whileChatting() throws IOException {
        String message = "";
        messageBox.setEditable(true);
        do {
            try {
                message = (String) in.readObject();
                messageDisplay.append("\n" + message);
            } catch (ClassNotFoundException classNotFoundException) {

            }
        } while (!message.equals("Client - END"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageDisplay = new javax.swing.JTextArea();
        messageBox = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        connectionStatus = new javax.swing.JLabel();
        serverTitle = new javax.swing.JLabel();
        text = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 255, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(102, 255, 204));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(null);

        messageDisplay.setColumns(20);
        messageDisplay.setRows(5);
        jScrollPane1.setViewportView(messageDisplay);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 90, 480, 250);

        messageBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageBoxActionPerformed(evt);
            }
        });
        jPanel1.add(messageBox);
        messageBox.setBounds(10, 350, 400, 40);

        sendButton.setBackground(new java.awt.Color(102, 102, 255));
        sendButton.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        sendButton.setText("Send");
        sendButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        jPanel1.add(sendButton);
        sendButton.setBounds(410, 350, 80, 40);

        connectionStatus.setForeground(new java.awt.Color(255, 255, 255));
        connectionStatus.setText("...");
        jPanel1.add(connectionStatus);
        connectionStatus.setBounds(10, 60, 300, 40);

        serverTitle.setFont(new java.awt.Font("Myriad Pro", 1, 48)); // NOI18N
        serverTitle.setForeground(new java.awt.Color(51, 0, 51));
        serverTitle.setText("Server Interface");
        serverTitle.setToolTipText("");
        serverTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.add(serverTitle);
        serverTitle.setBounds(80, 10, 380, 60);

        text.setBackground(new java.awt.Color(153, 255, 204));
        jPanel1.add(text);
        text.setBounds(0, 35, 460, 410);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(549, 469));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed

        sendMessage(messageBox.getText());
        messageBox.setText("");
    }//GEN-LAST:event_sendButtonActionPerformed

    private void messageBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageBoxActionPerformed

        sendMessage(messageBox.getText());
        messageBox.setText("");
    }//GEN-LAST:event_messageBoxActionPerformed

    private void sendMessage(String message) {
        try {

            messageDisplay.append("\nME(Server) - " + message);
            String encryptedmsg = encyrDecry.encrptionator(message, secretKey);

            out.writeObject("                                                             (enc):" + encryptedmsg);
            EcryptionMessages encyrDecry = new EcryptionMessages();
            message = encyrDecry.decryptionator(encryptedmsg, secretKey);
            out.writeObject("                                                             Server(decrypt) - " + message);
            out.flush();
        } catch (IOException ioException) {
            messageDisplay.append("\n Unable to Send Message");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel connectionStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField messageBox;
    private javax.swing.JTextArea messageDisplay;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel serverTitle;
    private javax.swing.JLabel text;
    // End of variables declaration//GEN-END:variables
}
