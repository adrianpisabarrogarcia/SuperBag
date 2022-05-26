package com.adriantxupisi.superbag

import com.google.type.Date

class Product(val name: String, val description: String, val date: Date, val repeat: Boolean) {

    constructor() : this("", "", Date(), false)


}