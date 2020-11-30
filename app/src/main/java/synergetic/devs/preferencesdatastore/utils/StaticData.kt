package synergetic.devs.preferencesdatastore.utils

import androidx.datastore.preferences.preferencesKey
import synergetic.devs.preferencesdatastore.modelClass.UserInfoClass

object StaticData {

     val name= preferencesKey<String>("my_name");
     val userInfo= preferencesKey<String>("user_info");

}