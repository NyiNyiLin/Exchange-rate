package com.nyi.exchangerate

import android.app.DatePickerDialog
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyi.exchangerate.adapters.RateAdapter
import com.nyi.exchangerate.responses.ExchangeRateResponse
import com.nyi.exchangerate.services.ApiService
import com.nyi.exchangerate.utils.ConversionUtil
import com.nyi.exchangerate.viewholder.RateVH
import com.nyi.exchangerate.vos.RateItemVO
import com.nyi.exchangerate.vos.RateVO

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class MainActivity : AppCompatActivity(), RateVH.ControllerRateItem {

    private var selectedDate : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

        }

        srl.setOnRefreshListener {
            loadRate(selectedDate)
        }

        btnSelectDate.setOnClickListener {
            showDatePicker()
        }
    }

    override fun onResume() {
        super.onResume()

        showTodayRate()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*
    format @date = 29-10-2018
     */
    fun loadRate(date : String){
        srl.isRefreshing = true
        val api = ApiService.create()

        api.getRateByDate(date).enqueue(object : Callback<ExchangeRateResponse>{
            override fun onResponse(call: Call<ExchangeRateResponse>, response: Response<ExchangeRateResponse>) {

                srl.isRefreshing = false
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, response.body()!!.description, Toast.LENGTH_SHORT).show()

                    showRateList(response.body()!!.rates)
                }
            }

            override fun onFailure(call: Call<ExchangeRateResponse>, t: Throwable) {
                srl.isRefreshing = false
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                rvRate.visibility = View.GONE
            }
        })
    }

    fun showRateList(rateVO : RateVO){

        var rateList = ConversionUtil.convertExchangeRateItemToExchangeRateList(rateVO)

        var adapter = RateAdapter(rateList, this)
        rvRate.visibility = View.VISIBLE
        rvRate.adapter = adapter
        rvRate.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    fun showTodayRate(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //month need to add 1 bec month is start counting form 0
        var dateString = "" + day + "-" + (month + 1) + "-" + year
        loadRate(dateString)
    }

    fun showDatePicker(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            selectedDate = "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year

            btnSelectDate.text = "Please Select Date. Current Date is " + selectedDate
            loadRate(selectedDate)

        }, year, month, day)

        dpd.show()
    }

    override fun onClickRateItem(rateItem: RateItemVO) {
        Toast.makeText(this, rateItem.rate, Toast.LENGTH_SHORT).show()
    }




}
