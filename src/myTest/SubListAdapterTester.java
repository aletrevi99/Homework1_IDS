package myTest;

import myAdapter.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Vector;

import static org.junit.Assert.*;

public class SubListAdapterTester
{
    private final ListAdapter list = getACollection();
    private HList sublist;

    /**
     * Istanzio una nuova sublist di list da indice 2 a indice 8 prima di ogni test.
     */
    @Before
    public void initialize()
    {
        sublist = list.subList(2, 8);
    }

    /**
     * Se a fine di ogni test sublist non è vuota, viene svuotata.
     */
    @After
    public void clearSubList()
    {
        if (!sublist.isEmpty()) sublist.clear();
    }

    /**
     * Test delle pre-condizioni di ogni metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: Stampa su terminale i valori di sublist
     */
    @Test
    public void initializeTest()
    {
        assertEquals(6, sublist.size());
        Object[] a = sublist.toArray();
        System.out.println("Elementi presenti nella sublist\n--------------------------------------");
        for (int i = 0; i < sublist.size(); i++)
            System.out.println(i + ".\t\t\t\t" + a[i]);
        System.out.println("--------------------------------------\n");
    }

    /**
     * Test del metodo     <b>public void add(int index, Object element)</b>
     * <br><br>
     * <b>Sommario</b>: Mi salvo in due variabili size e sizeList rispettivamente la dimensione della sublist e della lista.
     * aggiungo due elementi in sublist negli indici 0 e 1 e controllo che sia la dimensione di list che di sublist sia aumentata di due.
     * Verifico che gli elementi siano stati effettivamente aggiunti negli indici scelti. Infine, oltre a verificare che aggiungendo un elemento a sublist in un index già occupato trasli
     * gli elementi di una posizione a destra, controllo che aggiungendo elementi con indice
     * non valido o con elemento nullo, lanci rispettivamente IndexOutOfBoundException e NullPointerException.
     * <br><br>
     * <b>Design</b>: corretto inserimento degli elementi nella sublist con indice dato. Inserimento di elementi di tipo diverso. Controllo che
     * gli elementi vengano traslati se aggiunto un elemento con indice già precedentemente occupato. Controllo
     * dell'effettivo lancio delle eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6 e che il
     * metodo insertElementAt di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: il metodo aggiunge correttamente gli elementi a sublist e modifica anche la lista da cui
     * è stata generata. Gestirà inoltre correttamente le due eccezioni.
     */
    @Test
    public void addIndexTest()
    {
        int size = sublist.size();
        int sizeList = list.size();
        sublist.add(0, 2233);
        sublist.add(1, "test1");
        assertEquals(size + 2, sublist.size());
        assertEquals(sizeList + 2, list.size());
        assertTrue(sublist.get(0).equals(2233));
        assertTrue(sublist.get(1).equals("test1"));
        sublist.add(0, "pippo");
        assertTrue(sublist.get(1).equals(2233));


        assertThrows(NullPointerException.class, () -> sublist.add(0, null));
        assertThrows(IndexOutOfBoundsException.class, () -> sublist.add(100, "test"));
    }

    /**
     * Test del metodo     <b>public boolean add(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Mi salvo in due variabili size e sizeList rispettivamente la dimensione della sublist e della lista.
     * aggiungo due elementi in sublist, controllo che sia la dimensione di list che di sublist sia aumentata di due e che
     * gli elementi siano stati effettivamente aggiunti. Controllo che aggiungendo yn elemento nullo
     * lanci NullPointerException.
     * <br><br>
     * <b>Design</b>: corretto inserimento degli elementi nella sublist uno di seguito all'altro. Corretto inserimento di elementi di tipo diverso.
     * Controllo dell'effettiva gestione della eccezione.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6 e che il
     * metodo addElement di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se l'oggetto
     * verrà correttamente aggiunto, altrimenti false.
     */
    @Test
    public void addTest()
    {
        int size = sublist.size();
        int sizeList = list.size();
        sublist.add("test");
        assertTrue(sublist.get(sublist.size() - 1).equals("test"));
        sublist.add(2233);
        assertTrue(sublist.get(sublist.size() - 1).equals(2233));
        assertEquals(size + 2, sublist.size());
        assertEquals(sizeList + 2, list.size());

        assertThrows(NullPointerException.class, () -> sublist.add(null));
    }

    /**
     * Test del metodo     <b>public boolean addAll(HCollection c)</b>
     * <br><br>
     * <b>Sommario</b>: Mi salvo in due variabili size e sizeList rispettivamente la dimensione della sublist e della lista.
     * aggiungo tutti gli elementi di una collection creata con il metodo privato getACollection() in sublist, controllo
     * che sia la dimensione di list che di sublist sia aumentata di 10 e che stringa 5 sia stata effettivamente aggiunta.
     * Controllo che aggiungendo yn elemento nullo
     * lanci NullPointerException.
     * <br><br>
     * <b>Design</b>: corretto inserimento di tutti gli elementi di una collection nella sublist. Corretto inserimento di elementi di
     * tipo diverso. Controllo dell'effettiva gestione della eccezione.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se la collection
     * verrà correttamente aggiunta, altrimenti false. Gestirà inoltre correttamente l'eccezione.
     */
    @Test
    public void addAllTest()
    {
        int size = sublist.size();
        int sizeList = list.size();
        assertTrue(sublist.addAll(getACollection()));
        assertEquals(size + 10, sublist.size());
        assertEquals(sizeList + 10, list.size());
        assertEquals("stringa 5", list.get(list.size() - 1));

        assertThrows(NullPointerException.class, () -> sublist.addAll(null));
    }

