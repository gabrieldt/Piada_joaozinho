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
public class TotalProfessorLinhaDePesquisa
{
    private float artigos_revistas_A1;
    private float artigos_revistas_A2;
    private float artigos_revistas_B1;
    private float artigos_revistas_B2;
    private float artigos_revistas_B3;
    private float artigos_revistas_B4;
    private float artigos_revistas_C;
    private float artigos_revistas_NC;
    
    private float artigos_eventos_A1;
    private float artigos_eventos_A2;
    private float artigos_eventos_B1;
    private float artigos_eventos_B2;
    private float artigos_eventos_B3;
    private float artigos_eventos_B4;
    private float artigos_eventos_C;
    private float artigos_eventos_NC;
    
    private float participacao_banca_doutorado;
    private float participacao_banca_mestrado;
    private float participacao_banca_graduacao;
    
    private float orientacao_concluida_doutorado;
    private float orientacao_concluida_mestrado;
    private float orientacao_concluida_graduacao;
    
    private float orientacao_andamento_doutorado;
    private float orientacao_andamento_mestrado;
    private float orientacao_andamento_graduacao;
    
    private float total_por_linha;
    
    public TotalProfessorLinhaDePesquisa()
    {
        this.artigos_revistas_A1 = 0;
        this.artigos_revistas_A2 = 0;
        this.artigos_revistas_B1 = 0;
        this.artigos_revistas_B2 = 0;
        this.artigos_revistas_B3 = 0;
        this.artigos_revistas_B4 = 0;
        this.artigos_revistas_C = 0;
        this.artigos_revistas_NC = 0;
        
        this.artigos_eventos_A1 = 0;
        this.artigos_eventos_A2 = 0;
        this.artigos_eventos_B1 = 0;
        this.artigos_eventos_B2 = 0;
        this.artigos_eventos_B3 = 0;
        this.artigos_eventos_B4 = 0;
        this.artigos_eventos_C = 0;
        this.artigos_eventos_NC = 0;
        
        this.participacao_banca_doutorado = 0;
        this.participacao_banca_mestrado = 0;
        this.participacao_banca_graduacao = 0;
        
        this.orientacao_concluida_doutorado = 0;
        this.orientacao_concluida_mestrado = 0;
        this.orientacao_concluida_graduacao = 0;
        
        this.orientacao_andamento_doutorado = 0;
        this.orientacao_andamento_mestrado = 0;
        this.orientacao_andamento_graduacao = 0;
        
        this.total_por_linha = 0;
    }

    public float getArtigos_revistas_A1()
    {
        return artigos_revistas_A1;
    }

    public float getArtigos_revistas_A2()
    {
        return artigos_revistas_A2;
    }

    public float getArtigos_revistas_B1()
    {
        return artigos_revistas_B1;
    }

    public float getArtigos_revistas_B2()
    {
        return artigos_revistas_B2;
    }

    public float getArtigos_revistas_B3()
    {
        return artigos_revistas_B3;
    }

    public float getArtigos_revistas_B4()
    {
        return artigos_revistas_B4;
    }

    public float getArtigos_revistas_C()
    {
        return artigos_revistas_C;
    }

    public float getArtigos_revistas_NC()
    {
        return artigos_revistas_NC;
    }

    public float getArtigos_eventos_A1()
    {
        return artigos_eventos_A1;
    }

    public float getArtigos_eventos_A2()
    {
        return artigos_eventos_A2;
    }

    public float getArtigos_eventos_B1()
    {
        return artigos_eventos_B1;
    }

    public float getArtigos_eventos_B2()
    {
        return artigos_eventos_B2;
    }

    public float getArtigos_eventos_B3()
    {
        return artigos_eventos_B3;
    }

    public float getArtigos_eventos_B4()
    {
        return artigos_eventos_B4;
    }

    public float getArtigos_eventos_C()
    {
        return artigos_eventos_C;
    }

