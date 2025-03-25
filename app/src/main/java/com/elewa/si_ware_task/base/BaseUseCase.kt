package com.elewa.si_ware_task.base

/**
 * Base Use Case class
 */
interface BaseUseCase<Params, Result> {
    suspend fun execute(params: Params?): Result
}