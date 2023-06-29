package com.hj.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hj.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val database =
        Firebase.database("https://bowstringhero-default-rtdb.asia-southeast1.firebasedatabase.app/")
    val myRef = database.getReference("users")

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fun addItem(user: User) {
            val id = myRef.push().key!!
            user.id = id
            myRef.child(id).setValue(user)
        }

        with(binding) {
            btnPost.setOnClickListener {
                val name = editName.text.toString()
                val password = editPassword.text.toString()
                val user = User(name, password)
                addItem(user)
            }
        }

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.textList.setText("")

                for (item in snapshot.children) {
                    item.getValue(User::class.java)?.let { user ->
                        binding.textList.append("${user.name} : ${user.password} \n")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        })

//        myRef.setValue("Hello, Developers must get jobs.")
//        myRef.child("name").setValue("Scott")
//        myRef.child("age").setValue(19)
//
//        myRef.child("name").get().addOnSuccessListener {
//            Log.d("파이어베이스", "name=${it.value}")
//        }.addOnFailureListener {
//            Log.d("파이어베이스", "error=${it}")
//        }
//
//        myRef.child("name").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.d("파이어베이스","${snapshot.value}")
//                print(snapshot.value)
//            }
//        })
    }
}
class User {
    var id: String = ""
    var name: String = ""
    var password: String = ""
    constructor()

    constructor(name: String, password: String) {
        this.name = name
        this.password = password
    }
}
