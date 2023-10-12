package Week1.Time;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple example of a server that can accept TCP/IP connections.
 *
 * <p>When a client connects, the server responds by writing to
 * the client a string of bytes representing the current time
 * on the server machine.</p>
 *
 * <p>Run the server with</p>
 * <pre>
 *   java Week1.Time.TimeServer
 * </pre>
 * <p>followed optionally by a port number.</p>
 *
 * @author Karim Djemame
 * @version 1.3 [2021-09-24]
 */

public class TimeServer {


    private static final int DEFAULT_PORT_NUMBER = 1234;
    private static final String DEFAULT_FORMAT = "hh:mm:ss, dd MMMM yyyy";
    private int portNumber;
    private DateFormat formatter;


    /**
     * Creates a Week1.Time.TimeServer, listening on the default port.
     */

    public TimeServer() {
        portNumber = DEFAULT_PORT_NUMBER;
        formatter = new SimpleDateFormat(DEFAULT_FORMAT);
    }


    /**
     * Creates a Week1.Time.TimeServer, listening on the specified port.
     *
     * @param port port number for this Week1.Time.TimeServer
     */

    public TimeServer(int port) {
        portNumber = port;
        formatter = new SimpleDateFormat(DEFAULT_FORMAT);
    }


    /**
     * Starts the server.
     *
     * @throws IOException if there is a problem accepting a connection
     *                     from a client or writing data to the client
     */

    public void start() throws IOException {
        // Create a server socket, listening on the specified port

        ServerSocket server = new ServerSocket(portNumber);
        System.out.println("Server started.");

        // Now loop perpetually, servicing clients...

        while (true) {
            System.out.println("Waiting for client...");

            // Accept a connection and report on who has connected

            Socket client = server.accept();
            System.out.println("Client from " +
                    client.getInetAddress() + " connected");

            // Obtain an output stream for the socket linking server to client

            OutputStream out = client.getOutputStream();
            Date now = new Date();
            byte[] data = formatter.format(now).getBytes();
            out.write(data);
            System.out.println(data.length + " bytes written to socket");
        }
    }


    /**
     * Program to create and start a Week1.Time.TimeServer.
     *
     * @param argv vector of optional command-line arguments, the
     *             first of which specifies a port number for the server
     * @throws IOException in the event of server failure
     */

    public static void main(String[] argv) throws IOException, UnknownHostException {

        // print server host
        InetAddress iAddress = InetAddress.getLocalHost();
        String hostName = iAddress.getHostName();
        System.out.println("Server HostName:" + hostName);

        if (argv.length > 0) {
            int port = Integer.parseInt(argv[0]);
            new TimeServer(port).start();
        } else
            new TimeServer().start();
    }


}
