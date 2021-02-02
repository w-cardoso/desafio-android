package com.picpay.desafio.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.database.dao.UsersDAO
import com.picpay.desafio.android.domain.model.User

private const val NOME_BANCO_DE_DADOS = "users.db"

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val usersDAO: UsersDAO

    companion object {

        private lateinit var db: AppDatabase

        fun getInstance(context: Context): AppDatabase {

            if(Companion::db.isInitialized) return db

            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                NOME_BANCO_DE_DADOS
            ).build()

            return db
        }

    }

}