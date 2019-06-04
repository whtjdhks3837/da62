package com.da62.usecase

import org.koin.dsl.module

val useCaseModules = module {
    factory { LoginUseCaseImpl(get()) as LoginUseCase }
}