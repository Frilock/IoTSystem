package org.iot.core.entity

data class Device(val id: Int,
                  val name: String,
                  val description: String,
                  val ip: String)