/* UTF-8 encoding: Příliš žluťoučký kůň úpěl ďábelské ódy. */

/************************************************************
 * Instance třídy {@code Rozmer} představují ...
 *
 * @author  jméno autora
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Rozmer {
  //== KONSTANTNÍ ATRIBUTY TŘÍDY ===========================
  //== PROMĚNNÉ ATRIBUTY TŘÍDY =============================
  //== STATICKÝ INICIALIZAČNÍ BLOK =========================
  //== KONSTANTNÍ ATRIBUTY INSTANCÍ ========================
  /** Konstantni promenna sirky */
  public final int sirka;
  
  /** Konstantni promenna vysky */
  public final int vyska;
  
  //== PROMĚNNÉ ATRIBUTY INSTANCÍ ==========================
  //########################################################
  //== KONSTRUKTORY A TOVÁRNÍ METODY =======================

  /**
   */
  public Rozmer(int sirka, int vyska) {
    this.sirka = sirka;
    this.vyska = vyska;
  }

  //########################################################
  //== PUBLIC METODY TŘÍDY =================================
  /** Funkce vracejici sirku */
  public int getSirka() {
    return sirka;
  }
  
  /** Funkce vracejici vysku */ 
  public int getVyska(){
    return vyska;
  }
  
  /** Funkce vracejici tridu toString */
  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "[sirka=" + this.getSirka() + ", vyska=" + this.getVyska() + "]";
  }
  
  
  //== PRIVATE METODY TŘÍDY ================================
  //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =============== 
  //== PUBLIC METODY INSTANCÍ ==============================
  //== PRIVATE METODY INSTANCÍ =============================
}
