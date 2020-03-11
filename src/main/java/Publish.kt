import Controller.MqttController
import Model.MqttPublishModel

fun main(args: Array<String>){

    println("BENVENUTO")

    var mqttController = MqttController()

    println("<TOPIC>")

    val topic = readLine()!!

    println("<MESSAGGIO>")

    val message = readLine()!!

    val messagePublishModel = MqttPublishModel(topic, message, true, 0)

    mqttController.publishMessage(messagePublishModel)

    println("Messaggio pubblicato")

    return
}