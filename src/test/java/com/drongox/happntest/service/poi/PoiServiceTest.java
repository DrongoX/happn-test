package com.drongox.happntest.service.poi;

import static org.assertj.core.api.Assertions.assertThat;

import com.drongox.happntest.entity.poi.MeshCoordinate;
import com.drongox.happntest.repository.poi.PoiRepository;
import java.math.BigDecimal;
import org.junit.Test;

public class PoiServiceTest
{
  private final TestPoiRepository poiRepository = new TestPoiRepository();


  @Test
  public void should_find_2_poi_when_coordinate_6_5_lat_minus_7_0_long()
  {
    //given
    PoiService poiService = new PoiService(poiRepository);
    MeshCoordinate coordinate = MeshCoordinate.of(BigDecimal.valueOf(6.5), BigDecimal.valueOf(-7.0));
    int expectedCount = 2;
    //when
    int actualCount = poiService.poiCountOn(coordinate);
    //then
    assertThat(actualCount).isEqualTo(expectedCount);
  }


  private class TestPoiRepository extends PoiRepository {}
}
