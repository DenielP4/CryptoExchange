package com.example.cryptoexchange.data.repository

import com.example.cryptoexchange.data.remote.CoinPaprikaApi
import com.example.cryptoexchange.data.remote.dto.CoinDetailDto
import com.example.cryptoexchange.data.remote.dto.CoinDto
import com.example.cryptoexchange.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}