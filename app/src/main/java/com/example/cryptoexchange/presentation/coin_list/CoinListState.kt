package com.example.cryptoexchange.presentation.coin_list

import com.example.cryptoexchange.domain.model.Coin

data class CoinListState(
    val isLosding: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
