package com.airmineral.mymvvmsample.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.airmineral.mymvvmsample.data.repositories.QuotesRepository
import com.airmineral.mymvvmsample.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}
