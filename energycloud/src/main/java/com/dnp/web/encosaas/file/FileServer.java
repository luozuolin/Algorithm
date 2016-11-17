package com.dnp.web.encosaas.file;

import javax.servlet.http.Part;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by luozl on 2016/10/25.
 */
public class FileServer extends Thread {

    private ServerSocket ss;

    public FileServer(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                Socket clientSock = ss.accept();
                saveFile(clientSock);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Socket clientSock) throws IOException {
        DataInputStream dis = new DataInputStream(clientSock.getInputStream());

        FileOutputStream fos = new FileOutputStream("testfile.jpg");
        byte[] buffer = new byte[4096];

        int filesize = 15123; // Send file size in separate msg
        int read = 0;
        int totalRead = 0;
        int remaining = filesize;
        while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
            totalRead += read;
            remaining -= read;
            System.out.println("read " + totalRead + " bytes.");
            fos.write(buffer, 0, read);
        }

        fos.close();
        dis.close();
    }
    private String getFileName(Part part) {

        // UUID uuid = UUID.randomUUID();
        // System.out.println(uuid);
        String header = part.getHeader("Content-Disposition");
        String submittedFileName = part.getSubmittedFileName();
        String fileName = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
        header.lastIndexOf("\"");
        return fileName;
    }
    public static void main(String[] args) {
        FileServer fs = new FileServer(1988);
        fs.start();
    }

}