    /**
     * Test del metodo     <b>public boolean addAll(int index, HCollection c)</b>
     * <br><br>
     * <b>Sommario</b>: Mi salvo in due variabili size e sizeList rispettivamente la dimensione della sublist e della lista.
     * aggiungo tutti gli elementi di una collection creata con il metodo privato getACollection() in sublist all'indice 0, controllo
     * che sia la dimensione di list che di sublist sia aumentata di 10 e che il valore 0 sia stato effettivamente aggiunto
     * in posizione 0. Controllo che aggiungendo collection con indice
     * non valido o collection nulle, lanci rispettivamente IndexOutOfBoundException e NullPointerException.
     * <br><br>
     * <b>Design</b>: corretto inserimento di tutti gli elementi di una collection nella sublist dall'indice dato. Corretto inserimento di elementi di
     * tipo diverso. Controllo dell'effettiva gestione della eccezione.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se la collection
     * verrà correttamente aggiunta dall'indice scelto, altrimenti false. Gestirà inoltre correttamente l'eccezione.
     */
    @Test
    public void addAllIndexTest()
    {
        int size = sublist.size();
        int sizeList = list.size();
        assertTrue(sublist.addAll(0, getACollection()));
        assertEquals(size + 10, sublist.size());
        assertEquals(sizeList + 10, list.size());
        assertEquals(0, list.get(2));

        assertThrows(IndexOutOfBoundsException.class, () -> sublist.addAll(50, getACollection()));
        assertThrows(NullPointerException.class, () -> sublist.addAll(5, null));
    }

    /**
     * Test del metodo     <b>public void clear()</b>
     * <br><br>
     * <b>Sommario</b>: Controllo che la dimensione della sublist sia effettivamente 6 all'inizio del test. Aggiungo alla sublist
     * un collection di dimensione 10 e controllo che la dimensione sia aumentata del numero degli elementi della collection.
     * Successivamente chiamo il metodo clear e controllo che la dimensione sia pari a zero e che
     * quindi la sublist sia stata correttamente svuotata. Infine verifico che la lista invece sia rimasta con 4 elementi.
     * <br><br>
     * <b>Design</b>: corretto svuotamento della sola sublist con degli elementi e non di tutta la lista.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6
     * e che il metodo removeAllElements di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo svuota correttamente la sublist e riporta la sua dimensione a zero.
     */
    @Test
    public void clearTest()
    {
        assertEquals(6, sublist.size());
        sublist.addAll(getACollection());
        assertEquals(6 + 10, sublist.size());
        sublist.clear();
        assertEquals(0, sublist.size());
        assertEquals(4, list.size());
    }

    /**
     * Test del metodo     <b>public boolean contains(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Verifico che l'elemento in posizione 0 della sublist sia 2 e controllo che il metodo mi confermi la sua presenza in essa.
     * Verifico che l'elemento in posizione 4 della sublist sia "stringa 2" e controllo che il metodo mi confermi la sua presenza in essa.
     * Controllo che ritorni false se gli passo come argomento un oggetto non presente nella sublist.
     * Infine controllo che chiamando il metodo con argomento nullo, lanci NullPointerException.
     * <br><br>
     * <b>Design</b>: corretto controllo della presenza di un oggetto nella sublist. Controllo della corretta gestione delle eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6
     * e che il metodo contains di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se un elemento è presente nella sublist, altrimenti se non presente
     * ritorna false. Se passo un elemento nullo il metodo lancia un'eccezione.
     */
    @Test
    public void containsTest()
    {
        assertEquals(2, sublist.get(0));
        assertTrue(sublist.contains(2));

        assertEquals("stringa 2", sublist.get(4));
        assertTrue(sublist.contains("stringa 2"));

        assertFalse(sublist.contains("pippo"));

        assertThrows(NullPointerException.class, () -> sublist.contains(null));
    }

    /**
     * Test del metodo     <b>public boolean containsAll(HCollection c)</b>
     * <br><br>
     * <b>Sommario</b>: Creo una listAdapter test con il metodo privato getACollection(). Controllo che containsAll ritorni false
     * se gli passo come argomento una collection vuota. Aggiungo a sublist tutti gli elementi di test e controllo che la
     * sua dimensione sia effettivamente aumentata del numero degli elementi aggiunti e controllo che il metodo containsAll ritorni
     * true se gli passo la collection test come argomento. Controllo che aggiungendo altri valori alla sublist, il funzionamento del
     * metodo non venga compromesso. Alla fine controllo che chiamando il metodo con una collection nulla o con un oggetto
     * non di tipo HCollection, mi lanci rispettivamente NullPointerException e ClassCastException.
     * <br><br>
     * <b>Design</b>: corretto controllo della presenza di tutti gli oggetti nella sublist. Controllo della corretta gestione delle eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6
     * e che il metodo contains di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se tutti gli elementi della collection sono presenti nella sublist, altrimenti se non
     * sono tutti presenti ritorna false. Se passo una collection non valida il metodo lancia un'eccezione.
     */
    @Test
    public void containsAllTest()
    {
        ListAdapter test = getACollection();

        assertFalse(sublist.containsAll(test));

        assertTrue(sublist.addAll(test));
        assertEquals(6 + 10, sublist.size());
        assertTrue(sublist.containsAll(test));


        sublist.add(2, 111);
        sublist.add(4, 43531);

        assertTrue(sublist.containsAll(test));

        assertThrows(NullPointerException.class, () -> sublist.containsAll(null));
        assertThrows(ClassCastException.class, () -> sublist.containsAll((HCollection) getAVector()));
    }

