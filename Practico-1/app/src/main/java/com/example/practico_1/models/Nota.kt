package com.example.practico_1.models

import android.graphics.Color
import java.io.Serializable

class Nota (
    var id: Int,
    var title: String,
    var content: String,
    var isChecked: Boolean = false,
    var color: Int = Color.WHITE,
): Serializable