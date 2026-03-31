package pe.com.master.machines.network.model.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivePdaNetwork(
    @SerialName("cod_barra_new")
    val codBarraNew: String,
    val cod_barra_padre_new: String,
    val componente_completo: Int,
    val des_activo_new: Any,
    val des_ampliada_new: Any,
    val des_capacidad_new: String,
    val des_centro_new: String,
    val des_color_new: String,
    val des_marca_new: String,
    val des_modelo_new: String,
    val des_observacion_new: String,
    val des_potencia_new: String,
    val des_proceso_new: String,
    val des_tipo_activo_new: String,
    val estado: String,
    val fecha: String,
    val fecha_ult_modificacion: String,
    val fotoPathLocal: String,
    val fotoPathUrl: String,
    val horometro: Double,
    val id_Activos_PDA: Int,
    val id_centro_costo: Int,
    val id_centro_new: Int,
    val id_inventario_activo_fijo: Int,
    val id_marca_new: Int,
    val id_operario: Int,
    val id_pda: String,
    val id_proceso_new: Int,
    val id_tipo_activo_new: Int,
    val id_tipo_bien: Int,
    val id_unidad_new: Int,
    val id_usuario_responsable: Int,
    val nro_chasis: String,
    val nro_motor_new: String,
    val nro_placa_new: String,
    val nro_serie_new: String,
    val operativo: Int,
    val tag: String
)