package model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel : ViewModel() {


    var quantity = MutableLiveData<Int>()
        private set

    var flavor = MutableLiveData<String>()
        private set

    var date = MutableLiveData<String>()
        private set

    var subTotal = MutableLiveData<Double>()
        private set
    var subTotalFormatted: LiveData<String> = Transformations.map(subTotal) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    val dateOptions = getPickupOptions()

    init {
        resetOrder()
    }

    fun setQuantity(quantityCupcake: Int) {
        quantity.value = quantityCupcake
    }

    fun setFlavor(nameFlavor: String) {
        flavor.value = nameFlavor
    }

    fun setDate(whichDate: String) {
        date.value = whichDate
        setTotal()

    }

    fun defaultFlavor(): Boolean {
        return flavor.value.isNullOrEmpty()
    }

    fun setTotal() {
        if (date.value == dateOptions[0]) {
            subTotal.value = ((quantity.value ?: 0) * 2 + 3).toDouble()
        } else {
            subTotal.value = ((quantity.value ?: 0) * 2).toDouble()


        }


    }

    fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    fun resetOrder(){
        date.value = dateOptions[0]
        flavor.value = ""
        subTotal.value = 0.0

    }
}