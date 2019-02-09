package anderltda.com.br.githubaac.ui.userprofile

import anderltda.com.br.githubaac.data.local.entity.User
import anderltda.com.br.githubaac.data.repositories.UserRepository
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(var userRepository: UserRepository) : ViewModel() {

    var  user: LiveData<User> = MutableLiveData<User>()

    fun getUser(login: String) {

        user = userRepository.getUser(login)


    }

}