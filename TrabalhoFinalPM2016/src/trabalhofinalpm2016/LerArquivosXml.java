/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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
    public static String getNomePosGraduacaoUnirio( InputStream arquivo_memoria_xml ) 
                                                        throws ParserConfigurationException, 
                                                                SAXException, 
                                                                IOException
    {
        Document doc = getDocumentXml( arquivo_memoria_xml );
        
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
    
    public static List<Professor> getProfessores( InputStream arquivo_memoria_xml )
                                                    throws ParserConfigurationException, 
                                                                SAXException, 
                                                                IOException
    {
        List<Professor> professores = new ArrayList<Professor>();
        Professor p;
        
        Document doc = getDocumentXml( arquivo_memoria_xml );
        Element root = doc.getDocumentElement();
        NodeList lista_nos_pesquisa_xml = root.getChildNodes();
        
       for(int n=0; n < lista_nos_pesquisa_xml.getLength(); n++)
       {
            Node no_linhas_pesquisa_xml = lista_nos_pesquisa_xml.item(n);
            
            /*  verifica se e um no xml com linhas de pesquisa  e nao com
                espacos em branco   */
            if( no_linhas_pesquisa_xml instanceof Element )
            {
                NodeList lista_nos_professores_xml = no_linhas_pesquisa_xml.getChildNodes();
                
                for( int i=0; i < lista_nos_professores_xml.getLength(); i++)
                {
                    Node no_professor_xml = lista_nos_professores_xml.item(i);
                    
                    /*  verifica se e um no xml com professores e nao com
                        espacos em branco   */
                    if( no_professor_xml instanceof Element )
                    {
                        Element linha_pesquisa_element = (Element)no_linhas_pesquisa_xml;
                        Element professor_element = (Element)no_professor_xml;
                       
                        p = new Professor( professor_element.getAttribute("nome"),
                                           professor_element.getAttribute("codigo"),
                                           linha_pesquisa_element.getAttribute("nome"));
                        professores.add(p);
                    }
                }
               
            }
       }
       
       return professores;
    }
    
    
    public static void parseCurriculoProfessor( InputStream curriculo_memoria, Professor p,
                                                String ano_inicial, String ano_final ) 
                                            throws SAXException,
                                                   IOException, 
                                                   ParserConfigurationException
    {
        Document doc = getDocumentXml( curriculo_memoria );
        /*  faz o download do arquivo qualis.xml para classificar os artigos    */
        String caminho_arquivo_qualis = "https://s3.amazonaws.com/posgraduacao/qualis.xml";
        String novo_nome_arquivo = "qualis.xml";
        InputStream arquivo_qualis_memoria = Util.downloadMemoria( caminho_arquivo_qualis );
        Document doc_qualis = getDocumentXml( arquivo_qualis_memoria );
        
        parseArtigosRevistas( doc, doc_qualis, p, ano_inicial, ano_final );
        parseArtigosEventos( doc, doc_qualis, p, ano_inicial, ano_final );
        parseParticipacoesBancas( doc, p, ano_inicial, ano_final );
        parseOrientacoes( doc, p, ano_inicial, ano_final );
    }
    
    private static void parseArtigosRevistas( Document doc, Document doc_qualis, Professor p, String ano_inicial, String ano_final ) 
                                                throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
       NodeList nList = doc.getElementsByTagName("ARTIGO-PUBLICADO");
       boolean ano_dentro_da_faixa;
       
       for(int n=0; n < nList.getLength(); n++)
       {
            Node nNode = nList.item(n);
            
            if( nNode instanceof Element )
            {
                ano_dentro_da_faixa = true;
                NodeList nListTituloAno = nNode.getChildNodes();
            
                for( int i=0; i < nListTituloAno.getLength(); i++ )
                {
                    /*  se o ano do no nao estiver no range requisitado
                        pelo usuario, pula para o proximo no ARTIGO-PUBLICADO   */
                    if( !ano_dentro_da_faixa )
                        break;
                    
                    Node nNodeTituloAno = nListTituloAno.item(i);
                    
                    if( nNodeTituloAno instanceof Element )
                        {
                            Element eElement = (Element)nNodeTituloAno;
                            if( eElement.getNodeName().equals("DADOS-BASICOS-DO-ARTIGO") )
                            {
                                String ano_artigo = eElement.getAttribute( "ANO-DO-ARTIGO" );
                                /*  se a data do artigo nao for dentro do periodo definido
                                    pelo usuario entao pula para o proximo artigo   */
                                if( !verificaDataPublicacao( ano_inicial, ano_final, ano_artigo ) )
                                    ano_dentro_da_faixa = false;
                            }
                            if( eElement.getNodeName().equals( "DETALHAMENTO-DO-ARTIGO" ) )
                            {
                                Artigo a = new Artigo();
                                String titulo_periodico_revista = eElement.getAttribute( "TITULO-DO-PERIODICO-OU-REVISTA" );
                                String classificacao = getClassificacaoArtigo( doc_qualis, titulo_periodico_revista );
                                a.setPublicacao( "REVISTAS" );
                                a.setClassificacao( classificacao );
                                p.adicionaArtigoNoCurriculo( a );
                            }
                        }   /*    fim if    */
                }   /*    fim for    */     
            }
       }    /*    fim for    */     
    }
    
    private static void parseArtigosEventos( Document doc, Document doc_qualis, Professor p, String ano_inicial, String ano_final ) 
                                                throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
       NodeList nList = doc.getElementsByTagName("TRABALHO-EM-EVENTOS");
       boolean ano_dentro_da_faixa;
       
       for(int n=0; n < nList.getLength(); n++)
       {
            Node nNode = nList.item(n);
            
            if( nNode instanceof Element )
            {
                ano_dentro_da_faixa = true;
                NodeList nListTituloAno = nNode.getChildNodes();
            
                for( int i=0; i < nListTituloAno.getLength(); i++ )
                {
                    /*  se o ano do no nao estiver no range requisitado
                        pelo usuario, pula para o proximo no ARTIGO-PUBLICADO   */
                    if( !ano_dentro_da_faixa )
                        break;
                    
                    Node nNodeTituloAno = nListTituloAno.item(i);
                    
                    if( nNodeTituloAno instanceof Element )
                        {
                            Element eElement = (Element)nNodeTituloAno;
                            if( eElement.getNodeName().equals("DADOS-BASICOS-DO-TRABALHO") )
                            {
                                String ano_artigo = eElement.getAttribute( "ANO-DO-TRABALHO" );
                                /*  se a data do artigo nao for dentro do periodo definido
                                    pelo usuario entao pula para o proximo artigo   */
                                if( !verificaDataPublicacao( ano_inicial, ano_final, ano_artigo ) )
                                    ano_dentro_da_faixa = false;
                            }
                            if( eElement.getNodeName().equals( "DETALHAMENTO-DO-TRABALHO" ) )
                            {
                                String titulo_periodico_revista = eElement.getAttribute( "NOME-DO-EVENTO" );
                                
                                /*  o nome do evento e precedido pelo numero sequencial do evento
                                    que nao tem no arquivo qualis.xml.
                                    Ex.: 
                                
                                curriculo: International Conference on Engineering Education (ICEE 2003)
                                qualis: International Conference on Engineering Education
                                
                                curriculo: The 7th IASTED International Conference on Computers and Advanced Technology in Education (CATE 2004)
                                qualis: IASTED International Conference on Computers and Advanced Technology in Education
                                
                                curriculo: EATIS 2007 - Euro American Conference on Telematics and Information Systems
                                qualis: Euro American Conference on Telematics and Information Systems
                                
                                por isso tem-se que dividir o NOME-DO-EVENTO retirando alguns padroes do inicio e do fim 
                                */
                                //titulo_periodico_revista = Util.tratarNomeDoEvento();
                                String classificacao = getClassificacaoArtigo( doc_qualis, titulo_periodico_revista );
                                Artigo a = new Artigo();
                                a.setPublicacao( "EVENTOS" );
                                a.setClassificacao( classificacao );
                                p.adicionaArtigoNoCurriculo( a );
                            }
                        }   /*    fim if    */
                }   /*    fim for    */     
            }
       }    /*    fim for    */     
    }
    
    
    /**
     * 
     * @param doc_qualis
     * @param titulo_periodico_revista
     * @return                      retorna classificacao ( A1 | A2 ... C | NC )
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException 
     */
    public static String getClassificacaoArtigo( Document doc_qualis, String titulo_periodico_revista )
                                                  throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException,
                                                       SAXException
    {
       NodeList nList = doc_qualis.getElementsByTagName("entry");
        
       for(int n=0; n < nList.getLength(); n++)
       {
            Node nNode = nList.item(n);
            
            if( nNode instanceof Element )
            {
                Element eElement = (Element)nNode;

                String regex = eElement.getAttribute( "regex" );
                //System.out.println("#####");
                //System.out.println( "Titulo: " + titulo_periodico_revista.toUpperCase() );
                //System.out.println( "Regex: " + regex );
                
                //if( titulo_periodico_revista.compareToIgnoreCase( regex ) == 0 )
                  //  return eElement.getAttribute( "class" );
                if( regex.toLowerCase().contains( titulo_periodico_revista.toLowerCase() ))
                    return eElement.getAttribute( "class" );
                    
            }
                
            
       }
       /*   nao classificado    */
       return "NC";
    }
    
    public static void parseParticipacoesBancas( Document doc, Professor p, String ano_inicial, String ano_final )
                                                    throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
       parseParticipacoesBancasGraduacao( doc, p, ano_inicial, ano_final );
       parseParticipacoesBancasMestrado( doc, p, ano_inicial, ano_final );
       parseParticipacoesBancasDoutorado( doc, p, ano_inicial, ano_final );
       /*
       System.out.println( "PROF: " + p.getNome() + " PEB  graduacao: " + p.getNumParticipacaoBancas( "GRADUACAO" ) );
       System.out.println( "PROF: " + p.getNome() + " PEB  mestrado: " + p.getNumParticipacaoBancas( "MESTRADO" ) );
       System.out.println( "PROF: " + p.getNome() + " PEB  doutorado: " + p.getNumParticipacaoBancas( "DOUTORADO" ) );
       */
    }
    
    
    /**
     * 
     * @param doc
     * @param p
     * @param ano_inicial
     * @param ano_final
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException 
     */
    public static void parseParticipacoesBancasMestrado( Document doc, Professor p, String ano_inicial, String ano_final )
                                                    throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
   {
        NodeList nList = doc.getElementsByTagName("PARTICIPACAO-EM-BANCA-DE-MESTRADO");
        ParticipacaoEmBanca peb;
       
       for(int n=0; n < nList.getLength(); n++)
       {
            Node nNodeBancaMestrado = nList.item(n);
            
            if( nNodeBancaMestrado instanceof Element )
                    {
                        NodeList nListDadosBasicos = nNodeBancaMestrado.getChildNodes();
                        
                        for( int i=0; i < nListDadosBasicos.getLength(); i++ )
                        {
                            Node nNodeDadosBasicos = nListDadosBasicos.item(i);
                            
                            if( nNodeDadosBasicos instanceof Element )
                            {
                                Element eElement = (Element)nNodeDadosBasicos;
                                
                                if( eElement.getNodeName().equals("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-MESTRADO") )
                                {
                                    String ano_participacao_banca = eElement.getAttribute("ANO" );
                                    
                                    if( verificaDataPublicacao( ano_inicial, ano_final, ano_participacao_banca ) )
                                    {
                                        peb = new ParticipacaoEmBanca( "MESTRADO" );
                                        p.adicionaParticipacaoEmBancaNoCurriculo( peb );
                                    }
                                }
                            }
                        }
                        
                    }   /*    fim if    */
       }    /*    fim for    */     
    }
    
    
    /**
     * 
     * @param doc
     * @param p
     * @param ano_inicial
     * @param ano_final
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException 
     */
    public static void parseParticipacoesBancasDoutorado( Document doc, Professor p, String ano_inicial, String ano_final )
                                                    throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
        NodeList nList = doc.getElementsByTagName("PARTICIPACAO-EM-BANCA-DE-DOUTORADO");
        ParticipacaoEmBanca peb;
       
       for(int n=0; n < nList.getLength(); n++)
       {
            Node nNodeBancaDoutorado = nList.item(n);
            
            if( nNodeBancaDoutorado instanceof Element )
                    {
                        NodeList nListDadosBasicos = nNodeBancaDoutorado.getChildNodes();
                        
                        for( int i=0; i < nListDadosBasicos.getLength(); i++ )
                        {
                            Node nNodeDadosBasicos = nListDadosBasicos.item(i);
                            
                            if( nNodeDadosBasicos instanceof Element )
                            {
                                Element eElement = (Element)nNodeDadosBasicos;
                                
                                if( eElement.getNodeName().equals("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-DOUTORADO") )
                                {
                                    String ano_participacao_banca = eElement.getAttribute("ANO" );
                                    
                                    if( verificaDataPublicacao( ano_inicial, ano_final, ano_participacao_banca ) )
                                    {
                                        peb = new ParticipacaoEmBanca( "DOUTORADO" );
                                        p.adicionaParticipacaoEmBancaNoCurriculo( peb );
                                    }
                                }
                            }
                        }
                        
                    }   /*    fim if    */
       }    /*    fim for    */     
    }
    
    /**
     * 
     * @param doc
     * @param p
     * @param ano_inicial
     * @param ano_final
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException 
     */
    public static void parseParticipacoesBancasGraduacao( Document doc, Professor p, String ano_inicial, String ano_final )
                                                    throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
        NodeList nList = doc.getElementsByTagName("PARTICIPACAO-EM-BANCA-DE-GRADUACAO");
        ParticipacaoEmBanca peb;
       
       for(int n=0; n < nList.getLength(); n++)
       {
            Node nNodeBancaGraduacao = nList.item(n);
            
            if( nNodeBancaGraduacao instanceof Element )
                    {
                        NodeList nListDadosBasicos = nNodeBancaGraduacao.getChildNodes();
                        
                        for( int i=0; i < nListDadosBasicos.getLength(); i++ )
                        {
                            Node nNodeDadosBasicos = nListDadosBasicos.item(i);
                            
                            if( nNodeDadosBasicos instanceof Element )
                            {
                                Element eElement = (Element)nNodeDadosBasicos;
                                
                                if( eElement.getNodeName().equals("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-GRADUACAO") )
                                {
                                    String ano_participacao_banca = eElement.getAttribute("ANO" );
                                    
                                    if( verificaDataPublicacao( ano_inicial, ano_final, ano_participacao_banca ) )
                                    {
                                        peb = new ParticipacaoEmBanca( "GRADUACAO" );
                                        p.adicionaParticipacaoEmBancaNoCurriculo( peb );
                                    }
                                }
                            }
                        }
                        
                    }   /*    fim if    */
       }    /*    fim for    */     
    }
    
    
    public static void parseOrientacoes( Document doc, Professor p, String ano_inicial, String ano_final )
                                                    throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
       parseOrientacoesAndamentoGraduacao( doc, p, ano_inicial, ano_final );
       parseOrientacoesAndamentoMestrado( doc, p, ano_inicial, ano_final );
       parseOrientacoesAndamentoDoutorado( doc, p, ano_inicial, ano_final );
       parseOrientacoesConcluidasGraduacao( doc, p, ano_inicial, ano_final );
       parseOrientacoesConcluidasMestrado( doc, p, ano_inicial, ano_final );
       parseOrientacoesConcluidasDoutorado( doc, p, ano_inicial, ano_final );
    }
    
    
    public static void parseOrientacoesConcluidasGraduacao( Document doc, Professor p, String ano_inicial, String ano_final )
                                                    throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
        NodeList nList = doc.getElementsByTagName("OUTRAS-ORIENTACOES-CONCLUIDAS");
        Orientacao o;
       
       for( int n=0; n < nList.getLength(); n++ )
       {
            Node nNodeOrientacaoGraduacao = nList.item(n);
            
            if( nNodeOrientacaoGraduacao instanceof Element )
                    {
                        Element eElement = (Element)nNodeOrientacaoGraduacao;
                        NodeList nListDadosBasicos = nNodeOrientacaoGraduacao.getChildNodes();
                        
                        for( int i=0; i < nListDadosBasicos.getLength(); i++ )
                        {
                            Node nNodeDadosBasicos = nListDadosBasicos.item(i);
                            
                            if( nNodeDadosBasicos instanceof Element )
                            {
                                
                                Element eElementDados = (Element)nNodeDadosBasicos;
                                
                                if( eElementDados.getNodeName().equals( "DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS" ) )
                                {
                                    String natureza = eElementDados.getAttribute("NATUREZA" );
                                    String ano_participacao_banca = eElementDados.getAttribute( "ANO" );
                                    
                                    if( !natureza.equals( "TRABALHO_DE_CONCLUSAO_DE_CURSO_GRADUACAO" ) )
                                        break;
                                    
                                    if( !verificaDataPublicacao( ano_inicial, ano_final, ano_participacao_banca ) )
                                        break;
                                    
                                    boolean concluida = true;
                                    o = new Orientacao( concluida, "GRADUACAO" );
                                    p.adicionaOrientacaoNoCurriculo( o );
                                    continue;
                                }
                            }
                        }   /*    fim for    */ 
                    }   /*    fim if    */
        }    /*    fim for    */     
    }
    
    public static void parseOrientacoesConcluidasMestrado( Document doc, Professor p, String ano_inicial, String ano_final )
                                                    throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
        NodeList nList = doc.getElementsByTagName("ORIENTACOES-CONCLUIDAS-PARA-MESTRADO");
        Orientacao o;
       
       for( int n=0; n < nList.getLength(); n++ )
       {
            Node nNodeOrientacaoMestrado = nList.item(n);
            
            if( nNodeOrientacaoMestrado instanceof Element )
                    {
                        Element eElement = (Element)nNodeOrientacaoMestrado;
                        NodeList nListDadosBasicos = nNodeOrientacaoMestrado.getChildNodes();
                        
                        for( int i=0; i < nListDadosBasicos.getLength(); i++ )
                        {
                            Node nNodeDadosBasicos = nListDadosBasicos.item(i);
                            
                            if( nNodeDadosBasicos instanceof Element )
                            {
                                
                                Element eElementDados = (Element)nNodeDadosBasicos;
                                
                                if( eElementDados.getNodeName().equals( "DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO" ) )
                                {
                                    String ano_participacao_banca = eElementDados.getAttribute( "ANO" );
                                    
                                    if( !verificaDataPublicacao( ano_inicial, ano_final, ano_participacao_banca ) )
                                        break;
                                    
                                    boolean concluida = true;
                                    o = new Orientacao( concluida, "MESTRADO" );
                                    p.adicionaOrientacaoNoCurriculo( o );
                                    continue;
                                }
                            }
                        }   /*    fim for    */ 
                    }   /*    fim if    */
        }    /*    fim for    */     
    }
    
    
       public static void parseOrientacoesConcluidasDoutorado( Document doc, Professor p, String ano_inicial, String ano_final )
                                                    throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
        NodeList nList = doc.getElementsByTagName("ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO");
        Orientacao o;
       
       for( int n=0; n < nList.getLength(); n++ )
       {
            Node nNodeOrientacaoDoutorado = nList.item(n);
            
            if( nNodeOrientacaoDoutorado instanceof Element )
                    {
                        Element eElement = (Element)nNodeOrientacaoDoutorado;
                        NodeList nListDadosBasicos = nNodeOrientacaoDoutorado.getChildNodes();
                        
                        for( int i=0; i < nListDadosBasicos.getLength(); i++ )
                        {
                            Node nNodeDadosBasicos = nListDadosBasicos.item(i);
                            
                            if( nNodeDadosBasicos instanceof Element )
                            {
                                
                                Element eElementDados = (Element)nNodeDadosBasicos;
                                
                                if( eElementDados.getNodeName().equals( "DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO" ) )
                                {
                                    String ano_participacao_banca = eElementDados.getAttribute( "ANO" );
                                    
                                    if( !verificaDataPublicacao( ano_inicial, ano_final, ano_participacao_banca ) )
                                        break;
                                    
                                    boolean concluida = true;
                                    o = new Orientacao( concluida, "DOUTORADO" );
                                    p.adicionaOrientacaoNoCurriculo( o );
                                    continue;
                                }
                            }
                        }   /*    fim for    */ 
                    }   /*    fim if    */
        }    /*    fim for    */     
    } 
    
    public static void parseOrientacoesAndamentoGraduacao( Document doc, Professor p, String ano_inicial, String ano_final )
                                                    throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
        NodeList nList = doc.getElementsByTagName("ORIENTACAO-EM-ANDAMENTO-DE-GRADUACAO");
        Orientacao o;
       
       for( int n=0; n < nList.getLength(); n++ )
       {
            Node nNodeOrientacaoGraduacao = nList.item(n);
            
            if( nNodeOrientacaoGraduacao instanceof Element )
                    {
                        Element eElement = (Element)nNodeOrientacaoGraduacao;
                        NodeList nListDadosBasicos = nNodeOrientacaoGraduacao.getChildNodes();
                        
                        for( int i=0; i < nListDadosBasicos.getLength(); i++ )
                        {
                            Node nNodeDadosBasicos = nListDadosBasicos.item(i);
                            
                            if( nNodeDadosBasicos instanceof Element )
                            {
                                Element eElementDados = (Element)nNodeDadosBasicos;
                                
                                if( eElementDados.getNodeName().equals( "DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-GRADUACAO" ) )
                                {
                                    String ano_participacao_banca = eElementDados.getAttribute( "ANO" );
                                    
                                    if( !verificaDataPublicacao( ano_inicial, ano_final, ano_participacao_banca ) )
                                        break;
                                    
                                    boolean concluida = false;
                                    o = new Orientacao( concluida, "GRADUACAO" );
                                    p.adicionaOrientacaoNoCurriculo( o );
                                    
                                    continue;
                                }
                            }
                        }   /*    fim for    */ 
                    }   /*    fim if    */
        }    /*    fim for    */     
    } 
    
    public static void parseOrientacoesAndamentoMestrado( Document doc, Professor p, String ano_inicial, String ano_final )
                                                    throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
        NodeList nList = doc.getElementsByTagName("ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO");
        Orientacao o;
       
       for( int n=0; n < nList.getLength(); n++ )
       {
            Node nNodeOrientacaoMestrado = nList.item(n);
            
            if( nNodeOrientacaoMestrado instanceof Element )
                    {
                        Element eElement = (Element)nNodeOrientacaoMestrado;
                        NodeList nListDadosBasicos = nNodeOrientacaoMestrado.getChildNodes();
                        
                        for( int i=0; i < nListDadosBasicos.getLength(); i++ )
                        {
                            Node nNodeDadosBasicos = nListDadosBasicos.item(i);
                            
                            if( nNodeDadosBasicos instanceof Element )
                            {
                                Element eElementDados = (Element)nNodeDadosBasicos;
                                
                                if( eElementDados.getNodeName().equals( "DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO" ) )
                                {
                                    String ano_participacao_banca = eElementDados.getAttribute( "ANO" );
                                    
                                    if( !verificaDataPublicacao( ano_inicial, ano_final, ano_participacao_banca ) )
                                        break;
                                    
                                    boolean concluida = false;
                                    o = new Orientacao( concluida, "MESTRADO" );
                                    p.adicionaOrientacaoNoCurriculo( o );
                                    
                                    continue;
                                }
                            }
                        }   /*    fim for    */ 
                    }   /*    fim if    */
        }    /*    fim for    */     
    } 
    
    
    public static void parseOrientacoesAndamentoDoutorado( Document doc, Professor p, String ano_inicial, String ano_final )
                                                    throws IOException, 
                                                       MalformedURLException, 
                                                       ParserConfigurationException, 
                                                       SAXException 
    {
        NodeList nList = doc.getElementsByTagName("ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO");
        Orientacao o;
       
       for( int n=0; n < nList.getLength(); n++ )
       {
            Node nNodeOrientacaoMestrado = nList.item(n);
            
            if( nNodeOrientacaoMestrado instanceof Element )
                    {
                        Element eElement = (Element)nNodeOrientacaoMestrado;
                        NodeList nListDadosBasicos = nNodeOrientacaoMestrado.getChildNodes();
                        
                        for( int i=0; i < nListDadosBasicos.getLength(); i++ )
                        {
                            Node nNodeDadosBasicos = nListDadosBasicos.item(i);
                            
                            if( nNodeDadosBasicos instanceof Element )
                            {
                                Element eElementDados = (Element)nNodeDadosBasicos;
                                
                                if( eElementDados.getNodeName().equals( "DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO" ) )
                                {
                                    String ano_participacao_banca = eElementDados.getAttribute( "ANO" );
                                    
                                    if( !verificaDataPublicacao( ano_inicial, ano_final, ano_participacao_banca ) )
                                        break;
                                    
                                    boolean concluida = false;
                                    o = new Orientacao( concluida, "DOUTORADO" );
                                    p.adicionaOrientacaoNoCurriculo( o );
                                    
                                    continue;
                                }
                            }
                        }   /*    fim for    */ 
                    }   /*    fim if    */
        }    /*    fim for    */     
    } 
    
    /**
     * 
     * @param caminho_arquivo_xml
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
   
    
    public static Document getDocumentXml( InputStream arquivo_memoria_xml ) 
                                            throws ParserConfigurationException, 
                                                   SAXException, 
                                                   IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse( arquivo_memoria_xml );
        doc.getDocumentElement().normalize();
        
        return doc;
    }
    
    /**
     * 
     * @param data_inicial  periodo inicial fornecido pelo usuario
     * @param data_final    periodo final fornecido pelo usuario    
     * @param data_artigo   data do artigo
     * @return 
     */
    private static boolean verificaDataPublicacao( String ano_inicial, String ano_final, String ano_artigo )
    {
        int ano_inicial_int, ano_final_int, ano_artigo_int;
        
        ano_inicial_int = Integer.parseInt( ano_inicial );
        ano_final_int = Integer.parseInt( ano_final );
        ano_artigo_int = Integer.parseInt( ano_artigo );
        //System.out.println( "Ano: ano_artigo" );
        return ( ( ano_artigo_int >= ano_inicial_int ) && ( ano_artigo_int <= ano_final_int ) );
    }
}

