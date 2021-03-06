package anderltda.com.br.githubaac.di.modules

import anderltda.com.br.githubaac.data.local.MeuBancoDeDados
import anderltda.com.br.githubaac.data.local.dao.UserDao
import anderltda.com.br.githubaac.data.remote.UserWebService
import anderltda.com.br.githubaac.data.repositories.UserRepository
import android.app.Application
import android.arch.persistence.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): MeuBancoDeDados {
        return Room.databaseBuilder(
                application,
                MeuBancoDeDados::class.java,
                "meuqueridobanco.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: MeuBancoDeDados): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideOkhttp() : OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build();
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com")
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideUserWebService(retrofit: Retrofit): UserWebService {
        return retrofit.create(UserWebService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userWebService: UserWebService, userDao: UserDao, executor: Executor)
            : UserRepository {
        return UserRepository(userWebService, userDao, executor)
    }


}