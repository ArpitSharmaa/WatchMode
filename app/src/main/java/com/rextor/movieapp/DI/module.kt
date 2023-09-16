package com.rextor.movieapp.DI



import android.app.Application
import android.content.Context
import androidx.room.Room
import com.rextor.movieapp.Details.Details_repository
import com.rextor.movieapp.LocalStrorage.database.database
import com.rextor.movieapp.Netwrok.NetworkService
import com.rextor.movieapp.Repository.Repository
import com.rextor.movieapp.SourcesOTT.sourceottRepository
import com.rextor.movieapp.TVshows.tv_ShowRepository
import com.rextor.movieapp.Utils.coroutineexceptionhandler
import com.rextor.movieapp.favourites.favrepository
import com.rextor.movieapp.movies.Movie_list_repository
import com.rextor.movieapp.tvSpeacial.tvspeacialRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

const val base_url = "https://api.watchmode.com"
@Module
@InstallIn(SingletonComponent::class)
object module {

    @Provides
    @Singleton
    fun provideretrofitInstance(): NetworkService {
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }
    @Provides
    @Singleton
    fun provudedatabase(context: Context): database {

        return Room.databaseBuilder(context, database::class.java,"favoritedatabase").build()
    }

    @Provides
    @Singleton
    fun provideapplicationContext(application: Application):Context{
        return application.applicationContext
    }

    @Provides
    @Singleton
    @Named("Movies")
    fun providemoviesRepository(networkService: NetworkService,database: database): Repository {
        return Movie_list_repository(
            ApiServices = networkService,
            database = database
        )
    }
    @Provides
    @Singleton
    @Named("Details")
    fun providedetailRepository(networkService: NetworkService,database: database):Repository {
        return Details_repository(
            networkService = networkService,
            database = database
        )
    }



    @Provides
    @Singleton
    @Named("tvspeacial")
    fun providetvspeacialrepository(networkService: NetworkService):Repository{
        return tvspeacialRepository(
            networkService
        )
    }
    @Provides
    @Singleton
    @Named("OTT")
    fun providesourceOTTrepository(networkService: NetworkService):Repository{
        return sourceottRepository(
            networkService
        )
    }

    @Provides
    @Singleton
    fun providecoroutineExceptionHandlerclass(context: Context):coroutineexceptionhandler{
        return coroutineexceptionhandler(
            context = context
        )
    }


    @Provides
    @Singleton
    @Named("TvShow")
    fun providetvshowrepository(networkService: NetworkService,database: database):Repository{
        return tv_ShowRepository(
            networkService,
            database = database
        )
    }

    @Provides
    @Singleton
    @Named("room")
    fun providefavrouiterepository(database: database):Repository{
        return favrepository(database)
    }
}