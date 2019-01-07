package com.jongzazaal.samplenoti.notification

data class NotificationData(val map: Map<String, Any?>) {
    val title: String           by map
    val des: String             by map
    val channel_id: String      by map
    val channel_name: String    by map
    val channel_des: String     by map
    val click: String           by map
    val extra: String           by map
    val picture_url: String     by map
}