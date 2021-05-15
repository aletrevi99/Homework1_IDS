package myTest;

import myAdapter.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Vector;

import static org.junit.Assert.*;

public class ValuesCollectionTester
{
    private MapAdapter map;
    private HCollection vColl;

    /**
     * Istanzia un nuovo ValuesCollection pieno prima di ogni test.
     * */
    @Before
    public void initialize()
    {
        map = getAMap();
        vColl = map.values();
    }

    /**
     * Se a fine di ogni test vColl non è vuota, viene svuotata.
     * */
    @After
    public void clear()
    {
        if (!vColl.isEmpty()) vColl.clear();
    }

    /**
     * Test delle pre-condizioni di ogni metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: Stampa su terminale i valori di getAMap().
     * */
    @Test
    public void initializeTest()
    {
        assertEquals(10, vColl.size());
    }

    /**
     * Test del metodo     <b>public boolean add(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che il metodo lanci UnsupportedOperationException se aggiungo un elemento.
     *<br><br>
     * <b>Design</b>: corretto comportamento del metodo.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo conferma che non può essere utilizzato.
     */
    @Test
    public void addTest()
    {
        assertThrows(UnsupportedOperationException.class, () -> {
            vColl.add("test");});
    }

    /**
     * Test del metodo     <b>public boolean addAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che il metodo lanci UnsupportedOperationException se aggiungo una collection.
     *<br><br>
     * <b>Design</b>: corretto comportamento del metodo.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo conferma che non può essere utilizzato.
     */
    @Test
    public void addAllTest()
    {
        assertThrows(UnsupportedOperationException.class, () -> {
            vColl.addAll((HCollection) getACollection());});
    }

    /**
     * Test del metodo     <b>public void clear()</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che la dimensione della collection sia effettivamente 10 all'inizio del test. Successivamente
     * chiamo il metodo clear e controllo che la dimensione sia pari a zero e che
     * quindi la collection sia stata correttamente svuotata.
     *<br><br>
     * <b>Design</b>: corretto svuotamento della collection con degli elementi.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo svuoterà correttamente la collection da qualsiasi valore contenuto all'interno portando
     * la dimensione di essa a zero.
     */
    @Test
    public void clearTest()
    {
        assertEquals(10, vColl.size());
        vColl.clear();
        assertEquals(0, vColl.size());
    }

    /**
     * Test del metodo     <b>public boolean contains(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che i valori di vColl siano effettivamente contenuti in essa tramite un for che
     * scorre i valori di un toArray di vColl.
     * Infine controllo che chiamando il metodo con argomento nullo, lanci NullPointerException.
     *<br><br>
     * <b>Design</b>: corretto controllo della presenza di un valore in vColl. Controllo della corretta gestione dell'eccezione
     *<br><br>
     * <b>Pre-condizioni</b>:oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10 e che il
     * metodo get di Hashtable funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se il valore passato come argomento è contenuto nella collection,
     * altrimento false. Gestisce correttamente le eccezioni.
     */
    @Test
    public void containsTest()
    {
        for (int i = 0; i < vColl.size(); i++)
            assertTrue(vColl.contains(vColl.toArray()[i]));

        assertThrows(NullPointerException.class, () -> {
            vColl.contains(null);});
    }

