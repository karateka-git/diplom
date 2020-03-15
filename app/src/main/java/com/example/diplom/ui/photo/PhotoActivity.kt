package com.example.diplom.ui.photo

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.diplom.utils.PhotoCreator
import com.example.diplom.R
import com.example.diplom.base.BaseActivity
import com.example.diplom.model.PhotoContainer
import java.io.File

class PhotoActivity : BaseActivity<PhotoPresenter>(), PhotoView {
    val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var photo: Button
    private lateinit var imagePhoto: ImageView
    val photoContainer = PhotoContainer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photo = findViewById<Button>(R.id.photo)
        imagePhoto = findViewById<ImageView>(R.id.imagePhoto)

        photo.setOnClickListener{
            val photoCreator =
                PhotoCreator(this, photoContainer)
            photoCreator.photoGo()
        }

        presenter.onViewCreated()
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

    override fun instantiatePresenter(): PhotoPresenter {
        return PhotoPresenter(this)
    }
}