    /**
     * Test del metodo     <b>public boolean equals(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Svuoto la sublist. Creo una listAdapter test inizialmente vuoto e aggiungo sia a questo che alla sublist l'elemento
     * "1" e controllo che le due liste siano uguali. Aggiungo un ulteriore elemento alla lista
     * test e controllo ora che siano diverse. Controllo che la sublist non sia uguale alla lista creata con il metodo privato getACollection(),
     * ad una lista nulla e ad un oggetto non di tipo ListAdapter.
     * <br><br>
     * <b>Design</b>: corretto controllo della uguaglianza fra due liste anche nei casi limite dove la lista passata come
     * argomento sia nulla o non di tipo listAdapter.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se la lista passata è uguale a sublist, altrimenti false.
     */
    @Test
    public void equalsTest()
    {
        sublist.clear();
        ListAdapter test = new ListAdapter();
        test.add(1);
        sublist.add(1);
        assertTrue(sublist.equals(test));
        test.add(2);
        assertFalse(sublist.equals(test));

        assertFalse(sublist.equals(getACollection()));
        assertFalse(sublist.equals(null));
        assertFalse(sublist.equals(getAVector()));
    }

    /**
     * Test del metodo     <b>public int hashCode()</b>
     * <br><br>
     * <b>Sommario</b>: Creo due listAdapter test1, test2 inizialmente vuoti, aggiungo ad ognuna degli elementi diverso e genero
     * le loro sublist dall'indice 2 a 4, rispettivamente sTest1 e sTest2. Svuoto list, aggiungo
     * a list gli stessi elementi aggiunti a test1, rigenero la sublist con
     * indici 2 e 4 e controllo che l'hashcode di sublist sia uguale all'hashcode di sTest1 e
     * diverso dall'hashcode di sTest2. Infine aggiungo un elemento a sTest1 e verifico che il suo hashcode sia diverso da
     * quello di sublist.
     * <br><br>
     * <b>Design</b>: corretto calcolo dell'hashcode in base agli elementi contenuti e controllo dell'uguaglianza di esso per
     * liste con elementi uguali o diversi.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna l'hashcode corretto della sublist sotto forma di int
     */
    @Test
    public void hashCodeTest()
    {
        ListAdapter test1 = new ListAdapter();
        for (int i = 0; i < 10; i++)
            test1.add(1);

        HList sTest1 = test1.subList(2, 4);

        ListAdapter test2 = new ListAdapter();
        for (int i = 0; i < 10; i++)
            test2.add(2);

        HList sTest2 = test2.subList(2, 4);

        list.clear();
        for (int i = 0; i < 10; i++)
            list.add(1);

        sublist = list.subList(2, 4);

        assertEquals(sTest1.hashCode(), sublist.hashCode());
        assertNotEquals(sTest2.hashCode(), sublist.hashCode());

        sTest1.add(1);
        assertNotEquals(sTest1.hashCode(), sublist.hashCode());
    }

    /**
     * Test del metodo     <b>public Object get(int index)</b>
     * <br><br>
     * <b>Sommario</b>: Con due for,
     * uno per tipo di oggetto di sublist, controllo che il metodo get ritorni l'elemento effettivamente contenuto nella
     * lista all'indice i. Verifico che l'elemento alla seconda posizione della lista sia lo stesso di quello in posizione
     * 0 della sublist (per costruzione di quest'ultima) e che sia diverso da quello in posizione 1 della sublist.
     * Inoltre controllo che se passo come argomento un indice non valido, mi lanci IndexOutOfBoundsException.
     * <br><br>
     * <b>Design</b>: verifica la correttezza dell'oggetto ritornato dal metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6
     * e che il metodo elementAt di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna l'elemento della sublist di indice passato come argomento.
     */
    @Test
    public void getTest()
    {
        for (int i = 2; i < 5; i++)
            assertEquals(i, sublist.get(i - 2));

        for (int i = 5; i < 8; i++)
            assertEquals("stringa " + (i - 4), sublist.get(i - 2));

        assertEquals(list.get(2), sublist.get(0));
        assertNotEquals(list.get(2), sublist.get(1));

        assertThrows(IndexOutOfBoundsException.class, () -> sublist.get(999));
    }

    /**
     * Test del metodo     <b>public int indexOf(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Con due for,
     * uno per tipo di oggetto di sublist, controllo che il metodo indexOf ritorni l'indice del primo elemento effettivamente
     * contenuto nella sublist all'indice i. Controllo che se passo come argomento un oggetto non contenuto nella sublist,
     * mi ritorni l'indice -1. Controllo che se chiamo il metodo indexOf mi ritorni effettivamente l'indice del primo
     * oggetto contenuto nella sublist aggiungendo l'oggetto 2 presente all'indice 0, anche in posizione finale.
     * Inoltre controllo che se passo come argomento un oggetto non valido, mi lanci NullPointerException.
     * <br><br>
     * <b>Design</b>: verifica la correttezza dell'indice ritornato dal metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6
     * e che il metodo indexOf di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna l'indice del primo elemento trovato passato come argomento nella sublist.
     */
    @Test
    public void indexOfTest()
    {
        for (int i = 2; i < 5; i++)
            assertEquals(i - 2, sublist.indexOf(i));
        for (int i = 5; i < 8; i++)
            assertEquals(i - 2, sublist.indexOf("stringa " + (i - 4)));

        assertEquals(-1, sublist.indexOf("pippo"));

        sublist.add(2);
        assertEquals(0, sublist.indexOf(2));
        assertEquals(list.indexOf(0), sublist.indexOf(2));

        assertThrows(NullPointerException.class, () -> sublist.indexOf(null));
    }

