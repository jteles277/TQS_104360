package hm1.data;

import hm1.data.Quality_Info;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MyRepository extends CrudRepository<Quality_Info, String> {

    public Optional<Quality_Info> findById(String Id);
}
