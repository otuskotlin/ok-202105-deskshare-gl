package com.deskshare.ktorapp.service

import com.deskshare.ktorapp.controller.ReservationController
import com.deskshare.ktorapp.controller.ReservationControllerInterface
import com.deskshare.service.ReservationCommandService
import com.deskshare.service.ReservationCommandServiceInterface
import com.deskshare.service.ReservationQueryService
import com.deskshare.service.ReservationQueryServiceInterface
import org.koin.dsl.module

val deskshare = module {
    single<ReservationCommandServiceInterface> { ReservationCommandService() }
    single<ReservationQueryServiceInterface> { ReservationQueryService() }
    single<ReservationControllerInterface> { ReservationController(get(), get() ) }
}
