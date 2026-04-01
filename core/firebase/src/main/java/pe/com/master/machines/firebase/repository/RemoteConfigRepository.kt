package pe.com.master.machines.firebase.repository

import kotlinx.coroutines.flow.Flow

interface RemoteConfigRepository {
    fun getString(key: String): String
    fun getBoolean(key: String): Boolean
    fun getLong(key: String): Long
    fun getDouble(key: String): Double
    fun getStringFlow(key: String): Flow<String>
}