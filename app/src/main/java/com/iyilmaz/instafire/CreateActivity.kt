package com.iyilmaz.instafire

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

private const val PICK_PHOTO_CODE = 1234

class CreateActivity : AppCompatActivity() {

    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        findViewById<Button>(R.id.btnPickImage).setOnClickListener {
            Log.i(TAG, "Open up image picker on device")
            val imagePickerIntent = Intent(Intent.ACTION_GET_CONTENT)
            imagePickerIntent.type = "image/*"
            if (imagePickerIntent.resolveActivity(packageManager) != null) {
                @Suppress("DEPRECATION")
                startActivityForResult(imagePickerIntent, PICK_PHOTO_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        @Suppress("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PHOTO_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                photoUri = data?.data
                Log.i(TAG,"photoUri $photoUri")
                findViewById<ImageView>(R.id.imageView).setImageURI(photoUri)
            } else {
                Toast.makeText(this,"Image picker action canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}