package com.github.laksyt.marketer.controllers

import com.github.laksyt.marketer.offers.Offer
import com.github.laksyt.marketer.offers.OfferService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.streams.toList

@RestController
@RequestMapping("listing")
class MarketerController(
    val offerService: OfferService
) {

    companion object {
        const val DEFAULT_AMOUNT: Long = 10
    }

    @RequestMapping
    fun getListing(
        @RequestParam(required = false) amount: String?
    ): List<Offer> {
        return offerService.first(amount?.toLongOrNull() ?: DEFAULT_AMOUNT).toList()
    }

    @RequestMapping("random")
    fun getRandomListing(
        @RequestParam(required = false) amount: String?
    ): List<Offer> {
        return offerService.random(amount?.toLongOrNull() ?: DEFAULT_AMOUNT).toList()
    }
}
