package myTest;

import myAdapter.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class MapAdapterTester
{
    private MapAdapter map;

    /**
     * Istanzia una nuova MapAdapter prima di ogni test.
     */
    @Before
    public void initialize()
    {
        map = new MapAdapter();
    }

    /**
     * Se a fine di ogni test map non è vuota, viene svuotata.
     */
    @After
    public void clear()
    {
        if (!map.isEmpty()) map.clear();
    }

    /**
     * Test delle pre-condizioni di ogni metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero.
     * <br><br>
     * <b>Post-Condizioni</b>: n/a
     */
    @Test
    public void initializeTest()
    {
        assertEquals(0, map.size());
    }

    /**
     * Test del metodo     <b>public void clear()</b>
     * <br><br>
     * <b>Sommario</b>: Controllo che la dimensione di map sia effettivamente 0 all'inizio del test. Creo una nuova
     * MapAdapter test e le assegno gli elementi della MapAdapter generata dal metodo privato getAMap(). Aggiungo a map
     * un gli elementi di test tramite due for; uno per le chiavi numeriche, l'altro per le chivi di tipo string e
     * controllo che la dimensione sia effettivamente del numero degli elementi
     * aggiunti, ossia 10. Successivamente chiamo il metodo clear e controllo che la dimensione sia pari a zero e che
     * quindi la mappa sia stata correttamente svuotata.
     * <br><br>
     * <b>Design</b>: corretto svuotamento della mappa riempita con delle mappautre chiave valore (k/v).
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo clear di Hashtable funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: il metodo svuoterà correttamente la mappa da qualsiasi mappatura k/v contenuto all'interno portando
     * la dimensione della mappa a zero.
     */
    @Test
    public void clearTest()
    {
        assertEquals(0, map.size());

        MapAdapter test = getAMap();

        for (int i = 0; i < test.size() / 2; i++)
            map.put(i, test.get(i));
        for (int i = 5; i < test.size(); i++)
            map.put("chiave " + (i - 4), i);

        // map.putAll(getAMap());
        assertEquals(10, map.size());
        map.clear();
        assertEquals(0, map.size());
    }

    /**
     * Test del metodo     <b>public boolean containsKey(Object key)</b>
     * <br><br>
     * <b>Sommario</b>: Aggiungo alla mappa map inizialmente vuota uno ad uno le mappature k/v di una MapAdapter test
     * (generata con il metodo privato getAMap()) ripetutamente in due for, il primo per le mappature con chiavi
     * numeriche ed il secondo per le mappature con chiavi di tipo string e controllo che alla fine la dimensione
     * equivalga effettivamente al numero delle mappature
     * aggiunte, ossia 10. Successivamente controllo che le mappature aggiunte siano effettivamente contenute in essa
     * tramite due for che scorrono prima le mappature con chiavi numeriche e successivamente le mappature con chiavi
     * di tipo string. Controllo che ritorni false se gli passo come argomento una chiave non presente nella mappa.
     * Infine controllo che chiamando il metodo con argomento nullo, lanci NullPointerException.
     * <br><br>
     * <b>Design</b>: corretto controllo della presenza di una mappatura k/v nella mappa in base alla chiave fornita. Controllo della
     * corretta gestione delle eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo Mapdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo containsKey di Hashtable funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se è presente una mappatura con la chiave passata come
     * argomento, altrimenti false se non è contenuta.
     */
    @Test
    public void containsKeyTest()
    {
        MapAdapter test = getAMap();

        for (int i = 0; i < test.size() / 2; i++)
            map.put(i, test.get(i));
        for (int i = 5; i < test.size(); i++)
            map.put("chiave " + (i - 4), i);

        assertEquals(10, map.size());

        for (int i = 0; i < map.size() / 2; i++)
            assertTrue(map.containsKey(i));

        for (int i = 5; i < map.size(); i++)
            assertTrue(map.containsKey("chiave " + (i - 4)));

        assertFalse(map.containsKey("pippo"));

        assertThrows(NullPointerException.class, () -> {
            map.containsKey(null);
        });
    }

    /**
     * Test del metodo     <b>public boolean containsValue(Object value)</b>
     * <br><br>
     * <b>Sommario</b>: Aggiungo alla mappa map inizialmente vuota uno ad uno le mappature k/v di una MapAdapter test
     * (generata con il metodo privato getAMap()) ripetutamente in due for, il primo per le mappature con chiavi
     * numeriche ed il secondo per le mappature con chiavi di tipo string e controllo che alla fine la dimensione
     * equivalga effettivamente al numero delle mappature
     * aggiunte, ossia 10. Successivamente controllo che le mappature aggiunte siano effettivamente contenute in essa
     * tramite due for che scorrono prima le mappature con valori di tipo string e successivamente le mappature con valori
     * numerici. Controllo che ritorni false se gli passo come argomento un valore non presente nella mappa.
     * Infine controllo che chiamando il metodo con argomento nullo, lanci NullPointerException.
     * <br><br>
     * <b>Design</b>: corretto controllo della presenza di almeno una mappatura k/v nella mappa in base al valore fornito. Controllo della
     * corretta gestione delle eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo Mapdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo containsValue di Hashtable funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se è presente almeno una mappatura con il valore passato come
     * argomento, altrimenti false se non è presente.
     */
    @Test
    public void containsValueTest()
    {
        MapAdapter test = getAMap();

        for (int i = 0; i < test.size() / 2; i++)
            map.put(i, test.get(i));
        for (int i = 5; i < test.size(); i++)
            map.put("chiave " + (i - 4), i);

        assertEquals(10, map.size());

        for (int i = 0; i < map.size() / 2; i++)
            assertTrue(map.containsValue("stringa " + i));

        for (int i = 5; i < map.size(); i++)
            assertTrue(map.containsValue(i));

        assertFalse(map.containsValue("pippo"));

        assertThrows(NullPointerException.class, () -> {
            map.containsValue(null);
        });
    }

    /**
     * Test del metodo     <b>public HSet entrySet()</b>
     * <br><br>
     * <b>Sommario</b>: Aggiungo alla mappa map inizialmente vuota tutte le mappature k/v di una MapAdapter
     * (generata con il metodo privato getAMap()).
     * Controllo che il metodo entrySet() mi ritorni una HSet non null.
     * <br><br>
     * <b>Design</b>: verificare la corretta generazione di un HSet degli Entry di map.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo genera un HSet di tutti gli Entry di map, utilizzabile.
     */
    @Test
    public void entrySetTest()
    {
        map.putAll(getAMap());
        assertNotNull(map.entrySet());
    }

    /**
     * Test del metodo     <b>public Object get(Object key)</b>
     * <br><br>
     * <b>Sommario</b>: Creo un MapAdapter test con il metodo privato getAMap() e lo uso per riempire map. Con due for,
     * uno per tipo di chiave delle mappature k/v contenute dentro map, controllo che il metodo get ritorni il valore
     * della mappatura k/v effettivamente contenuta nella mappa con chiave (i) e valore ("stringa " + i) nelle prime 5
     * posizioni della mappa e chiave ("chiave " + i) e valore (i) nelle altre 5 posizioni della mappa (ossia le
     * mappature k/v del generate dal metodo getAMap(). Verifico che se invoco il metodo get passando una chiave non
     * presente in map, mi ritorna null.
     * Inoltre controllo che se passo come argomento una chiave null, mi lanci l'eccezione NullPointerException.
     * <br><br>
     * <b>Design</b>: verifica la correttezza del valore della mappatura k/v di chiave key ritornato dal metodo. Controllo
     * dell'effettivo lancio delle eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo get di Hashtable funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornera  valore della mappatura k/v di chiave key se quest'ultima è una
     * chiave presente in una mappatura k/v di map, null se non è presente.
     */
    @Test
    public void getTest()
    {
        MapAdapter test = getAMap();

        map.putAll(test);

        for (int i = 0; i < test.size() / 2; i++)
            assertEquals("stringa " + i, map.get(i));
        for (int i = 5; i < test.size(); i++)
            assertEquals(i, map.get("chiave " + (i - 4)));

        assertNull(map.get("pippo"));

        assertThrows(NullPointerException.class, () -> {
            map.get(null);
        });
    }

    /**
     * Test del metodo     <b>public boolean isEmpty()</b>
     * <br><br>
     * <b>Sommario</b>: Controllo che all'inizio la mappa sia effettivamente vuota. Creo un MapAdapter test con il metodo
     * privato getAMap() e con due for, il primo per le mappature k/v con chiave numerica, il secondo per le mappature
     * k/v con chiave di tipo string, aggiungo le mappature k/v di test a map una ad una, controllando che ad ogni
     * ripetizione la mappa non sia più vuota. Alla fine svuoto la mappa e controllo che la dimensione sia zero e
     * che la mappa torni ad essere vuota.
     * <br><br>
     * <b>Design</b>: verificare che la condizione di mappa vuota equivalga alla sua dimensione pari a zero.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo isEmpty di Hashtable funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna true se la mappa è effettivamente vuota, false se contiene almeno una mappatura k/v.
     */
    @Test
    public void isEmptyTest()
    {
        assertTrue(map.isEmpty());

        MapAdapter test = getAMap();

        for (int i = 0; i < test.size() / 2; i++)
        {
            map.put(i, test.get(i));
            assertFalse(map.isEmpty());
        }
        for (int i = 5; i < test.size(); i++)
        {
            map.put("chiave " + (i - 4), i);
            assertFalse(map.isEmpty());
        }

        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * Test del metodo     <b>public HSet keySet()</b>
     * <br><br>
     * <b>Sommario</b>: Aggiungo alla mappa map inizialmente vuota tutte le mappature k/v di una MapAdapter
     * (generata con il metodo privato getAMap()).
     * Controllo che il metodo keySet() mi ritorni una HSet non null.
     * <br><br>
     * <b>Design</b>: verificare la corretta generazione di un HSet delle chiavi di map.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo genera un HSet di tutte le chiavi di map, utilizzabile.
     */
    @Test
    public void keySetTest()
    {
        map.putAll(getAMap());
        assertNotNull(map.keySet());
    }

    /**
     * Test del metodo     <b>public Object put(Object key, Object value)</b>
     * <br><br>
     * <b>Sommario</b>:  Creo un MapAdapter test con il metodo
     * privato getAMap() e con due for, il primo per le mappature k/v con chiave numerica, il secondo per le mappature
     * k/v con chiave di tipo string, aggiungo le mappature k/v di test a map una ad una e controllo che la dimensione
     * di map sia esattamente del numero delle mappature k/v aggiunte. Successivamente con due for, con la stessa
     * logica utilizzata precedentemente, controllo che i valori della mappatura k/v con le chiavi date, inseriti in map con le
     * iterazioni di put(), siano effettivamente presenti nella posizione corretta. Creo una variabile Object tmp e
     * gli assegno il valore della mappatura k/v di chiave 0 e confronto che il valore ritornato da una invocazione di put
     * sempre con chiave 0 (e valore "test"), sia uguale a tmp. In seguito controllo che il valore appena immesso (test)
     * con chiave 0, sia effettivamente "test". Controllo che se aggiungo una nuova mappatura k/v con una chiave non ancora
     * immessa, la dimensione di map diventa 11. Infine controllo che venga lanciato NullPointerException se invoco put con
     * chiave null o valore null o entrambi null.
     *
     * <br><br>
     * <b>Design</b>: Verificare il corretto inserimento di una mappatura k/v in map. Verificare che se invoco il metodo
     * per una chiave già presente in map, mi ritorna il valore precedentemente contenuto in quella mappatura, sostituendolo
     * con il valore appena immesso. Controllo dell'effettivo lancio delle eccezioni.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo inserisce una nuova mappatura k/v se la chiave non è presente nella mappa,
     * altrimenti se presente, sostituisce il valore con quello della nuova mappatura e ritorna il valore precedentemente
     * contenuto in essa. Gestisce correttamente le eccezioni.
     */
    @Test
    public void putTest()
    {
        MapAdapter test = getAMap();

        for (int i = 0; i < test.size() / 2; i++)
            map.put(i, test.get(i));
        for (int i = 5; i < test.size(); i++)
            map.put("chiave " + (i - 4), i);

        assertEquals(10, map.size());

        for (int i = 0; i < test.size() / 2; i++)
            assertEquals("stringa " + i, map.get(i));
        for (int i = 5; i < test.size(); i++)
            assertEquals(i, map.get("chiave " + (i - 4)));

        Object tmp = map.get(0);
        assertSame(tmp, map.put(0, "test"));

        tmp = map.get(0);
        assertEquals("test", tmp);

        map.put("chiave", "valore");
        assertEquals(11, map.size());

        assertThrows(NullPointerException.class, () -> {
            map.put(null, 0);
        });
        assertThrows(NullPointerException.class, () -> {
            map.put(0, null);
        });
        assertThrows(NullPointerException.class, () -> {
            map.put(null, null);
        });
    }

    /**
     * Test del metodo     <b>public void putAll(HMap t)</b>
     * <br><br>
     * <b>Sommario</b>:  Creo due MapAdapter test, test2 con il metodo
     * privato getAMap() e aggiungo tutte le mappature k/v dentro map con putAll() e controllo che la dimensione
     * di map sia esattamente del numero delle mappature k/v aggiunte. Successivamente con due for, il
     * primo per le mappature k/v con chiave numerica, il secondo per le mappature k/v con chiave di tipo string,
     * controllo che i valori della mappatura k/v con le chiavi date, siano effettivamente presenti nella posizione corretta.
     * Infine controllo che venga lanciato NullPointerException se invoco il metodo con una mappa null.
     * <br><br>
     * <b>Design</b>: Verificare il corretto inserimento di tutte le mappature k/v di un'altra mappa in map. Controllo
     * dell'effettivo lancio dell'eccezione.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo inserisce tutte le mappature k/v della mappa passata come argomento in map.
     * Gestisce correttamente l'eccezione.
     */
    @Test
    public void putAllTest()
    {
        MapAdapter test = getAMap();
        MapAdapter test2 = getAMap2();

        map.putAll(test);
        map.putAll(test2);

        assertEquals(20, map.size());

        for (int i = 0; i < test.size() / 2; i++)
            assertEquals(test.get(i), map.get(i));
        for (int i = 5; i < test.size(); i++)
            assertEquals(test.get(i), map.get("stringa " + (i - 4)));

        for (int i = 10; i < test2.size() / 2 + 10; i++)
            assertEquals(test2.get(i), map.get(i));
        for (int i = 15; i < test2.size() + 10; i++)
            assertEquals(test2.get(i), map.get("stringa " + (i - 4)));

        assertThrows(NullPointerException.class, () -> {
            map.putAll(null);
        });
    }

    /**
     * Test del metodo     <b>public Object remove(int index)</b>
     * <br><br>
     * <b>Sommario</b>: Creo un MapAdapter test con il metodo privato getAMap() e aggiungo tutte le sue mappature k/v a map.
     * Istanzio tre nuove variabili; un Object tmp che successivamente conterrà il valore della mappatura k/v da confrontare con
     * il valore della mappatura k/v ritornato dal metodo remove (remove(i) nel primo for, remove("chiave " + (i - 4))
     * nel secondo for poiché le chiavi delle mappature k/v sono di due tipi diversi) per verificare che siano effettivamente uguali, un integer count per
     * tener traccia dell'effettiva riduzione della dimensione della mappa ad ogni invocazione del metodo remove e un
     * altro integer size per poter iterare l'iperazione di remove un numero di volte pari alla dimensione iniziale della mappa.
     * Una volta rimossi tutte le mappature k/v da map, riaggiungo tutte le mappature k/v di test ad essa, assegno alla variabile
     * tmp il valore della mappatura di chiave 2 per poter verificare che il metodo remove ritorni lo stesso valore
     * invocandolo con chiave pari a 2 e successivamente con chiave pari a 3 per confermare che il valore ritornato sia
     * diverso dalla variabile tmp. Verifico che se invoco il metodo remove(Object key) con chiave 8 (non presente nella mappa), mi ritorna
     * null. Verifico che la dimensione della mappa sia diminuita di due unità dopo aver invocato
     * remove due volte. Infine verifico il lancio della eccezione NullPointerException se viene invocato remove con una
     * chiave null.
     * <br><br>
     * <b>Design</b>: verificare la corretta rimozione delle mappature k/v dalla mappa in base alla chiave scelta e che ritorni
     * il valore della mappatura k/v appena rimossa, la riduzione della dimensione della mappa in base al numero di volte che viene invocato
     * il metodo e che gestisca correttamente l'eccezione.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo remove di Hashtable funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo rimuove correttamente la mappatura k/v di chiave key e ritorna il valore mappato, riducendo la dimensione
     * della mappa, a seconda del numero di volte che viene invocato. Gestisce correttamente l'eccezione.
     */
    @Test
    public void removeTest()
    {
        MapAdapter test = getAMap();
        map.putAll(test);

        Object tmp;
        int count = map.size();
        int size = map.size();

        for (int i = 0; i < size / 2; i++)
        {
            tmp = map.get(i);
            assertSame(tmp, map.remove(i));
            assertEquals(--count, map.size());
        }

        for (int i = 5; i < size; i++)
        {
            tmp = map.get("chiave " + (i - 4));
            assertSame(tmp, map.remove("chiave " + (i - 4)));
            assertEquals(--count, map.size());
        }

        map.putAll(test);
        tmp = map.get(2);
        assertSame(tmp, map.remove(2));
        assertNotSame(tmp, map.remove(3));
        assertNull(map.remove(8));
        assertEquals(size - 2, map.size());

        assertThrows(NullPointerException.class, () -> {
            map.remove(null);
        });
    }

    /**
     * Test del metodo     <b>public int size()</b>
     * <br><br>
     * <b>Sommario</b>: Verifico che la dimensione della mappa inizialmente sia zero. Sfrutto un for che aggiunge esattamente
     * una mappatura k/v a map per iterazione e confronto ogni volta la dimensione della mappa con un contatore che
     * riproduce il corretto funzionamento dell'incremento della dimensione di una mappa dopo l'aggiunta di una mappatura k/v.
     * A fine iterazioni confronto per l'ultima volta la dimensione della mappa con la variabile count, svuoto map e
     * verifico che la dimensione sia tornata a zero.
     * <br><br>
     * <b>Design</b>: verificare la corretteza del metodo size controllando la coerenza di esso con un confronto con una
     * variabile che riproduce lo stesso meccanismo del metodo.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero e che il
     * metodo size di Hashtable funzioni.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritorna l'effettiva dimensione della mappa.
     */
    @Test
    public void sizeTest()
    {
        assertEquals(0, map.size());

        int count = 0;
        for (int i = 0; i < 10; i++)
        {
            map.put(i, i);
            assertEquals(++count, map.size());
        }

        assertEquals(count, map.size());
        map.clear();
        assertEquals(0, map.size());
    }

    /**
     * Test del metodo     <b>public HCollection values()</b>
     * <br><br>
     * <b>Sommario</b>: Aggiungo alla mappa map inizialmente vuota tutte le mappature k/v di una MapAdapter
     * (generata con il metodo privato getAMap()).
     * Controllo che il metodo values() mi ritorni una HCollection non null.
     * <br><br>
     * <b>Design</b>: verificare la corretta generazione di un HCollection dei valori di map.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo genera un HCollection di tutti i valori di map, utilizzabile.
     */
    @Test
    public void valuesTest()
    {
        map.putAll(getAMap());
        assertNotNull(map.values());
    }

    /**
     * Test del metodo     <b>public int hashCode()</b>
     * <br><br>
     * <b>Sommario</b>: Creo due MapAdapter test1, test2 inizialmente vuoti e aggiungo ad ognuna una mappatura k/v diversa. Aggiungo
     * a map la stessa mappatura aggiunta a test1 e controllo che l'hashcode di map sia uguale all'hashcode di test1 e
     * diverso dall'hashcode di test2. Aggiungo una ulteriore mappatura a test1 per renderla diversa da map e controllo
     * che gli hashcode di map e test1 ora siano effettivamente diversi.
     * <br><br>
     * <b>Design</b>: corretto calcolo dell'hashcode in base alle mappature contenute e controllo dell'uguaglianza di esso per
     * mappe con elementi uguali o diversi.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornera l'hashcode corretto della mappa sottoforma di integer.
     */
    @Test
    public void hashCodeTest()
    {
        MapAdapter test1 = new MapAdapter();
        test1.put(1, "test1");

        MapAdapter test2 = new MapAdapter();
        test2.put(2, "test2");

        map.put(1, "test1");

        assertEquals(test1.hashCode(), map.hashCode());
        assertNotEquals(test2.hashCode(), map.hashCode());

        test1.put(1, 1);
        assertNotEquals(test1.hashCode(), map.hashCode());
    }

    /**
     * Test del metodo     <b>public boolean equals(Object o)</b>
     * <br><br>
     * <b>Sommario</b>: Creo una MapAdapter test inizialmente vuoto e aggiungo sia a questa che alla mappa map la mappatura k/v
     * 1/"test" e controllo che le due mappe siano uguali. Aggiungo un ulteriore elemento alla mappa
     * test e controllo che ora siano diverse. Controllo che la mappa non sia uguale alla mappa creata con il metodo privato getAMap(),
     * ad una mappa nulla e ad un oggetto non di tipo MapAdapter.
     * <br><br>
     * <b>Design</b>: corretto controllo della uguaglianza fra due mappe anche nei casi limite dove la mappa passata come
     * argomento sia nulla o non di tipo MapAdapter.
     * <br><br>
     * <b>Pre-condizioni</b>: oggetto di tipo MapAdapter correttamente inizializzato con dimensione uguale a zero.
     * <br><br>
     * <b>Post-Condizioni</b>: la mappa torna ad essere vuota e di dimensione zero.
     * <br><br>
     * <b>Risultato Atteso</b>: Il metodo ritornerà true se l'oggetto passato come argomento sia una mappa uguale a map,
     * altrimenti false.
     */
    @Test
    public void equalsTest()
    {
        MapAdapter test = new MapAdapter();
        test.put(1, "test");
        map.put(1, "test");
        assertTrue(map.equals(test));
        test.put(2, "test2");
        assertFalse(map.equals(test));

        assertFalse(map.equals(getAMap()));
        assertFalse(map.equals(null));
        assertFalse(map.equals(getAVector()));
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

    private Vector getAVector()
    {
        Vector test = new Vector();
        for (int i = 0; i < 10; i++) test.addElement(i);
        return test;
    }

}
