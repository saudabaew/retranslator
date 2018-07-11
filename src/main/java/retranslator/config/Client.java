package retranslator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

@Component
public class Client {

    @Value("${server.address}")
    private String address;

    @Value("${server.port}")
    private int port;

    public void forward(String message){
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(address);

            try {
                Socket socket = new Socket(ip, port);

                try(DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())){
                    outputStream.writeBytes(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }



}
