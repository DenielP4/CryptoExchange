package com.example.cryptoexchange.domain.repository

import com.example.cryptoexchange.data.remote.dto.CoinDetailDto
import com.example.cryptoexchange.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}