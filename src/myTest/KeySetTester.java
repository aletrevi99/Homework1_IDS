package myTest;

import myAdapter.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Vector;

import static org.junit.Assert.*;

public class KeySetTester
{
    private MapAdapter map;
    private HSet kSet;

    /**
     * Creo un nuovo KeySet pieno prima di ogni test.
     */
    @Before
    public void initialize()
    {
        map = getAMap();
        kSet = map.keySet();
    }

    /**
     * Se a fine di ogni test map non è vuota, viene svuotata.
     */
    @After
    public void clear()
    {
        if (!kSet.isEmpty()) kSet.clear();
    }

    /**
     * Test delle pre-condizioni di ogni metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: Stampa su terminale i valori di getAMap().
     */
    @Test
    public void initializeTest()
    {
        assertEquals(10, kSet.size());
    }

    /**
     * Test del metodo     <b>public boolean add(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Controllo che il metodo lanci UnsupportedOperationException se aggiungo un elemento.
     * <br><br>
     * <b>Design</b>: corretto comportamento del metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: il metodo conferma che non può essere utilizzato.
     */
    @Test
    public void addTest()
    {
        assertThrows(UnsupportedOperationException.class, () -> kSet.add("test"));
    }

    /**
     * Test del metodo     <b>public boolean addAll(HCollection c)</b>
     * <br><br>
     * <b>Sommario</b>: Controllo che il metodo lanci UnsupportedOperationException se aggiungo una collection.
     * <br><br>
     * <b>Design</b>: corretto comportamento del metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: il metodo conferma che non può essere utilizzato.
     */
    @Test
    public void addAllTest()
    {
        assertThrows(UnsupportedOperationException.class, () -> kSet.addAll(getACollection()));
    }

    /**
     * Test del metodo     <b>public void clear()</b>
     * <br><br>
     * <b>Sommario</b>: Controllo che la dimensione del set sia effettivamente 10 all'inizio del test. Successivamente
     * chiamo il metodo clear e controllo che la dimensione sia pari a zero e che
     * quindi il set sia stato correttamente svuotato.
     * <br><br>
     * <b>Design</b>: corretto svuotamento del set con degli elementi.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: il metodo svuoterà correttamente la lista da qualsiasi elemento contenuto all'interno portando
     * la dimensione di essa a zero.
     */
    @Test
    public void clearTest()
    {
        assertEquals(10, kSet.size());
        kSet.clear();
        assertEquals(0, kSet.size());
    }

    /**
     * Test del metodo     <b>public boolean contains(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Controllo che gli elementi di kSet siano effettivamente contenuti in essa tramite un for che
     * scorre gli elementi di un toArray di kSet.
     * Infine controllo che chiamando il metodo con argomento nullo, lanci NullPointerException.
     * <br><br>
     * <b>Design</b>: corretto controllo della presenza di una chiave in eSet. Controllo della corretta gestione dell'eccezione
     * <br><br>
     * <b>Pre-condizioni</b>:oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10 e che il
     * metodo get di Hashtable funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuoto e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se la chiave passato come argomento è contenuta nel set,
     * altrimenti false. Gestisce correttamente le eccezioni.
     */
    @Test
    public void containsTest()
    {
        for (int i = 0; i < kSet.size(); i++)
            assertTrue(kSet.contains(kSet.toArray()[i]));

        assertThrows(NullPointerException.class, () -> kSet.contains(null));
    }

