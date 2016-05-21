/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

import java.util.ArrayList;
import java.util.Enumeration;
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
    public int getNumArtigos( String classificacao )
    {
        int num_artigos = 0;
        
        for(Artigo a : this.artigos)
        {
            if( a.getClassificacao().equals(classificacao) )
                num_artigos++;
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

    @Override
    public String toString()
    {
        return "Professor{" + "nome=" + nome + ", codigo=" + codigo + ", linhaPesquisa=" + linhaPesquisa + '}';
    }
    
    
}
