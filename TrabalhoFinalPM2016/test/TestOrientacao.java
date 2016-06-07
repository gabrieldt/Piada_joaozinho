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
import trabalhofinalpm2016.Orientacao;

/**
 *
 * @author Gabriel
 */
public class TestOrientacao {
    
        public Orientacao PrimeiraInstancia = new Orientacao(true, "DOUTORADO");
    
        @Test
        public void TestIsConcluida(){
            assertTrue(PrimeiraInstancia.isConcluida());
            
        }
    
        @Test
        public void testegetNivel_graduacao (){
             assertEquals("DOUTORADO", PrimeiraInstancia.getNivel_graduacao());
            
        }
}
