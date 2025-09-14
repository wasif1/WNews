package com.wasif.topheadlines.utills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wasif.topheadlines.presentation.viewmodel.TopHeadlinesViewModel
import javax.inject.Inject
import javax.inject.Provider


//class ViewModelFactory @Inject constructor(
//    private val topHeadlinesViewModelProvider: Provider<TopHeadlinesViewModel>
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(TopHeadlinesViewModel::class.java)) {
//            return topHeadlinesViewModelProvider.get() as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown ViewModel class $modelClass")

        return creator.get() as T
    }
}
