package com.airmineral.mymvvmsample.ui.home.quotes

import com.airmineral.mymvvmsample.R
import com.airmineral.mymvvmsample.data.db.entities.Quote
import com.airmineral.mymvvmsample.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>() {
    override fun getLayout(): Int = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }

}