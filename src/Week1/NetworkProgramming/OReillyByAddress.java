package Week1.NetworkProgramming;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OReillyByAddress {

    public static void main(String[] args) {

        try {
            InetAddress address = InetAddress.getByName("204.148.40.9");
            System.out.println(address);
        } catch (UnknownHostException e) {
            System.out.println("Could not find 204.148.40.9");
        }

    }

}
