package Controller

import Model.Mqtt
import Model.MqttCallbackModel
import Model.MqttPublishModel
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage

class MqttController {

    fun publishMessage(messagePublishModel: MqttPublishModel){

        if(messagePublishModel.topic.isNullOrEmpty()){
            throw MqttException(1)
        }

        var mqttMessage = MqttMessage()
        mqttMessage.payload = messagePublishModel.message.toByteArray()

        val start = System.currentTimeMillis()
        Mqtt.getInstance().publish(messagePublishModel.topic, mqttMessage)
        println("Tempo trascorso in millis: " + (System.currentTimeMillis() - start))
    }


    fun subscribeChannel(topic: String) {

        Mqtt.getInstance().subscribeWithResponse(topic) { _, mqttMessage: MqttMessage ->
                val mqttCallbackModel = MqttCallbackModel()
                mqttCallbackModel.messageArrived(topic, mqttMessage)
        }

    }


}