package anderltda.com.br.githubaac.data.repositories

import anderltda.com.br.githubaac.data.local.dao.UserDao
import anderltda.com.br.githubaac.data.local.entity.User
import anderltda.com.br.githubaac.data.remote.UserWebService
import android.arch.lifecycle.LiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject
constructor(private val webservice: UserWebService, private val userDao: UserDao, private val executor: Executor) {

    fun getUser(userLogin: String): LiveData<User> {
        refreshUser(userLogin)
        return userDao.load(userLogin)
    }

    private fun refreshUser(userLogin: String) {
        executor.execute {

            var usr = userDao.hasUser(userLogin, getMaxRefreshTime(Date()))

            val userExists = (usr != null)

            if (!userExists) {
                webservice.getUser(userLogin).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        executor.execute {
                            val user = response.body()
                            user?.lastRefresh = Date()
                            if (user != null) {
                                Log.w(" USUARIO FOUND GIT ", userLogin)
                                userDao.save(user)
                            } else {
                                Log.w(" USUARIO NOT FOUND GIT ", userLogin)
                            }

                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Log.e("onFailure", t.message)
                    }
                })
            } else {
                Log.i(" USER FOUND DATABASE ", userExists.toString())
            }
        }
    }

    private fun getMaxRefreshTime(currentDate: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = currentDate
        cal.add(Calendar.MINUTE, - FRESH_TIMEOUT_IN_MINUTES)
        return cal.time
    }

    companion object {
        private const val FRESH_TIMEOUT_IN_MINUTES = 3
    }
}

