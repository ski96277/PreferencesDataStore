package synergetic.devs.preferencesdatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import synergetic.devs.preferencesdatastore.viewModel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init view model
        mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
//read string data to from data store
        mainViewModel.readStringFromDataStore(StaticData.name).observe(this,{myName->

            textView.text=myName

        })

        button.setOnClickListener {
            val myName=editTextTextPersonName.text.toString()
            Toast.makeText(this,myName,Toast.LENGTH_SHORT).show()
            //save data to data store
            mainViewModel.saveStringToDataBaseStore(myName,StaticData.name)
        }

    }
}


