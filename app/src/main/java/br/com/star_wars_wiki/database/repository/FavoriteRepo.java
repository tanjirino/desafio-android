package br.com.star_wars_wiki.database.repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.star_wars_wiki.database.RoomDatabase;
import br.com.star_wars_wiki.database.dao.FavoriteDAO;
import br.com.star_wars_wiki.entity.Favorite;

public class FavoriteRepo {
    private RoomDatabase database;
    private LiveData<List<Favorite>> getAllFavorites;

    public FavoriteRepo(Application application){
        database = RoomDatabase.getInstance(application.getBaseContext());
        getAllFavorites = database.favoritesDAO().getAllFavorites();
    }

    public void insert(Favorite favorite){
        new InsertAsyncTask(database).execute(favorite);
    }

    public LiveData<List<Favorite>> getAllFavorites(){
        return this.getAllFavorites;
    }

    public void remove(Favorite favorite, Context context){
        new RemoveAsyncTask(database, context).execute(favorite);
    }

    static class InsertAsyncTask extends AsyncTask<Favorite, Void, Void>{
        private FavoriteDAO favoriteDAO;

        InsertAsyncTask(RoomDatabase roomDatabase){
            favoriteDAO = roomDatabase.favoritesDAO();
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            favoriteDAO.insertFavorite(favorites[0]);
            return null;
        }
    }

    static class RemoveAsyncTask extends AsyncTask<Favorite, Void, Void>{
        private FavoriteDAO favoriteDAO;
        private Context context;
        private Favorite favorite;

        RemoveAsyncTask(RoomDatabase roomDatabase, Context context){
            favoriteDAO = roomDatabase.favoritesDAO();
            this.context = context;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            favorite = favoriteDAO.getFavorite(favorites[0].getName());
            if(favorite != null){
                favoriteDAO.removeFavorite(favorites[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if(favorite != null){
                Toast.makeText(context, "Favorito removido com sucesso", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Desculpe, esse personagem não está na lista de favoritos", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
