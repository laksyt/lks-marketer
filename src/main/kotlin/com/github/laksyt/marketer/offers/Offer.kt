package com.github.laksyt.marketer.offers

import java.math.BigDecimal

data class Offer(
    val offerId: Long,
    val productTitle: String,
    val vendorId: Long,
    val price: BigDecimal
)
