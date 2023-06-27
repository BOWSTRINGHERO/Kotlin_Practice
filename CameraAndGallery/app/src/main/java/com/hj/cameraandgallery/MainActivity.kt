package com.hj.cameraandgallery

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.hj.cameraandgallery.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var cameraPermission: ActivityResultLauncher<String>
    lateinit var storagePermission: ActivityResultLauncher<String>
    lateinit var cameraLauncher: ActivityResultLauncher<Uri>

    var photoUri:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        storagePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
            if (isGranted) {
                setViews()
            } else {
                Toast.makeText(baseContext,"외부 저장소 권한을 승인해야 앱을 사용할 수 있습니다.", Toast.LENGTH_LONG).show()
                finish()
            }
        }

        cameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
            if (isGranted) {
                openCamera()
            } else {
                Toast.makeText(baseContext,"카메라 권한을 승인해야 카메라를 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                binding.imagepreview.setImageURI(photoUri)
            }
        }

        storagePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    }
    fun setViews() {
        binding.buttonCamera.setOnClickListener{
            cameraPermissionLauncher.launch(manifest.permission.CAMERA)
        }
    }
    fun openCamera() {
        val photoFile = File.createTempFile(
            "IMG_",
            ".JPG",
            getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )
        photoUri = FileProvider.getUriForFile(
            this,
            "$(packageName).provider",
            photoFile
        )
        cameraLauncher.launch(photoUri)
    }
}