package com.drongox.happntest.repository.poi;

import static java.math.BigDecimal.valueOf;

import com.drongox.happntest.object.GeoCoordinate;
import com.drongox.happntest.object.mesh.WorldMesh;
import com.drongox.happntest.object.poi.Poi;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PoiRepositoryTest
{
  @Autowired
  private PoiRepository poiRepository;


  @Test
  public void readMesh()
  {
    //given
    WorldMesh expectedMesh = new WorldMesh();
    expectedMesh.addPoi(Poi.of("id1", GeoCoordinate.of(valueOf(-48.6), valueOf(-37.7))));
    expectedMesh.addPoi(Poi.of("id2", GeoCoordinate.of(valueOf(-27.1), valueOf(8.4))));
    expectedMesh.addPoi(Poi.of("id3", GeoCoordinate.of(valueOf(6.6), valueOf(-6.9))));
    expectedMesh.addPoi(Poi.of("id4", GeoCoordinate.of(valueOf(-2.3), valueOf(38.3))));
    expectedMesh.addPoi(Poi.of("id5", GeoCoordinate.of(valueOf(6.8), valueOf(-6.9))));
    expectedMesh.addPoi(Poi.of("id6", GeoCoordinate.of(valueOf(-2.5), valueOf(38.3))));
    expectedMesh.addPoi(Poi.of("id7", GeoCoordinate.of(valueOf(0.1), valueOf(-0.1))));
    expectedMesh.addPoi(Poi.of("id8", GeoCoordinate.of(valueOf(-2.1), valueOf(38.1))));
    //when
    WorldMesh worldMesh = poiRepository.readMesh();
    //then
    Assertions.assertThat(worldMesh)
              .isEqualTo(expectedMesh);
  }
}