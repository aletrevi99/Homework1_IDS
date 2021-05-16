Supponete di essere in ambiente Java Micro Edition, precisamente CLDC1.1.
( https://docs.oracle.com/javame/config/cldc/ref‐impl/cldc1.1/jsr139/index.html ).


Supponete di voler utilizzare in questo ambiente una libreria di classi (myLib) nata in ambiente J2SE 1.4.2 (http://geas.dei.unipd.it/jdk1.4.2/docs/api/ https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html).
In particolare, la libreria contiene classi che fanno uso delle interfacce List e Map e, di conseguenza, Set, Collection, Iterator e ListIterator del Java2 Collections Framework versione 1.4.2.


Dovete utilizzare la metodologia Test Driven Development, e, quindi, definire ed Implementare le test suite Junit per le classi sviluppate. Le classi di test devono essere contenute in un package myTest (senza ulteriori livelli di nidificazione). Il package deve contenere una classe TestRunner che possa essere invocata da linea di comando, eseguire tutti i test da voi definiti, fornire almeno il risultato dei test ed il numero complessivo di test eseguiti.
Documentate la/le vostra test suite utilizzando il template “SAFe” descritto nella tabella 1 di questo documento. (ulteriore documentazione relativa a SAFe e’ disponibile qui https://jazz.net/help‐dev/clm/index.jsp?re=1&topic=/com.ibm.rational.test.qm.doc/topics/r_testsuite_template_ref.html&scope=null)
Documentate ogni test case secondo il template “Homework” descritto nella tabella 2 di questo documento.


---------------------------------------------------------------
ListAdapterSuite


Sommario: Questa TestSuite è stata concepita allo scopo di testare a fondo tutti i metodi della classe ListAdapter, verificando il loro corretto funzionamento e creando un test per ogni singolo metodo sviluppato, sia per l'interfaccia pubblica, sia per le classi private necessarie per implementare correttamente iteratori e sublist con funzionalità di backing. È sviluppata in due classi: ListAdapterTester.java che racchiude tutti i test relativi ai metodi della classe ListAdapter e i relativi iteratori; SubListAdapterTester.java che racchiude tutti i test relativi ai metodi della classe interna privata SubList, creata apposta per garantire il backing della sublist sulla lista la quale è stata creata tramite il metodo subList(int, int).


Design: Per la classe ListAdapterTester.java, prima di ogni test viene creata una lista vuota, che poi verrà riempita, se necessario, nei vari test dei metodi, e vengono stampati a schermo gli elementi, indice per indice, contenuti nella lista che viene generata tramite il metodo privato getACollection(), creato per automatizzare un'azione molto frequente nei test. Questi ultimi sono stati scritti in modo tale da testare tutte le funzionalità dei metodi, verificando la loro corretta esecuzione, sia passando come argomenti oggetti e variabili validi, e non, sia per controllare la gestione delle eccezioni. Alla fine di ogni test, la lista viene svuotata se non è già vuota.
Per la seconda classe SubListAdapterTester.java, prima di ogni test viene creata una lista e riempita con il metodo privato getACollection() da cui poi genero la sublist da testare e stampo a schermo i suoi elementi, indice per indice. Alla fine di ogni test, svuoto la sublist se non è già vuota. Anche in questa classe di test i metodi sono stati scritti in modo tale da testare tutte le loro funzionalità verificando la loro corretta esecuzione, sia passando come argomenti oggetti e variabili valide, e non, sia per controllare la gestione delle eccezioni e inoltre per garantire il backing della sublist sulla lista.
In entrambe le classi è stato sviluppato un test per metodo, ad eccezione di iteratorTest() e listIteratorTest(), poiché ho voluto testare tutti i metodi della loro implementazione in un unico test.
Per entrambe le classi utilizzo un metodo privato getAVector() per controllare l’eccezione ClassCastException().


Pre-Condizioni: La versione di Java utilizzata per la creazione di questa suite è OpenJDK 16. Per quanto riguarda JUnit invece è stata utilizzata la versione 4.13.1.


Post-Condizioni: Tutti i test creati, sia quelli di ListAdapterTester.java che quelli di SubListAdapterTester.java, devono dare esito positivo.


Execution Records: di seguito riporto il testo generato sul terminale in seguito all’esecuzione del TestRunner, per quanto riguarda questa Suite


Elementi presenti nella lista getACollection() 

0. 0
1. 1
2. 2
3. 3
4. 4
5. stringa 1
6. stringa 2
7. stringa 3
8. stringa 4
9. stringa 5 

Elementi presenti nella sublist

0. 2
1. 3
2. 4
3. stringa 1
4. stringa 2
5. stringa 3 


======== Inizio Test per ListAdapter ======== 

Tutti i test sono stati completati con successo. 

Test eseguiti: 52

========= Fine Test per ListAdapter =========


Execution Variable: n/a

------------------------------------------------------------
 MapAdapterSuite


Sommario: Questa TestSuite è stata concepita allo scopo di testare a fondo tutti i metodi della classe MapAdapter, verificando il loro corretto funzionamento e creando un test per ogni singolo metodo sviluppato, sia per l'interfaccia pubblica, sia per le classi private necessarie per implementare correttamente i Set e le Collection ritornate rispettivamente dai metodi entrySet(), keySet() e values() con funzionalità di backing.
È sviluppata in tre classi: MapAdapterTester.java, che racchiude tutti i test relativi ai metodi della classe pubblica MapAdapter; EntrySetTester.java, KeySetTester.java, ValuesCollectionTester.java che racchiudono tutti i test relativi ai metodi delle classi private EntrySet, KeySet e ValuesCollection, create apposta per garantire il backing dei Set e Collection sulla mappa la quale è stata creata tramite i metodi entrySet(), keySet() e values().


Design: Per la classe MapAdapterTester.java, prima di ogni test viene creata una mappa vuota, che poi verrà riempita, se necessario, nei vari test dei metodi. Per automatizzare questa ultima funzione ho creato un metodo privato getAMap(), che mi ritorna una mappa piena di dimensione 10. I metodi di test sono stati scritti in modo tale da testare tutte le funzionalità di questi verificando la loro corretta esecuzione, sia passando come argomenti oggetti e variabili valide, e non, sia per controllare la gestione delle eccezioni. Alla fine dei ogni test, la mappa viene svuotata se non è già vuota.
Per le rimanenti EntrySetTester.java, KeySetTester.java, ValuesCollectionTester.java, prima di ogni test vengono create tre mappe vuote e da esse, vengono generati rispettivamente un EntrySet, un KeySet ed un ValuesCollection. A fine di ogni test, vengono tutte e tre svuotate se non sono già vuote. Anche in queste classi di test, i metodi sono stati scritti in modo tale da testare tutte le loro funzionalità, sia verificando la loro corretta esecuzione, passando come argomenti oggetti e variabili valide, e non, sia per controllare la gestione delle eccezioni.
In tutte le classi è stato sviluppato un test per metodo, ad eccezione di iteratorTest(), poiché ho voluto testare tutti i metodi della sua implementazione in un unico test.
Per le classi utilizzo i metodi privati getAVector() per controllare l’eccezione ClassCastException(), getAMap2() per automatizzare la creazione di una mappa con chiavi e valori diversi, getAMap3() per automatizzare la creazione di una mappa con solo 5 elementi diversi dalla mappa generata da getAMap() e getACollection() per automatizzare la creazione di una Collection.


Pre-Condizioni: La versione di Java utilizzata per la creazione di questa suite è OpenJDK 16. Per quanto riguarda JUnit invece è stata utilizzata la versione 4.13.1.


Post-Condizioni: Tutti i test creati in MapAdapterTester.java, EntrySetTester.java, KeySetTester.java, ValuesCollectionTester.java devono dare esito positivo.


Execution Records: di seguito riporto il testo generato sul terminale in seguito all’esecuzione del TestRunner, per quanto riguarda questa Suite


======== Inizio Test per MapAdapter ======== 

Tutti i test sono stati completati con successo.

Test eseguiti: 63

========= Fine Test per MapAdapter =========


Execution Variable: n/a