    /**
     * Test del metodo     <b>public boolean isEmpty()</b>
     * <br><br>
     * <b>Sommario</b>: Mi salvo in due variabili size e sizeList rispettivamente la dimensione della sublist e della lista.
     * Controllo che all'inizio la sublist non sia vuota. Svuoto la sublist e controllo che la sua dimensione ora sia zero
     * e che sia vuota. Alla fine controllo che la dimensione della lista sia pari alla differenza delle due dimensioni salvate
     * all'inizio ossia 10 - 6 = 4.
     * <br><br>
     * <b>Design</b>: verificare che la condizione di lista vuota equivalga alla sua dimensione pari a zero.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6
     * e che il metodo isEmpty di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se la sublist è vuota e di dimensione pari a zero.
     */
    @Test
    public void isEmptyTest()
    {
        int size = sublist.size();
        int sizeList = list.size();
        assertFalse(sublist.isEmpty());

        sublist.clear();
        assertEquals(0, sublist.size());
        assertTrue(sublist.isEmpty());

        assertEquals(sizeList - size, list.size());
    }

    /**
     * Test del metodo     <b>public HIterator iterator()</b>
     * <br><br>
     * <b>Sommario</b>: Svuoto sublist. Creo un iteratore di sublist e controllo che non sia nullo. Aggiungo dentro a sublist gli elementi di un
     * ListAdapter creato con il metodo privato getACollection() e creo un nuovo iteratore test di sublist. Controllo con
     * un for che tutti gli elementi di sublist siano uguali agli elementi che scorre l'iteratore test tramite il metodo
     * next(). Creo un secondo iteratore test1 per controllare con un while che il metodo hasNext() funzioni e si
     * comporti allo stesso modo del for testato precedentemente. Verifico che il metodo remove dell'iteratore
     * rimuova effettivamente l'ultimo oggetto controllato da test1 controllando che la dimensione della sublist sia
     * diminuita e che l'ultimo oggetto della sublist non sia più contenuto in essa. Infine verifico che se viene invocato
     * il metodo next() di un iteratore arrivato a fine sublist o se invoco il metodo remove ripetutamente, vengono lanciate
     * vengono lanciate rispettivamente NoSuchElementException e IllegalStateException.
     * <br><br>
     * <b>Design</b>: verificare il corretto funzionamento del metodo testando ogni metodo dell'iteratore, controllando
     * che esso agisca effettivamente sulla sublist su cui è stato creato e che gestisca correttamente  le eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna un nuovo iteratore ad ogni chiamata che permette di scorrere correttamente
     * la sublist e di rimuovere l'ultimo oggetto analizzato da esso. Gestisce correttamente le eccezioni.
     */
    @Test
    public void iteratorTest()
    {
        sublist.clear();
        HIterator testNull = sublist.iterator();
        assertNotNull(testNull);

        sublist.addAll(getACollection());
        HIterator test = sublist.iterator();

        for (int i = 0; i < sublist.size(); i++)
            assertSame(test.next(), sublist.get(i));

        HIterator test1 = sublist.iterator();
        int j = 0;
        while (test1.hasNext())
            assertSame(test1.next(), sublist.get(j++));

        assertEquals(j, sublist.size());
        int size = sublist.size();
        test1.remove();
        assertEquals(size - 1, sublist.size());
        assertFalse(sublist.contains("stringa 5"));

        assertThrows(NoSuchElementException.class, test1::next);
        assertThrows(IllegalStateException.class, test1::remove);
    }

    /**
     * Test del metodo     <b>public int indexOf(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Con due for,
     * uno per tipo di oggetto di sublist, controllo che il metodo lastIndexOf ritorni l'indice dell'ultimo elemento effettivamente
     * contenuto nella sublist all'indice i. Controllo che se passo come argomento un oggetto non contenuto nella sublist,
     * mi ritorni l'indice -1. Controllo che se chiamo il metodo lastIndexOf mi ritorni effettivamente l'indice dell'ultimo
     * oggetto contenuto nella sublist aggiungendo l'oggetto 2 presente all'indice 0, anche in posizione finale.
     * Inoltre controllo che se passo come argomento un oggetto non valido, mi lanci NullPointerException.
     * <br><br>
     * <b>Design</b>: verifica la correttezza dell'indice ritornato dal metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6
     * e che il metodo indexOf di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna l'indice dell'ultimo elemento trovato passato come argomento nella sublist.
     */
    @Test
    public void lastIndexOfTest()
    {
        for (int i = 2; i < 5; i++)
            assertEquals(i - 2, sublist.lastIndexOf(i));
        for (int i = 5; i < 8; i++)
            assertEquals(i - 2, sublist.lastIndexOf("stringa " + (i - 4)));

        assertEquals(-1, sublist.indexOf("pippo"));

        sublist.add(2);
        assertEquals(sublist.size() - 1, sublist.lastIndexOf(2));

        assertThrows(NullPointerException.class, () -> sublist.indexOf(null));
    }

