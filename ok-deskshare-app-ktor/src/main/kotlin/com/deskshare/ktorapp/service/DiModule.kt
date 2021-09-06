package com.deskshare.ktorapp.service

import com.deskshare.ktorapp.controller.ReservationController
import com.deskshare.ktorapp.controller.ReservationControllerInterface
import com.deskshare.service.ReservationService
import com.deskshare.service.ReservationServiceInterface
import org.koin.dsl.module

val deskshare = module {
    single<ReservationServiceInterface> { ReservationService() }
    single<ReservationControllerInterface> { ReservationController(get() ) }
}
