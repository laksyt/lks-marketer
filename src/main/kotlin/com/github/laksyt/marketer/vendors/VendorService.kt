package com.github.laksyt.marketer.vendors

import com.github.laksyt.marketer.randomizer.RandomizerService
import org.springframework.stereotype.Service
import java.util.stream.Stream

@Service
class VendorService(
    private val randomizerService: RandomizerService
) {

    companion object {
        const val NUMBER_OF_VENDORS: Int = 10
    }

    private val vendors: List<Vendor> = (1..NUMBER_OF_VENDORS).map { Vendor(randomizerService.randomVendorId) }

    val all: Stream<Vendor> get() = vendors.stream()

    val random: Vendor get() = vendors.random()
}
