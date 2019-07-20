package com.da62.model

import java.util.*

/**
 *  @param id
 *  @param name - 식물 이름
 *  @param description - 식물 정보
 * */
data class Plant(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val alarm: Boolean = false,
    val card: String = "",
    val kind: String = "",
    val waterTime: Date = Date(),
    val waterDate: Int = 0,
    val updateLove: Date = Date(),
    val love: Int = 0,
    val grow: String = ""
)