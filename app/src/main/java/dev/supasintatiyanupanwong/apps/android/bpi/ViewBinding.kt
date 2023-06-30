package dev.supasintatiyanupanwong.apps.android.bpi

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> Activity.inflate(
    crossinline action: (inflater: LayoutInflater) -> T
): T = action(layoutInflater)

inline fun <T : ViewBinding> ViewGroup.attach(
    crossinline action: (inflater: LayoutInflater, parent: ViewGroup) -> T
): T = action(LayoutInflater.from(context), this)
