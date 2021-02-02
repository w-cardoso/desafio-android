package com.picpay.desafio.android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.android.domain.model.User

@Dao
interface UsersDAO {

    @Query("SELECT * FROM User ORDER BY id DESC")
    fun getUsers(): List<User>

    @Insert
    fun saveUsers(users: List<User>)

}
