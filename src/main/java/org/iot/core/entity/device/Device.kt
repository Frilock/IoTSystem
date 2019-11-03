package org.iot.core.entity.device

data class Device(val id: Int,
                  val name: String,
                  val description: String,
                  val ip: String)