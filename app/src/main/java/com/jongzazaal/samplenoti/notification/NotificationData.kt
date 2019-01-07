package com.jongzazaal.samplenoti.notification

data class NotificationData(val map: Map<String?, Any?>) {
    private val defaultMap = map.withDefault { null }
    val title: String?           by defaultMap
    val des: String?             by defaultMap
    val channel_id: String      by defaultMap
    val channel_name: String    by defaultMap
    val channel_des: String     by defaultMap
    val click: String           by defaultMap
    val extra: String           by defaultMap
    val picture_url: String     by defaultMap
}