    /**
     * Test del metodo     <b>public boolean containsAll(HCollection c)</b>
     * <br><br>
     * <b>Sommario</b>: Creo due HSet, test con le stesse chiavi di kSet e test2 con alcune chiavi non contenuti in kSet.
     * Controllo che non tutte le chiavi di test2 siano presenti in kSet, verifico tutte le chiavi di test siano
     * presenti in kSet e successivamente mi accerto di questo con un for che itera il metodo contains su kSet per tutte
     * le chiavi di test. Alla fine controllo che chiamando il metodo con una collection nulla o con un oggetto
     * non di tipo HCollection, mi lanci rispettivamente NullPointerException e ClassCastException.
     * <br><br>
     * <b>Design</b>: corretto controllo della presenza di tutte le chiavi in kSet. Controllo della corretta gestione
     * delle eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuoto e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se tutte le chiavi della collection sono contenute nel set,
     * altrimenti false.
     */
    @Test
    public void containsAllTest()
    {
        MapAdapter map2 = getAMap2();
        HSet test = map.keySet();
        HSet test2 = map2.keySet();

        assertFalse(kSet.containsAll(test2));

        assertTrue(kSet.containsAll(test));

        for (int i = 0; i < kSet.size(); i++)
            assertTrue(kSet.contains(test.toArray()[i]));

        assertThrows(NullPointerException.class, () -> kSet.containsAll(null));
        assertThrows(ClassCastException.class, () -> kSet.containsAll((HCollection) getAVector()));
    }

    /**
     * Test del metodo     <b>public boolean isEmpty()</b>
     * <br><br>
     * <b>Sommario</b>: Controllo che il metodo ritorni false quando ho verificato che la dimensione di kSet sia 10.
     * Svuoto kSet, controllo che la dimensione sia 0 e verifico che kSet torni ad essere vuoto.
     * Alla fine creo un mappa con un solo elemento, genero il suo keySet e controllo che non sia vuoto e che la
     * dimensione sia effettivamente 1.
     * <br><br>
     * <b>Design</b>: verificare che la condizione di set vuoto equivalga alla sua dimensione pari a 0.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10 e che il
     * metodo isEmpty di Hashtable funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se il set è effettivamente vuota, false se contiene almeno una chiave.
     */
    @Test
    public void isEmptyTest()
    {
        assertEquals(10, kSet.size());
        assertFalse(kSet.isEmpty());
        kSet.clear();
        assertEquals(0, kSet.size());
        assertTrue(kSet.isEmpty());
        MapAdapter test = new MapAdapter();
        test.put(1, 1);
        HSet testSet = test.keySet();
        assertFalse(testSet.isEmpty());
        assertEquals(1, testSet.size());
    }

    /**
     * Test del metodo     <b>public HIterator iterator()</b>
     * <br><br>
     * <b>Sommario</b>: Creo un iteratore test di kSet e controllo che non sia nullo. Controllo con
     * un for che tutte le chiavi di kSet(ottenute con un toArray(). Lo scorro al contrario poiché l'iteratore naviga nel set in ordine
     * inverso (per definizione di Enumeration)) siano uguali alle chiavi che scorre l'iteratore test tramite il metodo
     * next(). Creo un secondo iteratore test1 per controllare con un while che il metodo hasNext() funzioni e si
     * comporti allo stesso modo del for testato precedentemente. Verifico che il metodo remove() dell'iteratore
     * rimuova effettivamente l'ultima chiave ispezionata da test1 controllando che la dimensione del set sia
     * diminuita. Infine verifico che se viene invocato
     * il metodo next() di un iteratore arrivato a fine set o se invoco il metodo remove ripetutamente, vengono lanciate
     * rispettivamente NoSuchElementException e IllegalStateException.
     * <br><br>
     * <b>Design</b>: verificare il corretto funzionamento del metodo iterator testando ogni metodo dell'iteratore, controllando
     * che esso agisca effettivamente sul set su cui è stato creato e che gestisca correttamente le eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuoto e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna un nuovo iteratore ad ogni chiamata, che permette di scorrere correttamente
     * il set e di rimuovere l'ultima chiave analizzata da esso. Gestisce correttamente le eccezioni.
     */
    @Test
    public void iteratorTest()
    {
        HIterator test = kSet.iterator();
        assertNotNull(test);

        int y = kSet.size();
        for (int i = 0; i < kSet.size(); i++)
            assertEquals(test.next(), kSet.toArray()[--y]);

        HIterator test1 = kSet.iterator();
        int j = kSet.size();
        while (test1.hasNext())
        {
            assertEquals(test1.next(), kSet.toArray()[--j]);
            y++;
        }
        assertEquals(y, kSet.size());
        int size = kSet.size();
        test1.remove();
        assertEquals(size - 1, kSet.size());

        assertThrows(NoSuchElementException.class, test1::next);
        assertThrows(IllegalStateException.class, test1::remove);
    }

