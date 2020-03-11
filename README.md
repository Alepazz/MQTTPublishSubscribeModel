Con questa semplice applicazione è possibile testare il protocollo MQTT per inviare o ricevere messaggi.
Il protocollo in questione è infatti basato sul modello Publish/Subscriber: abbiamo qualcuno che invia dei messaggi, 
poichè decidere di produrre nuovo contenuto, ed altri che decidono di 'sottoscriversi' a quel contenuto. La sottoscrizione,
così come la pubblicazione di informazioni, avviene tramite l'utilizzo di un Topic, ovvero un argomento presso cui chi pubblica
decide di dedicare il proprio contentuo e chi si sottoscrivere decide di ricevere i messaggi.
Es. Se mi sottoscrivo al topic "JavaNews" non riceverò notifche per contenuti che verranno pubblicati sotto il topic "KotlinNews"
ma solo per quelli prodotti per "JavaNews"

Funzionamento
-

Il codice è strutturato come segue:
1. Un file Publish.kt
2. Un file Subscribe1.kt
3. Un file MqttController.kt
4. Una serie di files che compongono il modello

Il nome dei file è abbastanza esplicativo. **Publish.kt** è infatti il file che va eseguito per essere in grado di pubblicare 
informazioni per uno specifico topic. Da quello appreso poco sopra, sarà dunque necessario inserire per quale topic vogliamo
pubblicare del contenuto e quale è tale contenuto.
Richiamando il controller nel seguente modo, è possibile quindi inviare il messaggio al Server designato che si occuperà di 
inoltrare il messaggio a tutti gli utenti sottoscritti a quel topic: _mqttController.publishMessage(messagePublishModel)_.\
**messagePublishModel** è dunque il modello di cui il punto 4, ovvero ciò che serve come parametro al metodo di pubblicazione del controller.\

La configurazione del Server a cui vengono inoltrate le richieste avviene nel file _Mqtt.kt_ presente nel Model, in cui dobbiamo specificare IP:PORT.

Il file **Subscribe1.kt** ha le stesse modalità di funzionamento di Publish.kt, con la differenza che sarà utilizzato per sottoscriversi ad un Topic e ricevere contenuto da Publishers. Quindi, allo stesso modo illustrato sopra, Subscribe1.kt utilizza il controller per poter sottoscriversi ad un Topic specificato dall'utente.

Il file **MqttController.kt** è quindi coloi che gestisce le comunicazioni. Tramite due semplici metodi, __publishMessage(messagePublishModel: MqttPublishModel)__ e __subscribeChannel(topic: String)__ gestisce la pubblicazione di contenuti per uno specifico Topic e la sottoscrizione ad uno di essi. Per quanto riguarda la sottoscrizione ad un Topic, il funzionamento è il seguente: tramite una funzione di Callback viene ricevuto il messaggio pubblicato per il topic di interesse e stampato a Console.\
Con uno sguardo rapido alla funzione di Callback, questa non fa altro che invocare un metodo alla ricezione di un nuovo messaggio, il quale il topic di interesse (p0), ovvero una stringa, e l'oggetto MqttMessage che contiene il vero e proprio messaggio, quindi Nome del publisher e contenuto della pubblicazione.
