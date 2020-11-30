package synergetic.devs.preferencesdatastore.viewModel

import android.app.Application
import androidx.datastore.preferences.Preferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import synergetic.devs.preferencesdatastore.modelClass.UserInfoClass
import synergetic.devs.preferencesdatastore.repository.DataStoreRepository

class DatastoreViewModel(application: Application):AndroidViewModel(application) {

    private val repository=DataStoreRepository(application)

    //save string to data base
    fun  saveStringToDataBaseStore(myNameValue: String, key: Preferences.Key<String>) = viewModelScope.launch(Dispatchers.IO){
        repository.saveDataToDataStore(myNameValue,key)
    }

        //read string to data base
    fun readStringFromDataStore(keName: Preferences.Key<String>): LiveData<String> {
       return repository.readStringFromDataStore(keName).asLiveData()
    }

}