    /**
     * Test del metodo     <b>public boolean remove(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Istanzio due nuove variabili; un integer count per tener traccia dell'effettiva riduzione
     * della dimensione del set ad ogni invocazione del metodo remove e un altro integer size per poter iterare
     * l'operazione di remove un numero di volte pari alla dimensione iniziale del set. Itero queste azioni con un for.
     * Una volta rimossi tutti gli elementi da kSet, aggiungo una mappature k/v a map e mi ricreo kSet tramite
     * keySet(), creo una nuova mappa con una mappatura k/v diversa da quella aggiunta a map e mi creo il relativo
     * HSet di chiavi. Questo mi permette di verificare che il metodo remove ritorni false se provo a invocarlo con una
     * chiave non presente nel set. Infine verifico il lancio della eccezione NullPointerException se viene invocato
     * con un oggetto null.
     * <br><br>
     * <b>Design</b>: verificare la corretta rimozione degli oggetti dal set in base alla chiave passata, la riduzione
     * della dimensione del set in base al numero di volte che viene invocato il metodo e che gestisca correttamente
     * l'eccezione.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10 e che il
     * remove di MapAdapter funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se la chiave passata è presente nel set rimuovendolo e
     * riducendone la dimensione del set,
     * altrimenti ritorna false. Gestisce correttamente l'eccezione.
     */
    @Test
    public void removeObjectTest()
    {
        int count = kSet.size();
        int size = kSet.size();

        for (int i = 0; i < size; i++)
        {
            assertTrue(kSet.remove(kSet.toArray()[0]));
            assertEquals(--count, kSet.size());
        }

        map.put(0, "stringa 0");
        kSet = map.keySet();

        MapAdapter testMap = new MapAdapter();
        testMap.put(0, "pippo");
        HSet testSet = testMap.entrySet();

        assertFalse(kSet.remove(testSet.toArray()[0]));
        assertEquals(1, kSet.size());

        assertThrows(NullPointerException.class, () -> kSet.remove(null));
    }

    /**
     * Test del metodo     <b>public boolean removeAll(HCollection c)</b>
     * <br><br>
     * <b>Sommario</b>: Creo due HSet, test con le stesse chiavi di kSet e test2 con nessuna chiave contenuta in kSet
     * e controllo che la dimensione di kSet sia effettivamente 10. Verifico che rimuovendo tutte le chiavi
     * della collection test da kSet, il metodo ritorni true e che la dimensione di quest'ultimo sia 0. Aggiungo a map,
     * con for iterato 10 volte, la mappatura k/v (i + 20)/(i) e rigenero kSet, il quale ora è un set delle chiavi di queste
     * 10 nuove mappature k/v. Verifico che tentando di rimuovere da kSet le chiavi della
     * collection test2 creata precedentemente ritorni false, poiché non c'é alcuna chiave in comune fra i due e quindi kSet
     * rimane immutato. Verifico che la dimensione di kSet sia rimasta 10.
     * Infine verifico il lancio della eccezione NullPointerException se viene
     * invocato removeAll con una collection null e della eccezione ClassCastException se viene invocato con un
     * oggetto non di tipo HCollection.
     * <br><br>
     * <b>Design</b>: verificare la corretta rimozione di tutte le chiavi presenti nella collection dal set, la riduzione
     * della dimensione del set in base al numero di chiavi rimosse, che ritorni true solo se vengono effettuate
     * rimozioni e che gestisca correttamente le eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se vengono effettuate delle rimozioni dal set di chiavi presenti
     * sia nella collection che nel set riducendone la dimensione in base al numero di chiavi rimosse,
     * altrimenti ritorna false se non vengono effettuate rimozioni. Gestisce correttamente le eccezioni.
     */
    @Test
    public void removeAllTest()
    {
        MapAdapter map2 = getAMap2();
        HSet test = map.keySet();
        HSet test2 = map2.keySet();

        assertEquals(10, kSet.size());

        assertTrue(kSet.removeAll(test));
        assertEquals(0, kSet.size());

        for (int i = 0; i < 10; i++) map.put(i + 20, i);
        kSet = map.keySet();

        assertFalse(kSet.removeAll(test2));
        assertEquals(10, kSet.size());

        assertThrows(NullPointerException.class, () -> kSet.removeAll(null));
        assertThrows(ClassCastException.class, () -> kSet.removeAll((HCollection) getAVector()));
    }