    /**
     * Test del metodo     <b>public boolean containsAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Creo due HCollection, test con gli stessi valori di vColl e test2 con alcuni valori non contenuti in vColl.
     * Controllo che non tutti i valori di test2 siano presenti in vColl, verifico tutti i valori di test siano
     * presenti in vColl e successivamente mi accerto di questo con un for che itera il metodo contains su vColl per tutti
     * i valori di test. Alla fine controllo che chiamando il metodo con una collection nulla o con un oggetto
     * non di tipo HCollection, mi lanci rispettivamente NullPointerException e ClassCastException.
     *<br><br>
     * <b>Design</b>: corretto controllo della presenza di tutti i valori in vColl. Controllo della corretta gestione
     * delle eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se tutti i valori della collection passata sono contenuti in vColl,
     * altrimento false.
     */
    @Test
    public void containsAllTest()
    {
        MapAdapter map2 = getAMap2();
        HCollection test = map.values();
        HCollection test2 = map2.values();

        assertFalse(vColl.containsAll(test2));

        assertTrue(vColl.containsAll(test));

        for (int i = 0; i < vColl.size(); i++)
            assertTrue(vColl.contains(test.toArray()[i]));

        assertThrows(NullPointerException.class, () -> {
            vColl.containsAll(null);});
        assertThrows(ClassCastException.class, () -> {
            vColl.containsAll((HCollection) getAVector());});
    }

    /**
     * Test del metodo     <b>public boolean isEmpty()</b>
     *<br><br>
     * <b>Sommario</b>: Controllo che il metodo ritorni false quando ho verificato che la dimensione di vColl sia 10.
     * Svuoto vColl, controllo che la dimensione sia 0 e verifico che vColl torni ad essere vuota.
     * Alla fine creo un mappa con un solo elemento, genero la sua valuesCollection e controllo che non sia vuota e che la
     * dimensione sia effettivamente 1.
     *<br><br>
     * <b>Design</b>: verificare che la condizione di collection vuota equivalga alla sua dimensione pari a 0.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10 e che il
     * metodo isEmpty di Hashtable funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se la collection è effettivamente vuota, false se contiene almeno un valore.
     */
    @Test
    public void isEmptyTest()
    {
        assertEquals(10, vColl.size());
        assertFalse(vColl.isEmpty());
        vColl.clear();
        assertEquals(0, vColl.size());
        assertTrue(vColl.isEmpty());
        MapAdapter test = new MapAdapter();
        test.put(1,1);
        HCollection testColl = test.values();
        assertFalse(testColl.isEmpty());
        assertEquals(1, testColl.size());
    }

    /**
     * Test del metodo     <b>public HIterator iterator()</b>
     * <br><br>
     * <b>Sommario</b>: Creo un iteratore test di vColl e controllo che non sia nullo. Controllo con
     * un for che tutti i valori di vColl (ottenuti con un toArray(). Lo scorro al contrario poiché l'iteratore naviga nella collection in ordine
     * inverso (per definizione di Enumeration)) siano uguali ai valori che scorre l'iteratore test tramite il metodo
     * next(). Creo un secondo iteratore test1 per controllare con un while che il metodo hasNext() funzioni e si
     * comporti allo stesso modo del for testato precedentemente. Verifico che il metodo remove() dell'iteratore
     * rimuova effettivamente l'ultimo valore ispezionato da test1 controllando che la dimensione della collection sia
     * diminuita. Infine verifico che se viene invocato
     * il metodo next() di un iteratore arrivato a fine collection o se invoco il metodo remove ripetutamente, vengono lanciate
     * rispettivamente NoSuchElementException e IllegalStateException.
     * <br><br>
     * <b>Design</b>: verificare il corretto funzionamento del metodo iterator testando ogni metodo dell'iteratore, controllando
     * che esso agisca effettivamente sulla collection su cui è stato creato e che gestisca correttamente le eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuoto e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna un nuovo iteratore ad ogni chiamata, che permette di scorrere corretamente
     * la collection e di rimuovere l'ultimo valore analizzato da esso. Gestisce correttamente le eccezioni.
     */
    @Test
    public void iteratorTest()
    {
        HIterator test = vColl.iterator();
        assertNotNull(test);

        int y = vColl.size();
        for (int i = 0; i < vColl.size(); i++)
            assertEquals(test.next(), vColl.toArray()[--y]);

        HIterator test1 = vColl.iterator();
        int j = vColl.size();
        while (test1.hasNext())
        {
            assertEquals(test1.next(), vColl.toArray()[--j]);
            y++;
        }
        assertEquals(y, vColl.size());
        int size = vColl.size();
        test1.remove();
        assertEquals(size - 1, vColl.size());

        assertThrows(NoSuchElementException.class, () -> {test1.next();});
        assertThrows(IllegalStateException.class, () -> {test1.remove();});
    }

