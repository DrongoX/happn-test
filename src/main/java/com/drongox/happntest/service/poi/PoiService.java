package com.drongox.happntest.service.poi;

import com.drongox.happntest.object.GeoCoordinate;
import com.drongox.happntest.object.mesh.MeshArea;
import com.drongox.happntest.repository.poi.PoiRepository;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class PoiService
{
  private final PoiRepository poiRepository;


  public PoiService(PoiRepository poiRepository)
  {
    this.poiRepository = poiRepository;
  }


  public int poiCountOn(GeoCoordinate geoCoordinate)
  {
    return poiRepository.readMesh()
                        .getPoiOn(geoCoordinate)
                        .size();
  }


  public List<MeshArea> findHeaviestAreas(int requestedAmount)
  {
    return poiRepository.readMesh()
                        .findHeaviestAreas(requestedAmount);
  }
}
