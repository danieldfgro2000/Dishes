package dfg.dishes.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dfg.dishes.application.FavDishApplication
import dfg.dishes.databinding.FragmentFavoriteDishesBinding
import dfg.dishes.model.entities.FavDish
import dfg.dishes.view.activities.MainActivity
import dfg.dishes.view.adapters.FavDishAdapter
import dfg.dishes.viewmodel.FavDishViewModel
import dfg.dishes.viewmodel.FavDishViewModelFactory

import dfg.dishes.viewmodel.FavoriteDishViewModel

class FavoriteDishesFragment : Fragment() {

    private lateinit var favoriteDishViewModel: FavoriteDishViewModel

    private val mFavDishesViewModel : FavDishViewModel by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }

    private var mBinding: FragmentFavoriteDishesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteDishViewModel =
            ViewModelProvider(this).get(FavoriteDishViewModel::class.java)

        mBinding = FragmentFavoriteDishesBinding.inflate(inflater, container, false)



        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFavDishesViewModel.favoriteDishes.observe(viewLifecycleOwner){
            dishes ->
            dishes.let {

                mBinding!!.rvFavoriteDishesList.layoutManager =
                    GridLayoutManager(requireActivity(), 2)
                val adapter = FavDishAdapter(this)
                mBinding!!.rvFavoriteDishesList.adapter = adapter


                if (it.isNotEmpty()){
                    mBinding!!.rvFavoriteDishesList.visibility = View.VISIBLE
                    mBinding!!.tvNoFavoriteDishesAvailable.visibility = View.GONE
                    adapter.dishesList(it)
                } else {
                    mBinding!!.rvFavoriteDishesList.visibility = View.GONE
                    mBinding!!.tvNoFavoriteDishesAvailable.visibility = View.VISIBLE
                }
            }
        }
    }

    fun dishDetails(favDish: FavDish) {
        findNavController().navigate(FavoriteDishesFragmentDirections
            .actionFavoriteDishesToDishDetails(favDish))

        if (requireActivity() is MainActivity){
            (activity as MainActivity?)!!.hideBottomNavigationView()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity){
            (activity as MainActivity?)!!.showBottomNavigationView()
        }
    }
}