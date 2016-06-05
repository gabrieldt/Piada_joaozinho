/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class Professor
{
    private String nome;
    private String codigo;
    private String linhaPesquisa;
    
    /*  curriculo e uma lista de Artigo, ParticipacaoEmBanca e Orientacao   */
    private List curriculo;
    
    /*  número de artigos em revistas   */
    private List<Artigo> artigos = new ArrayList<Artigo>();
    private List<ParticipacaoEmBanca> participacoes_bancas = new ArrayList<ParticipacaoEmBanca>();
    private List<Orientacao> orientacoes = new ArrayList<Orientacao>();

    
    public Professor(String nome, String codigo, String linhaPesquisa)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.linhaPesquisa = linhaPesquisa;
    }

    public String getCodigo()
    {
        return codigo;
    }
    
    public String getNome()
    {
        return nome;
    }

    public String getLinhaPesquisa()
    {
        return linhaPesquisa;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setLinhaPesquisa(String linhaPesquisa)
    {
        this.linhaPesquisa = linhaPesquisa;
    }
    
    /**
     * 
     * @param publicacao ( REVISTAS | EVENTOS )
     * @param classificacao ( A1 | A2 | B1 | B2 | B3 | B4 | C | NC )
     * 
     */
    public void adicionarArtigo( String publicacao, String classificacao )
    {
        Artigo a = new Artigo( publicacao, classificacao );
        this.artigos.add(a);
    }
    
    /**
     * 
     * @param nivel_graduacao ( DOUTORADO | MESTRADO | GRADUACAO )
     * 
     */
    public void adicionarParticipacaoBanca( String nivel_graduacao )
    {
        ParticipacaoEmBanca peb = new ParticipacaoEmBanca( nivel_graduacao );
        this.participacoes_bancas.add(peb);
    }
    
    /**
     * 
     * @param concluida ( TRUE | FALSE )
     * @param nivel_graduacao ( DOUTORADO | MESTRADO | GRADUACAO )
     * 
     */
    public void adicionarOrientacao( boolean concluida, String nivel_graduacao )
    {
        Orientacao o = new Orientacao( concluida, nivel_graduacao );
        this.orientacoes.add(o);
    }
    
    /**
     * 
     * @param classificacao ( A1 | A2 | B1 | B2 | B3 | B4 | C | NC )
     * @return 
     */
    public int getNumArtigosPublicados( String classificacao )
    {
        int num_artigos = 0;
        
        for(Artigo a : this.artigos)
        {
            if( a.getPublicacao().equals( "REVISTAS" ) )
            {
                if( a.getClassificacao().equals(classificacao) )
                    num_artigos++;
            }
            
        }
        
        return num_artigos;
    }
    
    /**
     * 
     * @param classificacao ( A1 | A2 | B1 | B2 | B3 | B4 | C | NC )
     * @return 
     */
    public int getNumArtigosEventos( String classificacao )
    {
        int num_artigos = 0;
        
        for(Artigo a : this.artigos)
        {
            if( a.getPublicacao().equals( "EVENTOS" ) )
            {
                if( a.getClassificacao().equals(classificacao) )
                    num_artigos++;
            }
        }
        
        return num_artigos;
    }
    
    /**
     * 
     * @param nivel_graduacao ( DOUTORADO | MESTRADO | GRADUACAO )
     * @return 
     */
    public int getNumParticipacaoBancas( String nivel_graduacao )
    {
        int num_participacao_bancas = 0;
        
        for( ParticipacaoEmBanca peb : this.participacoes_bancas )
        {
            if( peb.getNivel_graduacao().equals(nivel_graduacao) )
                num_participacao_bancas++;
        }
        
        return num_participacao_bancas;
    }
    
    /**
     * 
     * @param concluida ( TRUE | FALSE )
     * @param nivel_graduacao ( DOUTORADO | MESTRADO | GRADUACAO )
     * @return 
     */
    public int getNumOrientacoes( boolean concluida, String nivel_graduacao )
    {
        int num_orientacoes = 0;
        
        for( Orientacao o : this.orientacoes )
        {
            if( o.isConcluida() == concluida    &&  o.getNivel_graduacao().equals(nivel_graduacao) )
                num_orientacoes++;
        }
        
        return num_orientacoes;
    }

    public void adicionaArtigoNoCurriculo( Artigo a )
    {
        this.artigos.add(a);
    }
    
    public void adicionaParticipacaoEmBancaNoCurriculo( ParticipacaoEmBanca peb )
    {
        this.participacoes_bancas.add(peb);
    }
    
    public void adicionaOrientacaoNoCurriculo( Orientacao o )
    {
        this.orientacoes.add(o);
    }
    
    public String getLinhaProfessorArquivo()
    {
        return  "Professor(a) " + this.getNome() + "\t" +
                this.getNumArtigosPublicados( "A1" ) + "\t" +
                this.getNumArtigosPublicados( "A2" ) + "\t" +
                this.getNumArtigosPublicados( "B1" ) + "\t" +
                this.getNumArtigosPublicados( "B2" ) + "\t" +
                this.getNumArtigosPublicados( "B3" ) + "\t" +
                this.getNumArtigosPublicados( "B4" ) + "\t" +
                this.getNumArtigosPublicados( "C" ) + "\t" +
                this.getNumArtigosPublicados( "NC" ) + "\t" +
                this.getNumArtigosEventos("A1" ) + "\t" +
                this.getNumArtigosEventos( "A2" ) + "\t" +
                this.getNumArtigosEventos( "B1" ) + "\t" +
                this.getNumArtigosEventos( "B2" ) + "\t" +
                this.getNumArtigosEventos( "B3" ) + "\t" +
                this.getNumArtigosEventos( "B4" ) + "\t" +
                this.getNumArtigosEventos( "C" ) + "\t" +
                this.getNumArtigosEventos( "NC" ) + "\t" +
                this.getNumParticipacaoBancas( "DOUTORADO" ) + "\t" +
                this.getNumParticipacaoBancas( "MESTRADO" ) + "\t" +
                this.getNumParticipacaoBancas( "GRADUACAO" ) + "\t" +
                this.getNumOrientacoes( true, "DOUTORADO" ) + "\t" +
                this.getNumOrientacoes( true, "MESTRADO" ) + "\t" +
                this.getNumOrientacoes( true, "GRADUACAO" ) + "\t" +
                this.getNumOrientacoes( false, "DOUTORADO" ) + "\t" +
                this.getNumOrientacoes( false, "MESTRADO" ) + "\t" +
                this.getNumOrientacoes( false, "GRADUACAO" );
    }
    
     public String getLinhaProfessorArquivoBonitinho()
    {
        return  "Professor(a) " + this.getNome().toUpperCase() + "\t\t" +
                "\nArtigos em Revistas:\t\t" +
                "A1: " + this.getNumArtigosPublicados( "A1" ) + "\t" +
                "A2: " + this.getNumArtigosPublicados( "A2" ) + "\t" +
                "B1: " + this.getNumArtigosPublicados( "B1" ) + "\t" +
                "B2: " + this.getNumArtigosPublicados( "B2" ) + "\t" +
                "B3: " + this.getNumArtigosPublicados( "B3" ) + "\t" +
                "B4: " + this.getNumArtigosPublicados( "B4" ) + "\t" +
                "C: " + this.getNumArtigosPublicados( "C" ) + "\t" +
                "NC: " + this.getNumArtigosPublicados( "NC" ) + "\t" +
                "\nArtigos em Eventos:\t\t" +
                "A1: " + this.getNumArtigosEventos("A1" ) + "\t" +
                "A2: " + this.getNumArtigosEventos( "A2" ) + "\t" +
                "B1: " + this.getNumArtigosEventos( "B1" ) + "\t" +
                "B2: " + this.getNumArtigosEventos( "B2" ) + "\t" +
                "B3: " + this.getNumArtigosEventos( "B3" ) + "\t" +
                "B4: " + this.getNumArtigosEventos( "B4" ) + "\t" +
                "C: " + this.getNumArtigosEventos( "C" ) + "\t" +
                "NC: " + this.getNumArtigosEventos( "NC" ) + "\t" +
                "\nParticipacoes em Bancas:\t" +
                "Doutorado: " + this.getNumParticipacaoBancas( "DOUTORADO" ) + "\t" +
                "Mestrado: " + this.getNumParticipacaoBancas( "MESTRADO" ) + "\t" +
                "Graduacao: " + this.getNumParticipacaoBancas( "GRADUACAO" ) + "\t" +
                "\nOrientacoes Concluidas:\t\t" +
                "Doutorado: " + this.getNumOrientacoes( true, "DOUTORADO" ) + "\t" +
                "Mestrado: " + this.getNumOrientacoes( true, "MESTRADO" ) + "\t" +
                "Graduacao: " + this.getNumOrientacoes( true, "GRADUACAO" ) + "\t" +
                "\nOrientacoes em Andamento:\t" +
                "Doutorado: " + this.getNumOrientacoes( false, "DOUTORADO" ) + "\t" +
                "Mestrado: " + this.getNumOrientacoes( false, "MESTRADO" ) + "\t" +
                "Graduacao: " + this.getNumOrientacoes( false, "GRADUACAO" ) + "\t\n";
    }
    
    @Override
    public String toString()
    {
        return "Professor{" + "nome=" + nome + ", codigo=" + codigo + ", linhaPesquisa=" + linhaPesquisa + '}';
    }
    
    
}