    /**
     * Test del metodo     <b>public HListIterator listIterator()</b>
     * <br><br>
     * <b>Sommario</b>: Svuoto la sublist.
     * Creo un listIterator di sublist e controllo che non sia nullo. Aggiungo dentro a sublist gli elementi di un
     * ListAdapter creato con il metodo privato getACollection() e creo un nuovo listIterator test di sublist. Controllo con
     * un for che tutti gli elementi di sublist siano uguali agli elementi che scorre il listIterator test tramite il metodo
     * next(). Creo un secondo listIterator test1 per controllare con un while che il metodo hasNext() funzioni e si
     * comporti allo stesso modo del for testato precedentemente. Verifico che il metodo remove del listIterator
     * rimuova effettivamente l'ultimo oggetto controllato da test1 controllando che la dimensione della sublist sia
     * diminuita e che l'ultimo oggetto della sublist non sia più contenuto in essa. Verifico che se viene invocato
     * il metodo next() di un listIterator arrivato a fine sublist o se invoco il metodo remove ripetutamente, vengono lanciate rispettivamente
     * NoSuchElementException e IllegalStateException. Aggiungo l'oggetto "pippo" per testare il metodo add() e verifico che la dimensione sia aumentata di uno
     * e che la stringa "pippo" sia stata effettivamente aggiunta controllando pure il funzionamento del metodo previous().
     * Controllo che se chiamo add() con un oggetto null mi lancia NullPointerException. Verifico che i metodi nextIndex() e
     * previousIndex() ritornino rispettivamente l'indice che andrà ad ispezionare col metodo next() e l'indice appena ispezionato.
     * Infine verifico il funzionamento del metodo set() con una stringa "test" e che lanci rispettivamente NullPointerException
     * se lo invoco con un oggetto null e IllegalStateException se lo invoco successivamente ad un remove().
     * <br><br>
     * <b>Design</b>: verificare il corretto funzionamento del metodo testando ogni metodo di HListIterator, controllando
     * che esso agisca effettivamente sulla sublist su cui è stato creato e che gestisca correttamente le eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna un nuovo listIterator ad ogni chiamata che permette di scorrere correttamente
     * la sublist in entrambi i sensi e di agire su di essa. Gestisce correttamente le eccezioni.
     */
    @Test
    public void listIteratorTest()
    {
        sublist.clear();
        HListIterator testNull = sublist.listIterator();
        assertNotNull(testNull);

        sublist.addAll(getACollection());
        HListIterator test = sublist.listIterator();

        for (int i = 0; i < sublist.size(); i++)
            assertEquals(test.next(), sublist.get(i));

        HListIterator test1 = sublist.listIterator();

        assertFalse(test1.hasPrevious());
        assertThrows(NoSuchElementException.class, test1::previous);

        int j = 0;
        while (test1.hasNext())
        {
            assertEquals(test1.next(), sublist.get(j++));
            assertTrue(test1.hasPrevious());
        }
        assertEquals(j, sublist.size());
        int size = sublist.size();
        test1.remove();
        assertEquals(size - 1, sublist.size());
        assertFalse(sublist.contains("stringa 5"));

        assertThrows(NoSuchElementException.class, test1::next);
        assertThrows(IllegalStateException.class, test1::remove);

        test1.add("pippo");
        assertEquals(size, sublist.size());

        assertThrows(NullPointerException.class, () -> test1.add(null));

        assertEquals(sublist.size(), test1.nextIndex());
        assertEquals(sublist.size() - 1, test1.previousIndex());

        assertEquals("pippo", test1.previous());

        test1.set("test");
        assertEquals("test", test1.next());
        assertThrows(NullPointerException.class, () -> test1.set(null));

        test1.remove();
        assertThrows(IllegalStateException.class, () -> test1.set("test"));
    }

    /**
     * Test del metodo     <b>public HListIterator listIterator(int index)</b>
     * <br><br>
     * <b>Sommario</b>: Svuoto sublist. Creo un listIterator di sublist e controllo che non sia nullo. Aggiungo dentro a sublist gli elementi di un
     * ListAdapter creato con il metodo privato getACollection() e creo un nuovo listIterator test di sublist dall'indice 5.
     * Scorro test con un while per verificare che gli oggetti ispezionati da test siano effettivamenti quelli della sublist
     * a partire dall'indice 5. Infine verifico che se invoco il metodo con un indice non valido mi lanci IndexOutOfBoundException.
     * <br><br>
     * <b>Design</b>: verificare il corretto funzionamento del listIterator, controllando
     * che esso agisca effettivamente sulla sublist su cui è stato creato dall'indice scelto e che gestisca correttamente l'eccezione.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna un nuovo listIterator di sublist, a partire dall'indice scelto,
     * ad ogni chiamata che permette di scorrere correttamente
     * la sublist in entrambi i sensi e di agire su di essa. Gestisce correttamente le eccezioni.
     */
    @Test
    public void listIteratorIndexTest()
    {
        sublist.clear();
        HListIterator testNull = sublist.listIterator();
        assertNotNull(testNull);

        sublist.addAll(getACollection());
        HListIterator test = sublist.listIterator(5);

        int j = 0;
        while (test.hasNext())
            assertEquals(test.next(), sublist.get(j++ + 5));

        assertThrows(IndexOutOfBoundsException.class, () -> sublist.listIterator(999));
    }

