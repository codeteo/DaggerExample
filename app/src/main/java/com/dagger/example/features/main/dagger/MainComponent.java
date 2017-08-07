package com.dagger.example.features.main.dagger;

import com.dagger.example.dagger.components.ApplicationComponent;
import com.dagger.example.data.source.PhotosRepositoryModule;
import com.dagger.example.features.main.MainFragment;
import com.dagger.example.utils.FragmentScope;

import dagger.Component;

/**
 * Dagger Component for {@link MainFragment}.
 */

@FragmentScope
@Component( dependencies = {ApplicationComponent.class},
        modules = {MainPresenterModule.class, MainServiceModule.class, PhotosRepositoryModule.class})
public interface MainComponent {

    void inject(MainFragment mainFragment);

}
