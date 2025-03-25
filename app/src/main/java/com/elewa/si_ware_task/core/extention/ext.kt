package com.elewa.si_ware_task.core.extention

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun TextInputEditText.addSearchListener(
    debounceTime: Long = 300L,
    minLength: Int = 3,
    onSearch: (query: String) -> Unit,
    onClear: () -> Unit
) {
    var job: Job? = null
    var lastQuery: String? = null // Store the last query to prevent unnecessary clear calls

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            val query = editable.toString().trim()

            if (query == lastQuery) return // Prevent duplicate calls
            lastQuery = query

            job?.cancel()
            job = CoroutineScope(Dispatchers.Main).launch {
                delay(debounceTime)

                if (query.isEmpty() && lastQuery?.isNotEmpty() == true) {
                    onClear() // ✅ Only trigger if it was not already empty
                } else if (query.length >= minLength) {
                    onSearch(query)
                }
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}
