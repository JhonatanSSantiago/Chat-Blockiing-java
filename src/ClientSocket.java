/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
/**
 *
 * @author jhons
 */
public class ClientSocket {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;



    public ClientSocket (Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Cliente "+ socket.getRemoteSocketAddress() + " conectou");
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(),true);
    }

    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    public void close(){
        try{
            in.close();
            out.close();
            socket.close();
        }catch(IOException e){
            System.out.println("Erro ao fechar socket: "+ e.getMessage());
        }
        
    }

    public String getMessage(){
        try{
            return in.readLine();
        } catch(IOException e){
            return null;
        }
    }

    public boolean sendMsg(String msg){
        out.println(msg);
        return !out.checkError();
    }

  
}
