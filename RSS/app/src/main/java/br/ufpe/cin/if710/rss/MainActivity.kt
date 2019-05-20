package br.ufpe.cin.if710.rss

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {

    //variável para resolução oficional
    private val RSS_FEED = "http://leopoldomt.com/if1001/g1brasil.xml"

    //LINKS PARA TESTAR
    //private val RSS_FEED = "http://rss.cnn.com/rss/edition.rss"
    //private val RSS_FEED = "http://pox.globo.com/rss/g1/brasil/"
    //private val RSS_FEED = "http://pox.globo.com/rss/g1/ciencia-e-saude/"
    //private val RSS_FEED = "http://pox.globo.com/rss/g1/tecnologia/"

    //trocar para listview depois
    private TextView conteudoRSS;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        conteudoRSS = findViewById(R.id.conteudoRSS)
    }

    protected fun onStart() {
        super.onStart()
        try {
            //mudar código para lidar com acesso a internet fora da thread principal
            val feedXML = getRssFeed(RSS_FEED)
            conteudoRSS.setText(feedXML)
        } catch (e: IOException) {
            e.printStackTrace()
        }

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
            while ((count = input!!.read(buffer)) != -1) {
                out.write(buffer, 0, count)
            }
            val response = out.toByteArray()
            rssFeed = String(response, "UTF-8")
        } finally {
            if (input != null) {
                input!!.close()
            }
        }
        return rssFeed
    }
}
