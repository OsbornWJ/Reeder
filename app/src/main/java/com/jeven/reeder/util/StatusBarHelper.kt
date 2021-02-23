package com.jeven.reeder.util

import android.app.Activity
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS


/**
 * 创建人：wenjie
 * 邮箱： Osbornjie@163.com
 * 功能： 状态栏帮助类
 */
object StatusBarHelper {

    private const val SYSTEM_DEFAULT = 1
    private const val SYSTEM_MIUI = 2
    private const val SYSTEM_FLYME = 3
    private const val SYSTEM_ANDROID6 = 4

    private var mStatusBarType: Int = SYSTEM_DEFAULT

    @Suppress("DEPRECATION")
    private fun android6SetStatusBarLightMode(window: Window, light: Boolean): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.decorView.windowInsetsController
            if (light) {
                // 启用浅色-黑字状态栏内容
                controller?.setSystemBarsAppearance(
                    APPEARANCE_LIGHT_STATUS_BARS, // value
                    APPEARANCE_LIGHT_STATUS_BARS // mask
                )
            } else {
                controller?.setSystemBarsAppearance(0, 0)
            }
            return true
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView: View = window.decorView
            var systemUi = decorView.systemUiVisibility
            systemUi = if (light) {
                systemUi or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                0
            }
            decorView.systemUiVisibility = systemUi
            return true
        }
        return false
    }

    /**
     * 设置系统状态栏浅色模式
     */
    fun setStatusBarLightMode(activity: Activity) {
        if (mStatusBarType != SYSTEM_DEFAULT) {
            setStatusBarLightMode(activity, mStatusBarType)
        }
        mStatusBarType = SYSTEM_ANDROID6
        android6SetStatusBarLightMode(activity.window, true)
    }

    fun setStatusBarDarkMode(activity: Activity) {
        Log.d("设置颜色", "$mStatusBarType 的值1 $SYSTEM_DEFAULT")
        if (mStatusBarType == SYSTEM_DEFAULT) return
        Log.d("设置颜色", "$mStatusBarType 的值2")
        when (mStatusBarType) {
            SYSTEM_MIUI -> {

            }
            SYSTEM_FLYME -> {

            }
            SYSTEM_ANDROID6 -> {
                Log.d("设置颜色", "$mStatusBarType 的值3")
                android6SetStatusBarLightMode(activity.window, false)
            }
        }
    }

    /**
     * 适配MINU flyme的系统
     */
    fun setStatusBarLightMode(activity: Activity, statusBar_type: Int) {
        when (statusBar_type) {
            SYSTEM_MIUI -> {

            }
            SYSTEM_FLYME -> {

            }
            SYSTEM_ANDROID6 -> {
                android6SetStatusBarLightMode(activity.window, true)
            }
        }
    }
}