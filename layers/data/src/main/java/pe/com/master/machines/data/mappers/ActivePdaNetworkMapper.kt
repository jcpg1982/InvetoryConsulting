package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.ActivePda
import pe.com.master.machines.network.model.model.ActivePdaNetwork

fun ActivePdaNetwork?.asModelActivePda() = ActivePda(
    codBarraNew = this?.codBarraNew.orEmpty(),
    idActivosPda = this?.idActivosPda ?: -1,
    idUnidadNew = this?.idUnidadNew ?: -1,
    desActivoNew = this?.desActivoNew.orEmpty(),
    desAmpliadaNew = this?.desAmpliadaNew.orEmpty(),
    nroSerieNew = this?.nroSerieNew.orEmpty(),
    nroMotorNew = this?.nroMotorNew.orEmpty(),
    idCentroNew = this?.idCentroNew ?: -1,
    desCentroNew = this?.desCentroNew.orEmpty(),
    idProcesoNew = this?.idProcesoNew ?: -1,
    desProcesoNew = this?.desProcesoNew.orEmpty(),
    nroPlacaNew = this?.nroPlacaNew.orEmpty(),
    idTipoActivoNew = this?.idTipoActivoNew ?: -1,
    desTipoActivoNew = this?.desTipoActivoNew.orEmpty(),
    idMarcaNew = this?.idMarcaNew ?: -1,
    desMarcaNew = this?.desMarcaNew.orEmpty(),
    desModeloNew = this?.desModeloNew.orEmpty(),
    desPotenciaNew = this?.desPotenciaNew.orEmpty(),
    desCapacidadNew = this?.desCapacidadNew.orEmpty(),
    idOperario = this?.idOperario ?: -1,
    fecha = this?.fecha.orEmpty(),
    estado = this?.estado.orEmpty(),
    idPda = this?.idPda.orEmpty(),
    operativo = this?.operativo ?: -1,
    desObservacionNew = this?.desObservacionNew.orEmpty(),
    fechaUltModificacion = this?.fechaUltModificacion.orEmpty(),
    codBarraPadreNew = this?.codBarraPadreNew.orEmpty(),
    tag = this?.tag.orEmpty(),
    nroChasis = this?.nroChasis.orEmpty(),
    horometro = this?.horometro ?: 0.0,
    idUsuarioResponsable = this?.idUsuarioResponsable ?: -1,
    idCentroCosto = this?.idCentroCosto ?: -1,
    componenteCompleto = this?.componenteCompleto ?: -1,
    idTipoBien = this?.idTipoBien ?: -1,
    fotoPathLocal = this?.fotoPathLocal.orEmpty(),
    fotoPathUrl = this?.fotoPathUrl.orEmpty(),
    desColorNew = this?.desColorNew.orEmpty(),
    idInventarioActivoFijo = this?.idInventarioActivoFijo ?: -1,
    idSubprocess = this?.idSubprocess ?: -1,
    desSubprocess = this?.desSubprocess.orEmpty()
)

fun List<ActivePdaNetwork>?.asListActivePda() = this?.map { it.asModelActivePda() } ?: listOf()
