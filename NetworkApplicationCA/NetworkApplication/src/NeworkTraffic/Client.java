package NeworkTraffic;

/**
 *
 * @author joswel bautista, James Kawala, Lloyd Portes
 */
public class Client {

    public static void main(String[] args) {
        clientGUI client = new clientGUI("localhost");
        client.startRunning();
    }
}
