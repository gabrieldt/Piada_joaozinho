/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.InputStream;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import trabalhofinalpm2016.LerArquivosXml;
import trabalhofinalpm2016.Professor;
import trabalhofinalpm2016.Util;
//import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.w3c.dom.Document;
import static trabalhofinalpm2016.LerArquivosXml.getDocumentXml;
import static trabalhofinalpm2016.LerArquivosXml.parseOrientacoesAndamentoDoutorado;
import static trabalhofinalpm2016.LerArquivosXml.parseOrientacoesAndamentoGraduacao;
import static trabalhofinalpm2016.LerArquivosXml.parseOrientacoesAndamentoMestrado;
import static trabalhofinalpm2016.LerArquivosXml.parseOrientacoesConcluidasDoutorado;
import static trabalhofinalpm2016.LerArquivosXml.parseOrientacoesConcluidasGraduacao;
import static trabalhofinalpm2016.LerArquivosXml.parseOrientacoesConcluidasMestrado;

/**
 *
 * @author Gabriel
 */
public class TestLerArquivos {
    
        public LerArquivosXml PrimeiraInstancia  = new LerArquivosXml();
    /*
        @Test        
        public void TestgetNomePosGraduacaoUnirio() throws IOException, MalformedURLException, ParserConfigurationException, SAXException
    {
            
        String nome_programa_pos_graduacao = "PPGI-UNIRIO";
        String ano_inical = "2000";
        String ano_final = "2016";
        

        String caminho_arquivo_remoto_xml = "https://s3.amazonaws.com/posgraduacao/programas.xml";
        String new_file_name_programas = "programas.xml";
        
        InputStream arquivo_programa_pos_xml_memoria = Util.downloadMemoria(caminho_arquivo_remoto_xml);
        String nome_programa_pos_graduacao_unirio = LerArquivosXml.getNomePosGraduacaoUnirio(arquivo_programa_pos_xml_memoria);
         
        assertEquals("PPGI-UNIRIO", nome_programa_pos_graduacao_unirio);
            
        }
        
          @Test        
         public void TestgetProfessores() throws IOException, MalformedURLException, ParserConfigurationException, SAXException
    {
            
               String nome_programa_pos_graduacao = "Programa PPGI";
        String ano_inical = "2000";
        String ano_final = "2016";
        
  
        String caminho_arquivo_remoto_xml = "https://s3.amazonaws.com/posgraduacao/programas.xml";
        String new_file_name_programas = "programas.xml";
        
        InputStream arquivo_programa_pos_xml_memoria = Util.downloadMemoria( caminho_arquivo_remoto_xml );
        
        String nome_programa_pos_graduacao_unirio = LerArquivosXml.getNomePosGraduacaoUnirio( arquivo_programa_pos_xml_memoria );
   
        String caminho_arquivo_remoto_professores_xml = "https://s3.amazonaws.com/posgraduacao/" 
                                                        + nome_programa_pos_graduacao_unirio + "/contents.xml";
        
        InputStream arquivo_professores_xml_memoria = Util.downloadMemoria( caminho_arquivo_remoto_professores_xml );
     
        List<Professor> professores = LerArquivosXml.getProfessores( arquivo_professores_xml_memoria );
        
        //Assert.assertThat(professores.get(0).getNome(), CoreMatchers.containsString("Adriana Cesário de Faria Alvim"));
            
        
        
        }
         
      
          @Test
         public void TestparseCurriculoProfessor() throws IOException, MalformedURLException, ParserConfigurationException, SAXException {
             
        String nome_programa_pos_graduacao = "Programa PPGI";
        String ano_inicial = "2000";
        String ano_final = "2016";
        

        String caminho_arquivo_remoto_xml = "https://s3.amazonaws.com/posgraduacao/programas.xml";
        String new_file_name_programas = "programas.xml";
        
        InputStream arquivo_programa_pos_xml_memoria = Util.downloadMemoria( caminho_arquivo_remoto_xml );
        
        String nome_programa_pos_graduacao_unirio = LerArquivosXml.getNomePosGraduacaoUnirio( arquivo_programa_pos_xml_memoria );

        String caminho_arquivo_remoto_professores_xml = "https://s3.amazonaws.com/posgraduacao/" 
                                                        + nome_programa_pos_graduacao_unirio + "/contents.xml";
        
        InputStream arquivo_professores_xml_memoria = Util.downloadMemoria( caminho_arquivo_remoto_professores_xml );
                
        List<Professor> professores = LerArquivosXml.getProfessores( arquivo_professores_xml_memoria );
        
        for( Professor p : professores )
        {
            String caminho_arquivo_remoto_curriculo_professor = "https://s3.amazonaws.com/posgraduacao/" 
                                                        + nome_programa_pos_graduacao_unirio 
                                                        + "/" + p.getCodigo() + ".zip";
            
            InputStream arquivo_zip_memoria = Util.downloadMemoria( caminho_arquivo_remoto_curriculo_professor );
            
            InputStream arquivo_xml_descompactado = Util.descompactaArquivoEmMemoria( arquivo_zip_memoria );
            
            assertTrue(LerArquivosXml.parseCurriculoProfessor( arquivo_xml_descompactado, p, ano_inicial, ano_final));
            
        }
        
    }  
    */
    /**
     *
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    @Test
    public void TestparseArtigosRevistas() throws IOException, MalformedURLException, ParserConfigurationException, SAXException {
        String ano_inicial = "2000";
        String ano_final = "2016";
        
        /*  faz o download do arquivo qualis.xml para classificar os artigos    */
        String caminho_arquivo_qualis = "https://s3.amazonaws.com/posgraduacao/qualis.xml";
        InputStream arquivo_qualis_memoria = Util.downloadMemoria( caminho_arquivo_qualis );
        Document doc_qualis = getDocumentXml( arquivo_qualis_memoria );
        
        String caminho_arquivo_remoto_xml = "https://s3.amazonaws.com/posgraduacao/programas.xml";
        String new_file_name_programas = "programas.xml";
        
        InputStream arquivo_programa_pos_xml_memoria = Util.downloadMemoria( caminho_arquivo_remoto_xml );
        
        String nome_programa_pos_graduacao_unirio = LerArquivosXml.getNomePosGraduacaoUnirio( arquivo_programa_pos_xml_memoria );

        String caminho_arquivo_remoto_professores_xml = "https://s3.amazonaws.com/posgraduacao/" 
                                                        + nome_programa_pos_graduacao_unirio + "/contents.xml";
        
        InputStream arquivo_professores_xml_memoria = Util.downloadMemoria( caminho_arquivo_remoto_professores_xml );
                
        List<Professor> professores = LerArquivosXml.getProfessores( arquivo_professores_xml_memoria );
        
        for( Professor p : professores )
        {
            String caminho_arquivo_remoto_curriculo_professor = "https://s3.amazonaws.com/posgraduacao/" 
                                                        + nome_programa_pos_graduacao_unirio 
                                                        + "/" + p.getCodigo() + ".zip";
            
            InputStream arquivo_zip_memoria = Util.downloadMemoria( caminho_arquivo_remoto_curriculo_professor );
            
            InputStream arquivo_xml_descompactado = Util.descompactaArquivoEmMemoria( arquivo_zip_memoria );
            
             Document doc = getDocumentXml( arquivo_xml_descompactado );
            
        assertTrue(LerArquivosXml.parseArtigosRevistas( doc, doc_qualis, p,ano_inicial, ano_final));
       }
    }         
}//