    /**
     * Test del metodo     <b>public boolean remove(Object o)</b>
     *<br><br>
     * <b>Sommario</b>: Istanzio due nuove variabili; un integer count per tener traccia dell'effettiva riduzione
     * della dimensione della collection ad ogni invocazione del metodo remove e un altro integer size per poter iterare
     * l'operazione di remove un numero di volte pari alla dimensione iniziale della collection. Itero queste azioni con un for.
     * Una volta rimossi tutti gli elementi da vColl, aggiungo una mappature k/v a map e mi ricreo vColl tramite
     * values(), creo una nuova mappa con una mappatura k/v diversa da quella aggiunta a map e mi creo la relativa
     * HCollection di valori. Questo mi permette di verificare che il metodo remove ritorni false se provo a invocarlo con un
     * valore non presente nella collection. Infine verifico il lancio della eccezione NullPointerException se viene invocato
     * con un oggetto null.
     *<br><br>
     * <b>Design</b>: verificare la corretta rimozione degli oggetti dalla collection in base al valore passato, la riduzione
     * della dimensione della collection in base al numero di volte che viene invocato il metodo e che gestisca correttamente
     * l'eccezione.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10 e che il
     * remove di MapAdapter funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se il valore passato è presente nella collection rimuovendolo e
     * riducendone la dimensione di vColl,
     * altrimenti ritorna false. Gestisce correttamente l'eccezione.
     */
    @Test
    public void removeObjectTest()
    {
        int count = vColl.size();
        int size = vColl.size();

        for (int i = 0; i < size; i++)
        {
            assertTrue(vColl.remove(vColl.toArray()[0]));
            assertEquals(--count, vColl.size());
        }

        map.put(0, "stringa 0");
        vColl = map.values();

        MapAdapter testMap = new MapAdapter();
        testMap.put(0, "pippo");
        HCollection testSet = testMap.values();

        assertFalse(vColl.remove(testSet.toArray()[0]));
        assertEquals(1, vColl.size());

        assertThrows(NullPointerException.class, () -> {
            vColl.remove(null);});
    }

    /**
     * Test del metodo     <b>public boolean removeAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Creo due HCollection, test con gli stessi valori di vColl e test2 con nessun valore contenuto in vColl
     * e controllo che la dimensione di vColl sia effettivamente 10. Verifico che rimuovendo tutti i valori
     * della collection test da vColl, il metodo ritorni true e che la dimensione di quest'ultimo sia 0. Aggiungo a map,
     * con for iterato 10 volte, la mappatura k/v (i + 20)/(i + 1000) e rigenero vColl, il quale ora è una collection dei valori di queste
     * 10 nuove mappature k/v. Verifico che tentando di rimuovere da vColl i valori della
     * collection test2 creata precedentemente ritorni false, poiché non c'é alcun valore in comune fra i due e quindi vColl
     * rimane immutato. Verifico che la dimensione di vColl sia rimasta 10.
     * Infine verifico il lancio della eccezione NullPointerException se viene
     * invocato removeAll con una collection null e della eccezione ClassCastException se viene invocato con un
     * oggetto non di tipo HCollection.
     *<br><br>
     * <b>Design</b>: verificare la corretta rimozione di tutti i valori presenti nella collection passata dal vColl, la riduzione
     * della dimensione di vColl in base al numero di valori rimossi, che ritorni true solo se vengono effettuate
     * rimozioni e che gestisca correttamente le eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuota e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se vengono effettuate delle rimozioni di valori (da vColl) presenti
     * sia nella collection passata che in vColl riducendone la dimensione in base al numero di valori rimossi,
     * altrimenti ritorna false se non vengono effettuate rimozioni. Gestisce correttamente le eccezioni.
     */
    @Test
    public void removeAllTest()
    {
        MapAdapter map2 = getAMap2();
        HCollection test = map.values();
        HCollection test2 = map2.values();

        assertEquals(10, vColl.size());

        assertTrue(vColl.removeAll(test));
        assertEquals(0, vColl.size());

        for (int i = 0; i < 10; i++) map.put(i + 20, i + 1000);
        vColl = map.keySet();

        assertFalse(vColl.removeAll(test2));
        assertEquals(10, vColl.size());

        assertThrows(NullPointerException.class, () -> {
            vColl.removeAll(null);});
        assertThrows(ClassCastException.class, () -> {
            vColl.removeAll((HCollection) getAVector());});
    }

