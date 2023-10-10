package com.realllapp.jobber.retrofit.dataclasses

data class User(
    val birthday: String? = null,
    val data_reg: String? = null,
    val email: String? = null,
    val gender: String? = null,
    val id: String? = null,
    val name: String? = null,
    val password: String? = null,
    val result_code: Int,
    val rating_employer: Float? = null,
    val rating_worker: Float? = null,
    val status: String,
    val error: String? = null
)