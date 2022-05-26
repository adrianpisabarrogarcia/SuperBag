package com.adriantxupisi.superbag

import com.google.type.Date

class Product(val name: String, val description: String, val date: Date, val repeat: Boolean) {
    override fun toString(): String {
        return "Product(name='$name', description='$description', date=$date, repeat=$repeat)"
    }
}