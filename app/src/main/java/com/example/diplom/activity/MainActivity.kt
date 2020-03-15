package com.example.diplom.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.diplom.PhotoContainer
import com.example.diplom.PhotoCreator
import com.example.diplom.R
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var photo: Button
    private lateinit var imagePhoto: ImageView
    private lateinit var photoURI: Uri
    val photoContainer = PhotoContainer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photo = findViewById<Button>(R.id.photo)
        imagePhoto = findViewById<ImageView>(R.id.imagePhoto)

        photo.setOnClickListener{
            val photoCreator = PhotoCreator(this, photoContainer)
            photoCreator.photoGo()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //imagePhoto.setImageURI(photoURI )
            for (el in photoContainer.set) {
                var bitmap = BitmapFactory.decodeFile(File(el).absolutePath)
                imagePhoto.setImageBitmap(bitmap)
                imagePhoto.setRotation(90f)
            }
        }
    }


}
