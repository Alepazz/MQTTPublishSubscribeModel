Con questa semplice applicazione è possibile testare il protocollo MQTT per inviare o ricevere messaggi.
Il protocollo in questione è infatti basato sul modello Publish/Subscriber: abbiamo qualcuno che invia dei messaggi, 
poichè decidere di produrre nuovo contenuto, ed altri che decidono di 'sottoscriversi' a quel contenuto. La sottoscrizione,
così come la pubblicazione di informazioni, avviene tramite l'utilizzo di un Topic, ovvero un argomento presso cui chi pubblica
decide di dedicare il proprio contentuo e chi si sottoscrivere decide di ricevere i messaggi.
Es. Se mi sottoscrivo al topic "JavaNews" non riceverò notifche per contenuti che verranno pubblicati sotto il topic "KotlinNews"
ma solo per quelli prodotti per "JavaNews"

