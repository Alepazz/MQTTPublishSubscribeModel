package Model

import org.eclipse.paho.client.mqttv3.IMqttClient
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException

private const val MQTT_PUBLISHER_ID = "ID OF THE PUBLISHER"
private const val MQTT_SERVER_ADDRESS = "tcp://IP:PORT"
private var instance: IMqttClient? = null

class Mqtt {

    companion object {
        fun getInstance(): IMqttClient {
            try {
                if(instance == null) {
                    instance = MqttClient(MQTT_SERVER_ADDRESS, MQTT_PUBLISHER_ID)
                }

                val options = MqttConnectOptions()
                options.isAutomaticReconnect = true
                options.isCleanSession = true
                options.connectionTimeout = 10

                if(!instance!!.isConnected){
                    instance!!.connect(options)
                }

            } catch (e: MqttException){
                e.printStackTrace()
            }

            return instance!!
        }
    }
}