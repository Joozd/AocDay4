package nl.joozd.aocday4.data

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import nl.joozd.aocday4.App
import java.io.FileNotFoundException
import java.io.InputStream

class Repository: CoroutineScope by MainScope() {
    private val _data = MutableLiveData(listOf<String>())
    val data: LiveData<List<String>>
        get() = _data

    fun readUriToData(uri: Uri?) = launch(Dispatchers.IO) {
        uri?.getInputStream()?.reader(Charsets.UTF_8)?.let{
            _data.postValue(it.readLines())
        }
    }

    private fun Uri.getInputStream(): InputStream? = try {
        /*
         * Get the content resolver instance for this context, and use it
         * to get a ParcelFileDescriptor for the file.
         */
        App.instance.contentResolver.openInputStream(this)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        null
    }

    companion object{
        private var instance: Repository? = null
        fun getInstance() = synchronized(this){
            instance ?: Repository().also{ instance = it}
        }
    }
}