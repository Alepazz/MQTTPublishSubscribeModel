package Model

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttMessage

class MqttCallbackModel: MqttCallback {
    override fun messageArrived(p0: String?, p1: MqttMessage?) {
        println(message = "Messaggio ${p1?.payload!!.toString(Charsets.UTF_8)} arrivato, con topic $p0")
    }

    override fun connectionLost(p0: Throwable?) {
        TODO("not implemented")
    }

    override fun deliveryComplete(p0: IMqttDeliveryToken?) {
        TODO("not implemented")
    }
}
