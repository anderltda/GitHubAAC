package anderltda.com.br.githubaac.di.modules

import anderltda.com.br.githubaac.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules =  [FragmentModule::class])
    abstract fun contributeMainActivity() : MainActivity

}