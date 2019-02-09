package anderltda.com.br.githubaac.di.modules

import anderltda.com.br.githubaac.ui.userprofile.UserProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeUserProfileFragment() : UserProfileFragment

}