    public float getArtigos_eventos_NC()
    {
        return artigos_eventos_NC;
    }
    
    public float getParticipacao_banca_doutorado()
    {
        return participacao_banca_doutorado;
    }

    public float getParticipacao_banca_mestrado()
    {
        return participacao_banca_mestrado;
    }

    public float getParticipacao_banca_graduacao()
    {
        return participacao_banca_graduacao;
    }

    public float getOrientacao_concluida_doutorado()
    {
        return orientacao_concluida_doutorado;
    }

    public float getOrientacao_concluida_mestrado()
    {
        return orientacao_concluida_mestrado;
    }

    public float getOrientacao_concluida_graduacao()
    {
        return orientacao_concluida_graduacao;
    }

    public float getOrientacao_andamento_doutorado()
    {
        return orientacao_andamento_doutorado;
    }

    public float getOrientacao_andamento_mestrado()
    {
        return orientacao_andamento_mestrado;
    }

    public float getOrientacao_andamento_graduacao()
    {
        return orientacao_andamento_graduacao;
    }

    public float getTotal_por_linha()
    {
        return total_por_linha;
    }
    
    
    public void adicionaProfessorTotal( Professor p )
    {
        this.somaArtigosRevistasA1( p.getNumArtigosPublicados( "A1" ) );
        this.somaArtigosRevistasA2( p.getNumArtigosPublicados( "A2" ) );
        this.somaArtigosRevistasB1( p.getNumArtigosPublicados( "B1" ) );
        this.somaArtigosRevistasB2( p.getNumArtigosPublicados( "B2" ) );
        this.somaArtigosRevistasB3( p.getNumArtigosPublicados( "B3" ) );
        this.somaArtigosRevistasB4( p.getNumArtigosPublicados( "B4" ) );
        this.somaArtigosRevistasC( p.getNumArtigosPublicados( "C" ) );
        this.somaArtigosRevistasNC( p.getNumArtigosPublicados( "NC" ) );
        
        this.somaArtigosEventosA1( p.getNumArtigosEventos( "A1" ) );
        this.somaArtigosEventosA2( p.getNumArtigosEventos( "A2" ) );
        this.somaArtigosEventosB1( p.getNumArtigosEventos( "B1" ) );
        this.somaArtigosEventosB2( p.getNumArtigosEventos( "B2" ) );
        this.somaArtigosEventosB3( p.getNumArtigosEventos( "B3" ) );
        this.somaArtigosEventosB4( p.getNumArtigosEventos( "B4" ) );
        this.somaArtigosEventosC( p.getNumArtigosEventos( "C" ) );
        this.somaArtigosEventosNC( p.getNumArtigosEventos( "NC" ) );
        
        this.somaOrientacaoAndamentoDoutorado( p.getNumOrientacoes( false, "DOUTORADO" ) );
        this.somaOrientacaoAndamentoMestrado( p.getNumOrientacoes( false, "MESTRADO" ) );
        this.somaOrientacaoAndamentoGraduacao( p.getNumOrientacoes( false, "GRADUACAO" ) );
        
        this.somaOrientacaoConcluidaDoutorado( p.getNumOrientacoes( true, "DOUTORADO" ) );
        this.somaOrientacaoConcluidaMestrado(p.getNumOrientacoes( true, "MESTRADO" ) );
        this.somaOrientacaoConcluidaGraduacao(p.getNumOrientacoes( true, "GRADUACAO" ) );
        
        this.somaParticipacaoBancaDoutorado( p.getNumParticipacaoBancas( "DOUTORADO" ) );
        this.somaParticipacaoBancaMestrado(p.getNumParticipacaoBancas( "MESTRADO" ) );
        this.somaParticipacaoBancaGraduacao(p.getNumParticipacaoBancas( "GRADUACAO" ) );
        
        this.total_por_linha++;
    }
    
    /*      SOMATORIOS      */
    
