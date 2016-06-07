/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author gabriel
 */
public class Util
{
    /**
     * 
     * @param caminho_arquivo_xml_remoto
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException 
     */
     public static InputStream downloadMemoria( String caminho_arquivo_xml_remoto )
                                                throws MalformedURLException, 
                                                    IOException, 
                                                    ParserConfigurationException
    {
        URL website = new URL( caminho_arquivo_xml_remoto );
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        
        return Channels.newInputStream( rbc );
    }
     
     
     /**
     * 
     * @param arquivo_compactado_em_memoria
     * @return  retorna um InputStream para ser usado no parse xml
     * @throws IOException 
     */
    public static InputStream descompactaArquivoEmMemoria( InputStream arquivo_compactado_em_memoria )
                                                            throws IOException
    {
        ZipInputStream stream_entrada_zip = new ZipInputStream( arquivo_compactado_em_memoria );
        byte[] array_de_bytes = null;
        
        /*  extrai os arquivos dentro do .zip, um por um    */
        while ( stream_entrada_zip.getNextEntry() != null ) 
        {
            /*  cria um stream de saida para o arquivo lido */
            ByteArrayOutputStream stream_saida_array_bytes = new ByteArrayOutputStream();
            
            int c;
            
            /*  enquanto o byte lido nao for -1 */
            while( ( c = stream_entrada_zip.read() ) != -1 )
            {
                /*  escreve no stream de saida  */
                stream_saida_array_bytes.write( c );
            }
            
            /*  fecha a entrada atual para que se possa ler a proxima   */
            stream_entrada_zip.closeEntry();
            
            /*  converte de stream de saida para array de bytes */
            array_de_bytes = stream_saida_array_bytes.toByteArray();
            stream_saida_array_bytes.close();
        }
        stream_entrada_zip.close();
        /*  cria um stream de entrada para ser usado parse do arquivol xml  */
        ByteArrayInputStream is = new ByteArrayInputStream( array_de_bytes );
       
        return is;
    }
}
