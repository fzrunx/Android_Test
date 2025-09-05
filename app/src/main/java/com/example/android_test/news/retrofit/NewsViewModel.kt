package com.example.android_test.news.retrofit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_test.news.retrofit.NewsInstance.api
import com.example.android_test.user.info.UserRoom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NewsRepository()
    private val _newsList = MutableStateFlow<List<NewsRoom>>(emptyList())
    var newsList: StateFlow<List<NewsRoom>> = _newsList
    private val newsDao = NewsDB.getInstance(application).newsDao()

    init {
        viewModelScope.launch {
            newsDao.getAllNews().collect { news ->
                _newsList.value = news
            }
        }
    }

    // 앱 시작 시 기본 키워드 또는 마지막 검색어 뉴스 불러오기
    fun loadSavedNews(keyword: String) {
        viewModelScope.launch {
            if (keyword.isBlank()) {
                newsDao.getAllNews().collect { news -> _newsList.value = news }
            } else {
                newsDao.getNewsByKeyword(keyword).collect { news -> _newsList.value = news }
            }
        }

    }
    // 검색 후 DB 저장 + 화면 갱신
    fun fetchNews(keyword: String) {
        viewModelScope.launch {
            try {
                val newsEntities = repository.searchNews(keyword)
                newsDao.insert(newsEntities.map { it.copy(keyword = keyword) })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun delete(news: NewsRoom) {
        viewModelScope.launch {
            newsDao.deleteNews(news)  // DB에서도 삭제
            _newsList.value = _newsList.value.filterNot { it == news }
        }
    }


}
suspend fun searchNews(query: String): List<NewsRoom> {
    val response = api.getNews(query)
    return response.body()?.items?.map { it.toNewsRoom(query) } ?: emptyList()
}
fun Items.toNewsRoom(keyword: String): NewsRoom {
    return NewsRoom(
        link = link,
        title = title,
        originallink = originallink,
        description = description,
        pubDate = pubDate,
        keyword = keyword
    )
}