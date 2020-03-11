package Model


data class MqttPublishModel (val topic: String, val message: String, val retained: Boolean, val qos: Int)