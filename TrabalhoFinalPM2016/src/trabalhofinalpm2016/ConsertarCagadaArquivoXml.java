/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author gabriel
 */
public class ConsertarCagadaArquivoXml
{
    public static void removeFirstLine(String fileName) throws IOException {  
    RandomAccessFile raf = new RandomAccessFile(fileName, "rw");          
     //Initial write position                                             
    long writePosition = raf.getFilePointer();                            
    raf.readLine();                                                       
    // Shift the next lines upwards.                                      
    long readPosition = raf.getFilePointer();                             

    byte[] buff = new byte[1024];                                         
    int n;                                                                
    while (-1 != (n = raf.read( buff ) ) ) 
    {                                  
        raf.seek(writePosition);                                          
        raf.write(buff, 0, n);                                            
        readPosition += n;                                                
        writePosition += n;                                               
        raf.seek(readPosition);                                           
    }                                                                     
    raf.setLength(writePosition);                                         
    raf.close();                                                          
}   
}
