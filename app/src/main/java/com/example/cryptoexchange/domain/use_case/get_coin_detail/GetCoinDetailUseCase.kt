package com.example.cryptoexchange.domain.use_case.get_coin_detail

import com.example.cryptoexchange.common.Resource
import com.example.cryptoexchange.data.remote.dto.toCoin
import com.example.cryptoexchange.data.remote.dto.toCoinDetail
import com.example.cryptoexchange.domain.model.Coin
import com.example.cryptoexchange.domain.model.CoinDetail
import com.example.cryptoexchange.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Произошла непредвиденная ошибка"))
        } catch (e: IOException){
            emit(Resource.Error(message = "Сервер не найден. Проверьте свое подключение к интернету."))
        }
    }
}