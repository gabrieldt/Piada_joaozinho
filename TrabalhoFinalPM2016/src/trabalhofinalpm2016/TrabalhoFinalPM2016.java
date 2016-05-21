/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author gabriel
 */
public class TrabalhoFinalPM2016
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, MalformedURLException, ParserConfigurationException, SAXException
    {
          //DOWNLOAD ARQUIVO PROGRAMAS POS GRADUACAO
        String path_to_remote_programas_xml_file = "https://s3.amazonaws.com/posgraduacao/programas.xml";
        String new_file_name_programas = "programas.xml";
        
        String path_to_local_programas_xml_file = DownloadArquivos.downloadArquivo( path_to_remote_programas_xml_file, new_file_name_programas );
        
        String nome_programa_pos_graduacao_unirio = lerArquivosXml.getNomePosGraduacaoUnirio( path_to_local_programas_xml_file );
        
        // DOWNLOAD DO ARQUIVO COM O NOME E CODIGO DOS PROFESSORES QUE PARTICIPAM
        // DO PROGRAMA DE POS GRADUACAO DA UNIRIO
        String path_to_remote_professores_xml_file = "https://s3.amazonaws.com/posgraduacao/" 
                                                        + nome_programa_pos_graduacao_unirio + "/contents.xml";
        String new_file_name_professores = "professores.xml";
        
        String path_to_local_professores_xml_file = DownloadArquivos.downloadArquivo( path_to_remote_professores_xml_file, new_file_name_professores );
        
    }
    
}
