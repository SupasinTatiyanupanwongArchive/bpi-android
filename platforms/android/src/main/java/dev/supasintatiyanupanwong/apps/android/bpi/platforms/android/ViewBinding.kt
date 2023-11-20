package dev.supasintatiyanupanwong.apps.android.bpi.platforms.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> ViewGroup.attach(
    crossinline action: (inflater: LayoutInflater, parent: ViewGroup) -> T
): T = action(LayoutInflater.from(context), this)
