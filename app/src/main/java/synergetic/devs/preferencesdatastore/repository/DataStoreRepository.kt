package synergetic.devs.preferencesdatastore.repository

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import synergetic.devs.preferencesdatastore.BuildConfig
import java.io.IOException
//get package name
const val  PREFERENCE_NAME= BuildConfig.APPLICATION_ID

class DataStoreRepository(context:Context){

//init data store
    private val dataStore:DataStore<Preferences> = context.createDataStore(
        name = PREFERENCE_NAME
    )

    //save data to data store
    suspend fun saveDataToDataStore(name: String, key: Preferences.Key<String>){
        dataStore.edit { preference->
            preference[key]=name
        }
    }


    //read String data to from data store
    fun readStringFromDataStore(keyName: Preferences.Key<String>): Flow<String>  {

        return dataStore.data.catch { exception->
            if (exception is IOException){
                Log.e("TAG", "Data Store: "+exception.message.toString() )
                emit(emptyPreferences())
            }else{
                throw exception
            }

        }.map { preference->
            val myName:String = preference[keyName]?:"none"
            myName
        }
    }


}