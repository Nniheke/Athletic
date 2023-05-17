package com.theathletic.interview.core

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

/**
 * Formats the date string into a human-readable relative time format.
 *
 * @return The formatted date string.
 */
fun String.formatDate(): String {
    val isApiSupported = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O

    return if (isApiSupported) {
        // Using java.time APIs available from API level 26 (Oreo) onwards
        val instant = Instant.parse(this)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val duration = Duration.between(localDateTime, LocalDateTime.now())

        val yearsAgo = duration.toDays() / 365
        val monthsAgo = duration.toDays() / 30
        val daysAgo = duration.toDays()
        val hoursAgo = duration.toHours()
        val minutesAgo = duration.toMinutes()

        when {
            yearsAgo > 0 -> "$yearsAgo ${if (yearsAgo == 1L) "year" else "years"} ago"
            monthsAgo > 0 -> "$monthsAgo ${if (monthsAgo == 1L) "month" else "months"} ago"
            daysAgo > 0 -> "$daysAgo ${if (daysAgo == 1L) "day" else "days"} ago"
            hoursAgo > 0 -> "$hoursAgo ${if (hoursAgo == 1L) "hour" else "hours"} ago"
            minutesAgo > 0 -> "$minutesAgo ${if (minutesAgo == 1L) "minute" else "minutes"} ago"
            else -> "Less than a minute ago"
        }
    } else {
        // Using SimpleDateFormat for API levels below 26
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val date = inputFormat.parse(this)

        val duration = System.currentTimeMillis() - date.time
        val secondsAgo = duration / 1000
        val minutesAgo = secondsAgo / 60
        val hoursAgo = minutesAgo / 60
        val daysAgo = hoursAgo / 24
        val monthsAgo = daysAgo / 30
        val yearsAgo = daysAgo / 365

        when {
            yearsAgo > 0 -> "$yearsAgo ${if (yearsAgo == 1L) "year" else "years"} ago"
            monthsAgo > 0 -> "$monthsAgo ${if (monthsAgo == 1L) "month" else "months"} ago"
            daysAgo > 0 -> "$daysAgo ${if (daysAgo == 1L) "day" else "days"} ago"
            hoursAgo > 0 -> "$hoursAgo ${if (hoursAgo == 1L) "hour" else "hours"} ago"
            minutesAgo > 0 -> "$minutesAgo ${if (minutesAgo == 1L) "minute" else "minutes"} ago"
            else -> "Less than a minute ago"
        }
    }
}

