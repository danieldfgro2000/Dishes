package dfg.dishes.model.database

import androidx.room.*
import dfg.dishes.model.entities.FavDish
import kotlinx.coroutines.flow.Flow

@Dao
interface FavDishDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertFavDishDetails(favDish: FavDish)

    @Query("SELECT * FROM FAV_DISHES_TABLE ORDER BY ID")
    fun getAllDishesList(): Flow<List<FavDish>>

    @Update
    suspend fun updateFaveDishDetails(favDish: FavDish)

    @Query("SELECT * FROM FAV_DISHES_TABLE WHERE favorite_dish = 1")
    fun getFavoriteDishesList() : Flow<List<FavDish>>

    @Delete
    suspend fun deleteFavDishDetails(favDish: FavDish)

    @Query("SELECT * FROM FAV_DISHES_TABLE WHERE type = :filterType")
    fun getFilteredDishesList(filterType: String): Flow<List<FavDish>>
}