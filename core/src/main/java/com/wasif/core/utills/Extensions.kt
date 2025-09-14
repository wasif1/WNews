package com.wasif.core.utills

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object Extensions {

    fun Context.openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            this.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}