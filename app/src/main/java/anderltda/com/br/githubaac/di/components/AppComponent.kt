package anderltda.com.br.githubaac.di.components

import anderltda.com.br.githubaac.di.modules.ActivityModule
import anderltda.com.br.githubaac.di.modules.AppModule
import anderltda.com.br.githubaac.di.modules.FragmentModule
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            ActivityModule::class,
            FragmentModule::class,
            AppModule::class
        ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        fun build() : AppComponent
    }


}