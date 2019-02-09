package anderltda.com.br.githubaac.di.modules

import anderltda.com.br.githubaac.di.key.ViewModelKey
import anderltda.com.br.githubaac.ui.userprofile.UserProfileViewModel
import anderltda.com.br.githubaac.util.viewmodel.FactoryViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun bindUserProfileViewModel(repoViewModel: UserProfileViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: FactoryViewModel): ViewModelProvider.Factory
}
