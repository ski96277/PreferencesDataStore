package synergetic.devs.preferencesdatastore.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import synergetic.devs.preferencesdatastore.R
import synergetic.devs.preferencesdatastore.modelClass.UserInfoClass
import synergetic.devs.preferencesdatastore.utils.StaticData
import synergetic.devs.preferencesdatastore.viewModel.DatastoreViewModel
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var datastoreViewModel: DatastoreViewModel
    lateinit var userInfoClass: UserInfoClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init view model
        datastoreViewModel=ViewModelProvider(this).get(DatastoreViewModel::class.java)
//read string data to from data store
        datastoreViewModel.readStringFromDataStore(StaticData.name).observe(this, { myName ->
            textView.text = myName
        })

        //read Object data to from data store
        datastoreViewModel.readStringFromDataStore(StaticData.userInfo).observe(this, { userInfo ->

            try {

                val gson = Gson()
                val obj: UserInfoClass = gson.fromJson(userInfo,UserInfoClass::class.java)
                textView.append(obj.address.toString())

            }catch (e:Exception){

            }
        })

        userInfoClass= UserInfoClass(1, "Imran", "Dhaka")



        button.setOnClickListener {
            val myName=editTextTextPersonName.text.toString()

            //save data to data store
            datastoreViewModel.saveStringToDataBaseStore(myName, StaticData.name)

            //save object as a string
            val gson = Gson()
            val json = gson.toJson(userInfoClass)
            datastoreViewModel.saveStringToDataBaseStore(json, StaticData.userInfo)

        }

    }
}


