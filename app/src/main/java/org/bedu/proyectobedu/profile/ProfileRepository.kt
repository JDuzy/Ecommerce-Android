package org.bedu.proyectobedu.profile

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import okhttp3.*
import org.bedu.proyectobedu.R
import org.json.JSONObject
import java.io.IOException

class ProfileRepository {

    companion object{

        private const val profileRequestUrl = "https://reqres.in/api/users/"
        val profileLiveData = MutableLiveData<Profile>()


        fun fetchRandomProfile(){
            val okHttpClient = OkHttpClient()

            val request = Request.Builder()
                .url(profileRequestUrl + (1..12).random())
                .build()

            okHttpClient.newCall(request).enqueue(object: Callback {

                override fun onFailure(call: Call, e: IOException) {
                    Log.e("failed request", "Failed to fetch profile data")
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                        if (response.isSuccessful && body != null){
                            val jsonBody =  JSONObject(body)
                            val dataJson = jsonBody.getString("data")
                            val profile = Gson().fromJson(dataJson, Profile::class.java)
                            profileLiveData.postValue(profile)
                        }
                }
            })
        }
    }
}