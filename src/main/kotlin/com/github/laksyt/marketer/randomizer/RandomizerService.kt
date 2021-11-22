package com.github.laksyt.marketer.randomizer

import com.github.javafaker.Faker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import java.util.function.Supplier

@Service
class RandomizerService: CommandLineRunner {

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(this::class.java.simpleName)
        const val PRICE_PRECISION = 1
    }

    private val faker: Faker = Faker(Locale.US)

    private val offerIds: MutableSet<Long> = HashSet()
    private val productTitles: MutableSet<String> = HashSet()
    private val vendorIds: MutableSet<Long> = HashSet()

    val randomOfferId: Long get() = generateUnique(offerIds, "offer IDs") {
        faker.number().numberBetween(100L, 10_000L)
    }

    val randomProductTitle: String get() = generateUnique(productTitles, "product names") {
        "${faker.beer().name()} by ${faker.company().name()}"
    }

    val randomVendorId: Long get() = generateUnique(vendorIds, "vendor IDs") {
        faker.number().numberBetween(10L, 1_000L)
    }

    val randomPrice: BigDecimal get() = BigDecimal.valueOf(
        faker.number().randomDouble(PRICE_PRECISION, 5, 350)
    )

    private inline fun <reified T> generateUnique(
        registry: MutableSet<T>,
        desc: String,
        supplier: Supplier<T>
    ): T {
        var i = 100
        var result: T
        do {
            --i
            result = supplier.get()
        } while (registry.contains(result) && i >= 0)
        if (i < 0) {
            throw RuntimeException("Exhausted random pool of $desc")
        }
        registry.add(result)
        return result
    }

    override fun run(vararg args: String?) {
        LOG.info("Offer pool size         : ${offerIds.size}")
        LOG.info("Product title pool size : ${productTitles.size}")
        LOG.info("Vendor pool size        : ${vendorIds.size}")
    }
}
