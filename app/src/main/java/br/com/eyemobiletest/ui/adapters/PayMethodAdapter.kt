package br.com.eyemobiletest.ui.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.eyemobiletest.R
import br.com.eyemobiletest.data.PayMethod
import br.com.eyemobiletest.ui.activities.SuccessActivity
import kotlinx.android.synthetic.main.pay_method_item.view.*

class PayMethodAdapter(private val methods: ArrayList<PayMethod>,
                       private val context: Context) : Adapter<PayMethodAdapter.Companion.ViewHolder>() {

    var textValue:String? = null

    companion object{
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bindView(context:Context, m: PayMethod, textValue: String?){
                val title = itemView.tv_title
                val icon = itemView.iv_icon

                title.text = m.title
                icon.setImageBitmap(m.icon)
                icon.setOnClickListener{
                    val options:Bundle = Bundle()
                    options.putString("value", textValue)
                    options.putString("type", m.title)
                    startActivity(context, Intent(context, SuccessActivity::class.java), options)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pay_method_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return methods.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindView(context, methods[position], textValue)
    }
}