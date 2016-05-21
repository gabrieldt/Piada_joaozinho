/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author gabriel
 */
public class DownloadArquivos
{
    /**
     * 
     * @param path_to_remote_xml_file
     * @return retorna o path para o arquivo baixado
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException 
     */
    public static String downloadArquivo( String path_to_remote_xml_file, 
                                            String novoNomeArquivo ) 
                                            throws MalformedURLException, 
                                                    IOException, 
                                                    ParserConfigurationException
    {
        String path_to_local_xml_file = "ArquivosXml/" + novoNomeArquivo;
        URL website = new URL( path_to_remote_xml_file );
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream( path_to_local_xml_file );
        fos.getChannel().transferFrom( rbc, 0, Long.MAX_VALUE );

        consertarCagadaArquivoXml.removeFirstLine(path_to_local_xml_file);
        
        return path_to_local_xml_file;
    }
}
