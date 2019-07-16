package com.drongox.happntest.service.mesh;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import com.drongox.happntest.object.GeoCoordinate;
import com.drongox.happntest.object.mesh.MeshArea;
import com.drongox.happntest.object.mesh.WorldMesh;
import com.drongox.happntest.object.poi.Poi;
import com.drongox.happntest.repository.mesh.WorldMeshRepository;
import java.util.List;
import org.junit.Test;

public class WorldMeshServiceTest
{
  private final TestWorldMeshRepository poiRepository = new TestWorldMeshRepository();
  private final WorldMeshService worldMeshService = new WorldMeshService(poiRepository);


  @Test
  public void should_find_2_poi_when_coordinate_6_5_lat_minus_7_0_long()
  {
    //given
    GeoCoordinate geoCoordinate = GeoCoordinate.of(valueOf(6.5), valueOf(-7));
    int expectedCount = 2;
    //when
    int actualCount = worldMeshService.poiCountOn(geoCoordinate);
    //then
    assertThat(actualCount).isEqualTo(expectedCount);
  }


  @Test
  public void should_find_1_poi_when_coordinate_90_lat_0_long()
  {
    //given
    GeoCoordinate geoCoordinate = GeoCoordinate.of(valueOf(90), valueOf(0));
    int expectedCount = 1;
    //when
    int actualCount = worldMeshService.poiCountOn(geoCoordinate);
    //then
    assertThat(actualCount).isEqualTo(expectedCount);
  }


  @Test
  public void should_find_1_poi_when_coordinate_0_lat_180_long()
  {
    //given
    GeoCoordinate geoCoordinate = GeoCoordinate.of(valueOf(0), valueOf(180));
    int expectedCount = 1;
    //when
    int actualCount = worldMeshService.poiCountOn(geoCoordinate);
    //then
    assertThat(actualCount).isEqualTo(expectedCount);
  }


  @Test
  public void should_find_2_heaviest_areas_when_asked_for_2()
  {
    //given
    int requestedAmount = 2;
    List<MeshArea> expectedAreas = List.of(new MeshArea(GeoCoordinate.of(valueOf(-2.5), valueOf(38))),
                                           new MeshArea(GeoCoordinate.of(valueOf(6.5), valueOf(-7))));
    //when
    List<MeshArea> foundAreas = worldMeshService.findHeaviestAreas(requestedAmount);
    //then
    assertThat(foundAreas).hasSize(2)
                          .containsExactlyElementsOf(expectedAreas);
  }


  private static class TestWorldMeshRepository extends WorldMeshRepository
  {
    private static final WorldMesh WORLD_MESH = new WorldMesh();

    static {
      WORLD_MESH.addPoi(Poi.of("id1", GeoCoordinate.of(valueOf(-48.6), valueOf(-37.7))));
      WORLD_MESH.addPoi(Poi.of("id2", GeoCoordinate.of(valueOf(-27.1), valueOf(8.4))));
      WORLD_MESH.addPoi(Poi.of("id3", GeoCoordinate.of(valueOf(6.6), valueOf(-6.9))));
      WORLD_MESH.addPoi(Poi.of("id4", GeoCoordinate.of(valueOf(-2.3), valueOf(38.3))));
      WORLD_MESH.addPoi(Poi.of("id5", GeoCoordinate.of(valueOf(6.8), valueOf(-6.9))));
      WORLD_MESH.addPoi(Poi.of("id6", GeoCoordinate.of(valueOf(-2.5), valueOf(38.3))));
      WORLD_MESH.addPoi(Poi.of("id7", GeoCoordinate.of(valueOf(0.1), valueOf(-0.1))));
      WORLD_MESH.addPoi(Poi.of("id8", GeoCoordinate.of(valueOf(-2.1), valueOf(38.1))));
      WORLD_MESH.addPoi(Poi.of("id10", GeoCoordinate.of(valueOf(90), valueOf(0))));
      WORLD_MESH.addPoi(Poi.of("id11", GeoCoordinate.of(valueOf(0), valueOf(180))));
    }


    TestWorldMeshRepository()
    {
      super(null);
    }


    @Override
    public WorldMesh readMesh()
    {
      return WORLD_MESH;
    }
  }
}
