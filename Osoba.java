  /**
 * Trida {@code Osoba} slouzici k vytvoreni nove osoby
 * podle nadefinovanych hodnot
 *
 * @author Vojtech Jelinek
 * @version 3.00.000
 */
public class Osoba
{
    /**
     * Konstantni promenna barvy hlavy.
     */
    private static final Barva BARVA_HLAVY = Barva.RUZOVA;
    
    /**
     * Konstantni promenna barvy tela, ktera se pouziva v pripade,
     * ze uzivatel nezada zadnou barvu.
     */
    private static final Barva IMPL_BARVA_TELA = Barva.SEDA;
    
    /**
     * Konstatni promenna velikosti hlavy, ktera se pouziva v pripade,
     * ze uzivatel nezada vlastni velikost hlavy.
     */
    private static final int IMPL_VELIKOST_HLAVY = 60;
    
    /**
     * Konstantni promenna pomeru hlavy ku telu, ktera se pouziva
     * v pripade, ze uzivatel nezada vlastni pomer tela ku hlave.
     */
    private static final double POMER_HLAVA_TELO = 6.0/8.0;
    
    /**
     * Konstantni promenna pomeru vysky tela ku sirce tela, ktera
     * se pouzive v pripade, ze uzivatel nezada vlastni pomer vysky
     * ku sirce.
     */
    private static final double POMER_TELO_VYS_SIR = 8.0/7.0;
    
    
    /**
     * Pocet vytvorenych instanci.
     */
    private static int pocet = 0;
    
    
    /** Poradi vytvoreni dane instance v ramci tridy. */
    private final int PORADI = ++pocet;
    
    /** Nazev skladajici se z nazvu tridy + poradi. */
    private String nazev = null;
    
    /** Inicializace promenne hlava. */
    private final Elipsa hlava;
    
    /** Inicializace promenne telo. */
    private final Obdelnik telo;
    
    
    /**
     * Nejslozitejsi konstruktor tridy, ktery vytvori osobu, ve ktere
     * uzivatel nadefinoval vsechny parametry.
     * 
     * @param x                 X-ova souradnice osoby
     * @param y                 Y-ova souradnice osoby
     * @param velikostHlavy     Urceni velikosti hlavy osoby
     * @param pomerHlavaTelo    Urceni pomeru mezi hlavou a telem
     * @param pomerTelo         Urceni pomeru mezi sirkou a vyskou tela
     * @param barvaTela         Urceni barvy tela osoby
     */
    public Osoba(int x, int y, int velikostHlavy, 
    double pomerHlavaTelo, double pomerTelo, Barva barvaTela) {
        double vyskaTela = velikostHlavy/pomerHlavaTelo;
        double sirkaTela = vyskaTela/pomerTelo;
        if(velikostHlavy > sirkaTela) {
            hlava = new Elipsa(x,y,velikostHlavy,velikostHlavy,BARVA_HLAVY);
            telo = new Obdelnik(x+((int)sirkaTela/2),y+velikostHlavy,(int)sirkaTela,(int)vyskaTela,barvaTela);
        } else {
            hlava = new Elipsa(x+((int)sirkaTela/2 - velikostHlavy/2),y,velikostHlavy,velikostHlavy,BARVA_HLAVY);
            telo = new Obdelnik(x,y+velikostHlavy,(int)sirkaTela,(int)vyskaTela,barvaTela);
        }
    }
    
    
    /**
     * Konstruktor, ktery vytvori novou instanci tridy, kde
     * uzivatel definuje pouze souradnice osoby, velikost hlavy a 
     * barvu tela.
     */
    public Osoba(int x, int y, int velikostHlavy, Barva barvaTela) {
        this(x,y,velikostHlavy,POMER_HLAVA_TELO,POMER_TELO_VYS_SIR,barvaTela);
    }
    
    /**
     * Konstruktor, ktery vytvori novou instanci tridy, kde
     * uzivatel definuje pouze souradnice a barvu tela.
     */
    public Osoba(int x, int y, Barva barvaTela) {
        this(x,y,IMPL_VELIKOST_HLAVY,POMER_HLAVA_TELO,POMER_TELO_VYS_SIR,barvaTela);
    }
    
    /**
     * Konstruktor, ktery vytvori novou instanci tridy na pozici 0,0.
     * Tato trida ma vsechny parametry nastaveny implicitne
     */
    public Osoba() {
        this(0,0,IMPL_VELIKOST_HLAVY,POMER_HLAVA_TELO,POMER_TELO_VYS_SIR,IMPL_BARVA_TELA);
    }
    
    /** Konstruktor, ktery vyuziva prepravky Pozice a prislusneho dalsiho konstruktoru */
    public Osoba(Pozice pozice, Barva barvaTela) {
      this(pozice.getX(), pozice.getY(), barvaTela);
    }
    
