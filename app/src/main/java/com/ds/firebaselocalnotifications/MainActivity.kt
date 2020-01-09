package com.coroutines.com

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.inappmessaging.MessagesProto
import com.google.firebase.inappmessaging.display.FirebaseInAppMessagingDisplay
import com.google.firebase.inappmessaging.model.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val whiteHex = "#ffffff"
        val magHex = "#9C27B0"
        val appUrl ="app://open.my.app"
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val action: String? = intent?.action
        val data: Uri? = intent?.data

        data?.let {
            helloTextView.text ="You just clicked from Firebase Message"
            return
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        val text = Text.builder()
                .setHexColor(whiteHex)
                .setText("Local Firebase Message Body")
                .build()

        val title = Text.builder()
                .setHexColor(whiteHex)
                .setText("Local Firebase Message Title")
                .build()

        val imageData = ImageData.builder()
                .setImageUrl("https://homepages.cae.wisc.edu/~ece533/images/frymire.png")
                .build()

        val button = Button.builder()
                .setButtonHexColor(whiteHex).setText(text).build()

        val campaignMeta = CampaignMetadata("S", "D", true)

        val primaryAction = Action.builder()
                .setActionUrl(appUrl)
                .setButton(button)
                .build()

        val fmessage = CardMessage.builder()
                .setPrimaryAction(primaryAction)
                .setBackgroundHexColor(magHex)
                .setPortraitImageData(imageData)
                .setTitle(title).build(campaignMeta)

        val bannerMessage = BannerMessage.builder()
                .setAction(primaryAction)
                .setImageData(imageData)
                .setBackgroundHexColor(magHex)
                .setBody(text)
                .setTitle(title).build(campaignMeta)


                FirebaseInAppMessagingDisplay
                        .getInstance()
                        .testMessage(this, bannerMessage, null)

    }
}

