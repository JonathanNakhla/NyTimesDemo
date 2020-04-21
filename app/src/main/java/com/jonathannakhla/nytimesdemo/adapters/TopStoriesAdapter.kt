package com.jonathannakhla.nytimesdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jonathannakhla.nytimesdemo.R
import com.jonathannakhla.nytimesdemo.data.Article
import com.jonathannakhla.nytimesdemo.utils.ArticleClickListener
import com.jonathannakhla.nytimesdemo.utils.ViewArticleRowCallback
import kotlinx.android.synthetic.main.article_card_view.view.*

class TopStoriesAdapter(private val articles: List<Article>,
                        private val articleClickListener: ArticleClickListener,
                        private val viewArticleRowCallback: ViewArticleRowCallback): RecyclerView.Adapter<TopStoriesAdapter.TopStoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoriesViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.article_card_view, parent, false)
        return TopStoriesViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: TopStoriesViewHolder, position: Int) {
        holder.bind(articles[position], articleClickListener)
    }

    override fun onViewAttachedToWindow(holder: TopStoriesViewHolder) {
        super.onViewAttachedToWindow(holder)
        val position = holder.adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            viewArticleRowCallback.onViewArticleRow(articles[position])
        }
    }

    class TopStoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article, articleClickListener: ArticleClickListener) {

            ViewCompat.setTransitionName(itemView.article_image, "article_image_${article.hashCode()}")
            ViewCompat.setTransitionName(itemView.article_title, "article_title_${article.hashCode()}")

            itemView.setOnClickListener {
                articleClickListener.onArticleClick(article, itemView.article_image, itemView.article_title)
            }

            val cardView = itemView as CardView

            cardView.article_title.text = article.title
            Glide.with(itemView)
                .load(article.multimedias?.get(0)?.url)
                .placeholder(R.drawable.placeholder_drawable)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemView.article_image)
        }
    }

}