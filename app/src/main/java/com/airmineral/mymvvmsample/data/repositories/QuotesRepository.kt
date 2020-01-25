package com.airmineral.mymvvmsample.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.airmineral.mymvvmsample.data.db.AppDatabase
import com.airmineral.mymvvmsample.data.db.entities.Quote
import com.airmineral.mymvvmsample.data.network.MyApi
import com.airmineral.mymvvmsample.data.network.SafeApiRequest
import com.airmineral.mymvvmsample.data.preferences.PreferenceProvider
import com.airmineral.mymvvmsample.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.temporal.ChronoUnit

private const val MINIMUM_INTERVAL = 6
class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {
        val lastSavedAt = prefs.getLastSavedAt()
        if (lastSavedAt.isNullOrEmpty() || isFetchNeeded(lastSavedAt)) {
            val response = apiRequest {
                api.getQuotes()
            }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(lastSavedAt: String): Boolean {
        return System.currentTimeMillis() - lastSavedAt.toLong() > 1000*3600*MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            prefs.saveLastSavedAt(System.currentTimeMillis().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}