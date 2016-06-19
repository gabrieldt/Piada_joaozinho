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
import trabalhofinalpm2016.TotalProfessorPrograma;
/**
 *
 * @author Gabriel
 */
public class TestTotalProfessorPrograma {
      public TotalProfessorPrograma PrimeiraInstancia = new TotalProfessorPrograma();
    
    
   @Test
        public void testegetLinhaDePesquisaTotal (){
      
             assertEquals(0.0,PrimeiraInstancia.getArtigos_revistas_A1(),0.0);
            
        }
    
}
