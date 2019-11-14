package com.khinthirisoe.dynamiclinks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent != null) {
            handleDeepLink(intent)
        }
    }

    private fun handleDeepLink(intent: Intent) {
        //  adb shell am start -a android.intent.action.VIEW \ -d "https://httpbin.page.link/iGuj" com.khinthirisoe.dynamiclinks
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener { pendingDynamicLinkData ->

                if (pendingDynamicLinkData != null) {
                    Log.d("message","getDynamicLink:onSuccess " + pendingDynamicLinkData.link.toString())
                }
            }
            .addOnFailureListener {
                Log.d("message","getDynamicLink:onFailure ", it.cause)
            }
    }
}
