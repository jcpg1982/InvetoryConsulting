package pe.com.master.machines.network.model.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivePdaNetwork(
    @SerialName("cod_barra_new")
    val codBarraNew: String? = null,
    @SerialName("cod_barra_padre_new")
    val codBarraPadreNew: String? = null,
    @SerialName("componente_completo")
    val componenteCompleto: Int? = null,
    @SerialName("des_activo_new")
    val desActivoNew: String? = null,
    @SerialName("des_ampliada_new")
    val desAmpliadaNew: String? = null,
    @SerialName("des_capacidad_new")
    val desCapacidadNew: String? = null,
    @SerialName("des_centro_new")
    val desCentroNew: String? = null,
    @SerialName("des_color_new")
    val desColorNew: String? = null,
    @SerialName("des_marca_new")
    val desMarcaNew: String? = null,
    @SerialName("des_modelo_new")
    val desModeloNew: String? = null,
    @SerialName("des_observacion_new")
    val desObservacionNew: String? = null,
    @SerialName("des_potencia_new")
    val desPotenciaNew: String? = null,
    @SerialName("des_proceso_new")
    val desProcesoNew: String? = null,
    @SerialName("des_tipo_activo_new")
    val desTipoActivoNew: String? = null,
    @SerialName("estado")
    val estado: String? = null,
    @SerialName("fecha")
    val fecha: String? = null,
    @SerialName("fecha_ult_modificacion")
    val fechaUltModificacion: String? = null,
    @SerialName("fotoPathLocal")
    val fotoPathLocal: String? = null,
    @SerialName("fotoPathUrl")
    val fotoPathUrl: String? = null,
    @SerialName("horometro")
    val horometro: Double? = null,
    @SerialName("id_Activos_PDA")
    val idActivosPda: Int? = null,
    @SerialName("id_centro_costo")
    val idCentroCosto: Int? = null,
    @SerialName("id_centro_new")
    val idCentroNew: Int? = null,
    @SerialName("id_inventario_activo_fijo")
    val idInventarioActivoFijo: Int? = null,
    @SerialName("id_marca_new")
    val idMarcaNew: Int? = null,
    @SerialName("id_operario")
    val idOperario: Int? = null,
    @SerialName("id_pda")
    val idPda: String? = null,
    @SerialName("id_proceso_new")
    val idProcesoNew: Int? = null,
    @SerialName("id_tipo_activo_new")
    val idTipoActivoNew: Int? = null,
    @SerialName("id_tipo_bien")
    val idTipoBien: Int? = null,
    @SerialName("id_unidad_new")
    val idUnidadNew: Int? = null,
    @SerialName("id_usuario_responsable")
    val idUsuarioResponsable: Int? = null,
    @SerialName("nro_chasis")
    val nroChasis: String? = null,
    @SerialName("nro_motor_new")
    val nroMotorNew: String? = null,
    @SerialName("nro_placa_new")
    val nroPlacaNew: String? = null,
    @SerialName("nro_serie_new")
    val nroSerieNew: String? = null,
    @SerialName("operativo")
    val operativo: Int? = null,
    @SerialName("tag")
    val tag: String? = null,
    @SerialName("id_subproceso")
    val idSubprocess: Int? = null,
    @SerialName("des_subproceso")
    val desSubprocess: String? = null
)
