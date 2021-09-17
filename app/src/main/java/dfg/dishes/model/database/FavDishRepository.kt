package dfg.dishes.model.database

import androidx.annotation.WorkerThread
import dfg.dishes.model.entities.FavDish
import kotlinx.coroutines.flow.Flow

class FavDishRepository(private val favDishDao: FavDishDAO) {

    @WorkerThread
    suspend fun insertFavDishData(favDish: FavDish){
        favDishDao.insertFavDishDetails(favDish)
    }

    val allDishesList: Flow<List<FavDish>> = favDishDao.getAllDishesList()

    @WorkerThread
    suspend fun updateFavDishData(favDish: FavDish){
        favDishDao.updateFaveDishDetails(favDish)
    }

    val favoriteDishes: Flow<List<FavDish>> = favDishDao.getFavoriteDishesList()

    @WorkerThread
    suspend fun deleteFavDishData(favDish: FavDish){
        favDishDao.deleteFavDishDetails(favDish)
    }

    fun filteredListDishes(value: String) : Flow<List<FavDish>> =
        favDishDao.getFilteredDishesList(value)
}