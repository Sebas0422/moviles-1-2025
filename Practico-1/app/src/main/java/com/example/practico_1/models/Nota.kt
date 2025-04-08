package com.example.practico_1.models

import java.io.Serializable

class Nota (
    var id: Int,
    var title: String,
    var content: String,
    var isChecked: Boolean = false,
): Serializable