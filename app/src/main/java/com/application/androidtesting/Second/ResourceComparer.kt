package com.application.androidtesting.Second

import android.content.Context

class ResourceComparer {

    fun iSEqual(context: Context, resId: Int, string: String): Boolean {
        return context.getString(resId) == string
    }

}