    public void somaArtigosRevistasA1( float ara1 )
    {
        this.artigos_revistas_A1 += ara1;
    }
    public void somaArtigosRevistasA2( float ara2 )
    {
        this.artigos_revistas_A2 += ara2;
    }
    
    public void somaArtigosRevistasB1( float arb1 )
    {
        this.artigos_revistas_B1 += arb1;
    }
    
    public void somaArtigosRevistasB2( float arb2 )
    {
        this.artigos_revistas_B2 += arb2;
    }
    
    public void somaArtigosRevistasB3( float arb3 )
    {
        this.artigos_revistas_B3 += arb3;
    }
    
    public void somaArtigosRevistasB4( float arb4 )
    {
        this.artigos_revistas_B4 += arb4;
    }
    
    public void somaArtigosRevistasC( float arc )
    {
        this.artigos_revistas_C += arc;
    }
    
    public void somaArtigosRevistasNC( float arnc )
    {
        this.artigos_revistas_NC += arnc;
    }
    
    public void somaArtigosEventosA1( float aea1 )
    {
        this.artigos_eventos_A1 += aea1;
    }
    
    public void somaArtigosEventosA2( float aea2 )
    {
        this.artigos_eventos_A2 += aea2;
    }
    
    public void somaArtigosEventosB1( float aeb1 )
    {
        this.artigos_eventos_B1 += aeb1;
    }
    
    public void somaArtigosEventosB2( float aeb2 )
    {
        this.artigos_eventos_B2 += aeb2;
    }
    
    public void somaArtigosEventosB3( float aeb3 )
    {
        this.artigos_eventos_B3 += aeb3;
    }
    
    public void somaArtigosEventosB4( float aeb4 )
    {
        this.artigos_eventos_B4 += aeb4;
    }
     
    public void somaArtigosEventosC( float aec )
    {
        this.artigos_eventos_C += aec;
    }
    
    public void somaArtigosEventosNC( float aenc )
    {
        this.artigos_eventos_NC += aenc;
    }
    
    public void somaParticipacaoBancaDoutorado( float pbd )
    {
        this.participacao_banca_doutorado += pbd;
    }
    
    public void somaParticipacaoBancaMestrado( float pbm )
    {
        this.participacao_banca_mestrado += pbm;
    }
    
    public void somaParticipacaoBancaGraduacao( float pbg )
    {
        this.participacao_banca_graduacao += pbg;
    }
    
    public void somaOrientacaoConcluidaDoutorado( float ocd )
    {
        this.orientacao_concluida_doutorado += ocd;
    }
    
    public void somaOrientacaoConcluidaMestrado( float ocm )
    {
        this.orientacao_concluida_mestrado += ocm;
    }
    
    public void somaOrientacaoConcluidaGraduacao( float ocg )
    {
        this.orientacao_concluida_graduacao += ocg;
    }
    
    public void somaOrientacaoAndamentoDoutorado( float oad )
    {
        this.orientacao_andamento_doutorado += oad;
    }
    
    public void somaOrientacaoAndamentoMestrado( float oam )
    {
        this.orientacao_andamento_mestrado += oam;
    }
    
    public void somaOrientacaoAndamentoGraduacao( float oag )
    {
        this.orientacao_andamento_graduacao += oag;
    }
    
    /*      MEDIA ARTIGOS EM REVISTAS       */
    
    public float mediaArtigosRevistasA1()
    {
        return this.artigos_revistas_A1 / this.total_por_linha;
    }
    
    public float mediaArtigosRevistasA2()
    {
        return this.artigos_revistas_A2 / this.total_por_linha;
    }
    
    public float mediaArtigosRevistasB1()
    {
        return this.artigos_revistas_B1 / this.total_por_linha;
    }
    
    public float mediaArtigosRevistasB2()
    {
        return this.artigos_revistas_B2 / this.total_por_linha;
    }
    
    public float mediaArtigosRevistasB3()
    {
        return this.artigos_revistas_B3 / this.total_por_linha;
    }
    