    /**
     * Test del metodo     <b>public boolean retainAll(HCollection c)</b>
     *<br><br>
     * <b>Sommario</b>: Creo due HCollection, test con gli stessi valori di vColl e test3 con i primi 5 valori contenuti in vColl
     * e controllo che la dimensione di vColl sia effettivamente 10. Verifico che trattenendo tutti i valori
     * della collection test da vColl, il metodo ritorni false e che la dimensione di quest'ultimo sia ancora 10 poiché
     * entrambi contengono gli stessi valori.
     * Mi salvo in una variabile
     * la dimensione di vColl. Mantengo in vColl tutti i valori presenti nella collection test3 invocando
     * il metodo, ossia vengono rimossi da vColl tutti gli i valori non presenti in test3 (in questo esempio tutti i valori
     * di tipo string) e verifico se la rimozione è andata a buon fine col il for iterato per la dimensione di vColl,
     * confrontando i valori di test con i valori di vColl.
     * Verifico che la nuova dimensione di vColl sia esattamente la metà di quella precedente all'invocazione del
     * metodo poiché il metodo ha rimosso i cinque valori di tipo string presenti in vColl. Infine verifico il lancio della eccezione
     * NullPointerException se viene invocato retainAll con una collection null e della eccezione ClassCastException se
     * viene invocato con un oggetto non di tipo HCollection.
     *<br><br>
     * <b>Design</b>: verificare il corretto mantenimento di tutti gli Entry con i valori presenti nella collection passata da vColl, e quindi la
     * rimozione da questo dei valori non presenti nella collection, la riduzione della dimensione di vColl in
     * base al numero di valori rimossi, che ritorni true solo se vengono effettuate rimozioni e che gestisca
     * correttamente le eccezioni.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se vengono effettuate delle rimozioni di valori (dal set) non presenti
     * nella collection riducendone la dimensione in base al numero di valori rimossi, altrimenti ritorna false se
     * non vengono effettuate rimozioni. Gestisce correttamente le eccezioni.
     */
    @Test
    public void retainAllTest()
    {
        MapAdapter map3 = getAMap3();
        HCollection test = map.values();
        HCollection test3 = map3.values();

        assertEquals(10, vColl.size());
        assertFalse(vColl.retainAll(test));
        assertEquals(10, vColl.size());

        int size = vColl.size();
        assertTrue(vColl.retainAll(test3));
        for (int i = 0; i < vColl.size(); i++)
            assertEquals(test.toArray()[i], vColl.toArray()[i]);

        assertEquals(size / 2, vColl.size());

        assertThrows(NullPointerException.class, () -> {
            vColl.retainAll(null);});
        assertThrows(ClassCastException.class, () -> {
            vColl.retainAll((HCollection) getAVector());});
    }

