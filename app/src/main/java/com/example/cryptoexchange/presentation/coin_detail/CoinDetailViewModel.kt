package com.example.cryptoexchange.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoexchange.common.Constants
import com.example.cryptoexchange.common.Resource
import com.example.cryptoexchange.domain.use_case.get_coin_detail.GetCoinDetailUseCase
import com.example.cryptoexchange.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle //Дескриптор сохраненного состояния
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
        }
    }

    private fun getCoinDetail(coinId: String) {
        getCoinDetailUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinDetailState(
                        coin = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        error = result.message ?: "Произошла непредвиденная ошибка"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(
                        isLosding = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}