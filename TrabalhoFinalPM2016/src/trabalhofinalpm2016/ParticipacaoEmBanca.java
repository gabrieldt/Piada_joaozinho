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
public class ParticipacaoEmBanca
{
    /*  valores: ( DOUTORADO | MESTRADO | GRADUACAO ) */
    private String nivel_graduacao;

    public ParticipacaoEmBanca(String nivel_graduacao)
    {
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
    
    public String getNivel_graduacao()
    {
        return nivel_graduacao;
    }

    public void setNivel_graduacao(String nivel_graduacao)
    {
        this.nivel_graduacao = nivel_graduacao;
    }
    
    
}
