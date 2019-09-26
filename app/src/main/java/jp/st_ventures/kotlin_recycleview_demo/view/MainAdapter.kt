package jp.st_ventures.kotlin_recycleview_demo.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jp.st_ventures.kotlin_recycleview_demo.R
import jp.st_ventures.kotlin_recycleview_demo.model.HomeFeed
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder> (){

    /// 行数
    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    /// Row
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    /// Rowの見た目
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = homeFeed.videos.get(position)
        holder.view.textView_video_title.text = video.name
        holder.view.textView_chanel_name.text = video.channel.name + " ・ " + "20K Views\n4 days ago"

        val thumbnailImageView = holder.view.imageView_video_thumbnail
        Picasso.with(holder.view.context).load(video.imageUrl).into(thumbnailImageView)

        val channelProfileImageView = holder.view.imageView_chanel_thumbnail
        Picasso.with(holder.view.context).load(video.channel.profileImageUrl).into(channelProfileImageView)
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    init {

        //クリックイベント
        view.setOnClickListener {

            val intent = Intent(view.context, CourseDetailActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}