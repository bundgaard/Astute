/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club.lonelypenguin.astute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dbundgaard
 */
public class Application {

    protected static List<String> getMethodAndLink(String string) {
        String[] pair = string.split(" ");
        System.out.println("getMethod: " + string);
        List<String> content = new ArrayList<>();
        content.addAll(Arrays.asList(pair));
        for (int i = 0; i < content.size(); i++) {
            System.out.println("idx: #" + i + ": " + content.get(i));
        }
        return content;
    }

    protected static Map<String, String> getHeader() {
        return new HashMap<String, String>();
    }

    public static void main(String args[]) throws IOException {
        // Read from config file
        ServerSocket listener = new ServerSocket(3232);
        while(true) {
            Proxy t = new Proxy(listener.accept());
            t.start();            
        }

    }

}
