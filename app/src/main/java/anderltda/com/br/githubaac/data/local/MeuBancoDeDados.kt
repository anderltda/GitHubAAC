package anderltda.com.br.githubaac.data.local

import anderltda.com.br.githubaac.data.local.converter.DateConverter
import anderltda.com.br.githubaac.data.local.dao.UserDao
import anderltda.com.br.githubaac.data.local.entity.User
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

@Database(entities = [User::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MeuBancoDeDados: RoomDatabase() {

    abstract  fun userDao(): UserDao

    companion object {
        private val INSTANCE : MeuBancoDeDados? = null
    }

}