package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DayOfWeek;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    @Query("SELECT f FROM Forecast f" +
            " WHERE f.dayOfWeek =:dayOfWeek AND f.city.id =:cityId")
    Optional<Forecast> findByDayOfWeekAndCityId(@Param("dayOfWeek") DayOfWeek dayOfWeek,
                                               @Param(value = "cityId") long cityId);

    @Query("SELECT f FROM Forecast f" +
            " WHERE f.dayOfWeek = 'SUNDAY' AND f.city.population < 150000" +
            " ORDER BY f.maxTemperature DESC, f.id ASC")
    List<Forecast> extractSundayForecastFromCitiesLessThan150000();
}
