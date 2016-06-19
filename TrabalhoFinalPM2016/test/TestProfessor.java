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
import trabalhofinalpm2016.Professor;
/**
 *
 * @author Gabriel
 */
public class TestProfessor {
    
    
     public Professor PrimeiraInstancia;
    @Test
    
    public void TestNomeProfessor() {
        this.PrimeiraInstancia = new Professor("FULANO", "A321", "IOS");
         assertEquals("FULANO", PrimeiraInstancia.getNome());
    }
    
     @Test
    
    public void TestCOdigoProfessor() {
        this.PrimeiraInstancia = new Professor("FULANO", "A321", "IOS");
         assertEquals("A321", PrimeiraInstancia.getCodigo());
    }
    
     @Test
    
    public void TestLinhaProfessor() {
        this.PrimeiraInstancia = new Professor("FULANO", "A321", "IOS");
         assertEquals("IOS", PrimeiraInstancia.getLinhaPesquisa());
    }
}
