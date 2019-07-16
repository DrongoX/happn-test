package com.drongox.happntest.service.poi;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import com.drongox.happntest.entity.poi.MeshCoordinate;
import com.drongox.happntest.entity.poi.Poi;
import com.drongox.happntest.entity.poi.WorldMesh;
import com.drongox.happntest.repository.poi.PoiRepository;
import org.junit.Test;

public class PoiServiceTest
{
  private final TestPoiRepository poiRepository = new TestPoiRepository();


  @Test
  public void should_find_2_poi_when_coordinate_6_5_lat_minus_7_0_long()
  {
    //given
    PoiService poiService = new PoiService(poiRepository);
    MeshCoordinate coordinate = MeshCoordinate.of(valueOf(6.5), valueOf(-7.0));
    int expectedCount = 2;
    //when
    int actualCount = poiService.poiCountOn(coordinate);
    //then
    assertThat(actualCount).isEqualTo(expectedCount);
  }


  private static class TestPoiRepository extends PoiRepository
  {
    private static final WorldMesh WORLD_MESH = new WorldMesh();


    static {
      WORLD_MESH.addPoi(Poi.of("id1", MeshCoordinate.of(valueOf(-48.6), valueOf(-37.7))));
      WORLD_MESH.addPoi(Poi.of("id2", MeshCoordinate.of(valueOf(-27.1), valueOf(8.4))));
      WORLD_MESH.addPoi(Poi.of("id3", MeshCoordinate.of(valueOf(6.6), valueOf(-6.9))));
      WORLD_MESH.addPoi(Poi.of("id4", MeshCoordinate.of(valueOf(-2.3), valueOf(38.3))));
      WORLD_MESH.addPoi(Poi.of("id5", MeshCoordinate.of(valueOf(6.8), valueOf(-6.9))));
      WORLD_MESH.addPoi(Poi.of("id6", MeshCoordinate.of(valueOf(-2.5), valueOf(38.3))));
      WORLD_MESH.addPoi(Poi.of("id7", MeshCoordinate.of(valueOf(0.1), valueOf(-0.1))));
      WORLD_MESH.addPoi(Poi.of("id8", MeshCoordinate.of(valueOf(-2.1), valueOf(38.1))));
    }


    @Override
    public WorldMesh readMesh()
    {
      return WORLD_MESH;
    }
  }
}