    /**
     * Test del metodo     <b>public boolean retainAll(HCollection c)</b>
     * <br><br>
     * <b>Sommario</b>: Creo due HSet, test con le stesse chiavi di kSet e test3 con le prime 5 chiavi contenute in kSet
     * e controllo che la dimensione di eSet sia effettivamente 10. Verifico che trattenendo tutti gli elementi
     * del set test da kSet, il metodo ritorni false e che la dimensione di quest'ultimo sia ancora 10 poiché
     * entrambi contengono le stesse chiavi.
     * Mi salvo in una variabile
     * la dimensione di kSet. Mantengo in kSet tutte le chiavi presenti nel set test3 invocando
     * il metodo, ossia vengono rimosse da kSet tutte le chiavi non presenti in test3 (in questo esempio tutte le chiavi
     * di tipo string) e verifico se la rimozione è andata a buon fine col il for iterato per la dimensione di kSet,
     * confrontando le chiavi di test con le chiavi di kSet.
     * Verifico che la nuova dimensione di kSet sia esattamente la metà di quella precedente all'invocazione del
     * metodo poiché il metodo ha rimosso le cinque chiavi di tipo string presenti in kSet. Infine verifico il lancio della eccezione
     * NullPointerException se viene invocato retainAll con una collection null e della eccezione ClassCastException se
     * viene invocato con un oggetto non di tipo HCollection.
     * <br><br>
     * <b>Design</b>: verificare il corretto mantenimento di tutte le chiavi presenti nella collection da kSet, e quindi la
     * rimozione da questo delle chiavi non presenti nella collection, la riduzione della dimensione di kSet in
     * base al numero di chiavi rimosse, che ritorni true solo se vengono effettuate rimozioni e che gestisca
     * correttamente le eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuoto e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se vengono effettuate delle rimozioni di chiavi (dal set) non presenti
     * nella collection riducendone la dimensione in base al numero di chiavi rimosse, altrimenti ritorna false se
     * non vengono effettuate rimozioni. Gestisce correttamente le eccezioni.
     */
    @Test
    public void retainAllTest()
    {
        MapAdapter map3 = getAMap3();
        HSet test = map.keySet();
        HSet test3 = map3.keySet();

        assertEquals(10, kSet.size());
        assertFalse(kSet.retainAll(test));
        assertEquals(10, kSet.size());

        int size = kSet.size();
        assertTrue(kSet.retainAll(test3));
        for (int i = 0; i < kSet.size(); i++)
            assertEquals(test.toArray()[i], kSet.toArray()[i]);

        assertEquals(size / 2, kSet.size());

        assertThrows(NullPointerException.class, () -> kSet.retainAll(null));
        assertThrows(ClassCastException.class, () -> kSet.retainAll((HCollection) getAVector()));
    }

