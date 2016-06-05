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
public class Orientacao
{
    /*  se e uma orientacao concluida entao e TRUE  */
    private boolean concluida;
    
    /*  valores: ( DOUTORADO | MESTRADO | GRADUACAO ) */
    private String nivel_graduacao;

    /**
     * 
     * @param concluida TRUE ou FALSE
     * @param nivel_graduacao DOUTORADO | MESTRADO | GRADUACAO
     */
    public Orientacao( boolean concluida, String nivel_graduacao )
    {
        this.concluida = concluida;
        
        if( nivel_graduacao.equals("DOUTORADO") || nivel_graduacao.equals("MESTRADO")
                                                || nivel_graduacao.equals("GRADUACAO") )
        {
            this.nivel_graduacao = nivel_graduacao;
        }
        else
        {
            this.nivel_graduacao = "ERRO";
        }
    }

    public boolean isConcluida()
    {
        return concluida;
    }

    public String getNivel_graduacao()
    {
        return nivel_graduacao;
    }

    public void setConcluida(boolean concluida)
    {
        this.concluida = concluida;
    }

    public void setNivel_graduacao(String nivel_graduacao)
    {
        this.nivel_graduacao = nivel_graduacao;
    }
    
}
