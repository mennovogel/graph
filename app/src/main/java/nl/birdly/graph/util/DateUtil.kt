package nl.birdly.graph.util

import java.text.DateFormat
import java.util.*

fun Date.toDisplayableDate(): String = DateFormat.getDateInstance().format(this)