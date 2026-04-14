package com.example.moneyexpanse.core.data.dataModel

import java.time.temporal.TemporalAmount

data class dataModel(
    val id:String?="0",
    val amount: String="0.0",
    val date: String?=null,
    val  type: String?=null,
    val note: String?=null
)
