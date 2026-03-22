package pe.com.master.machines.model.request

import pe.com.master.machines.model.request.model.App
import pe.com.master.machines.model.request.model.Device
import pe.com.master.machines.model.request.model.User

data class RequestLoginUser(
    val app: App = App(),
    val device: Device = Device(),
    val user: User = User()
)
