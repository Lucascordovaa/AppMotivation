package com.lucascordova.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lucascordova.motivation.infra.MotivationConstants
import com.lucascordova.motivation.R
import com.lucascordova.motivation.infra.SecurityPreferences
import com.lucascordova.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()


    }

    //R means resources
    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            handleSave()
        }

    }



    //navigate between activities
    private fun handleSave() { //this fun validates what the user typed and proceeds
        val name = binding.editName.text.toString()
        if (name != "") {

            SecurityPreferences(this).storeString(
                MotivationConstants.KEY.USER_NAME,
                name
            )// saves user name


            finish() //ends app if click on back button of the android navbar

        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }

    }
}