package com.appetiserapps.testapplication.extension

import android.app.Activity
import android.widget.Toast

fun Activity.showShortToast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
        .show()
}
