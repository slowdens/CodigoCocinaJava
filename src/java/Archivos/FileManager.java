/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import java.io.*;

/**
 *
 * @author neftaly
 */
public class FileManager {
    private File archivo;
    private FileReader fr;
    private BufferedReader bfr;
    
    public void borrarArchivo()
    {
        try {
            int contador=0;
            archivo= new File("/home/neftaly/NetBeansProjects/Conycom/web/data/datos.txt");
            /*fr = new FileReader(archivo);
            bfr = new BufferedReader(fr);                      
            bfr.close();*/
            BufferedWriter bw  = new BufferedWriter(new FileWriter(archivo));
            bw.write("");
            bw.close();
        } catch (Exception es) {
            System.out.println(es.getMessage());
        }finally{
            try{
                bfr.close();
            }catch(Exception ess){
                System.out.println(ess);
            }
        }
        
    }
    
    
}
