/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
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
     * @throws java.io.IOException
     * @throws java.net.MalformedURLException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     */
    public static void main(String[] args) throws IOException, MalformedURLException, ParserConfigurationException, SAXException
    {
        String nome_programa_pos_graduacao = "Programa PPGI";
        String ano_inical = "2000";
        String ano_final = "2016";
        
        /*  DOWNLOAD ARQUIVO PROGRAMAS POS GRADUACAO    */
        String caminho_arquivo_remoto_xml = "https://s3.amazonaws.com/posgraduacao/programas.xml";
        String new_file_name_programas = "programas.xml";
        
        InputStream arquivo_programa_pos_xml_memoria = Util.downloadMemoria( caminho_arquivo_remoto_xml );
        
        String nome_programa_pos_graduacao_unirio = LerArquivosXml.getNomePosGraduacaoUnirio( arquivo_programa_pos_xml_memoria );
        //System.out.println(nome_programa_pos_graduacao_unirio); System.exit(1);
        
        /*  DOWNLOAD DO ARQUIVO COM O NOME E CODIGO DOS PROFESSORES QUE PARTICIPAM
        DO PROGRAMA DE POS GRADUACAO DA UNIRIO   */
        String caminho_arquivo_remoto_professores_xml = "https://s3.amazonaws.com/posgraduacao/" 
                                                        + nome_programa_pos_graduacao_unirio + "/contents.xml";
        
        InputStream arquivo_professores_xml_memoria = Util.downloadMemoria( caminho_arquivo_remoto_professores_xml );
     
        List<Professor> professores = LerArquivosXml.getProfessores( arquivo_professores_xml_memoria );
        
        
        /*  DOWNLOAD DOS ARQUIVOS DOS CURRICULOS DOS PROFESSORES QUE FAZEM PARTE
             DO PROGRAMA DE POS GRADUACAO DA UNIRIO */
        for( Professor p : professores )
        {
            String caminho_arquivo_remoto_curriculo_professor = "https://s3.amazonaws.com/posgraduacao/" 
                                                        + nome_programa_pos_graduacao_unirio 
                                                        + "/" + p.getCodigo() + ".zip";
            
            InputStream arquivo_zip_memoria = Util.downloadMemoria( caminho_arquivo_remoto_curriculo_professor );
            
            InputStream arquivo_xml_descompactado = Util.descompactaArquivoEmMemoria( arquivo_zip_memoria );
            
            LerArquivosXml.parseCurriculoProfessor( arquivo_xml_descompactado, p, ano_inical, ano_final );
        }
        
        CriarArquivosDeProfessores.escreverArquivoFinal( professores, nome_programa_pos_graduacao );
        CriarArquivosDeProfessores.escreverArquivoFinalBonitinho(professores, nome_programa_pos_graduacao );
        
    }
    
}
