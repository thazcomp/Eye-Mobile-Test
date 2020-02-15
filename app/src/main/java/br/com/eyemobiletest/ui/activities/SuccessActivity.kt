package br.com.eyemobiletest.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.eyemobiletest.R
import kotlinx.android.synthetic.main.activity_success.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class SuccessActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        tv_value.text = intent.getStringExtra("value")
        tv_type.text = intent.getStringExtra("type")

        var output: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             val localDateTime: LocalDateTime = LocalDateTime.now()
             val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
             output = formatter.format(localDateTime)
        } else {
             val currentTime:Date = Calendar.getInstance().getTime()
             val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
             val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
             output = formatter.format(parser.parse(currentTime.toString())!!)
        }
        tv_date.text = output

        iv_back.setOnClickListener {
            onBackPressed()
        }

        b_confirm.setOnClickListener {
            Toast.makeText(this, "Pagamento realizado com sucesso.", Toast.LENGTH_SHORT).show()
            ContextCompat.startActivity(this, Intent(this, MainActivity::class.java), null)
        }
    }
}
