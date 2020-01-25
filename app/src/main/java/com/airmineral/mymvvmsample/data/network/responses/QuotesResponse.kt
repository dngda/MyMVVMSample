package com.airmineral.mymvvmsample.data.network.responses

import com.airmineral.mymvvmsample.data.db.entities.Quote

data class QuotesResponse (
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)