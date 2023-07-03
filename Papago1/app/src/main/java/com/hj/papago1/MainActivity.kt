import android.R
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


class Papago : AppCompatActivity() {
    var et_target: EditText? = null
    var textView: TextView? = null
    var btn: Button? = null
    var getresult: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_papago)
        textView = findViewById<TextView>(R.id.textView)
        et_target = findViewById<EditText>(R.id.et_target)
        btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener(View.OnClickListener {
            val translate: Translate = Translate()
            translate.execute() //버튼 클릭시 ASYNC 사용
        })
    }

    internal inner class Translate :
        AsyncTask<String?, Void?, String?>() {
        //ASYNCTASK를 사용
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected override fun doInBackground(vararg strings: String): String? {

            //////네이버 API
            val clientId = "" //애플리케이션 클라이언트 아이디값";
            val clientSecret = "" //애플리케이션 클라이언트 시크릿값";
            try {
                val text =
                    URLEncoder.encode(et_target!!.text.toString(), "UTF-8") /// 번역할 문장 Edittext  입력
                val apiURL = "https://openapi.naver.com/v1/papago/n2mt"
                val url = URL(apiURL)
                val con = url.openConnection() as HttpURLConnection
                con.requestMethod = "POST"
                con.setRequestProperty("X-Naver-Client-Id", clientId)
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret)
                // post request
                val postParams = "source=ko&target=en&text=$text"
                con.doOutput = true
                val wr = DataOutputStream(con.outputStream)
                wr.writeBytes(postParams)
                wr.flush()
                wr.close()
                val responseCode = con.responseCode
                val br: BufferedReader
                br = if (responseCode == 200) { // 정상 호출
                    BufferedReader(InputStreamReader(con.inputStream))
                } else {  // 에러 발생
                    BufferedReader(InputStreamReader(con.errorStream))
                }
                var inputLine: String?
                val response = StringBuffer()
                while (br.readLine().also { inputLine = it } != null) {
                    response.append(inputLine)
                }
                br.close()
                println(response.toString())
                //        textView.setText(response.toString());
                getresult = response.toString()
                getresult =
                    getresult!!.split("\"".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()[27] //스플릿으로 번역된 결과값만 가져오기
                textView!!.text = getresult //  텍스트뷰에  SET해주기
            } catch (e: Exception) {
                println(e)
            }
            return null
        }
    }
}