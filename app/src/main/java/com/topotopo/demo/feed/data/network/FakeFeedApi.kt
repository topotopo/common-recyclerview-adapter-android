package com.topotopo.demo.feed.data.network

import android.content.Context
import com.google.gson.Gson
import com.topotopo.demo.feed.data.model.GetFeedResponse
import com.topotopo.demo.utils.FakeJsonFileReader
import retrofit2.http.GET
import retrofit2.mock.BehaviorDelegate

class FakeFeedApi(
    private val delegate: BehaviorDelegate<FeedApi>,
    private val context: Context
) : FeedApi {

    @GET("")
    override suspend fun fetchFeed(): GetFeedResponse {
        val jsonString = FakeJsonFileReader().readMockJson(context)
        val response= Gson().fromJson(jsonString, GetFeedResponse::class.java)
        return delegate.returningResponse(response).fetchFeed()
    }
}