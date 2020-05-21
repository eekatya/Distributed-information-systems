package com.spring.repository;

import com.spring.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NodeRepository extends JpaRepository<NodeEntity, Long> {
    List<NodeEntity> findAllByUser(String user);
    @Query(value = "SELECT * " + "FROM nodes1 " + "WHERE earth_box(ll_to_earth(?1,?2), ?3) " + "@> " + "ll_to_earth(lat, lon) ORDER by earth_distance(ll_to_earth(?1,?2), ll_to_earth(lat, lon));", nativeQuery = true )
    //fun getNodesInRadius(lat: Double, lon: Double, radius: Long): List<NodeEntity>
    List<NodeEntity> findByLocationAndRadius (Double lat, Double lon, Long radius);
    /* @Query(value = "" +
            "SELECT * " +
            "FROM place " +
            "WHERE earth_distance( " +
            "   ll_to_earth(place.latitude, place.longitude), " +
            "   ll_to_earth(:latitude, :longitude) " +
            ") < :radius", nativeQuery = true)
    List<NodeEntity> findByLocationAndRadius(@Param("latitude") Float latitude,
                                              @Param("longitude") Float longitude,
                                              @Param("radius") Long radius);*/
    /*    @Query(value = "" +
            "SELECT * " +
            "FROM nodes1 " +
            "WHERE earth_distance( " +
            "   ll_to_earth(nodes.lat, nodes.lon), " +
            "   ll_to_earth(:latitude, :longitude) " +
            ") < :radius", nativeQuery = true)
    //https://stackoverflow.com/questions/49680761/query-function-ll-to-earthdouble-precision-double-precision-does-not-exist
    //https://serverfault.com/questions/346568/how-do-i-install-the-earthdistance-and-requirement-cube-modules-for-postgresql
    Page<NodeEntity> findAllNodesByLocationAndRadius(@Param("latitude") float latitude,
                                                     @Param("longitude") float longitude,
                                                     @Param("radius") int radius,
                                                     Pageable pageable);*/

  /*  @Query("select u from Users u where u.email like '%@gmail.com%'")//если этого мало можно написать
        //собственный запрос на языке похожем на SQL
    List<Users> findWhereEmailIsGmail();

    @Query(value = "select * from users where name like '%smith%'", nativeQuery = true)
        //если и этого мало - можно написать запрос на чистом SQL и все это будет работать
    List<Users> findWhereNameStartsFromSmith();
*/}