    public float mediaArtigosRevistasB4()
    {
        return this.artigos_revistas_B4 / this.total_por_linha;
    }
    
    public float mediaArtigosRevistasC()
    {
        return this.artigos_revistas_C / this.total_por_linha;
    }
    
    public float mediaArtigosRevistasNC()
    {
        return this.artigos_revistas_NC / this.total_por_linha;
    }
    
    /*      MEDIA ARTIGOS EM EVENTOS        */
    
    public float mediaArtigosEventosA1()
    {
        return this.artigos_eventos_A1 / this.total_por_linha;
    }
    
    public float mediaArtigosEventosA2()
    {
        return this.artigos_eventos_A2 / this.total_por_linha;
    }
    
    public float mediaArtigosEventosB1()
    {
        return this.artigos_eventos_B1 / this.total_por_linha;
    }
    
    public float mediaArtigosEventosB2()
    {
        return this.artigos_eventos_B2 / this.total_por_linha;
    }
    
    public float mediaArtigosEventosB3()
    {
        return this.artigos_eventos_B3 / this.total_por_linha;
    }
    
    public float mediaArtigosEventosB4()
    {
        return this.artigos_eventos_B4 / this.total_por_linha;
    }
    
    public float mediaArtigosEventosC()
    {
        return this.artigos_eventos_C / this.total_por_linha;
    }
    
    public float mediaArtigosEventosNC()
    {
        return this.artigos_eventos_NC / this.total_por_linha;
    }
    
    /*      MEDIA PARTICIPACAO EM BANCA     */
    
    public float mediaParticipacaoBancaDoutorado()
    {
        return this.participacao_banca_doutorado / this.total_por_linha;
    }
    
    public float mediaParticipacaoBancaMestrado()
    {
        return this.participacao_banca_mestrado / this.total_por_linha;
    }
    
    public float mediaParticipacaoBancaGraduacao()
    {
        return this.participacao_banca_graduacao / this.total_por_linha;
    }
    
    /*      MEDIA ORIENTACAO CONCLUIDA      */
    
    public float mediaOrientacaoConcluidaDoutorado()
    {
        return this.orientacao_concluida_doutorado / this.total_por_linha;
    }
    
    public float mediaOrientacaoConcluidaMestrado()
    {
        return this.orientacao_concluida_mestrado / this.total_por_linha;
    }
    
    public float mediaOrientacaoConcluidaGraduacao()
    {
        return this.orientacao_concluida_graduacao / this.total_por_linha;
    }
    
    /*      MEDIA ORIENTACAO EM ANDAMENTO       */
    
    public float mediaOrientacaoAndamentoDoutorado()
    {
        return this.orientacao_andamento_doutorado / this.total_por_linha;
    }
    
    public float mediaOrientacaoAndamentoMestrado()
    {
        return this.orientacao_andamento_doutorado / this.total_por_linha;
    }
    
    public float mediaOrientacaoAndamentoGraduacao()
    {
        return this.orientacao_andamento_graduacao / this.total_por_linha;
    }
    
    
    public String getLinhaDePesquisaTotal()
    {
        return  String.format( "%.1f", this.mediaArtigosRevistasA1() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosRevistasA2() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosRevistasB1() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosRevistasB2() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosRevistasB3() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosRevistasB4() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosRevistasC() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosRevistasNC() ) + "\t" + 
                
                String.format( "%.1f", this.mediaArtigosEventosA1() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosEventosA2() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosEventosB1() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosEventosB2() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosEventosB3() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosEventosB4() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosEventosC() ) + "\t" + 
                String.format( "%.1f", this.mediaArtigosEventosNC() ) + "\t" + 
                
                String.format( "%.1f", this.mediaParticipacaoBancaDoutorado() ) + "\t" + 
                String.format( "%.1f", this.mediaParticipacaoBancaMestrado() ) + "\t" +
                String.format( "%.1f", this.mediaParticipacaoBancaGraduacao() ) + "\t" +
                
                String.format( "%.1f", this.mediaOrientacaoConcluidaDoutorado() ) + "\t" +
                String.format( "%.1f", this.mediaOrientacaoConcluidaMestrado() ) + "\t" +
                String.format( "%.1f", this.mediaOrientacaoConcluidaGraduacao() ) + "\t" +
                
                String.format( "%.1f", this.mediaOrientacaoAndamentoDoutorado() ) + "\t" +
                String.format( "%.1f", this.mediaOrientacaoAndamentoMestrado() ) + "\t" +
                String.format( "%.1f", this.mediaOrientacaoAndamentoGraduacao() ) + "\t" ;
    }
    
