package myTest;

import myAdapter.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Vector;

import static org.junit.Assert.*;

public class ListAdapterTester
{
    private ListAdapter list;

    /**
     * Istanzia una nuova ListAdapter prima di ogni test.
     * */
    @Before
    public void initialize()
    {
        list = new ListAdapter();
    }

    /**
     * Se a fine di ogni test list non è vuota, viene svuotata.
     * */
    @After
    public void clearList()
    {
        if (!list.isEmpty()) list.clear();
    }

    /**
     * Test delle pre-condizioni di ogni metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero.
     * <br><br>
     * <b>Post-Condizioni</b>: Stampa su terminale i valori di getACollection().
     * */
    @Test
    public void initializeTest()
    {
        assertEquals(0, list.size());
        Object[] a = getACollection().toArray();
        for (int i = 0; i < 10; i++)
            System.out.println(a[i]);
    }

    /**
     * Test del metodo     <b>public void add(int index, Object element)</b>
     *<br><br>
     * <b>Sommario</b>: Aggiungo alla lista list inizialmente vuota uno ad uno gli elementi con il relativo indice di una listAdapter test ripetutamente in
     * un for per la dimensione di test e controllo che alla fine equivalga effettivamente al numero degli elementi
     * aggiunti. Successivamente controllo che gli elementi aagiunti siano stati effettivamente aggiunti a list nella
     * posizione corretta tramite due for che scorrono prima gli elementi di tipo Object e successivamente gli elementi
     * di tipo string. Infine, oltre a verificare che aggiungendo un elemento alla lista in un index già occupato trasli
     * gli elementi di una posizione a destra e che aumenti la dimensione, controllo che aggiungendo elementi con indice
     * non valido o con elemento nullo, lancino le relative eccezioni.
     *<br><br>
     * <b>Design</b>: corretto inserimento degli elementi nella Lista con indice dato. Inserimento di elementi di tipo diverso. Controllo che
     * gli elementi vengano traslati se aggiunto un elemento con indice già precedentemente occupato. Controllo
     * dell'effettivo lancio delle eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo insertElementAt di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: la lista conterrà gli elementi della collection creata tramite il metodo privato getACollection
     * e l'elemento "test" nell'indice 0, con una dimensione pari a 11. Gestirà inoltre correttamente le due eccezioni.
     */
    @Test
    public void addIndexTest()
    {
        ListAdapter test = getACollection();

        for (int i = 0; i < test.size(); i++)
            list.add(i, test.get(i));

        assertEquals(10, list.size());

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.get(i));

        for (int i = 5; i < list.size(); i++)
            assertEquals("stringa " + (i - 4), list.get(i));

        list.add(0, "test");

        assertEquals("test", list.get(0));
        assertEquals(0, list.get(1));

        assertEquals(11, list.size());

