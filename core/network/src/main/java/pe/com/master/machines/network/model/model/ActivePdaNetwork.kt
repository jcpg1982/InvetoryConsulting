package pe.com.master.machines.network.model.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ActivePdaNetwork(
    @SerializedName("cod_barra_new")
    var codBarraNew: String = "",
    @SerializedName("id_Activos_PDA")
    var idActivosPda: Int? = null,
    @SerializedName("id_unidad_new")
    var idUnidadNew: Int? = null,
    @SerializedName("des_activo_new")
    var desActivoNew: String? = null,
    @SerializedName("des_ampliada_new")
    var desAmpliadaNew: String? = null,
    @SerializedName("nro_serie_new")
    var nroSerieNew: String? = null,
    @SerializedName("nro_motor_new")
    var nroMotorNew: String? = null,
    @SerializedName("id_centro_new")
    var idCentroNew: Int? = null,
    @SerializedName("des_centro_new")
    var desCentroNew: String? = null,
    @SerializedName("id_proceso_new")
    var idProcesoNew: Int? = null,
    @SerializedName("des_proceso_new")
    var desProcesoNew: String? = null,
    @SerializedName("nro_placa_new")
    var nroPlacaNew: String? = null,
    @SerializedName("id_tipo_activo_new")
    var idTipoActivoNew: Int? = null,
    @SerializedName("des_tipo_activo_new")
    var desTipoActivoNew: String? = null,
    @SerializedName("id_marca_new")
    var idMarcaNew: Int? = null,
    @SerializedName("des_marca_new")
    var desMarcaNew: String? = null,
    @SerializedName("des_modelo_new")
    var desModeloNew: String? = null,
    @SerializedName("des_potencia_new")
    var desPotenciaNew: String? = null,
    @SerializedName("des_capacidad_new")
    var desCapacidadNew: String? = null,
    @SerializedName("id_operario")
    var idOperario: Int? = null,
    @SerializedName("fecha")
    var fecha: String? = null,
    @SerializedName("estado")
    var estado: String? = null,
    @SerializedName("id_pda")
    var idPda: String? = null,
    @SerializedName("operativo")
    var operativo: Int? = null,
    @SerializedName("des_observacion_new")
    var desObservacionNew: String? = null,
    @SerializedName("fecha_ult_modificacion")
    var fechaUltModificacion: String? = null,
    @SerializedName("cod_barra_padre_new")
    var codBarraPadreNew: String? = null,
    @SerializedName("tag")
    var tag: String? = null,
    @SerializedName("nro_chasis")
    var nroChasis: String? = null,
    @SerializedName("horometro")
    var horometro: Double? = null,
    @SerializedName("id_usuario_responsable")
    var idUsuarioResponsable: Int? = null,
    @SerializedName("id_centro_costo")
    var idCentroCosto: Int? = null,
    @SerializedName("componente_completo")
    var componenteCompleto: Int? = null,
    @SerializedName("id_tipo_bien")
    var idTipoBien: Int? = null,
    @SerializedName("fotoPathLocal")
    var fotoPathLocal: String? = null,
    @SerializedName("fotoPathUrl")
    var fotoPathUrl: String? = null,
    @SerializedName("fotoBase64")
    var fotoBase64: String? = null,
    @SerializedName("des_color_new")
    var desColorNew: String? = null,
    @SerializedName("id_inventario_activo_fijo")
    var idInventarioActivoFijo: Int? = null
)

