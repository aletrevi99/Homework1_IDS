package myTest;

import myAdapter.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Vector;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class EntrySetTester
{
    private MapAdapter map;
    private HSet eSet;

    /**
     * Istanzia un nuovo EntrySet pieno prima di ogni test.
     * */
    @Before
    public void initialize()
    {
        map = getAMap();
        eSet = map.entrySet();
    }

    /**
     * Se a fine di ogni test eSet non è vuota, viene svuotata.
     * */
    @After
    public void clear()
    {
        if (!eSet.isEmpty()) eSet.clear();
    }

    /**
     * Test delle pre-condizioni di ogni metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: Stampa su terminale i valori di getAMap().
     * */
    @Test
    public void initializeTest()
    {
        assertEquals(10, eSet.size());
    }

    /**
     * Test del metodo     <b>public boolean add(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che il metodo lanci UnsupportedOperationException se aggiungo un elemento.
     *<br><br>
     * <b>Design</b>: corretto comportamento del metodo.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo conferma che non può essere utilizzato.
     */
    @Test
    public void addTest()
    {
        assertThrows(UnsupportedOperationException.class, () -> {eSet.add("test");});
    }

    /**
     * Test del metodo     <b>public boolean addAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che il metodo lanci UnsupportedOperationException se aggiungo una collection.
     *<br><br>
     * <b>Design</b>: corretto comportamento del metodo.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo conferma che non può essere utilizzato.
     */
    @Test
    public void addAllTest()
    {
        assertThrows(UnsupportedOperationException.class, () -> {eSet.addAll((HCollection) getACollection());});
    }

    /**
     * Test del metodo     <b>public void clear()</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che la dimensione del set sia effettivamente 10 all'inizio del test. Successivamente
     * chiamo il metodo clear e controllo che la dimensione sia pari a zero e che
     * quindi il set sia stato correttamente svuotato.
     *<br><br>
     * <b>Design</b>: corretto svuotamento del set con degli elementi.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo svuoterà correttamente la lista da qualsiasi elemento contenuto all'interno portando
     * la dimensione di essa a zero.
     */
    @Test
    public void clearTest()
    {
        assertEquals(10, eSet.size());
        eSet.clear();
        assertEquals(0, eSet.size());
    }

    /**
     * Test del metodo     <b>public boolean contains(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che gli elementi di eSet siano effettivamente contenuti in essa tramite un for che
     * scorre gli elementi di un toArray di eSet.
     * Infine controllo che chiamando il metodo con argomento nullo, lanci NullPointerException e che che chiamando il
     * metodo con argomento non Entry, lanci ClassCastException.
     *<br><br>
     * <b>Design</b>: corretto controllo della presenza di un Entry in eSet. Controllo della corretta gestione delle eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>:oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10 e che il
     * metodo get di Hashtable funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se l'Entry passato come argomento è contenuto nel set,
     * altrimento false. Gestisce correttamente le eccezioni.
     */
    @Test
    public void containsTest()
    {
        for (int i = 0; i < eSet.size(); i++)
            assertTrue(eSet.contains(eSet.toArray()[i]));

        assertThrows(NullPointerException.class, () -> {eSet.contains(null);});
        assertThrows(ClassCastException.class, () -> {eSet.contains("pippo");});
    }

    /**
     * Test del metodo     <b>public boolean containsAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Creo due HSet, test con gli stessi Entry di eSet e test2 con alcuni Entry non contenuti in eSet.
     * Controllo che non tutti gli Entry di test2 siano presenti in eSet, verifico tutti gli Entry di test siano
     * presenti in eSet e successivamente mi accerto di questo con un for che itera il metodo contains su eSet per tutti
     * gli Entry di test. Alla fine controllo che chiamando il metodo con una collection nulla o con un oggetto
     * non di tipo HCollection, mi lanci rispettivamente NullPointerException e ClassCastException.
     *<br><br>
     * <b>Design</b>: corretto controllo della presenza di tutti gli Entry in eSet. Controllo della corretta gestione
     * delle eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: il set torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se tutti gli Entry della collection sono contenuti nel set,
     * altrimento false.
     */
    @Test
    public void containsAllTest()
    {
        MapAdapter map2 = getAMap2();
        HSet test = map.entrySet();
        HSet test2 = map2.entrySet();

        assertFalse(eSet.containsAll((HCollection) test2));

        assertTrue(eSet.containsAll((HCollection) test));

        for (int i = 0; i < eSet.size(); i++)
            assertTrue(eSet.contains(test.toArray()[i]));

        assertThrows(NullPointerException.class, () -> {eSet.containsAll(null);});
        assertThrows(ClassCastException.class, () -> {eSet.containsAll((HCollection) getAVector());});
    }

    /**
     * Test del metodo     <b>public boolean isEmpty()</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che il metodo ritorni false quando ho verificato che la dimensione di eSet sia 10.
     * Svuoto eSet, controllo che la dimensione sia 0 e verifico che eSet torni ad essere vuoto.
     * Alla fine creo un mappa con un solo elemento, genero il suo EntrySet e controllo che non sia vuoto e che la
     * dimensione sia effettivamente 1.
     *<br><br>
     * <b>Design</b>: verificare che la condizione di set vuoto equivalga alla sua dimensione pari a 0.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10 e che il
     * metodo isEmpty di Hashtable funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se il set è effettivamente vuota, false se contiene almeno un Entry.
     */
    @Test
    public void isEmptyTest()
    {
        assertEquals(10, eSet.size());
        assertFalse(eSet.isEmpty());
        eSet.clear();
        assertEquals(0, eSet.size());
        assertTrue(eSet.isEmpty());
        MapAdapter test = new MapAdapter();
        test.put(1,1);
        HSet testSet = test.entrySet();
        assertFalse(testSet.isEmpty());
        assertEquals(1, testSet.size());
    }

    /**
     * Test del metodo     <b>public HIterator iterator()</b>
     *<br><br>
     * <b>Sommario</b>: Creo un iteratore test di eSet e controllo che non sia nullo. Controllo con
     * un for che tutti gli Entry di eSet(ottenuti con un toArray()) siano uguali agli Entry che scorre l'iteratore test tramite il metodo
     * next(). Creo un secondo iteratore test1 per controllare con un while che il metodo hasNext() funzioni e si
     * comporti allo stesso modo del for testato precedentemente. Verifico che il metodo remove() dell'iteratore
     * rimuova effettivamente l'ultimo Entry controllato da test1 controllando che la dimensione del set sia
     * diminuita. Infine verifico che se viene invocato
     * il metodo next() di un iteratore arrivato a fine set o se invoco il metodo remove ripetutamente, vengono lanciate
     * rispettivamente NoSuchElementException e IllegalStateException.
     *<br><br>
     * <b>Design</b>: verificare il corretto funzionamento del metodo iterator testando ogni metodo dell'iteratore, controllando
     * che esso agisca effettivamente sul set su cui è stato creato e che gestisca correttamente le eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna un nuovo iteratore ad ogni chiamata, che permette di scorrere corretamente
     * il set e di rimuovere l'ultimo Entry analizzato da esso. Gestisce correttamente le eccezioni.
     */
    @Test
    public void iteratorTest()
    {
        HIterator test = eSet.iterator();
        assertNotNull(test);

        int y = eSet.size();
        for (int i = 0; i < eSet.size(); i++)
            assertEquals(test.next(), eSet.toArray()[--y]);

        HIterator test1 = eSet.iterator();
        int j = eSet.size();
        while (test1.hasNext())
        {
            assertEquals(test1.next(), eSet.toArray()[--j]);
            y++;
        }
        assertEquals(y, eSet.size());
        int size = eSet.size();
        test1.remove();
        assertEquals(size - 1, eSet.size());

        assertThrows(NoSuchElementException.class, () -> {test1.next();});
        assertThrows(IllegalStateException.class, () -> {test1.remove();});
    }

    /**
     * Test del metodo     <b>public boolean remove(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Istanzio due nuove variabili; un integer count per tener traccia dell'effettiva riduzione
     * della dimensione del set ad ogni invocazione del metodo remove e un altro integer size per poter iterare
     * l'operazione di remove un numero di volte pari alla dimensione iniziale del set. Itero queste azioni con un for.
     * Una volta rimossi tutti gli elementi da eSet, aggiungo una mappature k/v a map e mi ricreo eSet tramite
     * entrySet(), creo una nuova mappa con una mappatura k/v diversa da quella aggiunta a map e mi creo il relativo
     * HSet di Entry. Questo mi permette di verificare che il metodo remove ritorni false se provo a invocarlo con un
     * entry non presente nel set. Infine verifico il lancio della eccezione NullPointerException se viene invocato
     * con un oggetto null.
     *<br><br>
     * <b>Design</b>: verificare la corretta rimozione degli oggetti dal set in base all'Entry passato, la riduzione
     * della dimensione del set in base al numero di volte che viene invocato il metodo e che gestisca correttamente
     * l'eccezione.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10 e che il
     * remove di MapAdapter funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se l'Entry passato è presente nel set rimuovendolo e
     * riducendone la dimensione del set,
     * altrimenti ritorna false. Gestisce correttamente l'eccezione.
     */
    @Test
    public void removeObjectTest()
    {
        int count = eSet.size();
        int size = eSet.size();

        for (int i = 0; i < size; i++)
        {
            assertTrue(eSet.remove(eSet.toArray()[0]));
            assertEquals(--count, eSet.size());
        }

        map.put(0, "stringa 0");
        eSet = map.entrySet();

        MapAdapter testMap = new MapAdapter();
        testMap.put(0, "pippo");
        HSet testSet = testMap.entrySet();

        assertFalse(eSet.remove(testSet.toArray()[0]));
        assertEquals(1, eSet.size());

        assertThrows(NullPointerException.class, () -> {eSet.remove(null);});
    }

    /**
     * Test del metodo     <b>public boolean removeAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Creo due HSet, test con gli stessi Entry di eSet e test2 con nessun Entry contenuto in eSet
     * e controllo che la dimensione di eSet sia effettivamente 10. Verifico che rimuovendo tutti gli Entry
     * della collection test da eSet, il metodo ritorni true e che la dimensione di quest'ultimo sia 0. Aggiungo a map,
     * con for iterato 10 volte, la mappatura k/v (i + 20)/(i) e rigenero eSet, il quale ora è un set di queste
     * 10 nuove mappature k/v. Verifico che tentando di rimuovere da eSet gli Entry della
     * collection test2 creata precedentemente ritorni false, poiché non c'é alcun Entry in comune fra i due e quindi eSet
     * rimane immutato. Verifico che la dimensione di eSet sia rimasta 10.
     * Infine verifico il lancio della eccezione NullPointerException se viene
     * invocato removeAll con una collection null e della eccezione ClassCastException se viene invocato con un
     * oggetto non di tipo HCollection.
     *<br><br>
     * <b>Design</b>: verificare la corretta rimozione di tutti gli Entry presenti nella collection dal set, la riduzione
     * della dimensione della set in base al numero di Entry rimossi, che ritorni true solo se vengono effettuate
     * rimozioni e che gestisca correttamente le eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se vengono effettuate delle rimozioni dal set di Entry presenti
     * sia nella collection che nel set riducendone la dimensione in base al numero di Entry rimossi,
     * altrimenti ritorna false se non vengono effettuate rimozioni. Gestisce correttamente le eccezioni.
     */
    @Test
    public void removeAllTest()
    {
        MapAdapter map2 = getAMap2();
        HSet test = map.entrySet();
        HSet test2 = map2.entrySet();

        assertEquals(10, eSet.size());

        assertTrue(eSet.removeAll((HCollection) test));
        assertEquals(0, eSet.size());

        for (int i = 0; i < 10; i++) map.put(i + 20, i);
        eSet = map.entrySet();

        assertFalse(eSet.removeAll((HCollection) test2));
        assertEquals(10, eSet.size());

        assertThrows(NullPointerException.class, () -> {eSet.removeAll(null);});
        assertThrows(ClassCastException.class, () -> {eSet.removeAll((HCollection) getAVector());});
    }

    /**
     * Test del metodo     <b>public boolean retainAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Creo due HSet, test con gli stessi Entry di eSet e test3 con i primi 5 Entry contenuti in eSet
     * e controllo che la dimensione di eSet sia effettivamente 10. Verifico che trattenendo tutti gli elementi
     * del set test da eSet, il metodo ritorni false e che la dimensione di quest'ultimo sia ancora 10 poiché
     * entrambi contengono gli stessi Entry.
     * Mi salvo in una variabile
     * la dimensione di eSet. Mantengo in eSet tutti gli Entry presenti nel set test3 invocando
     * il metodo, ossia vengono rimossi da eSet tutti gli Entry non presenti in test3 (in questo esempio tutti gli Entry con chiave
     * di tipo string) e verifico se la rimozione è andata a buon fine col il for iterato per la dimensione di eSet,
     * confrontando gli Entry di test con gli Entry di eSet.
     * Verifico che la nuova dimensione di eSet sia esattamente la metà di quella precedente all'invocazione del
     * metodo poiché il metodo ha rimosso i cinque Entry con chiave di tipo string presenti in eSet. Infine verifico il lancio della eccezione
     * NullPointerException se viene invocato retainAll con una collection null e della eccezione ClassCastException se
     * viene invocato con un oggetto non di tipo HCollection.
     *<br><br>
     * <b>Design</b>: verificare il corretto mantenimento di tutti gli Entry presenti nella collection da eSet, e quindi la
     * rimozione da questo degli Entry non presenti nella collection, la riduzione della dimensione di eSet in
     * base al numero di Entry rimossi, che ritorni true solo se vengono effettuate rimozioni e che gestisca
     * correttamente le eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se vengono effettuate delle rimozioni dal set di Entry non presenti
     * nella collection riducendone la dimensione in base al numero di Entry rimossi, altrimenti ritorna false se
     * non vengono effettuate rimozioni. Gestisce correttamente le eccezioni.
     */
    @Test
    public void retainAllTest()
    {
        MapAdapter map3 = getAMap3();
        HSet test = map.entrySet();
        HSet test3 = map3.entrySet();

        assertEquals(10, eSet.size());
        assertFalse(eSet.retainAll((HCollection) test));
        assertEquals(10, eSet.size());

        int size = eSet.size();
        assertTrue(eSet.retainAll((HCollection) test3));
        for (int i = 0; i < eSet.size(); i++)
            assertEquals(test.toArray()[i], eSet.toArray()[i]);

        assertEquals(size / 2, eSet.size());

        assertThrows(NullPointerException.class, () -> {eSet.retainAll(null);});
        assertThrows(ClassCastException.class, () -> {eSet.retainAll((HCollection) getAVector());});
    }

    /**
     * Test del metodo     <b>public int size()</b>
     *<br><br>
     * <b>Sommario</b>: Creo un iteratore di eSet e lo scorro in un while finché ha Entry. Ad ogni iterazione incremento la
     * variabile count che tiene traccia della dimensione di eSet, verifico che a fine ciclo sia effettivamente della
     * dimensione di eSet (ossia 10). Rimuovo un Entry con il remove dell'iteratore e verifico che la dimensione di eSet
     * sia diminuita di una unità. Infine svuoto eSet e controllo che la sua dimensione sia tornata a 0.
     *<br><br>
     * <b>Design</b>: verificare la corretteza del metodo size controllando la coerenza di esso con un confronto con una
     * variabile che riproduce lo stesso meccanismo del metodo.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10 e che il
     * metodo size di Hashtable funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna l'effettiva dimensione del set.
     */
    @Test
    public void sizeTest()
    {
        HIterator iter = eSet.iterator();

        int count = 0;
        while(iter.hasNext())
        {
            count++;
            iter.next();
        }

        assertEquals(10, count);
        assertEquals(count, eSet.size());
        iter.remove();
        assertEquals(--count, eSet.size());
        eSet.clear();
        assertEquals(0, eSet.size());
    }

    /**
     * Test del metodo     <b>public Object[] toArray()</b>
     *<br><br>
     * <b>Sommario</b>: Istanzio un nuovo array test di Object della dimesione di eSet e ci copio all'interno un Entry per
     * ogni iterazione di un for che scorre gli indici al contrario, poiché l'iteratore naviga nel set in ordine
     * inverso (per definizione di Enumeration). Verifico che tutti gli Entry di test siano presenti in eSet tramite un for.
     * Infine verifico che test sia uguale all'array ritornato dal metodo toArray.
     *<br><br>
     * <b>Design</b>: verificare che il metodo ritorni effettivamente un array con tutti gli Entry di eSet nell'ordine
     * corretto.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo ritorna correttamente un array con tutti gli Entry di eSet.
     */
    @Test
    public void toArrayTest()
    {
        Object[] test = new Object[eSet.size()];

        HIterator iter = eSet.iterator();

        for (int i = eSet.size() - 1; i >= 0; i--)
            test[i] = iter.next();

        for (int i = 0; i < eSet.size(); i++)
            assertTrue(eSet.contains(test[i]));

        assertArrayEquals(test, eSet.toArray());
    }

    /**
     * Test del metodo     <b>public Object[] toArray()</b>
     *<br><br>
     * <b>Sommario</b>: Istanzio tre nuovi array di
     * Object; il primo, test,  della dimesione di eSet, il secondo, test1, della dimensione di (eSet + 5) e il terzo,
     * test2, della dimensione di (eSet - 5). Dentro test copio tutti gli entry di eSet, uno per
     * ogni iterazione di un for che scorre gli indici al contrario, poiché l'iteratore naviga nel set in ordine
     * inverso (per definizione di Enumeration). Verifico che tutti gli Entry di test siano presenti in eSet tramite un for.
     * Infine verifico che test sia uguale all'array ritornato dal metodo toArray.
     * Passo il secondo array
     * come argomento del metodo toArray e verifico tramite un for che i nuovi Entry di test1 siano uguali agli Entry
     * di test. Eseguo la stessa cosa con il terzo array, salvandomi però l'array ritornato dal metodo in un
     * nuovo array di Object test2_1 poiché la dimensione di test2 è minore rispetto alla dimensione di eSet. Infine
     * verifico che venga lanciata l'eccezione NullPointerException se l'array passato come argomento è null.
     *<br><br>
     * <b>Design</b>: verificare che il metodo ritorni effettivamente un array con tutti gli Entry del set nell'ordine
     * corretto se la dimensione di quello passato per argomento è minore rispetto a quella del set o che sovrascriva
     * i dati di quello passato per argomento se la sua dimensione è maggiore o uguale a quella del set. Verificare
     * la corretta gestione del'eccezione.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: eSet torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo ritorna correttamente un array con tutti gli Entry del set o sovrascrive i
     * dati di quello passato come argomento in base alla dimensione di quest'ultimo.
     */
    @Test
    public void toArrayArgTest()
    {
        Object[] test = new Object[eSet.size()];
        Object[] test1 = new Object[eSet.size() + 5];
        Object[] test2 = new Object[eSet.size() - 5];

        HIterator iter = eSet.iterator();

        for (int i = eSet.size() - 1; i >= 0; i--)
            test[i] = iter.next();

        for (int i = 0; i < eSet.size(); i++)
            assertTrue(eSet.contains(test[i]));

        assertArrayEquals(test, eSet.toArray());

        eSet.toArray(test1);
        for (int i = eSet.size() - 1; i >= 0; i--)
            assertEquals(test[i], test1[i]);

        Object[] test2_1 = eSet.toArray(test2);
        for (int i = eSet.size() - 1; i >= 0; i--)
            assertEquals(test[i], test2_1[i]);

        assertThrows(NullPointerException.class, () -> {eSet.toArray(null);});
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
        map.clear();
        map.put(1, "pippo");

        MapAdapter test1 = new MapAdapter();
        test1.put(1, "pippo");
        HSet testSet1 = test1.entrySet();

        MapAdapter test2 = new MapAdapter();
        test2.put(1, "test");
        HSet testSet2 = test2.entrySet();

        assertEquals(testSet1.hashCode(), eSet.hashCode());
        assertNotEquals(testSet2.hashCode(), eSet.hashCode());
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
        map.clear();
        map.put(1, "pippo");

        MapAdapter test1 = new MapAdapter();
        test1.put(1, "pippo");
        HSet testSet1 = test1.entrySet();

        MapAdapter test2 = new MapAdapter();
        test2.put(1, "test");
        HSet testSet2 = test2.entrySet();

        assertTrue(eSet.equals(testSet1));
        assertFalse(eSet.equals(testSet2));

        assertFalse(eSet.equals(null));
        assertFalse(eSet.equals(getAVector()));
    }

    // metodi helper
    private MapAdapter getAMap()
    {
        MapAdapter m = new MapAdapter();
        for (int i = 0; i < 5; i++)
            m.put(i, "stringa " + i);
        for (int i = 5; i < 10; i++)
            m.put("chiave " + (i - 4), i);
        return m;
    }

    private MapAdapter getAMap2()
    {
        MapAdapter m = new MapAdapter();
        for (int i = 10; i < 15; i++)
            m.put(i, "stringa " + i);
        for (int i = 15; i < 20; i++)
            m.put("chiave " + (i - 4), i);
        return m;
    }

    private MapAdapter getAMap3()
    {
        MapAdapter m = new MapAdapter();
        for (int i = 0; i < 5; i++)
            m.put(i, "stringa " + i);
        for (int i = 5; i < 10; i++)
            m.put("chiave " + (i - 5), i);
        return m;
    }

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