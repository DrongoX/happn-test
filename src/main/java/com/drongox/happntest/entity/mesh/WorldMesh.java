package com.drongox.happntest.entity.mesh;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toList;

import com.drongox.happntest.entity.GeoCoordinate;
import com.drongox.happntest.entity.poi.Poi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public final class WorldMesh
{
  private final Map<MeshArea, List<Poi>> poiMesh = new HashMap<>();


  public List<Poi> getPoiOn(GeoCoordinate geoCoordinate)
  {
    MeshArea targetArea = new MeshArea(geoCoordinate);
    return poiMesh.getOrDefault(targetArea, List.of());
  }


  public void addPoi(Poi poi)
  {
    MeshArea poiArea = new MeshArea(poi.getGeoCoordinate());
    poiMesh.computeIfAbsent(poiArea, area -> new ArrayList<>())
           .add(poi);
  }


  public List<MeshArea> findHeaviestAreas(int requestedAmount)
  {
    return poiMesh.entrySet()
                  .stream()
                  .sorted(comparingByValue((o1, o2) -> o2.size() - o1.size()))
                  .limit(requestedAmount)
                  .map(Entry::getKey)
                  .collect(toList());
  }


  @Override
  public int hashCode()
  {
    return poiMesh.hashCode();
  }


  @Override
  public boolean equals(Object obj)
  {
    if (obj == this) return true;
    if (!(obj instanceof WorldMesh)) return false;
    return poiMesh.equals(((WorldMesh) obj).poiMesh);
  }
}
