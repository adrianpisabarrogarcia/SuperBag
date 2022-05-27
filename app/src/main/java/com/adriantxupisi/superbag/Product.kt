package com.adriantxupisi.superbag

import java.util.*

data class Product(
    var name: String? = null,
    var description: String ?= null,
    var date: String ?= null,
    var repeat: Boolean ?= null,
    var quantity: Int ?= null,
    var userEmail: String ?= null
)