    /**
     * Test del metodo     <b>public Object remove(int index)</b>
     * <br><br>
     * <b>Sommario</b>: Creo un ListAdapter test con il metodo privato getACollection().
     * Creo tre nuove variabili; un Object tmp che successivamente conterrà l'oggetto da confrontare con
     * l'oggetto ritornato dal metodo remove(0) per verificare che siano effettivamente uguali, un int count per
     * tener traccia dell'effettiva riduzione della dimensione della sublist ad ogni invocazione del metodo remove e un
     * altro int size per poter iterare l'operazione di remove un numero di volte pari alla dimensione iniziale della sublist.
     * Una volta rimossi tutti gli elementi da sublist, aggiungo tutti gli elementi di test ad essa, aggiorno la dimensione
     * della sublist, assegno alla variabile
     * tmp il quinto elemento della sublist per poter verificare che il metodo remove ritorni lo stesso oggetto
     * invocandolo con index pari a 5 e successivamente con index pari a 8 per confermare che l'oggetto ritornato sia
     * diverso dalla variabile tmp. Verifico che la dimensione della sublist sia diminuita di due unità dopo aver invocato
     * remove due volte. Infine verifico il lancio di IndexOutOfBoundsException se viene invocato remove con un indice non
     * valido.
     * <br><br>
     * <b>Design</b>: verificare la corretta rimozione degli oggetti dalla sublist in base all'indice scelto e che ritorni
     * l'oggetto appena rimosso, la riduzione della dimensione della sublist in base al numero di volte che viene invocato
     * il metodo e che gestisca correttamente l'eccezione.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6
     * e che il metodo removeElementAt di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo rimuove correttamente l'elemento di indice i e lo ritorna, riducendo la dimensione
     * della sublist, a seconda del numero di volte che viene invocato. Gestisce correttamente l'eccezione.
     */
    @Test
    public void removeIndexTest()
    {
        ListAdapter test = getACollection();

        Object tmp;
        int count = sublist.size();
        int size = sublist.size();

        for (int i = 0; i < size; i++)
        {
            tmp = sublist.get(0);
            assertSame(tmp, sublist.remove(0));
            assertEquals(--count, sublist.size());
        }

        sublist.addAll(test);
        size = sublist.size();
        tmp = sublist.get(5);
        assertSame(tmp, sublist.remove(5));
        assertNotSame(tmp, sublist.remove(8));
        assertEquals(size - 2, sublist.size());

        assertThrows(IndexOutOfBoundsException.class, () -> sublist.remove(999));
    }

    /**
     * Test del metodo     <b>public boolean remove(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Creo un ListAdapter test con il metodo privato getACollection().
     * Istanzio tre nuove variabili; un int count per tener traccia dell'effettiva riduzione della dimensione della
     * sublist ad ogni invocazione del metodo remove, un int size per poter iterare l' operazione di remove un
     * numero di volte pari alla dimensione iniziale della sublist, e un altro int sizeList per tenere in memoria la
     * dimensione originale della lista. Itero queste azioni con un for per i primi 3 oggetti della sublist e
     * ripeto fuori da esso, altre 3 volte per i rimanenti oggetti di essa. Una volta rimossi tutti gli elementi da sublist,
     * controllo che la dimensione della lista sia diminuita di un numero di valori pari alla variabile size,
     * aggiungo tutti gli elementi di test a sublist, aggiorno size, verifico che ritorni true se invoco remove per un oggetto presente
     * nella sublist e che invece ritorni false per un oggetto non presente in essa. Verifico che la dimensione della
     * sublist sia diminuita di una unità dopo aver invocato remove una volta. Infine verifico il lancio della eccezioni
     * NullPointerException se viene invocato remove con un oggetto null.
     * <br><br>
     * <b>Design</b>: verificare la corretta rimozione degli oggetti dalla sublist in base all'oggetto passato, la riduzione
     * della dimensione della sublist in base al numero di volte che viene invocato il metodo e che gestisca correttamente
     * l'eccezione.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6
     * e che il metodo removeElementAt di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se l'oggetto passato è presente nella sublist rimuovendolo e
     * riducendo la dimensione della sublist, altrimenti ritorna false. Gestisce correttamente l'eccezione.
     */
    @Test
    public void removeObjectTest()
    {
        ListAdapter test = getACollection();

        int count = sublist.size();
        int size = sublist.size();
        int sizeList = list.size();

        for (int i = 2; i < 5; i++) ///indici 2, 5 perché la sub list è stata creata partendo da indice 2
        {
            assertTrue(sublist.remove((Object) i));
            assertEquals(--count, sublist.size());
        }

        assertTrue(sublist.remove("stringa " + 1));
        assertEquals(--count, sublist.size());
        assertTrue(sublist.remove("stringa " + 2));
        assertEquals(--count, sublist.size());
        assertTrue(sublist.remove("stringa " + 3));
        assertEquals(--count, sublist.size());

        assertEquals(sizeList - size, list.size());

        sublist.addAll(test);
        size = sublist.size();
        assertTrue(sublist.remove("stringa 2"));
        assertFalse(sublist.remove("stringa 200"));
        assertEquals(size - 1, sublist.size());

        assertThrows(NullPointerException.class, () -> sublist.remove(null));
    }

    /**
     * Test del metodo     <b>public boolean removeAll(HCollection c)</b>
     * <br><br>
     * <b>Sommario</b>: Creo un ListAdapter test con il metodo privato getACollection(), aggiungo tutti i suoi elementi a sublist
     * due volte e controllo che la dimensione di sublist sia effettivamente 26. Verifico che rimuovendo tutti gli elementi
     * della collection test da sublist, il metodo ritorni true, che la dimensione di quest'ultima sia 16 e che la dimensione
     * della lista di base sia 20. Rimuovo tutti
     * gli elementi dalla sublist e aggiungo ad essa l'oggetto "pippo" 10 volte. Verifico che il metodo ritorni false se
     * esso non modifica la sublist, non essendo presente l'elemento "pippo" nella collection data e che la dimensione
     * della sublist sia rimasta sempre a 10. Infine verifico il lancio della eccezione NullPointerException se viene
     * invocato remove con un oggetto null e della eccezione ClassCastException se viene invocato con un oggetto non di
     * tipo HCollection.
     * <br><br>
     * <b>Design</b>: verificare la corretta rimozione di tutti gli elementi nella collection dalla sublist, la riduzione
     * della dimensione della sublist in base al numero di oggetti rimossi, che ritorni true solo se vengono effettuate
     * rimozioni e che gestisca correttamente le eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se vengono effettuate delle rimozioni dalla sublist di oggetti presenti
     * sia nella collection che nella sublist riducendone la dimensione in base al numero di oggetti rimossi,
     * altrimenti ritorna false se non vengono effettuate rimozioni. Gestisce correttamente le eccezioni.
     */
    @Test
    public void removeAllTest()
    {
        ListAdapter test = getACollection();
        sublist.addAll(test);
        sublist.addAll(test);

        assertEquals(6 + 10 + 10, sublist.size());
        assertTrue(sublist.removeAll(test));
        assertEquals(6 + 10, sublist.size());
        assertEquals(10 + 10, list.size());

        sublist.clear();
        for (int i = 0; i < 10; i++) sublist.add("pippo");
        assertFalse(sublist.removeAll(test));
        assertEquals(10, sublist.size());


        assertThrows(NullPointerException.class, () -> sublist.removeAll(null));
        assertThrows(ClassCastException.class, () -> sublist.removeAll((HCollection) getAVector()));
    }

