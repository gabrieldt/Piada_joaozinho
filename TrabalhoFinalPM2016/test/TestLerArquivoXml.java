
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import trabalhofinalpm2016.Artigo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author labccet
 */
public class TestLerArquivoXml {
    
    

    
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