    /**
     * Test del metodo     <b>public int size()</b>
     *<br><br>
     * <b>Sommario</b>: Creo un iteratore di vColl e lo scorro in un while finché ha valori. Ad ogni iterazione incremento la
     * variabile count che tiene traccia della dimensione di vColl, verifico che a fine ciclo sia effettivamente della
     * dimensione di vColl (ossia 10). Rimuovo un valore con il remove dell'iteratore e verifico che la dimensione di vColl
     * sia diminuita di una unità. Infine svuoto vColl e controllo che la sua dimensione sia tornata a 0.
     *<br><br>
     * <b>Design</b>: verificare la corretteza del metodo size controllando la coerenza di esso con un confronto con una
     * variabile che riproduce lo stesso meccanismo del metodo.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10 e che il
     * metodo size di Hashtable funzioni.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna l'effettiva dimensione della collection.
     */
    @Test
    public void sizeTest()
    {
        HIterator iter = vColl.iterator();

        int count = 0;
        while(iter.hasNext())
        {
            count++;
            iter.next();
        }

        assertEquals(10, count);
        assertEquals(count, vColl.size());
        iter.remove();
        assertEquals(--count, vColl.size());
        vColl.clear();
        assertEquals(0, vColl.size());
    }

    /**
     * Test del metodo     <b>public Object[] toArray()</b>
     *<br><br>
     * <b>Sommario</b>: Istanzio un nuovo array test di Object della dimesione di vColl e ci copio all'interno un valore per
     * ogni iterazione di un for che scorre gli indici al contrario, poiché l'iteratore naviga nella collection in ordine
     * inverso (per definizione di Enumeration). Verifico che tutti i valori di test siano presenti in vColl tramite un for.
     * Infine verifico che test sia uguale all'array ritornato dal metodo toArray.
     *<br><br>
     * <b>Design</b>: verificare che il metodo ritorni effettivamente un array con tutti i valori di vColl nell'ordine
     * corretto.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo ritorna correttamente un array con tutti i valori di vColl.
     */
    @Test
    public void toArrayTest()
    {
        Object[] test = new Object[vColl.size()];

        HIterator iter = vColl.iterator();

        for (int i = vColl.size() - 1; i >= 0; i--)
            test[i] = iter.next();

        for (int i = 0; i < vColl.size(); i++)
            assertTrue(vColl.contains(test[i]));

        assertArrayEquals(test, vColl.toArray());
    }

    /**
     * Test del metodo     <b>public Object[] toArray()</b>
     *<br><br>
     * <b>Sommario</b>: Istanzio tre nuovi array di
     * Object; il primo, test,  della dimesione di vColl, il secondo, test1, della dimensione di (vColl + 5) e il terzo,
     * test2, della dimensione di (vColl - 5). Dentro test copio tutti i valori di vColl, uno per
     * ogni iterazione di un for che scorre gli indici al contrario, poiché l'iteratore naviga nella collection in ordine
     * inverso (per definizione di Enumeration). Verifico che tutti i valori di test siano presenti in vColl tramite un for.
     * Infine verifico che test sia uguale all'array ritornato dal metodo toArray.
     * Passo il secondo array
     * come argomento del metodo toArray e verifico tramite un for che i nuovi valori di test1 siano uguali ai valori
     * di test. Eseguo la stessa cosa con il terzo array, salvandomi però l'array ritornato dal metodo in un
     * nuovo array di Object test2_1 poiché la dimensione di test2 è minore rispetto alla dimensione di vColl. Infine
     * verifico che venga lanciata l'eccezione NullPointerException se l'array passato come argomento è null.
     *<br><br>
     * <b>Design</b>: verificare che il metodo ritorni effettivamente un array con tutti i valori della collection nell'ordine
     * corretto se la dimensione di quello passato per argomento è minore rispetto a quella della collection o che sovrascriva
     * i dati di quello passato per argomento se la sua dimensione è maggiore o uguale a quella della collection. Verificare
     * la corretta gestione del'eccezione.
     *<br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 10.
     *<br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuoto e di dimensione zero.
     *<br><br>
     * <b>Risultato Atteso</b>: il metodo ritorna correttamente un array con tutti i valori della collection o sovrascrive i
     * dati di quello passato come argomento in base alla dimensione di quest'ultimo.
     */
    @Test
    public void toArrayArgTest()
    {
        Object[] test = new Object[vColl.size()];
        Object[] test1 = new Object[vColl.size() + 5];
        Object[] test2 = new Object[vColl.size() - 5];

        HIterator iter = vColl.iterator();

        for (int i = vColl.size() - 1; i >= 0; i--)
            test[i] = iter.next();

        for (int i = 0; i < vColl.size(); i++)
            assertTrue(vColl.contains(test[i]));

        assertArrayEquals(test, vColl.toArray());

        vColl.toArray(test1);
        for (int i = vColl.size() - 1; i >= 0; i--)
            assertEquals(test[i], test1[i]);

        Object[] test2_1 = vColl.toArray(test2);
        for (int i = vColl.size() - 1; i >= 0; i--)
            assertEquals(test[i], test2_1[i]);

        assertThrows(NullPointerException.class, () -> {
            vColl.toArray(null);});
    }