    /**
     * Test del metodo     <b>public boolean retainAll(HCollection c)</b>
     * <br><br>
     * <b>Sommario</b>: Creo un ListAdapter test con il metodo privato getACollection(), aggiungo tutti i suoi elementi a sublist
     * e controllo che la dimensione di list sia effettivamente 16. Verifico che trattenendo tutti gli elementi
     * della collection test da sublist, il metodo ritorni false, che la dimensione di quest'ultima sia ancora 16 poiché
     * entrambe contengono gli stessi elementi e che la dimensione della lista di base sia ancora 20.
     * Rimuovo tutti gli elementi dalla sublist, aggiungo ad essa i numeri interi da 0 a 9 e mi salvo in una variabile
     * la nuova dimensione della sublist. Trattengo nella sublist tutti gli elementi presenti nella collection test invocando
     * il metodo, ossia vengono rimossi da sublist tutti gli elementi non presenti nella collection (in questo esempio
     * tutte le stringhe) e confronto se la rimozione è andata a buon fine col il for iterato per la dimensione di sublist.
     * Verifico che la nuova dimensione della sublist sia esattamente la metà di quella precedente all'invocazione del
     * metodo poiché il metodo ha rimosso le cinque stringhe presenti in sublist. Infine verifico il lancio della eccezione
     * NullPointerException se viene invocato retainAll con una collection null e della eccezione ClassCastException se
     * viene invocato con un oggetto non di tipo HCollection.
     * <br><br>
     * <b>Design</b>: verificare il corretto mantenimento di tutti gli elementi nella collection dalla sublist, e quindi la
     * rimozione da questa degli elementi non presenti nella collection, la riduzione della dimensione della sublist in
     * base al numero di oggetti rimossi, che ritorni true solo se vengono effettuate rimozioni e che gestisca
     * correttamente le eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se vengono effettuate delle rimozioni dalla sublist di oggetti non presenti
     * nella collection riducendone la dimensione in base al numero di oggetti rimossi, altrimenti ritorna false se
     * non vengono effettuate rimozioni. Gestisce correttamente le eccezioni.
     */
    @Test
    public void retainAllTest()
    {
        ListAdapter test = getACollection();
        sublist.addAll(test);

        assertEquals(6 + 10, sublist.size());
        assertFalse(sublist.retainAll(test));
        assertEquals(6 + 10, sublist.size());
        assertEquals(20, list.size());

        sublist.clear();
        for (int i = 0; i < 10; i++) sublist.add(i);
        int size = sublist.size();
        assertTrue(sublist.retainAll(test));
        for (int i = 0; i < sublist.size(); i++)
            assertEquals(i, sublist.get(i));

        assertEquals(size / 2, sublist.size());

        assertThrows(NullPointerException.class, () -> sublist.retainAll(null));
        assertThrows(ClassCastException.class, () -> sublist.retainAll((HCollection) getAVector()));
    }

    /**
     * Test del metodo     <b>public Object set(int index, Object element)</b>
     * <br><br>
     * <b>Sommario</b>: Creo due nuove variabili;
     * un Object tmp che successivamente conterrà l' oggetto da confrontare con l' oggetto ritornato dal metodo set per
     * verificare che siano effettivamente uguali e un int size per poter verificare che la dimensione di sublist non
     * varia. Dentro il for imposto, con il metodo da testare, l' elemento i*i in posizione i e verifico che, oltre appunto
     * a ritornare l' elemento precedentemente contenuto in quell' indice, che il valore i*i sia stato impostato
     * correttamente. Infine verifico il lancio della eccezione NullPointerException se viene invocato il set di un
     * elemento nullo e della eccezione IndexOutOfBoundsException se viene invocato set con un indice non valido.
     * <br><br>
     * <b>Design</b>: verificare la corretta impostazione dell' elemento passato nella posizione desiderata, che ritorni, se
     * presente, l' elemento precedentemente contenuto nell' indice e che gestisca correttamente le eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6
     * e che il metodo size di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo imposta l' elemento nella posizione scelta correttamente, ritornando l' elemento
     * precedentemente contenuto in quella posizione se presente. Gestisce correttamente le eccezioni.
     */
    @Test
    public void setTest()
    {
        Object tmp;
        int size = sublist.size();

        for (int i = 0; i < size; i++)
        {
            tmp = sublist.get(i);
            assertSame(tmp, sublist.set(i, i * i));
            assertEquals(i * i, sublist.get(i));
            assertEquals(size, sublist.size());
        }

        sublist.set(0, 1000);
        assertEquals(1000, list.get(2));

        assertThrows(NullPointerException.class, () -> sublist.set(0, null));
        assertThrows(IndexOutOfBoundsException.class, () -> sublist.set(-1, 1));
    }

