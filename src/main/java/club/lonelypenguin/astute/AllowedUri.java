/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club.lonelypenguin.astute;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dbundgaard
 */
public class AllowedUri {
    
    private static final String PATH = "/tmp";
    private static final String ACL = "acl.lst";
    private final List<String> acl = new ArrayList<>();
    
    /*
    Reload will re-read the file and update the List<String> acl to reflect the new allowed uris
    
    */
    protected void reload() {
        
    }
    
    public List<String> getAcl() {
        return this.acl;
    }
    
}
