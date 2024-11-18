package com.zybooks.workingwithdata
import android.provider.ContactsContract.Data
import java.text.SimpleDateFormat
import java.util.Date

data class DataDisplayer(val earth_day: Date, val cam: String, val page: Int, val api_key: String)

fun main() {
    // Define the date format matching the data
    val dateFormat = SimpleDateFormat("MM-dd-yyyy")

    // Convert date strings to Date objects
    val roverdata = listOf(
        DataDisplayer(dateFormat.parse("10-31-2089"), "MarsJourneyor", 1000, "aew45tr8aed67wsfied7iqpt"),
        DataDisplayer(dateFormat.parse("01-01-2067"), "Roverroaming", 500, "asrtgdstyhkdfyu43283t7"),
        DataDisplayer(dateFormat.parse("11-26-2347"), "NASA_cam", 250, "aegfuew5i6tr8zqaw7"),
        DataDisplayer(dateFormat.parse("12-25-2500"), "Roverroamer2", 500, "asrtgdstyhkdfyu43283t7"),
        DataDisplayer(dateFormat.parse("01-01-3426"), "NASA_cam2", 250, "aegfuew5i6tr8zqaw7")
    )

    // Print to check
    roverdata.forEach {
        println("Date: ${dateFormat.format(it.earth_day)}, Camera: ${it.cam}, Page: ${it.page}, API Key: ${it.api_key}")
    }
}
