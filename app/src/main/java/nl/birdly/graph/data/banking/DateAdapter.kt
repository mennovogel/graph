package nl.birdly.graph.data.banking

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ"

class DateAdapter {

    private val dateFormat = SimpleDateFormat(
        DATE_PATTERN,
        Locale("nl")
    )

    @FromJson
    @Synchronized
    fun dateFromDateString(dateString: String) = dateFormat.parse(dateString)!!

    @ToJson
    @Synchronized
    fun dateStringToDate(date: Date) : String {
        return dateFormat.format(date)
    }
}

