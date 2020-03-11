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

Signature
-
**MqttController.kt**

--> fun publishMessage(messagePublishModel: MqttPublishModel)
Permette di pubblicare un messaggio. Il messaggio pubblicato deve essere speficato tramite l'unico parametro: MqttPublishModel. Tramite il metodo publish messo a disposizione da _Model.Mqtt_, dunque viene effettuato l'invio del messaggio al topic specificato.

--> data class MqttPublishModel (val topic: String, val message: String, val retained: Boolean, val qos: Int)\
Classe che funge da model per comporre un messaggio. Tra i vari parametri troviamo infatti il topic presso cui pubblicare il contenuto, e il messaggio che deve essere inviato.

--> fun subscribeChannel(topic: String)\
Se il primo metodo permette di pubblicare un messaggio presso un contenuto, tale metodo permette di sottoscriversi a tale contenuto. L'unico parametro, infatti, di tipo String, è il topic presso cui sottoiscriversi. La sottoscrizione si avvale di _Model.MqttCallbackModel_ che fornisce un metodo che verrà invocato in callback

**Mqtt.kt**
Tale classe permette di definire un'istanza MQTT, settata per dialogare con uno specifico Server, evidenziato dai parametri passati al costruttore della vera e propria connessione MQTT messa a disposizione dalla libreria _org.eclipse.paho.client.mqttv3.MqttClient_

**MqttCallbackModel.kt**
In tale classe, la quale estende MqttCallback, abbiamo l'implementazione del metodo specificato sopra: 

--> override fun messageArrived(p0: String?, p1: MqttMessage?)
L'override è dovuta all'implementazione di MqttCallback.\
Questa funzione viene chiamata quando si riceve un messaggio per il topic a cui ci si era sottoiscritti. Quello che viene passato alla funzione sono p0, ovvero il topic in questione, e il messaggio, sempre specificato nel formato specificato in precedenza. MqttMessagge, quindi contiene diverse informazione (mittente, messaggio, qos, ecc...). Per estrapolarne solo il contenuto testuale occorre la seguente sintassi: _${p1?.payload!!.toString(Charsets.UTF_8)}_





