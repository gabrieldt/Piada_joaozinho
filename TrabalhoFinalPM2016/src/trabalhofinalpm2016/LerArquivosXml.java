/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author gabriel
 */
public class LerArquivosXml
{
    /**
     * 
     * @param path_To_Programas_Xml
     * @return o nome do programa de pos graduacao da UNIRIO
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public static String getNomePosGraduacaoUnirio( String path_To_Programas_Xml ) 
                                                        throws ParserConfigurationException, 
                                                                SAXException, 
                                                                IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        File f = new File( path_To_Programas_Xml );
        
        Document doc = builder.parse(f);

        Element root = doc.getDocumentElement();

        String nomeProgramaPosGraduacao = "empty";
        NodeList childrenNodes = root.getChildNodes();
        
       for(int n=0; n < childrenNodes.getLength(); n++)
       {
            Node child = childrenNodes.item(n);
            if( child instanceof Element )
            {
                Element childElement = (Element)child;
                nomeProgramaPosGraduacao = childElement.getAttribute("nome");
                System.out.println(nomeProgramaPosGraduacao);
            }
       }
        return nomeProgramaPosGraduacao;
    }
    
    
    public static List<Professor> getProfessores( String path_To_Professores_Xml )
                                                    throws ParserConfigurationException, 
                                                                SAXException, 
                                                                IOException
    {
        List<Professor> professores = new ArrayList<Professor>();
        Professor p;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        File f = new File( path_To_Professores_Xml );
        
        Document doc = builder.parse(f);
        Element root = doc.getDocumentElement();
        NodeList lista_nos_pesquisa_xml = root.getChildNodes();
        
       for(int n=0; n < lista_nos_pesquisa_xml.getLength(); n++)
       {
            Node no_linhas_pesquisa_xml = lista_nos_pesquisa_xml.item(n);
            
            /*  verifica se e um no xml com linhas de pesquisa  */
            if( no_linhas_pesquisa_xml instanceof Element )
            {
                NodeList lista_nos_professores_xml = no_linhas_pesquisa_xml.getChildNodes();
                
                for( int i=0; i < lista_nos_professores_xml.getLength(); i++)
                {
                    Node no_professor_xml = lista_nos_professores_xml.item(i);
                    
                    /*  verifica se e um no xml com professores  */
                    if( no_professor_xml instanceof Element )
                    {
                        Element linha_pesquisa_element = (Element)no_linhas_pesquisa_xml;
                        Element professor_element = (Element)no_professor_xml;
                        //System.out.println( linha_pesquisa_element.getAttribute("nome") );
                        //System.out.println( "Nome : " + professor_element.getAttribute("nome") 
                         //                   + " | codigo : " + professor_element.getAttribute("codigo"));
                        
                        /**/ 
                        p = new Professor( professor_element.getAttribute("nome"),
                                           professor_element.getAttribute("codigo"),
                                           linha_pesquisa_element.getAttribute("nome"));
                        p.toString();
                        professores.add(p);
                    }
                }
               
            }
       }
       
       return professores;
    }
    
}
