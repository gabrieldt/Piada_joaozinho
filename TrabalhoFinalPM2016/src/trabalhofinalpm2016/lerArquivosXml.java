/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

import java.io.File;
import java.io.IOException;
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
public class lerArquivosXml
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
            }
       }
        return nomeProgramaPosGraduacao;
    }
    
}
