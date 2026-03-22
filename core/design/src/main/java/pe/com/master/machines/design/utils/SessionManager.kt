package pe.com.master.machines.design.utils

import pe.com.master.machines.model.response.ResponseLoginUser

object SessionManager {
    private var _userSession: ResponseLoginUser? = null

    val userSession: ResponseLoginUser?
        get() = _userSession

    fun saveSession(user: ResponseLoginUser) {
        _userSession = user
    }

    fun clearSession() {
        _userSession = null
    }

    fun isLoggedIn(): Boolean = _userSession != null
}