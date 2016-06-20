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
import trabalhofinalpm2016.Artigo;

/**
 *
 * @author Gabriel
 */
public class TestArtigo {
    
  public Artigo PrimeiraInstancia = new Artigo("REVISTAS","A1");
    
  @Test
  public void testePublicacaoRevista (){
      assertEquals("REVISTAS", PrimeiraInstancia.getPublicacao());

  }
  
  @Test
   public void testePublicacaoClassificacao (){
    assertEquals("A1", PrimeiraInstancia.getClassificacao());
  }
}
