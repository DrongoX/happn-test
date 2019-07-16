package com.drongox.happntest.service.poi;

import com.drongox.happntest.entity.Coordinate;
import com.drongox.happntest.repository.poi.PoiRepository;

public class PoiService
{
  private final PoiRepository poiRepository;


  public PoiService(PoiRepository poiRepository)
  {
    this.poiRepository = poiRepository;
  }


  public int poiCountOn(Coordinate coordinate)
  {
    return poiRepository.readMesh()
                        .getPoiOn(coordinate)
                        .size();
  }
}
