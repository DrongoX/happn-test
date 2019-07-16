package com.drongox.happntest.entity.mesh;

import com.drongox.happntest.entity.Coordinate;
import com.drongox.happntest.entity.poi.Poi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class WorldMesh
{
  private final Map<MeshArea, List<Poi>> poiMesh = new HashMap<>();


  public List<Poi> getPoiOn(Coordinate coordinate)
  {
    MeshArea targetArea = new MeshArea(coordinate);
    return poiMesh.getOrDefault(targetArea, List.of());
  }


  public void addPoi(Poi poi)
  {
    MeshArea poiArea = new MeshArea(poi.getCoordinate());
    poiMesh.computeIfAbsent(poiArea, area -> new ArrayList<>())
           .add(poi);
  }
}
