package com.github.laksyt.marketer.offers

import com.github.laksyt.marketer.randomizer.RandomizerService
import com.github.laksyt.marketer.vendors.VendorService
import org.springframework.stereotype.Service
import java.util.stream.Stream
import kotlin.streams.toList

@Service
class OfferService(
    private val randomizerService: RandomizerService,
    private val vendorService: VendorService
) {

    companion object {
        const val NUMBER_OF_OFFERS = 100
    }

    private val offers: List<Offer> = (1..NUMBER_OF_OFFERS).map {
        Offer(
            randomizerService.randomOfferId,
            randomizerService.randomProductTitle,
            vendorService.random.id,
            randomizerService.randomPrice
        )
    }

    val all: Stream<Offer> get() = offers.stream()

    val random: Offer get() = offers.random()

    fun first(amount: Long): Stream<Offer> = all.limit(amount)

    fun random(amount: Long): Stream<Offer> = all.toList().shuffled().stream().limit(amount)
}
