package br.com.eyemobiletest.ui.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
                    textValue?.let{
                        if(it != "R$ 00,00"){
                            val intent = Intent(context, SuccessActivity::class.java)
                            intent.putExtra("value", textValue)
                            intent.putExtra("type", m.title)
                            startActivity(context, intent, null)
                        }else{
                            Toast.makeText(context, "Por favor, ensira um valor.", Toast.LENGTH_SHORT).show()
                        }
                    }?:run {
                        Toast.makeText(context, "Por favor, ensira um valor.", Toast.LENGTH_SHORT).show()
                    }
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