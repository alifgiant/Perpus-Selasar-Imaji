package com.selasarimaji.perpus.repository

import android.arch.lifecycle.MutableLiveData
import com.google.firebase.firestore.QuerySnapshot
import com.google.gson.JsonArray
import com.selasarimaji.perpus.model.RepoDataModel

class BookRepo : BaseRepo<RepoDataModel.Book>() {
    override val contentName: String
        get() = "book"

    private val liveData by lazy {
        MutableLiveData<List<RepoDataModel.Book>>()
    }

    override val fetchedData: MutableLiveData<List<RepoDataModel.Book>>
        get() = liveData

    override fun onLoadCallback(querySnapshot: QuerySnapshot?) {
        querySnapshot?.documents?.map {
            createLocalItem(RepoDataModel.Book.turnDocumentToObject(it))
        }
    }

    override fun onLoadCallback(jsonArray: JsonArray?) {
        jsonArray?.map {
            val data = it.asJsonObject
            createLocalItem(RepoDataModel.Book.turnDocumentToObject(data))
        }
    }
}