package br.com.eyemobiletest.ui.activities

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.eyemobiletest.R
import br.com.eyemobiletest.data.PayMethod
import br.com.eyemobiletest.ui.adapters.PayMethodAdapter
import br.com.eyemobiletest.ui.decorators.CirclePagerIndicatorDecoration
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat


class MainActivity : AppCompatActivity(), View.OnClickListener {

    val methods: ArrayList<PayMethod> = arrayListOf()
    var valueArray: ArrayList<Char> = arrayListOf()
    val sb = StringBuilder()
    var count = 0
    var adapter:PayMethodAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMethods()
        initValueArray()
        adapter = PayMethodAdapter(methods,this)
        initRecycler()

        setListeners()
    }

    private fun initRecycler() {
        recycler.adapter = adapter

        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        recycler.layoutManager = layoutManager
        recycler.setHasFixedSize(true)
        recycler.addItemDecoration(CirclePagerIndicatorDecoration())
    }

    private fun initValueArray() {
        valueArray.clear()
        valueArray.add('0')
        valueArray.add('0')
        valueArray.add('.')
        valueArray.add('0')
    }

    private fun setListeners() {
        kb_number0.setOnClickListener(this)
        kb_number1.setOnClickListener(this)
        kb_number2.setOnClickListener(this)
        kb_number3.setOnClickListener(this)
        kb_number4.setOnClickListener(this)
        kb_number5.setOnClickListener(this)
        kb_number6.setOnClickListener(this)
        kb_number7.setOnClickListener(this)
        kb_number8.setOnClickListener(this)
        kb_number9.setOnClickListener(this)
        kb_delete.setOnClickListener(this)
    }

    private fun getMethods() {
        val imgMoney = getBitmap(R.drawable.ic_money)
        val imgDebit = getBitmap(R.drawable.ic_debit)
        val imgCredit = getBitmap(R.drawable.ic_credit)
        val imgVr = getBitmap(R.drawable.ic_vr)
        val imgCupom = getBitmap(R.drawable.ic_cupom)

        methods.add(PayMethod(getString(R.string.dinheiro), imgMoney))
        methods.add(PayMethod(getString(R.string.debito),imgDebit))
        methods.add(PayMethod(getString(R.string.credito), imgCredit))
        methods.add(PayMethod(getString(R.string.vr),imgVr))
        methods.add(PayMethod(getString(R.string.cupom), imgCupom))
    }

    private fun getBitmap(ic:Int): Bitmap{
        val drawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            resources.getDrawable(ic, null)
        } else {
            resources.getDrawable(ic)
        }
        return drawable.toBitmap()
    }

    override fun onClick(p0: View?) {
        mapKeys(p0!!.id)
        setTextValue()
        adapter!!.textValue = tv_value.text.toString()
        initRecycler()
    }

    private fun setTextValue() {
        if(valueArray.size>5){
            if(count< 5){
                for(pos in 0..valueArray.size-2){
                    if((pos!=1) && (pos != 2)){
                        valueArray[pos] = valueArray[pos+1]
                    }else{
                        if(pos==1){
                            valueArray[pos] = valueArray[pos+2]
                        }
                    }
                }
                valueArray.removeAt(5)
            }else{
                valueArray[valueArray.size-4] = valueArray[valueArray.size-3]
                valueArray[valueArray.size-3] = ','

            }
        }

        for(char in valueArray)sb.append(char)
        val format = DecimalFormat("###,#00.00")
        try{
            sb.toString().replace(",",".").toDouble()?.let{
                tv_value.text = "R$ " + format.format(it)
            }
        }catch (e:NumberFormatException){
            tv_value.text = getString(R.string.r_00_00)
            initValueArray()
        }
        sb.clear()
    }

    private fun mapKeys(id: Int) {
        when(id){
            R.id.kb_number0 -> {
                valueArray.add('0')
                count++
            }
            R.id.kb_number1 -> {
                valueArray.add('1')
                count++
            }
            R.id.kb_number2 -> {
                valueArray.add('2')
                count++
            }
            R.id.kb_number3 -> {
                valueArray.add('3')
                count++
            }
            R.id.kb_number4 -> {
                valueArray.add('4')
                count++
            }
            R.id.kb_number5 -> {
                valueArray.add('5')
                count++
            }
            R.id.kb_number6 -> {
                valueArray.add('6')
                count++
            }
            R.id.kb_number7 -> {
                valueArray.add('7')
                count++
            }
            R.id.kb_number8 -> {
                valueArray.add('8')
                count++
            }
            R.id.kb_number9 -> {
                valueArray.add('9')
                count++
            }
            R.id.kb_delete -> {
                initValueArray()
            }
        }
    }
}
