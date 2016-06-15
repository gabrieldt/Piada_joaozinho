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
import trabalhofinalpm2016.TotalProfessorLinhaDePesquisa;
/**
 *
 * @author Gabriel
 */


public class TestTotalProfessorLinhaDePesquisa {
    
        public TotalProfessorLinhaDePesquisa PrimeiraInstancia = new TotalProfessorLinhaDePesquisa();
    
    
        @Test
        public void TestgetArtigos_revistas_A2(){
      
             assertEquals(0.0,PrimeiraInstancia.getArtigos_revistas_A2(),0.0);
            
        }
}
