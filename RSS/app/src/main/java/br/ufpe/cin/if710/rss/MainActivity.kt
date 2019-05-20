package br.ufpe.cin.if710.rss

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.widget.TextView
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.text.Charsets.UTF_8

class MainActivity : Activity() {

    //variável para resolução oficional
    private val RSS_FEED = "http://leopoldomt.com/if1001/g1brasil.xml"

    //LINKS PARA TESTAR
    //private val RSS_FEED = "http://rss.cnn.com/rss/edition.rss"
    //private val RSS_FEED = "http://pox.globo.com/rss/g1/brasil/"
    //private val RSS_FEED = "http://pox.globo.com/rss/g1/ciencia-e-saude/"
    //private val RSS_FEED = "http://pox.globo.com/rss/g1/tecnologia/"

    private var conteudoRSS: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        conteudoRSS = findViewById(R.id.conteudoRSS)
    }

    protected override fun onStart() {
        super.onStart()
        try {
            var textXML = CustomAsyncTask().execute(RSS_FEED).get()
            var listRSS = ParserRSS.parse(textXML)
            //conteudoRSS.setText(textXML)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    class CustomAsyncTask: AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg p0: String): String {
            var feed: String = p0[0]
            var result = getRssFeed(feed)
            return result
        }

        //Opcional - pesquise outros meios de obter arquivos da internet - bibliotecas, etc.
        @Throws(IOException::class)
        private fun getRssFeed(feed: String): String {
            var input: InputStream? = null
            var rssFeed = ""
            try {
                val url = URL(feed)
                val conn = url.openConnection() as HttpURLConnection
                input = conn.getInputStream()
                val out = ByteArrayOutputStream()
                val buffer = ByteArray(1024)
                var count: Int
                do {
                    count = input!!.read(buffer)
                    if(count == -1); break
                    out.write(buffer, 0, count)
                }while(true)
                val response = out.toByteArray()
                rssFeed = String(response, UTF_8)
            } finally {
                if (input != null) {
                    input!!.close()
                }
            }
            return rssFeed
        }
    }
}
