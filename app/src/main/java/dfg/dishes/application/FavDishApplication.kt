package dfg.dishes.application

import android.app.Application
import dfg.dishes.model.database.FavDishRepository
import dfg.dishes.model.database.FavDishRoomDatabase

class FavDishApplication : Application() {

    private val database by lazy { FavDishRoomDatabase.getDatabase(this@FavDishApplication)}

    val repository by lazy { FavDishRepository(database.favDishDao())}
}