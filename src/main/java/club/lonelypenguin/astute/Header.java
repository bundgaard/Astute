/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club.lonelypenguin.astute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dbundgaard
 */
public class Header {
    
    private final List<String> header = new ArrayList<>();
    private final Map<String, String> properties = new HashMap<>();
    
    public Header() {
        this.header.add("HTTP/1.0 200 OK\r\n");
    }
    
    
    
    
    @Override
    public String toString() {
        return "";
    }
    
}