    /**
     * Test del metodo     <b>public int size()</b>
     * <br><br>
     * <b>Sommario</b>: Creo un iteratore di kSet e lo scorro in un while finché ha chiavi. Ad ogni iterazione incremento la
     * variabile count che tiene traccia della dimensione di kSet, verifico che a fine ciclo sia effettivamente della
     * dimensione di kSet (ossia 10). Rimuovo una chiave con il remove dell'iteratore e verifico che la dimensione di kSet
     * sia diminuita di una unità. Infine svuoto kSet e controllo che la sua dimensione sia tornata a 0.
     * <br><br>
     * <b>Design</b>: verificare la correttezza del metodo size controllando la coerenza di esso con un confronto con una
     * variabile che riproduce lo stesso meccanismo del metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10 e che il
     * metodo size di Hashtable funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuoto e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna l'effettiva dimensione del set.
     */
    @Test
    public void sizeTest()
    {
        HIterator iter = kSet.iterator();

        int count = 0;
        while (iter.hasNext())
        {
            count++;
            iter.next();
        }

        assertEquals(10, count);
        assertEquals(count, kSet.size());
        iter.remove();
        assertEquals(--count, kSet.size());
        kSet.clear();
        assertEquals(0, kSet.size());
    }

    /**
     * Test del metodo     <b>public Object[] toArray()</b>
     * <br><br>
     * <b>Sommario</b>: Istanzio un nuovo array test di Object della dimensione di kSet e ci copio all'interno una chiave per
     * ogni iterazione di un for che scorre gli indici al contrario, poiché l'iteratore naviga nel set in ordine
     * inverso (per definizione di Enumeration). Verifico che tutte le chiavi di test siano presenti in kSet tramite un for.
     * Infine verifico che test sia uguale all'array ritornato dal metodo toArray.
     * <br><br>
     * <b>Design</b>: verificare che il metodo ritorni effettivamente un array con tutte le chiavi di kSet nell'ordine
     * corretto.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuoto e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: il metodo ritorna correttamente un array con tutte le chiavi di kSet.
     */
    @Test
    public void toArrayTest()
    {
        Object[] test = new Object[kSet.size()];

        HIterator iter = kSet.iterator();

        for (int i = kSet.size() - 1; i >= 0; i--)
            test[i] = iter.next();

        for (int i = 0; i < kSet.size(); i++)
            assertTrue(kSet.contains(test[i]));

        assertArrayEquals(test, kSet.toArray());
    }

    /**
     * Test del metodo     <b>public Object[] toArray()</b>
     * <br><br>
     * <b>Sommario</b>: Istanzio tre nuovi array di
     * Object; il primo, test,  della dimensione di kSet, il secondo, test1, della dimensione di (kSet + 5) e il terzo,
     * test2, della dimensione di (kSet - 5). Dentro test copio tutte le chiavi di kSet, uno per
     * ogni iterazione di un for che scorre gli indici al contrario, poiché l'iteratore naviga nel set in ordine
     * inverso (per definizione di Enumeration). Verifico che tutte le chiavi di test siano presenti in kSet tramite un for.
     * Infine verifico che test sia uguale all'array ritornato dal metodo toArray.
     * Passo il secondo array
     * come argomento del metodo toArray e verifico tramite un for che le nuove chiavi di test1 siano uguali alle chiavi
     * di test. Eseguo la stessa cosa con il terzo array, salvandomi però l'array ritornato dal metodo in un
     * nuovo array di Object test2_1 poiché la dimensione di test2 è minore rispetto alla dimensione di kSet. Infine
     * verifico che venga lanciata l'eccezione NullPointerException se l'array passato come argomento è null.
     * <br><br>
     * <b>Design</b>: verificare che il metodo ritorni effettivamente un array con tutte le chiavi del set nell'ordine
     * corretto se la dimensione di quello passato per argomento è minore rispetto a quella del set o che sovrascriva
     * i dati di quello passato per argomento se la sua dimensione è maggiore o uguale a quella del set. Verificare
     * la corretta gestione del'eccezione.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 10.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuoto e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: il metodo ritorna correttamente un array con tutte le chiavi del set o sovrascrive i
     * dati di quello passato come argomento in base alla dimensione di quest'ultimo.
     */
    @Test
    public void toArrayArgTest()
    {
        Object[] test = new Object[kSet.size()];
        Object[] test1 = new Object[kSet.size() + 5];
        Object[] test2 = new Object[kSet.size() - 5];

        HIterator iter = kSet.iterator();

        for (int i = kSet.size() - 1; i >= 0; i--)
            test[i] = iter.next();

        for (int i = 0; i < kSet.size(); i++)
            assertTrue(kSet.contains(test[i]));

        assertArrayEquals(test, kSet.toArray());

        kSet.toArray(test1);
        for (int i = kSet.size() - 1; i >= 0; i--)
            assertEquals(test[i], test1[i]);

        Object[] test2_1 = kSet.toArray(test2);
        for (int i = kSet.size() - 1; i >= 0; i--)
            assertEquals(test[i], test2_1[i]);

        assertThrows(NullPointerException.class, () -> kSet.toArray(null));
    }

