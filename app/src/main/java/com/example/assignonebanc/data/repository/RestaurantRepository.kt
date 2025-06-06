package com.example.assignonebanc.data.repository

import com.example.assignonebanc.data.api.RestaurantApiService
import com.example.assignonebanc.data.models.*

class RestaurantRepository {
    private val apiService = RestaurantApiService()
    
    suspend fun getItemList(page: Int = 1, count: Int = 10): Result<GetItemListResponse> {
        return apiService.getItemList(page, count)
    }
    
    suspend fun getItemById(itemId: Long): Result<GetItemByIdResponse> {
        return apiService.getItemById(itemId)
    }
    
    suspend fun getItemByFilter(filterRequest: GetItemByFilterRequest): Result<GetItemByFilterResponse> {
        return apiService.getItemByFilter(filterRequest)
    }
    
    suspend fun makePayment(paymentRequest: MakePaymentRequest): Result<MakePaymentResponse> {
        return apiService.makePayment(paymentRequest)
    }
}
