/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 *
 * @author gabriel
 */
public class CriarArquivosDeProfessores
                        
{
    public static void escreverArquivoFinal( List<Professor> listaProfessores, String nome_programa ) 
                                    throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter writer = new PrintWriter("./RelatorioFinal/" + nome_programa + ".txt", "UTF-8");
        String linha_de_pesquisa_anterior = "VAZIO";
        TotalProfessorLinhaDePesquisa totalPLP = new TotalProfessorLinhaDePesquisa();
        TotalProfessorPrograma totalPP = new TotalProfessorPrograma();
        
        for( Professor p : listaProfessores )
        {
            if( linha_de_pesquisa_anterior.equals( "VAZIO" ) )
            {
                linha_de_pesquisa_anterior = p.getLinhaPesquisa();
                writer.println( p.getLinhaProfessorArquivo());
                totalPLP.adicionaProfessorTotal( p );
                totalPP.adicionaProfessorTotal( p );
            }
            else if( linha_de_pesquisa_anterior.equals( p.getLinhaPesquisa() ) )
            {
                writer.println( p.getLinhaProfessorArquivo());
                totalPLP.adicionaProfessorTotal( p );
                totalPP.adicionaProfessorTotal( p );
            }
            else
            {
                writer.println( "Total da linha de pesquisa " + linha_de_pesquisa_anterior + "\t" + totalPLP.getLinhaDePesquisaTotal() );
                linha_de_pesquisa_anterior = p.getLinhaPesquisa();
                totalPLP = new TotalProfessorLinhaDePesquisa();
                
                writer.println( p.getLinhaProfessorArquivo());
                totalPLP.adicionaProfessorTotal( p );
                totalPP.adicionaProfessorTotal( p );
            }
        }
        
        writer.println( "Total da linha de pesquisa " + linha_de_pesquisa_anterior + "\t" + totalPLP.getLinhaDePesquisaTotal() );
        writer.println( "\nTotal do Programa " + totalPP.getNome_programa().toUpperCase() +  "\t" + totalPP.getProgramaTotal() );
        writer.close(); 
    }
    
    public static void escreverArquivoFinalBonitinho( List<Professor> listaProfessores, String nome_programa ) 
                                    throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter writer = new PrintWriter("./RelatorioFinal/" + nome_programa + "_legivel.txt", "UTF-8");
        String linha_de_pesquisa_anterior = "VAZIO";
        TotalProfessorLinhaDePesquisa totalPLP = new TotalProfessorLinhaDePesquisa();
        TotalProfessorPrograma totalPP = new TotalProfessorPrograma();
        totalPP.setNome_programa( nome_programa );
        
        for( Professor p : listaProfessores )
        {
            if( linha_de_pesquisa_anterior.equals( "VAZIO" ) )
            {
                linha_de_pesquisa_anterior = p.getLinhaPesquisa();
                writer.println( p.getLinhaProfessorArquivoBonitinho());
                totalPLP.adicionaProfessorTotal( p );
                totalPP.adicionaProfessorTotal( p );
            }
            else if( linha_de_pesquisa_anterior.equals( p.getLinhaPesquisa() ) )
            {
                writer.println( p.getLinhaProfessorArquivoBonitinho());
                totalPLP.adicionaProfessorTotal( p );
                totalPP.adicionaProfessorTotal( p );
            }
            else
            {
                writer.println( "\nTotal da linha de pesquisa " + linha_de_pesquisa_anterior + "\t" + totalPLP.getLinhaDePesquisaTotalBonitinho() + "\n\n" );
                linha_de_pesquisa_anterior = p.getLinhaPesquisa();
                totalPLP = new TotalProfessorLinhaDePesquisa();
               
                writer.println( p.getLinhaProfessorArquivoBonitinho());
                totalPLP.adicionaProfessorTotal( p );
                totalPP.adicionaProfessorTotal( p );
            }
        }
        
        writer.println( "\nTotal da linha de pesquisa " + linha_de_pesquisa_anterior + "\t" + totalPLP.getLinhaDePesquisaTotalBonitinho());
        writer.println( "\nTotal do Programa " + totalPP.getNome_programa().toUpperCase() +  "\t" + totalPP.getProgramaTotalBonitinho());
        writer.close(); 
    }
    
}
