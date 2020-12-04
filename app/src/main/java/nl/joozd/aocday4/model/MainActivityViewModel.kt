package nl.joozd.aocday4.model

import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import nl.joozd.aocday4.data.Repository

class MainActivityViewModel: ViewModel() {
    private var intent: Intent? = null
    private val repo = Repository.getInstance()

    val passports: LiveData<List<Passport>> = Transformations.map(repo.data){ data ->
        data.joinToString("~").split("~~").map{Passport.of(it)}
    }


    fun runOnce(newIntent: Intent){
        if (intent == null){
            intent = newIntent
            run()
        }
    }
    private fun run() {
        intent?.let {
            val uri = (it.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri ?: it.data)
            repo.readUriToData(uri)
        }
    }







}