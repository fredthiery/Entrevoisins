package com.openclassrooms.entrevoisins.ui.neighbour_detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.ActivityNeighbourDetailBinding;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Objects;

public class NeighbourDetailActivity extends AppCompatActivity {

    private Neighbour neighbour;
    private ActivityNeighbourDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_neighbour_detail);

        NeighbourApiService mApiService = DI.getNeighbourApiService();

        // Utilise un Extra pour transmettre l’Id du voisin à afficher, et récupère le voisin depuis l’api grâce à getFromId()
        neighbour = mApiService.getFromId(getIntent().getLongExtra("Id",0));
        // Si aucun voisin n’a été trouvé par getFromId, en génère un nouveau
        if (neighbour == null) {
            neighbour = new Neighbour(
                    0,
                    "Fox Mulder",
                    "https://pbs.twimg.com/profile_images/683708020954501120/6rttb3kT_400x400.jpg",
                    "Apartment 42, 2630 Hegal Place, Alexandria, Virginia",
                    "555-0199",
                    "I want to believe");
        }
        binding.setNeighbour(neighbour);

        // Charge l’image du voisin depuis AvatarUrl
        Glide.with(this).load(neighbour.getAvatarUrl()).placeholder(R.drawable.ic_account).into(binding.activityNeighbourDetailPicture);

        // Inverse le statut favori du voisin quand on clique sur le bouton étoile
        binding.favoriteActionButton.setOnClickListener(v -> {
            neighbour.setFavorite(!neighbour.isFavorite());
        });
        configureToolbar();
    }

    // Lorsqu’on clique sur le bouton Up, revenir à l’activité précédente
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // Génère une actionBar transparente et sans titre pour afficher le bouton Up
    private void configureToolbar() {
        setSupportActionBar(binding.activityNeighbourDetailToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}