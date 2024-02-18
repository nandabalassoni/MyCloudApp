package br.com.mba.mycloudapp.di
import androidx.lifecycle.ViewModel
import br.com.mba.mycloudapp.repository.ClothingItemRepository
import br.com.mba.mycloudapp.ui.viewModel.ClothingItemFormViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel


import org.koin.dsl.module


val repositoryModule = module {
    single<ClothingItemRepository> {ClothingItemRepository(get())}
}
val viewModelModule = module {
    viewModel<ClothingItemFormViewModel>{ ClothingItemFormViewModel(get()) }
}

val firebaseModule = module {
    single<FirebaseFirestore> { Firebase.firestore }
}