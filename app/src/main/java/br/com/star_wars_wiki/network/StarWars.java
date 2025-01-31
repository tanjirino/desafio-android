package br.com.star_wars_wiki.network;

import br.com.star_wars_wiki.entity.*;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface StarWars {
    @GET("/")
    public void getRootUrls(Callback<Root> callback);

    @GET("/people/")
    public void getAllPeople(@Query("page") int page,
                             Callback<SWModelList<People>> callback);

    @GET("/people/{id}/")
    public void getPeople(@Path("id") int peopleId,
                          Callback<People> callback);

    @GET("/films/")
    public void getAllFilms(@Query("page") int page,
                            Callback<SWModelList<Film>> callback);

    @GET("/films/{id}/")
    public void getFilm(@Path("id") int filmId,
                        Callback<Film> callback);

    @GET("/starships")
    public void getAllStarships(@Query("page") int page,
                                Callback<SWModelList<Starship>> callback);

    @GET("/starships/{id}/")
    public void getStarship(@Path("id") int starshipId,
                            Callback<Starship> callback);

    @GET("/vehicles/")
    public void getAllVehicles(@Query("page") int page,
                               Callback<SWModelList<Vehicle>> callback);

    @GET("/vehicles/{id}/")
    public void getVehicle(@Path("id") int vehicleId,
                           Callback<Vehicle> callback);

    @GET("/species/")
    public void getAllSpecies(@Query("page") int page,
                              Callback<SWModelList<Specie>> callback);

    @GET("/{route}")
    public void getSpecies(@Path("route") String route,
                           Callback<Specie> callback);

    @GET("/planets/")
    public void getAllPlanets(@Query("page") int page,
                              Callback<SWModelList<Planet>> callback);

    @GET("/{route}")
    public void getPlanet(@Path("route") String route,
                          Callback<Planet> callback);

    @POST("/favorite/{id}")
    public void setFavorite(@Path("id") String id,
                            Callback<ResponseFavorite> callBack);

}
