package com.spring.repository;

import com.spring.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NodeRepository extends JpaRepository<NodeEntity, Long> {
    List<NodeEntity> findAllByUser(String user);
    @Query(value = "SELECT * " + "FROM nodes1 " + "WHERE earth_box(ll_to_earth(?1,?2), ?3) " + "@> " + "ll_to_earth(lat, lon) ORDER by earth_distance(ll_to_earth(?1,?2), ll_to_earth(lat, lon));", nativeQuery = true )
    List<NodeEntity> findByLocationAndRadius (Double lat, Double lon, Long radius);

  /*  @Query("select u from Users u where u.email like '%@gmail.com%'")//если этого мало можно написать
        //собственный запрос на языке похожем на SQL
    List<Users> findWhereEmailIsGmail();

    @Query(value = "select * from users where name like '%smith%'", nativeQuery = true)
        //если и этого мало - можно написать запрос на чистом SQL и все это будет работать
    List<Users> findWhereNameStartsFromSmith();
*/}
