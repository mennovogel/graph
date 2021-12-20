package nl.birdly.graph.util

import java.text.DateFormat
import java.util.*

fun Date.toDisplayableDate() = DateFormat.getDateInstance().format(this)