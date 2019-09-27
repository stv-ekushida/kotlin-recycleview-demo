package jp.st_ventures.kotlin_recycleview_demo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.st_ventures.kotlin_recycleview_demo.R
import kotlinx.android.synthetic.main.activity_course_lesson.*

class CourseLessonActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_course_lesson)

        val courceLink = intent.getStringExtra(CourseDetailActivity.CourseDetailLessionViewHolder.COURSE_LESSON_LINK_KEY)

        webview_course_lesson.settings.javaScriptEnabled = true
        webview_course_lesson.settings.loadWithOverviewMode = true
        webview_course_lesson.settings.useWideViewPort = true

        webview_course_lesson.loadUrl(courceLink)
    }
}