package me.madrapeau.sbieaspiprestfulapi;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "rights", path = "rights")
public interface RightRepository extends PagingAndSortingRepository<Right, Long> {

	List<Right> findByUserId(@Param("user_id") String user_id);

}