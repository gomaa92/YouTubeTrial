package com.sgn.apps.loadimagefromyoutube

import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_main.*
import java.net.MalformedURLException
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val videoId = extractYoutubeId("http://www.youtube.com/watch?v=t7UxjpUaL3Y")!!//"1FJHYqE0RDg"
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })








        var videoId = extractYoutubeId("http://www.youtube.com/watch?v=t7UxjpUaL3Y")
        Log.e("VideoId is->", "" + videoId)
        val img_url =
            "http://img.youtube.com/vi/" + videoId.toString() + "/0.jpg" // this is link which will give u thumnail image of that video



      /*  val target: Target = object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                if (bitmap != null) {
                    img_thumnail.setImageBitmap(bitmap)
                    Toast.makeText(this@MainActivity,"loaded",Toast.LENGTH_LONG).show()
                }
                else
                    Toast.makeText(this@MainActivity,"null",Toast.LENGTH_LONG).show()

            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                Toast.makeText(this@MainActivity,"error",Toast.LENGTH_LONG).show()
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                img_thumnail.setImageDrawable(placeHolderDrawable)
                Toast.makeText(this@MainActivity,"onPrepareLoad",Toast.LENGTH_LONG).show()

            }

        }
        val uiHandler = Handler(Looper.getMainLooper())
        uiHandler.post(Runnable {
            Picasso.get()
                .load(img_url)
                .resize(500, 500)
                .into(target)
        })*/

    }


    @Throws(MalformedURLException::class)
    fun extractYoutubeId(url: String?): String? {
        val query: String = URL(url).query
        val param = query.split("&").toTypedArray()
        var id: String? = null
        for (row in param) {
            val param1 = row.split("=").toTypedArray()
            if (param1[0] == "v") {
                id = param1[1]
            }
        }
        return id
    }
}