package com.example.algoliatask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingConfig
import com.algolia.instantsearch.android.paging3.Paginator
import com.algolia.instantsearch.android.paging3.filterstate.connectPaginator
import com.algolia.instantsearch.android.paging3.searchbox.connectPaginator
import com.algolia.instantsearch.core.connection.ConnectionHandler
import com.algolia.instantsearch.core.selectable.list.SelectionMode
import com.algolia.instantsearch.filter.facet.DefaultFacetListPresenter
import com.algolia.instantsearch.filter.facet.FacetListConnector
import com.algolia.instantsearch.filter.facet.FacetSortCriterion
import com.algolia.instantsearch.filter.state.FilterState
import com.algolia.instantsearch.searchbox.SearchBoxConnector
import com.algolia.instantsearch.searcher.connectFilterState
import com.algolia.instantsearch.searcher.hits.HitsSearcher
import com.algolia.instantsearch.stats.StatsConnector
import com.algolia.search.model.APIKey
import com.algolia.search.model.APIKey.Companion.serializer
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.IndexName.Companion.serializer


class MyViewModel : ViewModel() {
/*
    private val searcher = HitsSearcher(
        applicationID = ApplicationID("TLPEM8HW8C"),
        apiKey = APIKey("473f4d6de29ab1a4aae9f8a268d46371"),
        indexName = IndexName("dev_to_products")
    )
*/


    private val searcher = HitsSearcher(
        applicationID = ApplicationID("ZBUGRCNOHV"),
        apiKey = APIKey("9749b5c85822c5526b3681f55ced0f2b"),
        indexName = IndexName("sampleData")
    )



    val paginator = Paginator(
        searcher = searcher,
        pagingConfig = PagingConfig(pageSize = 50, enablePlaceholders = false),
        transformer = { hit -> hit.deserialize(Product.serializer()) }
    )

    val searchBox = SearchBoxConnector(searcher)
    val stats = StatsConnector(searcher)
    private val connection = ConnectionHandler(searchBox)


    private val filterState = FilterState()
    val facetList = FacetListConnector(
        searcher = searcher,
        filterState = filterState,
        attribute = Attribute("categories"),
        selectionMode = SelectionMode.Single
    )
    val facetPresenter = DefaultFacetListPresenter(
        sortBy = listOf(FacetSortCriterion.CountDescending, FacetSortCriterion.IsRefined),
        limit = 100
    )
    //val connection = ConnectionHandler(searchBox, stats, facetList)

    init {
        connection += searchBox.connectPaginator(paginator)
        connection += searcher.connectFilterState(filterState)
        connection += filterState.connectPaginator(paginator)
    }


    private val _displayFilters = MutableLiveData<Unit>()
    val displayFilters: LiveData<Unit> get() = _displayFilters

    fun navigateToFilters() {
        _displayFilters.value = Unit
    }

    override fun onCleared() {
        super.onCleared()
        searcher.cancel()
    }
}