    /**
     * Test del metodo     <b>public int size()</b>
     * <br><br>
     * <b>Sommario</b>: Verifico che la dimensione della sublist inizialmente sia 6. Svuoto la sublist e verifico che la
     * dimensione sia tornata a 0. Sfrutto un for che aggiunge esattamente
     * un elemento a sublist per iterazione e confronto ogni volta la dimensione della sublist con un contatore che
     * riproduce il corretto funzionamento dell' incremento della dimensione di una sublist dopo l' aggiunta di un elemento.
     * A fine iterazioni confronto per l' ultima volta la dimensione della sublist con la variabile count e verifico che la
     * dimensione della lista sia di 4 + gli elementi aggiunti.
     * <br><br>
     * <b>Design</b>: verificare la correttezza del metodo size controllando la coerenza di esso con un confronto con una
     * variabile che riproduce lo stesso meccanismo del metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6
     * e che il metodo size di Vector funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna l' effettiva dimensione della sublist.
     */
    @Test
    public void sizeTest()
    {
        assertEquals(6, sublist.size());
        sublist.clear();
        assertEquals(0, sublist.size());

        int count = 0;
        for (int i = 0; i < 10; i++)
        {
            sublist.add(i);
            assertEquals(++count, sublist.size());
        }

        assertEquals(count, sublist.size());
        assertEquals(4 + 10, list.size());
    }

    /**
     * Test del metodo     <b>public HList subList(int fromIndex, int toIndex)</b>
     * <br><br>
     * <b>Sommario</b>: Verifico il lancio della eccezione UnsupportedOperationException se viene invocato il metodo.
     * <br><br>
     * <b>Design</b>: verificare che il metodo non possa essere invocato.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo lancia l' eccezione UnsupportedOperationException se invocato.
     */
    @Test
    public void subListTest()
    {
        assertThrows(UnsupportedOperationException.class, () -> sublist.subList(3, 7));
    }

    /**
     * Test del metodo     <b>public Object[] toArray()</b>
     * <br><br>
     * <b>Sommario</b>: Istanzio un nuovo array di
     * Object della dimensione di sublist e ci copio all' interno un elemento di sublist per ogni iterazione del for. Infine
     * verifico che l' array appena creato sia uguale all' array ritornato dal metodo toArray.
     * <br><br>
     * <b>Design</b>: verificare che il metodo ritorni effettivamente un array con tutti gli elementi della sublist nell' ordine
     * corretto.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: il metodo ritorna correttamente un array con tutti gli elementi della sublist.
     */
    @Test
    public void toArrayTest()
    {
        Object[] test = new Object[sublist.size()];

        for (int i = 0; i < sublist.size(); i++)
            test[i] = sublist.get(i);

        assertArrayEquals(test, sublist.toArray());
    }

    /**
     * Test del metodo     <b>public Object[] toArray(Object[] a)</b>
     * <br><br>
     * <b>Sommario</b>: Istanzio tre nuovi array di
     * Object; il primo, test,  della dimensione di sublist, il secondo, test1, della dimensione di sublist + 5 e il terzo,
     * test2, della dimensione di sublist - 5. Nel primo copio all' interno un elemento di sublist per ogni iterazione del for
     * e verifico che l' array appena creato sia uguale all' array ritornato dal metodo toArray. Passo il secondo array
     * come argomento del metodo toArray e verifico tramite un for che i nuovi elementi di test1 siano uguali agli
     * elementi di test. Eseguo la stessa cosa con il terzo array, salvandomi però l' array ritornato dal metodo in un
     * nuovo array di Object test2_1 poiché la dimensione di test2 è minore rispetto alla dimensione di sublist. Infine
     * verifico che venga lanciata l' eccezione NullPointerException se l' array passato come argomento è null.
     * <br><br>
     * <b>Design</b>: verificare che il metodo ritorni effettivamente un array con tutti gli elementi della sublist nell' ordine
     * corretto se la dimensione di quello passato per argomento è minore rispetto a quella della sublist o che sovrascriva
     * i dati di quello passato per argomento se la sua dimensione è maggiore o uguale a quella della sublist. Verificare
     * la corretta gestione dell' eccezione.
     * <br><br>
     * <b>Pre-condizioni</b>: sublist di list correttamente inizializzata da indice 2 a indice 8 con dimensione uguale a 6.
     * <br><br>
     * <b>Post-Condizioni</b>: sublist torna ad essere vuota e di dimensione zero. List invece torna di dimensione 4.
     * <br><br>
     * <b>Risultato Atteso</b>: il metodo ritorna correttamente un array con tutti gli elementi della sublist o sovrascrive i
     * dati di quello passato come argomento in base alla dimensione dell' array.
     */
    @Test
    public void toArrayArgTest()
    {
        Object[] test = new Object[sublist.size()];
        Object[] test1 = new Object[sublist.size() + 5];
        Object[] test2 = new Object[sublist.size() - 5];

        for (int i = 0; i < sublist.size(); i++)
            test[i] = sublist.get(i);

        assertArrayEquals(test, sublist.toArray(test));

        sublist.toArray(test1);
        for (int i = 0; i < sublist.size(); i++)
            assertEquals(test[i], test1[i]);

        Object[] test2_1 = sublist.toArray(test2);
        for (int i = 0; i < sublist.size(); i++)
            assertEquals(test[i], test2_1[i]);

        assertThrows(NullPointerException.class, () -> sublist.toArray(null));
    }

    // metodi helper
    private ListAdapter getACollection()
    {
        ListAdapter l = new ListAdapter();
        for (int i = 0; i < 5; i++)
            l.add(i, i);

        for (int i = 5; i < 10; i++)
            l.add(i, "stringa " + (i - 4));

        return l;
    }

    private Vector getAVector()
    {
        Vector test = new Vector();
        for (int i = 0; i < 10; i++) test.addElement(i);
        return test;
    }
}