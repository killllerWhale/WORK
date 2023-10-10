package com.realllapp.jobber.retrofit.dataclasses

data class WorkByLocation(
    val result_code: String? = null,
    val rows: List<Row>? = null,
    val status: String
)