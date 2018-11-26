package sunny.koranpagi.entity

import com.google.gson.annotations.SerializedName


data class NewsHiburan(
        @SerializedName("status") val status: String,
        @SerializedName("totalResults") val totalResults: Int,
        @SerializedName("articles") val articles: List<Article>
) {

    data class Article(
            @SerializedName("source") val source: Source,
            @SerializedName("author") val author: Any,
            @SerializedName("title") val title: String,
            @SerializedName("description") val description: Any,
            @SerializedName("url") val url: String,
            @SerializedName("urlToImage") val urlToImage: Any,
            @SerializedName("publishedAt") val publishedAt: String,
            @SerializedName("content") val content: Any
    ) {

        data class Source(
                @SerializedName("id") val id: Any,
                @SerializedName("name") val name: String
        )
    }
}