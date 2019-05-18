package rifqimfahmi.github.io.simpleandroidbannerslider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_banner.view.tv_number
import rifqimfahmi.github.io.simpleandroidbannerslider.BannerAdapter.BannerItem

class BannerAdapter : RecyclerView.Adapter<BannerItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannerItem(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: BannerItem, position: Int) {
        holder.bind(position)
    }

    class BannerItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.tv_number.text = position.toString()
        }
    }
}