    /** Konstruktor, ktery vyuziva prepravky Pozice a prislusneho dalsiho konstruktoru */
    public Osoba(Pozice pozice, int velikostHlavy, Barva barvaTela) {
      this(pozice.getX(), pozice.getY(), velikostHlavy, barvaTela);
    }
    
    /** Konstruktor, ktery vyuziva prepravky Pozice a prislusneho dalsiho konstruktoru */
    public Osoba(Pozice pozice, int velikostHlavy, double pomerHlavaTelo, double pomerTelo, Barva barvaTela) {
      this(pozice.getX(), pozice.getY(), velikostHlavy, pomerHlavaTelo, pomerTelo, barvaTela);
    }
    
    /** Konstruktor, ktery vyuziva vyctoveho listu pohlavi */
    public Osoba(Pozice pozice, Pohlavi pohlavi) {
      this(pozice.getX(), pozice.getY(), IMPL_BARVA_TELA);
      
      if(pohlavi == Pohlavi.MUZ) {
        setBarvaTela(Barva.MODRA);
      } else {
        setBarvaTela(Barva.CERVENA);
      }
    }
    
    /** Metoda vracejici barvu tela */
    public Barva getBarvaTela() { return telo.getBarva(); }
    
    /** 
     * Metoda nastavujici barvu tela.
     * 
     * @param barva Vybrani barvy k nastaveni
     */
    public void setBarvaTela(Barva barva) { telo.setBarva(barva); }
    
    /** Metoda vracejici nazev osoby */
    public String getNazev() {
      nazev = this.getClass().getSimpleName() + "_" + PORADI;
      return nazev;
    }
    
    /** Metoda vracejici promenou hlava */
    public Elipsa getHlava() { return hlava; }
    
    /** Metoda vracejici promenou telo */
    public Obdelnik getTelo() { return telo; }
    
    /** Metoda vracejici X-ovou souradnici ohranicujiciho obdelnika */
    public int getX() { return Math.min(hlava.getX(), telo.getX()); }
    
    /** Metoda vracejici Y-ovou souradnici ohranicujiciho obdelnika */
    public int getY() { return Math.min(hlava.getY(), telo.getY()); }
    
    /** Metoda vracejici celkovou vysky osoby */ 
    public int getVyska() { return telo.getVyska() + hlava.getVyska(); }
    
    /** Metoda vracejici sirku cele osoby */
    public int getSirka() { return Math.max(telo.getSirka(), hlava.getSirka()); }
    
    /** Metoda vracejici objekt jako string */
    @Override
    public String toString() {
      return this.getNazev() + ": x=" + this.getX() + ", y=" +  this.getY() + ", výška=" + this.getVyska() 
      + ", šířka=" + this.getSirka() + ", barva těla=" + this.getBarvaTela();
    }
    
    /** Metoda pro nakresleni osoby */
    public void nakresli() {
      hlava.nakresli();
      telo.nakresli();
    }
    
    /** Metoda pro nastaveni pozice osoby */
    public Pozice getPozice() {
      return new Pozice(this.getX(), this.getY());
    }
    
    /** Metoda na zmenu pozice osoby */
    public void setPozice(int x, int y) {
      telo.smaz();
      hlava.smaz();
      
      int puvodniX = this.getX();
      int puvodniY = this.getY();
      
      int[] vektorPosunu = {x - puvodniX, y - puvodniY};
       
      int[] novaPoziceHlavy = {(hlava.getX() + vektorPosunu[0]), (hlava.getY() + vektorPosunu[1])};
      int[] novaPoziceTela = {(telo.getX() + vektorPosunu[0]), (telo.getY() + vektorPosunu[1])};
      
      hlava.setPozice(novaPoziceHlavy[0], novaPoziceHlavy[1]);
      telo.setPozice(novaPoziceTela[0],novaPoziceTela[1]);
      
      hlava.nakresli();
      telo.nakresli();
    }
    
    /** Metoda, ktera vraci rozmer osoby */
    public Rozmer getRozmer() {
      return new Rozmer(this.getSirka(), this.getVyska());
    }
    
    /** Test pomoci staticke tovarni metody */
    public static Osoba getBeznyMuz(Pozice pozice) {
      Osoba osBeznyMuz = new Osoba(pozice.getX(), pozice.getY(), Barva.MODRA);
      return osBeznyMuz;
    }
    
    /** Test pomoci staticke tovarni metody */
    public static Osoba getBeznaZena(Pozice pozice) {
      Osoba osBeznaZena = new Osoba(pozice.getX(), pozice.getY(), Barva.CERVENA);
      return osBeznaZena;
    }
}
