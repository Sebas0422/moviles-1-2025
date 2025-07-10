package com.example.proyectofinal.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.models.CategoryList
import com.example.proyectofinal.models.WorkerList
import com.example.proyectofinal.repositories.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {
    private val _categoryList = MutableLiveData<CategoryList>()
    val categoryList: LiveData<CategoryList>  = _categoryList

    private val _workerList = MutableLiveData<WorkerList>()
    val workerList: LiveData<WorkerList>  = _workerList

    fun loadCategories() {
        viewModelScope.launch {
            try {
                val categories = CategoryRepository.getCategoryList()
                _categoryList.postValue(categories)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchCategories(search: String): CategoryList {
        val filteredCategories = _categoryList.value?.filter { category ->
            category.name.contains(search, ignoreCase = true)
        } ?: emptyList()
        return CategoryList(filteredCategories)
    }

    fun loadWorkersByCategory(categoryId: Int) {
        viewModelScope.launch {
            try {
                val workers = CategoryRepository.getWorkersByCategoryId(categoryId)
                _workerList.postValue(workers)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchWorker(search: String): WorkerList {
        val filteredCategories = _workerList.value?.filter { worker ->
            worker.user.fullName.contains(search, ignoreCase = true)
        } ?: emptyList()
        return WorkerList(filteredCategories)
    }
}