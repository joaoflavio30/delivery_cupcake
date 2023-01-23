package UnitTest

import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import model.OrderViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ViewModelTests {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun quantity_twelve_cupcakes() {
        val viewModel = OrderViewModel()
        viewModel.quantity.observeForever { }
        viewModel.setQuantity(12)

        assertEquals(12, viewModel.quantity.value)
    }


    @Test
    fun order_twelve_chocolate_first_day() {
        val viewModel = OrderViewModel()
        viewModel.apply {
            quantity.observeForever { }
            flavor.observeForever { }
            date.observeForever { }
            setQuantity(12)
            setFlavor("Chocolate")
            setDate(dateOptions[0])
        }

        assertEquals(12, viewModel.quantity.value)
        assertEquals("Chocolate", viewModel.flavor.value)
        assertEquals(viewModel.dateOptions[0], viewModel.date.value)


    }

    @Test
    fun verifyPrice() {
        val viewModel = OrderViewModel()
        viewModel.apply {
            subTotalFormatted.observeForever { }
            setQuantity(6)
            setFlavor("Chocolate")
            setDate(dateOptions[2])
        }

        assertEquals("$12.00", viewModel.subTotalFormatted.value)

    }


}