package com.da62.usecase

import org.koin.dsl.module

val useCaseModules = module {
    factory { LoginUseCaseImpl(get()) as LoginUseCase }

    factory { WriteUseCaseImpl(get()) as WriteUseCase }

    factory { DetailUseCaseImpl(get()) as DetailUseCase }

    factory { MainUseCaseImpl(get()) as MainUseCase }
}