    /**
     * Test del metodo     <b>public int hashCode()</b>
     * <br><br>
     * <b>Sommario</b>: Svuoto map, ci aggiungo una mappatura k/v e rigenero vColl. Creo due mapAdapter test1, test2
     * inizialmente vuote, aggiungo ad ognuna una mappatura k/v diversa (quella di test1 è uguale a quella di map)
     * e genero i relativi values() in testColl1 e testColl2. Controllo che l'hashcode di vColl sia uguale all'hashcode di testColl1 e
     * diverso dall'hashcode di testColl2.
     * <br><br>
     * <b>Design</b>: corretto calcolo dell'hashcode in base agli elementi contenuti e controllo dell'uguaglianza di esso per
     * collection con elementi uguali o diversi.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a 0.
     * <br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuoto e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornera l'hashcode corretto della collection sottoforma di integer.
     */
    @Test
    public void hashCodeTest()
    {
        map.clear();
        map.put(1, "pippo");
        vColl = map.values();

        MapAdapter test1 = new MapAdapter();
        test1.put(1, "pippo");
        HCollection testColl1 = test1.values();

        MapAdapter test2 = new MapAdapter();
        test2.put(2, "test");
        HCollection testColl2 = test2.values();

        assertEquals(testColl1.hashCode(), vColl.hashCode());
        assertNotEquals(testColl2.hashCode(), vColl.hashCode());
    }

    /**
     * Test del metodo     <b>public boolean equals(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Svuoto map, ci aggiungo una mappatura k/v e rigenero vColl. Creo due mapAdapter test1, test2
     * inizialmente vuote, aggiungo ad ognuna una mappatura k/v diversa (quella di test1 è uguale a quella di map)
     * e genero i relativi values() in testColl1 e testColl2. Verifico che il metodo equals() dia esito positivo se confronto
     * vColl con testColl1 (poiché uguali) e che dia esito negativo se confronto vColl con testColl2 (poiché diversi).
     * Controllo che ritorni false se invoco il metodo con una lista nulla e un oggetto non di tipo ValuesCollection.
     * <br><br>
     * <b>Design</b>: corretto controllo della uguaglianza fra due ValuesCollection anche nei casi limite dove la lista passata come
     * argomento sia nulla o non di tipo ValuesCollection.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HCollection correttamente inizializzato con dimensione uguale a zero.
     * <br><br>
     * <b>Post-Condizioni</b>: vColl torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se l'oggetto passato come argomento sia un ValuesCollection uguale a vColl,
     * altrimenti false.
     */
    @Test
    public void equalsTest()
    {
        map.clear();
        map.put(1, "pippo");

        MapAdapter test1 = new MapAdapter();
        test1.put(1, "pippo");
        HCollection testColl1 = test1.values();

        MapAdapter test2 = new MapAdapter();
        test2.put(2, "test");
        HCollection testColl2 = test2.values();

        assertTrue(vColl.equals(testColl1));
        assertFalse(vColl.equals(testColl2));

        assertFalse(vColl.equals(null));
        assertFalse(vColl.equals(getAVector()));
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
        for (int i = 20; i < 25; i++)
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