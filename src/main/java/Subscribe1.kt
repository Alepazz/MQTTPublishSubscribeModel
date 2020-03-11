import Controller.MqttController

fun main(args: Array<String>){

    var mqttController = MqttController()

    println("<TOPIC>")

    val topic = readLine()!!

    mqttController.subscribeChannel(topic)


}