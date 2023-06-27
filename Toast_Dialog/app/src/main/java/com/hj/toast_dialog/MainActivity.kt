package com.hj.toast_dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button1 = findViewById<Button>(R.id.button1)
        var button2 = findViewById<View>(R.id.button2)

        button1.setOnClickListener{
            var dlg = AlertDialog.Builder(this@MainActivity)
            dlg.setTitle("제목입니다.")
            dlg.setMessage("이곳이 내용입니다.")
            dlg.setIcon(R.mipmap.ic_launcher)
            dlg.setPositiveButton("확인"){dialog, which ->
                Toast.makeText(this@MainActivity,
                    "확인을 눌렀네요",Toast.LENGTH_SHORT).show()
            }
            dlg.show()
        }

        button2.setOnClickListener {
            var versionArray = arrayOf("오레오", "파이", "큐(10)")
            var dlg = AlertDialog.Builder(this@MainActivity)
            dlg.setTitle("좋아하는 버전은?")
            dlg.setIcon(R.mipmap.ic_launcher)
            dlg.setItems(versionArray){ dialog, which ->
                (button2 as Button).text = versionArray[which]
            }
            dlg.setPositiveButton("닫기", null)
            dlg.show()
        }
    }
}
