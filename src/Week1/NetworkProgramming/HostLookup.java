package Week1.NetworkProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostLookup {

    public static void main(String[] args) {

        if (args.length > 0) { // use command line
            for (String arg : args) {
                System.out.println(lookup(arg));
            }
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter names and IP addresses. Enter \"exit\" to quit.");
            try {
                while (true) {
                    String host = in.readLine();
                    if (host.equalsIgnoreCase("exit") || host.equalsIgnoreCase("quit")) {
                        break;
                    }
                    System.out.println(lookup(host));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }

    } /* end main */


    private static String lookup(String host) {

        InetAddress thisComputer;
        byte[] address;

        // get the bytes of the IP address
        try {
            thisComputer = InetAddress.getByName(host);
            address = thisComputer.getAddress();
        } catch (UnknownHostException e) {
            return "Cannot find host " + host;
        }

        if (isHostname(host)) {
            // Print the IP address
            StringBuilder dottedQuad = new StringBuilder();
            for (int i = 0; i < address.length; i++) {
                int unsignedByte = address[i] < 0 ? address[i] + 256 : address[i];
                dottedQuad.append(unsignedByte);
                if (i != address.length - 1) dottedQuad.append(".");
            }
            return dottedQuad.toString();
        } else {  // this is an IP address
            return thisComputer.getHostName();
        }

    }  // end lookup

    private static boolean isHostname(String host) {

        char[] ca = host.toCharArray();
        // if we see a character that is neither a digit nor a period
        // then host is probably a hostname
        for (char c : ca) {
            if (!Character.isDigit(c)) {
                if (c != '.') return true;
            }
        }

        // Everything was either a digit or a period
        // so host looks like an IP address in dotted quad format
        return false;

    }  // end isHostName

} // end HostLookup