    /**
     * Test del metodo     <b>public int hashCode()</b>
     * <br><br>
     * <b>Sommario</b>: Svuoto map, ci aggiungo una mappatura k/v e rigenero kSet. Creo due mapAdapter test1, test2
     * inizialmente vuote, aggiungo ad ognuna una mappatura k/v diversa (quella di test1 è uguale a quella di map)
     * e genero i relativi keySet in testSet1 e testSet2. Controllo che l'hashcode di kSet sia uguale all'hashcode di testSet1 e
     * diverso dall'hashcode di testSet2.
     * <br><br>
     * <b>Design</b>: corretto calcolo dell'hashcode in base agli elementi contenuti e controllo dell'uguaglianza di esso per
     * set con elementi uguali o diversi.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a 0.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuoto e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà l'hashcode corretto del set sotto forma di integer.
     */
    @Test
    public void hashCodeTest()
    {
        map.clear();
        map.put(1, "pippo");
        kSet = map.keySet();

        MapAdapter test1 = new MapAdapter();
        test1.put(1, "pippo");
        HSet testSet1 = test1.keySet();

        MapAdapter test2 = new MapAdapter();
        test2.put(2, "test");
        HSet testSet2 = test2.keySet();

        assertEquals(testSet1.hashCode(), kSet.hashCode());
        assertNotEquals(testSet2.hashCode(), kSet.hashCode());
    }

    /**
     * Test del metodo     <b>public boolean equals(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Svuoto map, ci aggiungo una mappatura k/v e rigenero kSet. Creo due mapAdapter test1, test2
     * inizialmente vuote, aggiungo ad ognuna una mappatura k/v diversa (quella di test1 è uguale a quella di map)
     * e genero i relativi keySet() in testSet1 e testSet2. Verifico che il metodo equals() dia esito positivo se confronto
     * kSet con testSet1 (poiché uguali) e che dia esito negativo se confronto kSet con testSet2 (poiché diversi).
     * Controllo che ritorni false se invoco il metodo con una lista nulla e un oggetto non di tipo KeySet.
     * <br><br>
     * <b>Design</b>: corretto controllo della uguaglianza fra due KeySet anche nei casi limite dove la lista passata come
     * argomento sia nulla o non di tipo KeySet.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo HSet correttamente inizializzato con dimensione uguale a zero.
     * <br><br>
     * <b>Post-Condizioni</b>: kSet torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se l'oggetto passato come argomento sia un KeySet uguale a kSet,
     * altrimenti false.
     */
    @Test
    public void equalsTest()
    {
        map.clear();
        map.put(1, "pippo");

        MapAdapter test1 = new MapAdapter();
        test1.put(1, "pippo");
        HSet testSet1 = test1.keySet();

        MapAdapter test2 = new MapAdapter();
        test2.put(2, "test");
        HSet testSet2 = test2.keySet();

        assertTrue(kSet.equals(testSet1));
        assertFalse(kSet.equals(testSet2));

        assertFalse(kSet.equals(null));
        assertFalse(kSet.equals(getAVector()));
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