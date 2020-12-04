package nl.joozd.aocday4.ui.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import nl.joozd.aocday4.R
import nl.joozd.aocday4.databinding.ActivityMainBinding
import nl.joozd.aocday4.model.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val activity = this
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.runOnce(intent)

        super.onCreate(savedInstanceState)


        ActivityMainBinding.inflate(layoutInflater).apply {

            //RecyclerView thingies
            val passportAdapter = PassportsAdapter()
            passportList.adapter = passportAdapter
            passportList.layoutManager = LinearLayoutManager(activity)
            viewModel.passports.observe(activity){
                passportAdapter.list = it
            }

            setContentView(root)
        }
    }

}