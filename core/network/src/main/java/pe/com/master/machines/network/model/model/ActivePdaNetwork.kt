package pe.com.master.machines.network.model.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivePdaNetwork(
    @SerialName("cod_barra_new")
    var codBarraNew: String = "",
    @SerialName("id_Activos_PDA")
    var idActivosPda: Int? = null,
    @SerialName("id_unidad_new")
    var idUnidadNew: Int? = null,
    @SerialName("des_activo_new")
    var desActivoNew: String? = null,
    @SerialName("des_ampliada_new")
    var desAmpliadaNew: String? = null,
    @SerialName("nro_serie_new")
    var nroSerieNew: String? = null,
    @SerialName("nro_motor_new")
    var nroMotorNew: String? = null,
    @SerialName("id_centro_new")
    var idCentroNew: Int? = null,
    @SerialName("des_centro_new")
    var desCentroNew: String? = null,
    @SerialName("id_proceso_new")
    var idProcesoNew: Int? = null,
    @SerialName("des_proceso_new")
    var desProcesoNew: String? = null,
    @SerialName("nro_placa_new")
    var nroPlacaNew: String? = null,
    @SerialName("id_tipo_activo_new")
    var idTipoActivoNew: Int? = null,
    @SerialName("des_tipo_activo_new")
    var desTipoActivoNew: String? = null,
    @SerialName("id_marca_new")
    var idMarcaNew: Int? = null,
    @SerialName("des_marca_new")
    var desMarcaNew: String? = null,
    @SerialName("des_modelo_new")
    var desModeloNew: String? = null,
    @SerialName("des_potencia_new")
    var desPotenciaNew: String? = null,
    @SerialName("des_capacidad_new")
    var desCapacidadNew: String? = null,
    @SerialName("id_operario")
    var idOperario: Int? = null,
    @SerialName("fecha")
    var fecha: String? = null,
    @SerialName("estado")
    var estado: String? = null,
    @SerialName("id_pda")
    var idPda: String? = null,
    @SerialName("operativo")
    var operativo: Int? = null,
    @SerialName("des_observacion_new")
    var desObservacionNew: String? = null,
    @SerialName("fecha_ult_modificacion")
    var fechaUltModificacion: String? = null,
    @SerialName("cod_barra_padre_new")
    var codBarraPadreNew: String? = null,
    @SerialName("tag")
    var tag: String? = null,
    @SerialName("nro_chasis")
    var nroChasis: String? = null,
    @SerialName("horometro")
    var horometro: Double? = null,
    @SerialName("id_usuario_responsable")
    var idUsuarioResponsable: Int? = null,
    @SerialName("id_centro_costo")
    var idCentroCosto: Int? = null,
    @SerialName("componente_completo")
    var componenteCompleto: Int? = null,
    @SerialName("id_tipo_bien")
    var idTipoBien: Int? = null,
    @SerialName("fotoPathLocal")
    var fotoPathLocal: String? = null,
    @SerialName("fotoPathUrl")
    var fotoPathUrl: String? = null,
    @SerialName("fotoBase64")
    var fotoBase64: String? = null,
    @SerialName("des_color_new")
    var desColorNew: String? = null,
    @SerialName("id_inventario_activo_fijo")
    var idInventarioActivoFijo: Int? = null
)

