package pe.com.master.machines.firebase.repository

import kotlinx.coroutines.flow.Flow

interface RemoteConfigRepository {
    fun getString(key: String): String
    fun getBoolean(key: String): Boolean
    fun getLong(key: String): Long
    fun getStringFlow(key: String): Flow<String>
}