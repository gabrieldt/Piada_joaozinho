/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpm2016;

/**
 *
 * @author gabriel
 */
public class Artigo
{
    /*  publicacao valores:( REVISTAS | EVENTOS )   */
    private String publicacao;
    
    /*  classificacao valores:( A1 | A2 | B1 | B2 | B3 | B4 | C | NC )    */
    private String classificacao;

    public Artigo(String publicacao, String classificacao)
    {
        if( publicacao.equals("REVISTAS") || publicacao.equals("EVENTOS") )
        {
            this.publicacao = publicacao;
        }
        else
        {
            this.publicacao = "ERRO";
        }
            
        if( classificacao.equals("A1") || classificacao.equals("A2") 
                                       ||classificacao.equals("B1") 
                                       ||classificacao.equals("B2") 
                                       ||classificacao.equals("B3")
                                       ||classificacao.equals("B4")
                                       ||classificacao.equals("C") 
                                       ||classificacao.equals("NC") )
        {
            this.classificacao = classificacao;
        }
        else
        {
            this.classificacao = "ERRO";
        }
        
    }

    public String getPublicacao()
    {
        return publicacao;
    }

    public String getClassificacao()
    {
        return classificacao;
    }

    public void setPublicacao(String publicacao)
    {
        this.publicacao = publicacao;
    }

    public void setClassificacao(String classificacao)
    {
        this.classificacao = classificacao;
    }
    
    
}
