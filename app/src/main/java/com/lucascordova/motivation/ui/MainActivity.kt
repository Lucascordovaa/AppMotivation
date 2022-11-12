/*FIRST ENABLED VIEW BINDING: Go to build.gradle (Module: app), below KotlinOptions, add
buildFeatures {
        viewBinding true
    }
    and sync now
 */
package com.lucascordova.motivation.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.lucascordova.motivation.infra.MotivationConstants
import com.lucascordova.motivation.R
import com.lucascordova.motivation.data.Mock
import com.lucascordova.motivation.infra.SecurityPreferences
import com.lucascordova.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    View.OnClickListener { //Implement members afer adding view.onclicklistener, it created OnClick fun

    private lateinit var binding: ActivityMainBinding
    /*var binging is the one that receives the binding, it creates classes
    using ActivityMainBinging
     */

    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater) //takes layout and expands/creates
        setContentView(binding.root) //assigns to view

        supportActionBar?.hide() //hides the top bar, the "?" represents null safety. It will hide only if exists.

        handleFilter(R.id.image_all) //all already selected
        handleNextPhrase() //already starts showing a phrase

        binding.buttonNewPhrase.setOnClickListener(this) //click event, has to add View.OnClick Listener for (this) to work
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textUserName.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        handleUserName() //calls function that shows user name
    }

    override fun onClick(view: View) { //this function executes when you click the button
        if (view.id == R.id.button_new_phrase) { //goes into this if it founds the button
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id) //if you click on icons
        } else if (view.id == R.id.text_user_name) {
            startActivity(Intent(this, UserActivity::class.java)) //click on your name
        }
    }

    //gets phrase
    private fun handleNextPhrase(){
        binding.textPhrase.text = Mock().getPhrase(categoryId)
    }

    //changes color of icon when you click on it, and adds filter to buttons
    private fun handleFilter(id: Int) {

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }

    }


    //shows user name
    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!" //USER_NAME is a constant, check constants class.
    }
}