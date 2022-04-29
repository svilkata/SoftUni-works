package com;

import com.entities.album.Album;
import com.entities.album.AlbumRepository;
import com.entities.country.Country;
import com.entities.country.CountryRepository;
import com.entities.picture.Picture;
import com.entities.picture.PictureRepository;
import com.entities.town.Town;
import com.entities.town.TownRepository;
import com.entities.user.User;
import com.entities.user.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.*;

@Component
public class Application implements CommandLineRunner {
    private final UserRepositoty userRepositoty;
    private final CountryRepository countryRepository;
    private final TownRepository townRepository;
    private final AlbumRepository albumRepository;
    private final PictureRepository pictureRepository;

    @Autowired
    public Application(UserRepositoty userRepositoty,
                       CountryRepository countryRepository,
                       TownRepository townRepository,
                       AlbumRepository albumRepository,
                       PictureRepository pictureRepository) {
        this.userRepositoty = userRepositoty;
        this.countryRepository = countryRepository;
        this.townRepository = townRepository;
        this.albumRepository = albumRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Country country1 = new Country("Bulgaria");
        Country country2 = new Country("Romania");

        Town town1 = new Town("Varna");
        Town town2 = new Town("Sofia");
        Town town3 = new Town("Bucharest");

        Set<Town> towns1 = new HashSet<>(country1.getTowns());
        towns1.add(town1);
        towns1.add(town2);
        country1.setTowns(towns1);

        Set<Town> towns2 = new HashSet<>(country2.getTowns());
        towns2.add(town3);
        country2.setTowns(towns2);

        Picture pic1 = new Picture("filepath1 pic1");
        Picture pic2 = new Picture("filepath1 pic2");
        Picture pic3 = new Picture("filepath1 pic3");
        Picture pic4 = new Picture("filepath1 pic4");
        Picture pic5 = new Picture("filepath1 pic5");

        Album album1 = new Album("begachkata :D");
        album1.setPictures(new HashSet<>() {{
            add(pic1);
            add(pic2);
            add(pic3);
        }});

        Album album2 = new Album("dragan");
        album2.setPictures(new HashSet<>() {{
            add(pic3);
            add(pic4);
            add(pic5);
        }});


        List<User> users = new ArrayList<>() {{
            add(new User("IvanBegachkata", "password", "ivan@abv.bg", 15));
            add(new User("dragan", "password", "dragan@abv.bg", 20));
            add(new User("perkan", "password", "perkan@abv.bg", 6));
        }};

        users.get(0).setAlbums(new HashSet<>() {{
            add(album1);
        }});
        users.get(0).setBirthTown(town1);
        users.get(0).setCurrentTown(town2);

        users.get(1).setAlbums(new HashSet<>() {{
            add(album2);
        }});
        users.get(1).setCurrentTown(town3);
        users.get(1).setBirthTown(town3);


        try {
            pictureRepository.saveAll(new ArrayList<>() {{
                add(pic1);
                add(pic2);
                add(pic3);
                add(pic4);
                add(pic5);
            }});

            albumRepository.save(album1);
            albumRepository.save(album2);

            countryRepository.save(country1);
            countryRepository.save(country2);

            townRepository.save(town1);
            townRepository.save(town2);
            townRepository.save(town3);

            userRepositoty.saveAll(users);

        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(System.out::println);
        }

    }
}
