/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import trabalhofinalpm2016.ParticipacaoEmBanca;

/**
 *
 * @author Gabriel
 */
public class TestParticipacaoEmBanca {
    
  public ParticipacaoEmBanca PrimeiraInstancia = new ParticipacaoEmBanca("MESTRADO");
  public ParticipacaoEmBanca SegundaInstancia = new ParticipacaoEmBanca("DOUTORADO");
  public ParticipacaoEmBanca TerceiraInstancia = new ParticipacaoEmBanca("GRADUACAO");
  
   @Test
    public void TestgetNivel_graduacaoMestrado(){
        assertEquals("MESTRADO", PrimeiraInstancia.getNivel_graduacao());
    }
    
     @Test
    public void TestgetNivel_graduacaoDoutorado(){
        assertEquals("DOUTORADO", SegundaInstancia.getNivel_graduacao());
    }
    
     @Test
    public void TestgetNivel_graduacaoGraduacao(){
        assertEquals("GRADUACAO", TerceiraInstancia.getNivel_graduacao());
    }
  
}
