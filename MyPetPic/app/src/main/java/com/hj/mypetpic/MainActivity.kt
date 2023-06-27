package com.hj.mypetpic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var text1 : TextView
    lateinit var text2: TextView
    lateinit var checkAgr: CheckBox
    lateinit var rGroup1: RadioGroup
    lateinit var rdoDog: RadioButton
    lateinit var rdoCat: RadioButton
    lateinit var rdoMonkey: RadioButton
    lateinit var btnOK: Button
    lateinit var imgPet: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "myPetPic"

        text1 = findViewById<TextView>(R.id.Text1)
        checkAgr = findViewById<CheckBox>(R.id.ChkAgree)

        text2 = findViewById<TextView>(R.id.Text2)
        rGroup1 = findViewById<RadioGroup>(R.id.Rgroup1)
        rdoDog = findViewById<RadioButton>(R.id.RdoDog)
        rdoCat = findViewById<RadioButton>(R.id.RdoCat)
        rdoMonkey = findViewById<RadioButton>(R.id.RdoMonkey)

        btnOK = findViewById<Button>(R.id.BtnOK)
        imgPet = findViewById<ImageView>(R.id.ImgPet)

        checkAgr.setOnCheckedChangeListener{ compoundButton, b->
            if (checkAgr.isChecked == true) {
                text2.visibility = android.view.View.VISIBLE
                rGroup1.visibility = android.view.View.VISIBLE
                btnOK.visibility = android.view.View.VISIBLE
                imgPet.visibility = android.view.View.VISIBLE
            } else {
                text2.visibility = android.view.View.INVISIBLE
                rGroup1.visibility = android.view.View.INVISIBLE
                btnOK.visibility = android.view.View.INVISIBLE
                imgPet.visibility = android.view.View.INVISIBLE
            }
        }
        btnOK.setOnClickListener{
            when (rGroup1.checkedRadioButtonId) {
                R.id.RdoDog -> imgPet.setImageResource(R.drawable.puppy_)
                R.id.RdoCat -> imgPet.setImageResource(R.drawable.cat_)
                R.id.RdoMonkey -> imgPet.setImageResource(R.drawable.mokey_)
                else -> Toast.makeText(application, "반려동물 먼저 선택하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}