    public String getLinhaDePesquisaTotalBonitinho()
    {
        return  "\nArtigos em Revistas: \t\t" + 
                "A1: " + String.format( "%.1f", this.mediaArtigosRevistasA1() ) + "\t" + 
                "A2: " + String.format( "%.1f", this.mediaArtigosRevistasA2() ) + "\t" + 
                "B1: " + String.format( "%.1f", this.mediaArtigosRevistasB1() ) + "\t" + 
                "B2: " + String.format( "%.1f", this.mediaArtigosRevistasB2() ) + "\t" + 
                "B3: " + String.format( "%.1f", this.mediaArtigosRevistasB3() ) + "\t" + 
                "B4: " + String.format( "%.1f", this.mediaArtigosRevistasB4() ) + "\t" + 
                "C: " + String.format( "%.1f", this.mediaArtigosRevistasC() ) + "\t" + 
                "NC: " + String.format( "%.1f", this.mediaArtigosRevistasNC() ) + "\t" + 
                
                "\nArtigos em Eventos: \t\t" + 
                "A1: " + String.format( "%.1f", this.mediaArtigosEventosA1() ) + "\t" + 
                "A2: " + String.format( "%.1f", this.mediaArtigosEventosA2() ) + "\t" + 
                "B1: " + String.format( "%.1f", this.mediaArtigosEventosB1() ) + "\t" + 
                "B2: " + String.format( "%.1f", this.mediaArtigosEventosB2() ) + "\t" + 
                "B3: " + String.format( "%.1f", this.mediaArtigosEventosB3() ) + "\t" + 
                "B4: " + String.format( "%.1f", this.mediaArtigosEventosB4() ) + "\t" + 
                "C: " + String.format( "%.1f", this.mediaArtigosEventosC() ) + "\t" + 
                "NC: " + String.format( "%.1f", this.mediaArtigosEventosNC() ) + "\t" + 
                
                "\nParticipacao em Banca: \t\t" + 
                "Doutorado: " + String.format( "%.1f", this.mediaParticipacaoBancaDoutorado() ) + "\t" + 
                "Mestrado: " + String.format( "%.1f", this.mediaParticipacaoBancaMestrado() ) + "\t" +
                "Graduacao: " + String.format( "%.1f", this.mediaParticipacaoBancaGraduacao() ) + "\t" +
                
                "\nOrientacao  Concluida: \t\t" + 
                "Doutorado: " + String.format( "%.1f", this.mediaOrientacaoConcluidaDoutorado() ) + "\t" +
                "Mestrado: " + String.format( "%.1f", this.mediaOrientacaoConcluidaMestrado() ) + "\t" +
                "Graduacao: " + String.format( "%.1f", this.mediaOrientacaoConcluidaGraduacao() ) + "\t" +
                
                "\nOrientacao em Andamento: \t" + 
                "Doutorado: " + String.format( "%.1f", this.mediaOrientacaoAndamentoDoutorado() ) + "\t" +
                "Mestrado: " + String.format( "%.1f", this.mediaOrientacaoAndamentoMestrado() ) + "\t" +
                "Graduacao: " + String.format( "%.1f", this.mediaOrientacaoAndamentoGraduacao() ) + "\t\n";
    }
}
