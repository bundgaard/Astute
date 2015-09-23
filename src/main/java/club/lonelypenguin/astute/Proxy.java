/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club.lonelypenguin.astute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author dbundgaard
 */
public class Proxy extends Thread {

    private final Socket client;
    private final Map<String, String> headers= new HashMap<String, String>();
    
    
    @Override
    public String toString() {
        return "Proxy worker class";
    }

    boolean isValidUrlAndTimestamp(String url) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        boolean betweenTwelveAndEighteen = (hour > 12 && hour < 18);
        return (url.equals("http://www.google.com/") && betweenTwelveAndEighteen);
    }

    byte[] httpString(String str) throws UnsupportedEncodingException {
        return str.getBytes("utf-8");
    }
    
    

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream()));
            PrintWriter out = new PrintWriter(getOutputStream());

            String URI = reader.readLine();

            List<String> http = new ArrayList<>();
            String[] uri_http = URI.split(" ");
            http.addAll(Arrays.asList(uri_http));
            if (isValidUrlAndTimestamp(http.get(1))) {
                getOutputStream().write(httpString("HTTP/1.1 200 OK\r\n"));
                getOutputStream().write(httpString("Content-Type: text/html\r\n"));
                getOutputStream().write(httpString("Content-length: "+ "[{id: 0}]".length() +"\r\n\r\n"));
                

                getOutputStream().write(httpString("[{id: 0}]"));
                getOutputStream().close();

            } else {
                URL url = null;
                if (http.size() < 0) {

                    url = new URL(http.get(1));
                    URLConnection conn = url.openConnection();
                    InputStream site_in = conn.getInputStream();
                    byte[] request_bytes = new byte[1024];

                    System.out.print(Thread.currentThread().getId() + " ");
                    System.out.println("SERVER> " + URI);

                    int site_reads;
                    while ((site_reads = site_in.read(request_bytes)) != -1) {
                        getOutputStream().write(request_bytes, 0, site_reads);
                        getOutputStream().flush();
                    }

                    site_in.close();
                    getOutputStream().close();
                }
            }
            getClient().close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Proxy(Socket client) {
        this.client = client;
        
    }

    public Socket getClient() {
        return this.client;
    }

    private InputStream getInputStream() throws IOException {
        return getClient().getInputStream();

    }

    private OutputStream getOutputStream() throws IOException {
        return getClient().getOutputStream();
    }

}
