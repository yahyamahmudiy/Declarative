package com.example.declarative.utils

import android.util.Log
import com.example.declarative.network.Server

class Logger {
    companion object {
        fun d(tag: String, msg: String) {
            if (Server.IS_TESTER) Log.d(tag, msg)
        }

        fun i(tag: String, msg: String) {
            if (Server.IS_TESTER) Log.i(tag, msg)
        }

        fun v(tag: String, msg: String) {
            if (Server.IS_TESTER) Log.v(tag, msg)
        }

        fun e(tag: String, msg: String) {
            if (Server.IS_TESTER) Log.e(tag, msg)
        }
    }
}