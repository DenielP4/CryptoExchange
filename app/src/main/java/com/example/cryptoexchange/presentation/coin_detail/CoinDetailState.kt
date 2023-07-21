package com.example.cryptoexchange.presentation.coin_detail

import com.example.cryptoexchange.domain.model.Coin
import com.example.cryptoexchange.domain.model.CoinDetail

data class CoinDetailState(
    val isLosding: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