        assertThrows(NullPointerException.class, () -> {list.add(0, null);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(100, "test");});
    }

    /**
     * Test del metodo     <b>public boolean add(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Aggiungo alla lista list inizialmente vuota uno ad uno gli elementi di una list test ripetutamente in
     * un for per la dimensione di test e controllo che alla fine equivalga effettivamente al numero degli elementi
     * aggiunti. Successivamente controllo che gli elementi aggiunti siano stati effettivamente aggiunti a list nella
     * posizione corretta e che siano contenuti in essa tramite due for che scorrono prima gli elementi di tipo Object e successivamente gli elementi
     * di tipo string. Infine controllo che aggiungendo elementi nulli, lanci la corretta eccezione.
     *<br><br>
     * <b>Design</b>: corretto inserimento degli elementi nella Lista uno di seguito all'altro. Inserimento di elementi di tipo diverso.
     * Controllo dell'effettiva gestione della eccezione.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo addElement di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: la lista conterrà gli elementi della collection creata tramite il metodo privato getACollection
     * con una dimensione pari a 10. Gestirà inoltre correttamente l'eccezione. Il metodo ritornerà true se l'oggetto
     * verrà correttamente aggiunto, altrimento false.
     */
    @Test
    public void addTest()
    {
        ListAdapter test = getACollection();

        for (int i = 0; i < test.size(); i++)
            assertTrue(list.add(test.get(i)));

        assertEquals(10, list.size());

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.get(i));
        for (int i = 0; i < list.size() / 2; i++)
            assertTrue(list.contains(i));

        for (int i = 5; i < list.size(); i++)
            assertEquals("stringa " + (i - 4), list.get(i));
        for (int i = 5; i < list.size(); i++)
            assertTrue(list.contains("stringa " + (i - 4)));

        assertThrows(NullPointerException.class, () -> {list.add(null);});
    }

    /**
     * Test del metodo     <b>public boolean addAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Aggiungo alla lista list inizialmente vuota tutti gli elementi della collection creata con il metodo
     * privato getACollection(). Successivamente controllo che gli elementi aggiunti siano stati effettivamente aggiunti a list nella
     * posizione corretta tramite due for che scorrono prima gli elementi di tipo Object e successivamente gli elementi
     * di tipo string. Controllo che chiamando il metodo addAll non invalidi il funzionamento di altri metodi come add
     * e infine controllo che aggiungendo elementi di una collection nulla, lanci la corretta eccezione.
     *<br><br>
     * <b>Design</b>: corretto inserimento di tutti gli elementi di una collection nella Lista. Inserimento di elementi di
     * tipo diverso. Controllo dell'effettiva gestione della eccezione.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo addElement di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: la lista conterrà tutti gli elementi della collection creata tramite il metodo privato getACollection
     * e l'elemento "pippo" con una dimensione pari a 11. Gestirà inoltre correttamente l'eccezione. Il metodo ritornerà true se la collection
     * verrà correttamente aggiunta, altrimento false.
     */
    @Test
    public void addAllTest()
    {
        assertTrue(list.addAll((HCollection) getACollection()));
        assertEquals(10, list.size());

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.get(i));

        for (int i = 5; i < list.size(); i++)
            assertEquals("stringa " + (i-4), list.get(i));

        assertTrue(list.add("pippo"));
        assertEquals("pippo", list.get(10));

        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
    }

    /**
     * Test del metodo     <b>public boolean addAll(int index, HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Aggiungo alla lista list inizialmente vuota tutti gli elementi della collection all'indice zero creata con il metodo
     * privato getACollection() e una seconda all'indice 5. Successivamente controllo che gli elementi aggiunti siano stati effettivamente aggiunti a list nella
     * posizione corretta tramite due for che scorrono prima gli elementi di tipo Object e successivamente altri due for
     * per gli elementi di tipo string. Controllo che aggiungendo elementi di una collection nulla o con indice non
     * valido, lanci le corrette eccezioni.
     *<br><br>
     * <b>Design</b>: corretto inserimento di tutti gli elementi di una collection dall'indice scelto nella Lista.
     * Inserimento di elementi di tipo diverso. Controllo dell'effettiva gestione della eccezione.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo insertElementAt di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: la lista conterrà nei primi 10 indici i primi 5 elementi Object delle due collection create tramite
     * il metodo privato getACollection e negli altri 10 indici gli altri 5 elementi String delle due collection,
     * con una dimensione pari a 20. Gestirà inoltre correttamente le eccezioni. Il metodo ritornerà true se le collection
     * verranno correttamente aggiunte, altrimento false.
     */
    @Test
    public void addAllIndexTest()
    {
        assertTrue(list.addAll(0, getACollection()));
        assertEquals(10, list.size());
        assertTrue(list.addAll(5, getACollection()));
        assertEquals(20, list.size());

        for (int i = 0; i < list.size() / 4; i++)
            assertEquals(i, list.get(i));

        for (int i = 5; i < list.size() / 2; i++)
            assertEquals(i - 5, list.get(i));

        for (int i = 10; i < (list.size() - list.size() / 4); i++)
            assertEquals("stringa " + (i - 4 - 5), list.get(i));

        for (int i = 15; i < list.size(); i++)
            assertEquals("stringa " + (i - 4 - 10), list.get(i));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.addAll(50, getACollection());});
        assertThrows(NullPointerException.class, () -> {list.addAll(5, null);});
    }

    /**
     * Test del metodo     <b>public void clear()</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che la dimensione della lista sia effettivamente 0 all'inizio del test. Aggiungo alla lista
     * un collection di dimensione 10 e controllo che la dimensione sia effettivamente del numero degli elementi
     * aggiunti, ossia 10. Successivamente chiamo il metodo clear e controllo che la dimensione sia pari a zero e che
     * quindi la lista sia stata correttamente svuotata.
     *<br><br>
     * <b>Design</b>: corretto svuotamento della Lista con degli elementi.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo removeAllElements di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo svuoterà correttamente la lista da qualsiasi elemento contenuto all'interno portando
     * la dimensione di essa a zero.
     */
    @Test
    public void clearTest()
    {
        assertEquals(0, list.size());
        list.addAll(getACollection());
        assertEquals(10, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    /**
     * Test del metodo     <b>public boolean contains(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Aggiungo alla lista list inizialmente vuota uno ad uno gli elementi di una list test ripetutamente in
     * un for per la dimensione di test e controllo che alla fine la dimensione equivalga effettivamente al numero degli elementi
     * aggiunti. Successivamente controllo che gli elementi aggiunti siano effettivamente contenuti in essa tramite due for che scorrono prima gli elementi di tipo Object e successivamente gli elementi
     * di tipo string. Controllo che ritorni false se gli passo come argomento un oggetto non presente nella lista.
     * Infine controllo che chiamando il metodo con argomento nullo, lanci la corretta eccezione.
     *<br><br>
     * <b>Design</b>: corretto controllo della presenza di un oggetto nella lista. Controllo della corretta gestione delle eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo contains di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se l'oggetto passato come argomento se è contenuto nella lista,
     * altrimento false.
     */
    @Test
    public void containsTest()
    {
        ListAdapter test = getACollection();

        for (int i = 0; i < test.size(); i++)
            assertTrue(list.add(test.get(i)));

        assertEquals(10, list.size());

        for (int i = 0; i < list.size() / 2; i++)
            assertTrue(list.contains(i));

        for (int i = 5; i < list.size(); i++)
            assertTrue(list.contains("stringa " + (i - 4)));

        assertFalse(list.contains("pippo"));

        assertThrows(NullPointerException.class, () -> {list.contains(null);});
    }

    /**
     * Test del metodo     <b>public boolean containsAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Creo una listAdapter test con il metodo privato getACollection(). Controllo che containsAll ritorni false
     * se gli passo come argomento una collection vuota. Aggiungo a list tutti gli elementi di test e controllo che la
     * sua dimensione sia effettivamente del numero degli elementi aggiunti e controllo che il metodo containsAll ritorni
     * true se gli passo la collection test come argomento. Inoltre controllo la sua validità controllando uno ad uno la
     * presenza degli elementi di test su list. Controllo che aggiungendo altri valori alla lista, il funzionamento del
     * metodo non venga compromesso. Alla fine controllo che chiamando il metodo con una collection nulla o con un oggetto
     * non di tipo HCollection, mi lanci le eccezioni correttamente.
     *<br><br>
     * <b>Design</b>: corretto controllo della presenza di tutti gli oggetti nella lista e che il funzionamento del metodo sia
     * un effettivo contains() e non un equals(). Controllo della corretta gestione delle eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo contains di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se tutti gli elementi della collection sono contenuti nella lista,
     * altrimento false.
     */
    @Test
    public void containsAllTest()
    {
        ListAdapter test = getACollection();

        assertFalse(list.containsAll(test));

        assertTrue(list.addAll(test));
        assertEquals(10, list.size());
        assertTrue(list.containsAll((HCollection) test));

        for (int i = 0; i < list.size(); i++)
            assertTrue(list.contains(test.get(i)));

        list.add(2, 111);
        list.add(4, 43531);

        assertTrue(list.containsAll(test));

        assertThrows(NullPointerException.class, () -> {list.containsAll(null);});
        assertThrows(ClassCastException.class, () -> {list.containsAll((HCollection) getAVector());});
    }

    /**
     * Test del metodo     <b>public boolean equals(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Creo una listAdapter test inizialmente vuoto e aggiungo sia a questo che alla lista list l'elemento
     * "1" e controllo che le due liste siano uguali. Aggiungo un ulteriore elemento alla lista
     * test e controllo ora che siano diverse. Controllo che la lista non sia uguale alla lista creata con il metodo privato getACollection(),
     * ad una lista nulla e ad un oggetto non di tipo ListAdapter.
     *<br><br>
     * <b>Design</b>: corretto controllo della uguaglianza fra due liste anche nei casi limite dove la lista passata come
     * argomento sia nulla o non di tipo listAdapter.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se l'oggetto passato come argomento sia una lista uguale a list,
     * altrimenti false.
     */
    @Test
    public void equalsTest()
    {
        ListAdapter test = new ListAdapter();
        test.add(1);
        list.add(1);
        assertTrue(list.equals(test));
        test.add(2);
        assertFalse(list.equals(test));

        assertFalse(list.equals(getACollection()));
        assertFalse(list.equals(null));
        assertFalse(list.equals(getAVector()));
    }

    /**
     * Test del metodo     <b>public int hashCode()</b>
     *<br><br>
     * <b>Sommario</b>: Creo due listAdapter test1, test2 inizialmente vuoti e aggiungo ad ognuna un elemento diverso. Aggiungo
     * a list lo stesso elemento aggiunto a test1 e controllo che l'hashcode di list sia uguale all'hashcode di test1 e
     * diverso dall'hashcode di test2. Aggiungo un ulteriore elemento a test1 per renderla diversa da list e controllo
     * che gli hashcode di list e test1 ora siano effettivamente diversi.
     *<br><br>
     * <b>Design</b>: corretto calcolo dell'hashcode in base agli elementi contenuti e controllo dell'uguagglianza di esso per
     * liste con elementi uguali o diversi.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornera l'hashcode corretto della lista sottoforma di integer.
     */
    @Test
    public void hashCodeTest()
    {
        ListAdapter test1 = new ListAdapter();
        test1.add(1);

        ListAdapter test2 = new ListAdapter();
        test2.add(2);

        list.add(1);

        assertEquals(test1.hashCode(), list.hashCode());
        assertNotEquals(test2.hashCode(), list.hashCode());

        test1.add(1);
        assertNotEquals(test1.hashCode(), list.hashCode());
    }

    /**
     * Test del metodo     <b>public Object get(int index)</b>
     *<br><br>
     * <b>Sommario</b>: Creo un ListAdapter test con il metodo privato getACollection() e lo uso per riempire list. Con due for,
     * uno per tipo di oggetto di list, controllo che il metodo get ritorni l'elemento effettivamente contenuto nella
     * lista all'indice i. Inoltre controllo che se passo come argomento un indice non valido, mi lanci l'eccezione
     * corretta.
     *<br><br>
     * <b>Design</b>: verifica la correttezza dell'oggetto ritornato dal metodo.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo elementAt di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornera l'oggetto nella posizione index se quest'ultima è una posizione valida.
     */
    @Test
    public void getTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.get(i));
        for (int i = 5; i < list.size(); i++)
            assertEquals("stringa " + (i - 4), list.get(i));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(999);});
    }

    /**
     * Test del metodo     <b>public int indexOf(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Creo un ListAdapter test con il metodo privato getACollection() e lo uso per riempire list. Con due for,
     * uno per tipo di oggetto di list, controllo che il metodo indexOf ritorni l'inidce del primo elemento effettivamente
     * contenuto nella lista all'indice i. Controllo che se passo come argomento un oggetto non contenuto nella lista,
     * mi ritorni l'indice -1. Controllo che se chiamo il metodo indexOf mi ritorni effettivamente l'indice del primo
     * oggetto contenuto nella lista aggiungendo l'oggetto 1 presente all'indice 1, anche in posizione finale.
     * Inoltre controllo che se passo come argomento un oggetto non valido, mi lanci l'eccezione
     * corretta.
     *<br><br>
     * <b>Design</b>: verifica la correttezza dell'indice ritornato dal metodo.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo indexOf di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornera l'indice del primo oggetto (passato come argomento) presente in questa lista
     * se quest ultimo è effettivamente contenuto, altrimenti -1.
     */
    @Test
    public void indexOfTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.indexOf(i));
        for (int i = 5; i < list.size(); i++)
            assertEquals(i, list.indexOf("stringa " + (i - 4)));

        assertEquals(-1, list.indexOf("pippo"));

        list.add(1);

        assertEquals(1, list.indexOf(1));

        assertThrows(NullPointerException.class, () -> {list.indexOf(null);});
    }

    /**
     * Test del metodo     <b>public boolean isEmpty()</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che all'inizio la lista sia effettivamente vuota. Creo un ListAdapter test con il metodo
     * privato getACollection() e con un for aggiungo gli elementi di test a list uno ad uno, controllando che ad ogni
     * ripetizione la lista ora non sia più vuota. Alla fine svuoto la lista e controllo che la dimensione sia zero e
     * che la lista torni ad essere vuota.
     *<br><br>
     * <b>Design</b>: verificare che la condizione di lista vuota equivalga alla sua dimensione pari a zero.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo isEmpty di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se la lista è effettivamente vuota, false se contiene almeno un elemento.
     */
    @Test
    public void isEmptyTest()
    {
        assertTrue(list.isEmpty());
        ListAdapter test = getACollection();

        for (int i = 0; i < test.size(); i++)
        {
            assertTrue(list.add(test.get(i)));
            assertFalse(list.isEmpty());
        }
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    /**
     * Test del metodo     <b>public HIterator iterator()</b>
     *<br><br>
     * <b>Sommario</b>: Creo un iteratore di list e controllo che non sia nullo. Aggiungo dentro a list gli elementi di un
     * ListAdapter creato con il metodo privato getACollection() e creo un nuovo iteratore test di list. Controllo con
     * un for che tutti gli elementi di list siano uguali agli elementi che scorre l'iteratore test tramite il metodo
     * next(). Creo un secondo iteratore test1 per controllare con un while che il metodo hasNext() funzioni e si
     * comporti allo stesso modo del for testato precedentemente. Verifico che il metodo remove dell'iteratore
     * rimuova effettivamente l'ultimo oggetto controllato da test1 controllando che la dimensione della lista sia
     * diminuita e che l'ultimo oggetto della lista non sia più contenuto in essa. Infine verifico che se viene invocato
     * il metodo next() di un iteratore arrivato a fine lista o se invoco il metodo remove ripetutamente, vengono lanciate
     * le relative eccezioni.
     *<br><br>
     * <b>Design</b>: verificare il corretto funzionamento del metodo testando ogni metodo dell'iteratore, controllando
     * che esso agisca effettivamente sulla lista su cui è stato creato e che gestisca correttamente  le eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna un nuovo iteratore ad ogni chiamata che permette di scorrere corretamente
     * la lista e di rimuovere l'ultimo oggetto analizzato da esso. Gestisce correttamente le eccezioni.
     */
    @Test
    public void iteratorTest()
    {
        HIterator testNull = list.iterator();
        assertNotNull(testNull);

        list.addAll(getACollection());
        HIterator test = list.iterator();

        for (int i = 0; i < list.size(); i++)
            assertSame(test.next(), list.get(i));

        HIterator test1 = list.iterator();
        int j = 0;
        while (test1.hasNext())
            assertSame(test1.next(), list.get(j++));

        assertEquals(j, list.size());
        int size = list.size();
        test1.remove();
        assertEquals(size - 1, list.size());
        assertFalse(list.contains("stringa 5"));

        assertThrows(NoSuchElementException.class, () -> {test1.next();});
        assertThrows(IllegalStateException.class, () -> {test1.remove();});
    }

    /**
     * Test del metodo     <b>public int lastIndexOf(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Creo un ListAdapter test con il metodo privato getACollection() e lo uso per riempire list. Con due for,
     * uno per tipo di oggetto di list, controllo che il metodo lastIndexOf ritorni l'inidce dell'ultimo elemento effettivamente
     * contenuto nella lista all'indice i. Controllo che se passo come argomento un oggetto non contenuto nella lista,
     * mi ritorni l'indice -1. Controllo che se chiamo il metodo lastIndexOf mi ritorni effettivamente l'indice dell'ultimo
     * oggetto contenuto nella lista aggiungendo l'oggetto 1 presente all'indice 1, anche in posizione finale.
     * Inoltre controllo che se passo come argomento un oggetto non valido, mi lanci l'eccezione
     * corretta.
     *<br><br>
     * <b>Design</b>: verifica la correttezza dell'indice ritornato dal metodo.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo indexOf di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornera l'indice dell'ultimo oggetto (passato come argomento) presente in questa lista
     * se questo è effettivamente contenuto, altrimenti -1.
     */
    @Test
    public void lastIndexOfTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.lastIndexOf(i));
        for (int i = 5; i < list.size(); i++)
            assertEquals(i, list.lastIndexOf("stringa " + (i - 4)));

        assertEquals(-1, list.lastIndexOf("pippo"));

        list.add(1);

        assertEquals(list.size() - 1, list.lastIndexOf(1));

        assertThrows(NullPointerException.class, () -> {list.indexOf(null);});
    }

    /**
     * Test del metodo     <b>public HListIterator listIterator()</b>
     *<br><br>
     * <b>Sommario</b>: Creo un listIterator di list e controllo che non sia nullo. Aggiungo dentro a list gli elementi di un
     * ListAdapter creato con il metodo privato getACollection() e creo un nuovo listIterator test di list. Controllo con
     * un for che tutti gli elementi di list siano uguali agli elementi che scorre il listIterator test tramite il metodo
     * next(). Creo un secondo listIterator test1 per controllare con un while che il metodo hasNext() funzioni e si
     * comporti allo stesso modo del for testato precedentemente. Verifico che il metodo remove del listIterator
     * rimuova effettivamente l'ultimo oggetto controllato da test1 controllando che la dimensione della lista sia
     * diminuita e che l'ultimo oggetto della lista non sia più contenuto in essa. Infine verifico che se viene invocato
     * il metodo next() di un listIterator arrivato a fine lista o se invoco il metodo remove ripetutamente, vengono lanciate
     * le relative eccezioni. DA FINIRE
     *<br><br>
     * <b>Design</b>: verificare il corretto funzionamento del metodo testando ogni metodo dell'iteratore, controllando
     * che esso agisca effettivamente sulla lista su cui è stato creato e che gestisca correttamente  le eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna un nuovo iteratore ad ogni chiamata che permette di scorrere corretamente
     * la lista e di rimuovere l'ultimo oggetto analizzato da esso. Gestisce correttamente le eccezioni.
     */
    @Test
    public void listIteratorTest()
    {
        HListIterator testNull = list.listIterator();
        assertNotNull(testNull);

        list.addAll(getACollection());
        HListIterator test = list.listIterator();

        for (int i = 0; i < list.size(); i++)
            assertEquals(test.next(), list.get(i));

        HListIterator test1 = list.listIterator();
        int j = 0;
        while (test1.hasNext())
            assertEquals(test1.next(), list.get(j++));

        assertEquals(j, list.size());
        int size = list.size();
        test1.remove();
        assertEquals(size - 1, list.size());
        assertFalse(list.contains("stringa 5"));

        assertThrows(NoSuchElementException.class, test1::next);
        assertThrows(IllegalStateException.class, test1::remove);
    }

    @Test
    public void listIteratorIndexTest()
    {
        HListIterator testNull = list.listIterator();
        assertNotNull(testNull);

        list.addAll(getACollection());
        HListIterator test = list.listIterator(5);

        int j = 0;
        while (test.hasNext())
            assertEquals(test.next(), list.get(j++ + 5));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.listIterator(999);});
    }

    /**
     * Test del metodo     <b>public Object remove(int index)</b>
     *<br><br>
     * <b>Sommario</b>: Creo un ListAdapter test con il metodo privato getACollection() e aggiungo tutti i suoi elementi a list.
     * Istanzio tre nuove variabili; un Object tmp che successivamente conterrà l'oggetto da confrontare con
     * l'oggetto ritornato dal metodo remove(0) per verificare che siano effettivamente uguali, un integer count per
     * tener traccia dell'effettiva riduzione della dimensione della lista ad ogni invocazione del metodo remove e un
     * altro integer size per poter iterare l'iperazione di remove un numero di volte pari alla dimensione iniziale della lista.
     * Una volta rimossi tutti gli elementi da list, riaggiungo tutti gli elementi di test ad essa, assegno alla variabile
     * tmp il quinto elemento della lista per poter verificare che il metodo remove ritorni lo stesso oggetto
     * invocandolo con index pari a 5 e successivamente con index pari a 6 per confermare che l'oggetto ritornato sia
     * diverso dalla variabile tmp. Verifico che la dimensione della lista sia diminuita di due unità dopo aver invocato
     * remove due volte. Infine verifico il lancio della eccezioni corretta se viene invocato remove con un indice non
     * valido.
     *<br><br>
     * <b>Design</b>: verificare la corretta rimozione degli oggetti dalla lista in base all'indice scelto e che ritorni
     * l'oggetto appena rimosso, la riduzione della dimensione della lista in base al numero di volte che viene invocato
     * il metodo e che gestisca correttamente l'eccezione.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo removeElementAt di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo rimuove correttamente l'elemento di indice i e lo ritorna, riducendo la dimensione
     * della lista, a seconda del numero di volte che viene invocato. Gestisce correttamente l'eccezione.
     */
    @Test
    public void removeIndexTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        Object tmp;
        int count = list.size();
        int size = list.size();

        for (int i = 0; i < size; i++)
        {
            tmp = list.get(0);
            assertSame(tmp, list.remove(0));
            assertEquals(--count, list.size());
        }

        list.addAll(test);
        tmp = list.get(5);
        assertSame(tmp, list.remove(5));
        assertNotSame(tmp, list.remove(8));
        assertEquals(size - 2, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(999);});
    }

    /**
     * Test del metodo     <b>public boolean remove(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Creo un ListAdapter test con il metodo privato getACollection() e aggiungo tutti i suoi elementi a list.
     * Istanzio due nuove variabili; un integer count per tener traccia dell'effettiva riduzione della dimensione della
     * lista ad ogni invocazione del metodo remove e un altro integer size per poter iterare l'iperazione di remove un
     * numero di volte pari alla dimensione iniziale della lista. Itero queste azioni con un for per i primi 5 oggetti della lista e
     * ripeto fuori da esso, altre 5 volte per i rimanenti oggetti di essa. Una volta rimossi tutti gli elementi da list,
     * riaggiungo tutti gli elementi di test ad essa, verifico che ritorni true se invoco remove per un oggetto presente
     * nella lista e che invece ritorni false per un oggetto non presente in essa. Verifico che la dimensione della
     * lista sia diminuita di una unità dopo aver invocato remove una volta. Infine verifico il lancio della eccezioni
     * NullPointerException se viene invocato remove con un oggetto null.
     *<br><br>
     * <b>Design</b>: verificare la corretta rimozione degli oggetti dalla lista in base all'oggetto passato, la riduzione
     * della dimensione della lista in base al numero di volte che viene invocato il metodo e che gestisca correttamente
     * l'eccezione.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo removeElement di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se l'oggetto passato è presente nella lista rimuovendolo e
     * riducendone la dimensione del set,
     * altrimenti ritorna false. Gestisce correttamente l'eccezione.
     */
    @Test
    public void removeObjectTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        int count = list.size();
        int size = list.size();

        for (int i = 0; i < size / 2; i++)
        {
            assertTrue(list.remove((Object)i));
            assertEquals(--count, list.size());
        }

        assertTrue(list.remove("stringa " + 1));
        assertEquals(--count, list.size());
        assertTrue(list.remove("stringa " + 2));
        assertEquals(--count, list.size());
        assertTrue(list.remove("stringa " + 3));
        assertEquals(--count, list.size());
        assertTrue(list.remove("stringa " + 4));
        assertEquals(--count, list.size());
        assertTrue(list.remove("stringa " + 5));
        assertEquals(--count, list.size());


        list.addAll(test);
        assertTrue(list.remove("stringa 2"));
        assertFalse(list.remove("stringa 200"));
        assertEquals(size - 1, list.size());

        assertThrows(NullPointerException.class, () -> {list.remove(null);});
    }

    /**
     * Test del metodo     <b>public boolean removeAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Creo un ListAdapter test con il metodo privato getACollection(), aggiungo tutti i suoi elementi a list
     * due volte e controllo che la dimensione di list sia effettivamente 20. Verifico che rimuovendo tutti gli elementi
     * della collection test da list, il metodo ritorni true e che la dimensione di quest'ultima sia 10. Rimuovo tutti
     * gli elementi dalla lista e aggiungo ad essa l'oggetto "pippo" 10 volte. Verifico che il metodo ritorni false se
     * esso non modifica la lista, non essendo presente l'elemento "pippo" nella collection data e che la dimensione
     * della lista sia rimasta sempre a 10. Infine verifico il lancio della eccezione NullPointerException se viene
     * invocato remove con un oggetto null e della eccezione ClassCastException se viene invocato con un oggetto non di
     * tipo HCollection.
     *<br><br>
     * <b>Design</b>: verificare la corretta rimozione di tutti gli elementi nella collection dalla lista, la riduzione
     * della dimensione della lista in base al numero di oggetti rimossi, che ritorni true solo se vengono effettuate
     * rimozioni e che gestisca correttamente le eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se vengono effettuate delle rimozioni dalla lista di oggetti presenti
     * sia nella collection che nella lista riducendone la dimensione in base al numero di oggetti rimossi,
     * altrimenti ritorna false se non vengono effettuate rimozioni. Gestisce correttamente le eccezioni.
     */
    @Test
    public void removeAllTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);
        list.addAll(test);

        assertEquals(20, list.size());
        assertTrue(list.removeAll((HCollection) test));
        assertEquals(10, list.size());

        list.clear();
        for (int i = 0; i < 10; i++) list.add("pippo");
        assertFalse(list.removeAll((HCollection) test));
        assertEquals(10, list.size());


        assertThrows(NullPointerException.class, () -> {list.removeAll(null);});
        assertThrows(ClassCastException.class, () -> {list.removeAll((HCollection) getAVector());});
    }

    /**
     * Test del metodo     <b>public boolean retainAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Creo un ListAdapter test con il metodo privato getACollection(), aggiungo tutti i suoi elementi a list
     * e controllo che la dimensione di list sia effettivamente 10. Verifico che trattenendo tutti gli elementi
     * della collection test da list, il metodo ritorni false e che la dimensione di quest'ultima sia ancora 10 poiché
     * entrambe contengono gli stessi elementi.
     * Rimuovo tutti gli elementi dalla lista, aggiungo ad essa i numeri interi da 0 a 9 e mi salvo in una variabile
     * la nuova dimensione della lista. Mantengo nella lista tutti gli elementi presenti nella collection test invocando
     * il metodo, ossia vengono rimossi da list tutti gli elementi non presenti nella collection (in questo esempio
     * tutte le stringhe) e confronto se la rimozione è andata a buon fine col il for iterato per la dimensione di list.
     * Verifico che la nuova dimensione della lista sia esattamente la metà di quella precedente all'invocazione del
     * metodo poiché il metodo ha rimosso le cinque stringhe presenti in list. Infine verifico il lancio della eccezione
     * NullPointerException se viene invocato retainAll con una collection null e della eccezione ClassCastException se
     * viene invocato con un oggetto non di tipo HCollection.
     *<br><br>
     * <b>Design</b>: verificare il corretto mantenimento di tutti gli elementi nella collection dalla lista, e quindi la
     * rimozione da questa degli elementi non presenti nella collection, la riduzione della dimensione della lista in
     * base al numero di oggetti rimossi, che ritorni true solo se vengono effettuate rimozioni e che gestisca
     * correttamente le eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se vengono effettuate delle rimozioni dalla lista di oggetti non presenti
     * nella collection riducendone la dimensione in base al numero di oggetti rimossi, altrimenti ritorna false se
     * non vengono effettuate rimozioni. Gestisce correttamente le eccezioni.
     */
    @Test
    public void retainAllTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        assertEquals(10, list.size());
        assertFalse(list.retainAll((HCollection) test));
        assertEquals(10, list.size());

        list.clear();
        for (int i = 0; i < 10; i++) list.add(i);
        int size = list.size();
        assertTrue(list.retainAll((HCollection) test));
        for (int i = 0; i < list.size(); i++)
            assertEquals(i, list.get(i));

        assertEquals(size / 2, list.size());

        assertThrows(NullPointerException.class, () -> {list.retainAll(null);});
        assertThrows(ClassCastException.class, () -> {list.retainAll((HCollection) getAVector());});
    }

    /**
     * Test del metodo     <b>public Object set(int index, Object element)</b>
     *<br><br>
     * <b>Sommario</b>: Aggiungo a list gli elementi di una lista creata con getACollection(). Istanzio due nuove variabili;
     * un Object tmp che successivamente conterrà l'oggetto da confrontare con l'oggetto ritornato dal metodo set per
     * verificare che siano effettivamente uguali e un integer size per poter verificare che la dimenzione di list non
     * varia. Dentro il for imposto, con il metodo da testare, l'elemento i*i in posizione i e verifico che, oltre appunto
     * a ritornare l'elemento precedentemente contenuto in quell'indice, che il valore i*i sia stato impostato
     * correttamente. Infine verifico il lancio della eccezione NullPointerException se viene invocato il set di un
     * elemento nullo e della eccezione IndexOutOfBoundsException se viene invocato set con un indice non valido.
     *<br><br>
     * <b>Design</b>: verificare la corretta impostazione dell'elemento passato nella posizione desiderata, che ritorni, se
     * presente, l'elemento precedemente contenuto nell'indice e che gestisca correttamente le eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo setElementAt di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo imposta l'elemento nella posizione scelta correttamente, ritornando l'elemento
     * precedentemente contenuto in quella posizione se presente. Gestisce correttamente le eccezioni.
     */
    @Test
    public void setTest()
    {
        list.addAll(getACollection());

        Object tmp;
        int size = list.size();

        for (int i = 0; i < size; i++)
        {
            tmp = list.get(i);
            assertSame(tmp, list.set(i, i*i));
            assertEquals(i*i, list.get(i));
            assertEquals(size, list.size());
        }

        assertThrows(NullPointerException.class, () -> {list.set(0, null);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.set(-1, 1);});
    }

    /**
     * Test del metodo     <b>public int size()</b>
     *<br><br>
     * <b>Sommario</b>: Verifico che la dimensione della lista inizialmente sia zero. Sfrutto un for che aggiunge esattamente
     * un elemento a list per iterazione e confronto ogni volta la dimensione della lista con un contatore che
     * riproduce il corretto funzionamento dell'incremento della dimensione di una lista dopo l'aggiunta di un elemento.
     * A fine iterazioni confronto per l'ultima volta la dimensione della lista con la variabile count, svuoto list e
     * verifico che la dimensione sia tornata a zero.
     *<br><br>
     * <b>Design</b>: verificare la corretteza del metodo size controllando la coerenza di esso con un confronto con una
     * variabile che riproduce lo stesso meccanismo del metodo.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo size di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna l'effettiva dimensione della lista.
     */
    @Test
    public void sizeTest()
    {
        assertEquals(0, list.size());

        int count = 0;
        for (int i = 0; i < 10; i ++)
        {
            list.add(i);
            assertEquals(++count, list.size());
        }

        assertEquals(count, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    /**
     * Test del metodo     <b>public HList subList(int fromIndex, int toIndex)</b>
     *<br><br>
     * <b>Sommario</b>: Aggiungo a list due volte gli elementi della lista generata dal metodo privato getACollection().
     * Controllo che il metodo subList mi ritorni una sublist non null. Infine verifico il lancio della eccezione
     * IndexOutOfBoundsException se viene invocato subList con degli indici non validi.
     *<br><br>
     * <b>Design</b>: verificare la corretta generazione di una subList e che gestisca correttamente le eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo genera una subList utilizzabile. Gestisce correttamente le eccezioni.
     */
    @Test
    public void subListTest()
    {
        list.addAll(getACollection());
        list.addAll(getACollection());

        assertNotNull(list.subList(2, 18));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.subList(-1, 10);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.subList(10, 999);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.subList(15, 5);});
    }

    /**
     * Test del metodo     <b>public Object[] toArray()</b>
     *<br><br>
     * <b>Sommario</b>: Aggiungo a list gli elementi di una lista creata con getACollection(). Istanzio un nuovo array di
     * Object della dimesione di list e ci copio all'interno un elemento di list per ogni iterazione del for. Infine
     * verifico che l'array appena creato sia uguale all'array ritornato dal metodo toArray.
     *<br><br>
     * <b>Design</b>: verificare che il metodo ritorni effettivamente un array con tutti gli elementi della lista nell'ordine
     * corretto.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo copyInto di Vector funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo ritorna correttamente un array con tutti gli elementi della lista.
     */
    @Test
    public void toArrayTest()
    {
        list.addAll(getACollection());
        Object[] test = new Object[list.size()];

        for (int i = 0; i < list.size(); i++)
            test[i] = list.get(i);

        assertArrayEquals(test, list.toArray());
    }

    /**
     * Test del metodo     <b>public Object[] toArray(Object[] a)</b>
     *<br><br>
     * <b>Sommario</b>: Aggiungo a list gli elementi di una lista creata con getACollection(). Istanzio tre nuovi array di
     * Object; il primo, test,  della dimesione di list, il secondo, test1, della dimensione di list + 5 e il terzo,
     * test2, della dimensione di list - 5. Nel primo copio all'interno un elemento di list per ogni iterazione del for
     * e verifico che l'array appena creato sia uguale all'array ritornato dal metodo toArray. Passo il secondo array
     * come argomento del metodo toArray e verifico tramite un for che i nuovi elementi di test1 siano uguali agli
     * elementi di test. Eseguo la stessa cosa con il terzo array, salvandomi però l'array ritornato dal metodo in un
     * nuovo array di Object test2_1 poiché la dimensione di test2 è minore rispetto alla dimensione di list. Infine
     * verifico che venga lanciata l'eccezione NullPointerException se l'array passato come argomento è null.
     *<br><br>
     * <b>Design</b>: verificare che il metodo ritorni effettivamente un array con tutti gli elementi della lista nell'ordine
     * corretto se la dimensione di quello passato per argomento è minore rispetto a quella della lista o che sovrascriva
     * i dati di quello passato per argomento se la sua dimensione è maggiore o uguale a quella della lista. Verificare
     * la corretta gestione del'eccezione.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo ListAdapter correttamente inizializzato con dimensione uguale a zero.
     *<br><br>
     * <b>Post-Condizioni</b>: la lista torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo ritorna correttamente un array con tutti gli elementi della lista o sovrascrive i
     * dati di quello passato come argomento in base alla dimensione di quest'ultimo.
     */
    @Test
    public void toArrayArgTest()
    {
        list.addAll(getACollection());
        Object[] test = new Object[list.size()];
        Object[] test1 = new Object[list.size() + 5];
        Object[] test2 = new Object[list.size() - 5];

        for (int i = 0; i < list.size(); i++)
            test[i] = list.get(i);

        assertArrayEquals(test, list.toArray(test));

        list.toArray(test1);
        for (int i = 0; i < list.size(); i++)
            assertEquals(test[i], test1[i]);

        Object[] test2_1 = list.toArray(test2);
        for (int i = 0; i < list.size(); i++)
            assertEquals(test[i], test2_1[i]);

        assertThrows(NullPointerException.class, () -> {list.toArray(null);});
    }

    // metodi helper
    private ListAdapter getACollection()
    {
        ListAdapter l = new ListAdapter();
        for (int i = 0; i < 5; i++)
            l.add(i, i);

        for (int i = 5; i < 10; i++)
            l.add(i, "stringa " + (i-4));

        return l;
    }

    private Vector getAVector()
    {
        Vector test = new Vector();
        for (int i = 0; i < 10; i++) test.addElement(i);
        return test;
    }
}