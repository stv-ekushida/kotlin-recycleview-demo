package jp.st_ventures.kotlin_recycleview_demo.model

class HomeFeed(val videos: List<Video>)

class Video(val id: Int, val name: String, val imageUrl: String, numberOfViews: Int, val channel: Channel)

class Channel(val name: String, val profileImageUrl: String)
